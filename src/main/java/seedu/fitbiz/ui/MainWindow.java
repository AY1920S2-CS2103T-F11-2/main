package seedu.fitbiz.ui;

import static seedu.fitbiz.logic.commands.HelpCommand.SHOWING_HELP_MESSAGE;
import static seedu.fitbiz.logic.commands.HelpCommand.USER_GUIDE_URL;

import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.fitbiz.commons.core.GuiSettings;
import seedu.fitbiz.commons.core.LogsCenter;
import seedu.fitbiz.logic.Logic;
import seedu.fitbiz.logic.commands.CommandResult;
import seedu.fitbiz.logic.commands.exceptions.CommandException;
import seedu.fitbiz.logic.parser.exceptions.ParseException;

/**
 * The Main Window. Provides the basic application layout containing a menu bar
 * and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> {

    private static final String FXML = "MainWindow.fxml";
    private static final String GOOGLE_FONT_URL =
            "https://fonts.googleapis.com/css2?family=Open+Sans:"
        + "wght@300;400;600;700;800&family=Ubuntu+Mono&display=swap";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private Stage primaryStage;
    private Logic logic;

    // Independent Ui parts residing in this Ui container
    private ClientListPanel clientListPanel;
    private ClientViewDisplay clientViewDisplay;
    private SchedulePanel schedulePanel;
    private ResultDisplay resultDisplay;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private StackPane clientListPanelPlaceholder;

    @FXML
    private StackPane clientViewPanelPlaceholder;

    @FXML
    private StackPane personalBestTablePlaceholder;

    @FXML
    private StackPane exerciseListTablePlaceholder;

    @FXML
    private StackPane schedulePanelPlaceholder;

    @FXML
    private StackPane resultDisplayPlaceholder;

    @FXML
    private StackPane statusbarPlaceholder;

    public MainWindow(Stage primaryStage, Logic logic) {
        super(FXML, primaryStage);

        // Set dependencies
        this.primaryStage = primaryStage;
        this.logic = logic;

        // Configure the UI
        setWindowDefaultSize(logic.getGuiSettings());

        setAccelerators();

        // set the font
        setFont(primaryStage);
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    private void setAccelerators() {
        setAccelerator(helpMenuItem, KeyCombination.valueOf("F1"));
    }

    private void setFont(Stage stage) {
        Scene scene = primaryStage.getScene();

        scene.getStylesheets().add(GOOGLE_FONT_URL);
    }

    /**
     * Sets the accelerator of a MenuItem.
     *
     * @param keyCombination the KeyCombination value of the accelerator
     */
    private void setAccelerator(MenuItem menuItem, KeyCombination keyCombination) {
        menuItem.setAccelerator(keyCombination);

        /*
         * TODO: the code below can be removed once the bug reported here
         * https://bugs.openjdk.java.net/browse/JDK-8131666 is fixed in later version of
         * SDK.
         *
         * According to the bug report, TextInputControl (TextField, TextArea) will
         * consume function-key events. Because CommandBox contains a TextField, and
         * ResultDisplay contains a TextArea, thus some accelerators (e.g F1) will not
         * work when the focus is in them because the key event is consumed by the
         * TextInputControl(s).
         *
         * For now, we add following event filter to capture such key events and open
         * help window purposely so to support accelerators even when focus is in
         * CommandBox or ResultDisplay.
         */
        getRoot().addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getTarget() instanceof TextInputControl && keyCombination.match(event)) {
                menuItem.getOnAction().handle(new ActionEvent());
                event.consume();
            }
        });
    }

    /**
     * Fills up all the placeholders of this window.
     */
    void fillInnerParts() {
        clientListPanel = new ClientListPanel(logic.getFilteredClientList());
        clientListPanelPlaceholder.getChildren().add(clientListPanel.getRoot());

        clientViewDisplay = new ClientViewDisplay();

        schedulePanel = new SchedulePanel(logic.getScheduleDayList());
        schedulePanelPlaceholder.getChildren().add(schedulePanel.getRoot());

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getFitBizFilePath());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());

        CommandBox commandBox = new CommandBox(this::executeCommand, resultDisplay);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());
    }

    /**
     * Sets the default size based on {@code guiSettings}.
     */
    private void setWindowDefaultSize(GuiSettings guiSettings) {
        primaryStage.setHeight(guiSettings.getWindowHeight());
        primaryStage.setWidth(guiSettings.getWindowWidth());
        if (guiSettings.getWindowCoordinates() != null) {
            primaryStage.setX(guiSettings.getWindowCoordinates().getX());
            primaryStage.setY(guiSettings.getWindowCoordinates().getY());
        }
    }

    /**
     * Opens FitBiz user guide in the user's default web browser.
     */
    @FXML
    public void handleHelp() {
        logic.openUrlInDefaultWebBrowser(USER_GUIDE_URL);
        resultDisplay.setFeedbackToUser(SHOWING_HELP_MESSAGE);
    }

    void show() {
        primaryStage.show();
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        GuiSettings guiSettings = new GuiSettings(primaryStage.getWidth(), primaryStage.getHeight(),
                (int) primaryStage.getX(), (int) primaryStage.getY());
        logic.setGuiSettings(guiSettings);
        primaryStage.hide();
    }

    /**
     * Removes UI elements from panels regarding {@code ClientInView}.
     *
     * @author @yonggiee
     */
    private void clearClientInViewDisplay() {
        clientViewPanelPlaceholder.getChildren().clear();
        exerciseListTablePlaceholder.getChildren().clear();
        personalBestTablePlaceholder.getChildren().clear();
    }

    /**
     * Adds UI elements to panels regarding {@code ClientInView}.
     *
     * @author @yonggiee
     */
    private void addClientInViewDisplay() {
        ClientView clientView = clientViewDisplay.getClientView();
        clientViewPanelPlaceholder.getChildren().add(clientView.getRoot());

        ExerciseListTable exerciseListTable = clientViewDisplay.getExerciseListTable();
        exerciseListTablePlaceholder.getChildren().add(exerciseListTable.getRoot());

        PersonalBestTable personalBestTable = clientViewDisplay.getPersonalBestTable();
        personalBestTablePlaceholder.getChildren().add(personalBestTable.getRoot());
    }

    /**
     * Updates {@code clientInView}.
     *
     * @author @yonggiee
     */
    private void refreshClientInViewDisplay() {
        clearClientInViewDisplay();

        if (logic.hasClientInView()) {
            clientViewDisplay.update(logic.getClientInView());
            addClientInViewDisplay();
        }
    }

    /**
     * Updates the SchedulePanel.
     *
     * @author @Dban1
     */
    private void refreshSchedulePanel() {
        schedulePanel = new SchedulePanel(logic.getScheduleDayList());

        schedulePanelPlaceholder.getChildren().clear();
        schedulePanelPlaceholder.getChildren().add(schedulePanel.getRoot());

    }

    /**
     * Executes the command and returns the result.
     *
     * @see seedu.fitbiz.logic.Logic#execute(String)
     */
    private CommandResult executeCommand(String commandText) throws CommandException, ParseException {
        try {
            CommandResult commandResult = logic.execute(commandText);
            logger.info("Result: " + commandResult.getFeedbackToUser());
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());

            refreshClientInViewDisplay();

            if (commandResult.isShowHelp()) {
                handleHelp();
            }

            if (commandResult.isExit()) {
                handleExit();
            }

            refreshSchedulePanel();

            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }
}
