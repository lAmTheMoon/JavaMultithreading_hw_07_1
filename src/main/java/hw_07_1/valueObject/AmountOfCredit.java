package hw_07_1.valueObject;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class AmountOfCredit {
    private final int AMOUNT_OF_CREDIT;

    public AmountOfCredit(int amountOfCredit) {
        ensureAmountIsWithinValidRange(amountOfCredit);
        AMOUNT_OF_CREDIT = amountOfCredit;
    }

    private void ensureAmountIsWithinValidRange(int amountOfCredit) {
        int MIN_AMOUNT_OF_CREDIT = 50000;
        int MAX_AMOUNT_OF_CREDIT = 5000000;
        if (amountOfCredit < MIN_AMOUNT_OF_CREDIT || amountOfCredit > MAX_AMOUNT_OF_CREDIT) {
            throw new IllegalArgumentException("Кредит предоставляется на сумму от 50 000 до 5 000 000 руб.");
        }
    }

    public BigDecimal getAmountOfCredit() {
        return new BigDecimal(AMOUNT_OF_CREDIT).setScale(2, RoundingMode.HALF_EVEN);
    }
}
