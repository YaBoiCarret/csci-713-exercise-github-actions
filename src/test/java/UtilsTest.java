import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class UtilsTest {

    @Test
    void checkNameReturnsFalseForNull() {
        assertFalse(Utils.checkName(null));
    }

    @Test
    void checkNameReturnsFalseForEmptyString() {
        assertFalse(Utils.checkName(""));
    }

    @Test
    void checkNameReturnsTrueForNonEmptyString() {
        assertTrue(Utils.checkName("Alice"));
    }

    @Test
    void isValidAgeReturnsFalseForNegativeAge() {
        assertFalse(Utils.isValidAge(-1));
    }

    @Test
    void isValidAgeReturnsTrueForZero() {
        assertTrue(Utils.isValidAge(0));
    }

    @Test
    void isValidAgeReturnsTrueForTypicalAdultAge() {
        assertTrue(Utils.isValidAge(25));
    }

    @Test
    void isValidAgeReturnsTrueForUpperBoundary() {
        assertTrue(Utils.isValidAge(120));
    }

    @Test
    void isValidAgeReturnsFalseForAboveUpperBoundary() {
        assertFalse(Utils.isValidAge(121));
    }
}
