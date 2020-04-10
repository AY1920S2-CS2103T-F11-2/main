package seedu.fitbiz.model;

import java.util.ArrayList;
import java.util.List;

/**
 * This represents the model of the command history and contains the underlying
 * data structure to be used by {@code CommandHistory}.
 */
public class CommandHistoryState {

    private static final int START_INDEX = 0;
    private static final int MAX_HISTORY_SIZE = 100;
    private static final String EMPTY_STRING = "";

    private ArrayList<String> history;
    private int index;

    /**
     * Default constructor for this class; initialises the state {@code history} and
     * the {@code index}.
     */
    public CommandHistoryState(List<String> initialState) {
        history = new ArrayList<String>(initialState);
        index = initialState.size();
    }

    private boolean isAtStart() {
        return index == START_INDEX;
    }

    private boolean isAtEnd() {
        return index == history.size();
    }

    private boolean hasNoHistory() {
        return history.isEmpty();
    }

    /**
     * Returns the last item added to {@code history}.
     */
    private String getMostRecentCommand() {
        return history.get(history.size() - 1);
    }

    private boolean isEmptyString(String toTest) {
        return toTest.equals(EMPTY_STRING);
    }

    /**
     * Returns true iff the parameter {@code toTest} is different from the most
     * recent command, or if {@code history} is currently empty.
     *
     * @param toTest parameter to test.
     * @return true iff {@code toTest} is different from the most recent command.
     */
    private boolean isSimilarToMostRecentCommand(String toTest) {
        if (hasNoHistory()) {
            return false; // no history means toTest is unique
        }
        return getMostRecentCommand().equals(toTest);
    }

    private boolean isAtMaxCapacity() {
        return history.size() >= MAX_HISTORY_SIZE;
    }

    /**
     * Adds the user input {@code command} String to this model and to the
     * {@code commandHistoryStorage}.
     *
     * @param command the user input command
     */
    public void add(String command) {
        if (isEmptyString(command)) {
            index = history.size();
            return;
        }
        if (isSimilarToMostRecentCommand(command)) {
            index = history.size();
            return;
        }
        if (isAtMaxCapacity()) {
            history.remove(START_INDEX);
        }

        history.add(command);
        index = history.size();
    }

    /**
     * Returns the previous command string in the {@code history}. If
     * {@code history} is empty, the empty string is returned.
     *
     * @return the previous command string
     */
    public String getPreviousCommand() {
        if (isAtStart()) {
            return hasNoHistory() ? EMPTY_STRING : history.get(index);
        }
        index--;
        return history.get(index);
    }

    /**
     * Returns the next command string in the {@code history}. If the end of the
     * {@code history} is reached, the empty string is returned.
     *
     * @return the next command string
     */
    public String getNextCommand() {
        if (isAtEnd()) {
            return EMPTY_STRING;
        }
        index++;
        return isAtEnd() ? EMPTY_STRING : history.get(index);
    }

    /**
     * Returns a copy of the {@code history}. Any modifications done to this
     * returned list will not affect the internal list stored in this class.
     */
    public ArrayList<String> getCurrentState() {
        return new ArrayList<String>(history);
    }

    /**
     * Clears and resets the state of the {@code history} and the {@code index}.
     */
    public void clearState() {
        history.clear();
        index = START_INDEX;
    }
}
