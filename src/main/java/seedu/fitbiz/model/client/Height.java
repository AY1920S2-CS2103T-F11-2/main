package seedu.fitbiz.model.client;

import static java.util.Objects.requireNonNull;
import static seedu.fitbiz.commons.util.AppUtil.checkArgument;

import java.math.BigDecimal;

/**
 * Represents current height (in cm) of a client in FitBiz. Guarantees:
 * immutable; is valid as declared in {@link #isValidHeight(String)}
 */
public class Height {

    public static final String MESSAGE_CONSTRAINTS = "Heights must either be a whole or decimal number"
            + " (eg. 165 or 186.22). Make sure that you only have a maximum of 3 digits before and"
            + " 2 digits after the decimal place.";
    public static final String VALIDATION_REGEX = "[0-9]{1,3}(\\.[0-9]{0,2})?";
    private static final String EMPTY_STRING = "";
    private static final String FORMAT_TWO_DECIMAL_PLACES = "%1$.2f";

    public final String value;

    /**
     * Constructs a {@code Height}.
     *
     * @param height A valid height.
     */
    public Height(String height) {
        requireNonNull(height);
        checkArgument(isValidHeight(height), MESSAGE_CONSTRAINTS);
        String formattedHeight = formatHeight(height);
        value = formattedHeight;
    }

    private static boolean isEmptyString(String toTest) {
        return toTest.equals(EMPTY_STRING);
    }

    /**
     * Formats the given {@code height} using {@code BigDecimal} to two decimal
     * places.
     *
     * @param height height to format
     * @return formatted height to 2 decimal places
     */
    private static String formatHeight(String height) {
        if (isEmptyString(height)) {
            return EMPTY_STRING;
        }
        BigDecimal bigDecimal = new BigDecimal(height);
        return String.format(FORMAT_TWO_DECIMAL_PLACES, bigDecimal);
    }

    /**
     * Returns true if a given string is a valid height.
     */
    public static boolean isValidHeight(String test) {
        return isEmptyString(test) || test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Height // instanceof handles nulls
                        && value.equals(((Height) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
