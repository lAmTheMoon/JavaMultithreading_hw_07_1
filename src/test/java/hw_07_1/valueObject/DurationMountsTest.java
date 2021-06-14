package hw_07_1.valueObject;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DurationMountsTest {

    @Test
    public void ensureThrowsExceptionWhenDurationIsTooSmall() {
        assertThrows(IllegalArgumentException.class, () -> {
            new DurationMounts(5);
        });
    }

    @Test
    public void ensureThrowsExceptionWhenDurationIsTooBig() {
        assertThrows(IllegalArgumentException.class, () -> {
            new DurationMounts(90);
        });
    }
}