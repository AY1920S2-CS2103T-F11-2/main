package seedu.fitbiz;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import seedu.fitbiz.commons.core.Config;
import seedu.fitbiz.commons.core.LogsCenter;
import seedu.fitbiz.commons.core.Version;
import seedu.fitbiz.commons.exceptions.DataConversionException;
import seedu.fitbiz.commons.util.ConfigUtil;
import seedu.fitbiz.commons.util.StringUtil;
import seedu.fitbiz.logic.Logic;
import seedu.fitbiz.logic.LogicManager;
import seedu.fitbiz.model.ClientInView;
import seedu.fitbiz.model.FitBiz;
import seedu.fitbiz.model.Model;
import seedu.fitbiz.model.ModelManager;
import seedu.fitbiz.model.ReadOnlyFitBiz;
import seedu.fitbiz.model.ReadOnlyUserPrefs;
import seedu.fitbiz.model.UserPrefs;
import seedu.fitbiz.model.util.SampleDataUtil;
import seedu.fitbiz.storage.FitBizStorage;
import seedu.fitbiz.storage.JsonFitBizStorage;
import seedu.fitbiz.storage.JsonUserPrefsStorage;
import seedu.fitbiz.storage.Storage;
import seedu.fitbiz.storage.StorageManager;
import seedu.fitbiz.storage.UserPrefsStorage;
import seedu.fitbiz.ui.Ui;
import seedu.fitbiz.ui.UiManager;

/**
 * Runs the application.
 */
public class MainApp extends Application {

    public static final Version VERSION = new Version(1, 3, 0, true);

    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    protected Ui ui;
    protected Logic logic;
    protected Storage storage;
    protected Model model;
    protected Config config;

    @Override
    public void init() throws Exception {
        logger.info("=============================[ Initializing FitBiz ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        FitBizStorage fitBizStorage = new JsonFitBizStorage(userPrefs.getFitBizFilePath());
        storage = new StorageManager(fitBizStorage, userPrefsStorage);
        ClientInView clientInView = new ClientInView();

        initLogging(config);

        model = initModelManager(storage, userPrefs, clientInView);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s FitBiz and {@code userPrefs}. <br>
     * The data from the sample FitBiz will be used instead if {@code storage}'s FitBiz is not found,
     * or an empty FitBiz will be used instead if errors occur when reading {@code storage}'s FitBiz.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs, ClientInView clientInView) {
        Optional<ReadOnlyFitBiz> fitBizOptional;
        ReadOnlyFitBiz initialData;
        try {
            fitBizOptional = storage.readFitBiz();
            if (!fitBizOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample FitBiz");
            }
            initialData = fitBizOptional.orElseGet(SampleDataUtil::getSampleFitBiz);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty FitBiz");
            initialData = new FitBiz();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty FitBiz");
            initialData = new FitBiz();
        }

        return new ModelManager(initialData, userPrefs, clientInView);
    }

    private void initLogging(Config config) {
        LogsCenter.init(config);
    }

    /**
     * Returns a {@code Config} using the file at {@code configFilePath}. <br>
     * The default file path {@code Config#DEFAULT_CONFIG_FILE} will be used instead
     * if {@code configFilePath} is null.
     */
    protected Config initConfig(Path configFilePath) {
        Config initializedConfig;
        Path configFilePathUsed;

        configFilePathUsed = Config.DEFAULT_CONFIG_FILE;

        if (configFilePath != null) {
            logger.info("Custom Config file specified " + configFilePath);
            configFilePathUsed = configFilePath;
        }

        logger.info("Using config file : " + configFilePathUsed);

        try {
            Optional<Config> configOptional = ConfigUtil.readConfig(configFilePathUsed);
            initializedConfig = configOptional.orElse(new Config());
        } catch (DataConversionException e) {
            logger.warning("Config file at " + configFilePathUsed + " is not in the correct format. "
                    + "Using default config properties");
            initializedConfig = new Config();
        }

        //Update config file in case it was missing to begin with or there are new/unused fields
        try {
            ConfigUtil.saveConfig(initializedConfig, configFilePathUsed);
        } catch (IOException e) {
            logger.warning("Failed to save config file : " + StringUtil.getDetails(e));
        }
        return initializedConfig;
    }

    /**
     * Returns a {@code UserPrefs} using the file at {@code storage}'s user prefs file path,
     * or a new {@code UserPrefs} with default configuration if errors occur when
     * reading from the file.
     */
    protected UserPrefs initPrefs(UserPrefsStorage storage) {
        Path prefsFilePath = storage.getUserPrefsFilePath();
        logger.info("Using prefs file : " + prefsFilePath);

        UserPrefs initializedPrefs;
        try {
            Optional<UserPrefs> prefsOptional = storage.readUserPrefs();
            initializedPrefs = prefsOptional.orElse(new UserPrefs());
        } catch (DataConversionException e) {
            logger.warning("UserPrefs file at " + prefsFilePath + " is not in the correct format. "
                    + "Using default user prefs");
            initializedPrefs = new UserPrefs();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty FitBiz");
            initializedPrefs = new UserPrefs();
        }

        //Update prefs file in case it was missing to begin with or there are new/unused fields
        try {
            storage.saveUserPrefs(initializedPrefs);
        } catch (IOException e) {
            logger.warning("Failed to save config file : " + StringUtil.getDetails(e));
        }

        return initializedPrefs;
    }

    @Override
    public void start(Stage primaryStage) {
        logger.info("Starting FitBiz " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping FitBiz ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}
