package hw_07_1.valueObject;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AmountOfCreditTest {

    @Test
    public void ensureThrowsExceptionWhenAmountIsTooSmall() {
        assertThrows(IllegalArgumentException.class, () -> {
            new AmountOfCredit(49999);
        });
    }

    @Test
    public void ensureThrowsExceptionWhenAmountIsTooBig() {
        assertThrows(IllegalArgumentException.class, () -> {
            new AmountOfCredit(5000001);
        });
    }
}