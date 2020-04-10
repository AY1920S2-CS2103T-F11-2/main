package seedu.fitbiz.logic.autocomplete;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class AutoCompleteTest {


    @Test
    public void constructor_nullFields_errorThrown() {
        assertThrows(NullPointerException.class, () -> new AutoComplete(null, null));
    }

}
