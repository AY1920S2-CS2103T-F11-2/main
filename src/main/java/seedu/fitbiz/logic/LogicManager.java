package seedu.fitbiz.logic;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.fitbiz.commons.core.GuiSettings;
import seedu.fitbiz.commons.core.LogsCenter;
import seedu.fitbiz.logic.commands.Command;
import seedu.fitbiz.logic.commands.CommandResult;
import seedu.fitbiz.logic.commands.exceptions.CommandException;
import seedu.fitbiz.logic.parser.FitBizParser;
import seedu.fitbiz.logic.parser.exceptions.ParseException;
import seedu.fitbiz.model.Model;
import seedu.fitbiz.model.ReadOnlyFitBiz;
import seedu.fitbiz.model.client.Client;
import seedu.fitbiz.model.schedule.ScheduleDay;
import seedu.fitbiz.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final FitBizParser fitBizParser;

    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        fitBizParser = new FitBizParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = fitBizParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveFitBiz(model.getFitBiz());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyFitBiz getFitBiz() {
        return model.getFitBiz();
    }

    @Override
    public ObservableList<Client> getFilteredClientList() {
        return model.getFilteredClientList();
    }

    @Override
    public Client getClientInView() {
        return model.getClientInView();
    }

    @Override
    public Boolean hasClientInView() {
        return model.hasClientInView();
    }

    @Override
    public Path getFitBizFilePath() {
        return model.getFitBizFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }

    @Override
    public ObservableList<ScheduleDay> getScheduleDayList() {
        return model.getScheduleDayList();
    }

    @Override
    public void openUrlInDefaultWebBrowser(String url) {
        String os = System.getProperty("os.name").toLowerCase().substring(0, 3);

        switch (os) {
        case "win": // windows
        case "mac": // macOS
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                try {
                    Desktop.getDesktop().browse(new URI(url));
                } catch (IOException | URISyntaxException e) {
                    e.printStackTrace();
                }
            }
            break;
        case "lin": // linux
        case "uni": // unix
            try {
                new ProcessBuilder("x-www-browser", url).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
            break;
        default:
            break;
        }
    }
}
