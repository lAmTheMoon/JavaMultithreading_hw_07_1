package hw_07_1_test;

import hw_07_1.CreateCreditApplicationService;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateCreditApplicationServiceTest {
    private final BigDecimal interestRate = new BigDecimal("0.11");
    private final BigDecimal monthlyInterestRate = interestRate.divide(new BigDecimal("12"), MathContext.DECIMAL64)
            .setScale(6, RoundingMode.HALF_EVEN);

    @Test
    public void ensureCreateCredit() {
        BigDecimal amountOfCredit = new BigDecimal("500000").setScale(2);
        int durationMounts = 24;
        BigDecimal powPart = (new BigDecimal("1").add(monthlyInterestRate)).pow(durationMounts, MathContext.DECIMAL64);
        BigDecimal annuityCoefficient = monthlyInterestRate.multiply(powPart).divide(powPart.subtract(new BigDecimal("1")),
                MathContext.DECIMAL64);
        CreateCreditApplicationService createCredit = new CreateCreditApplicationService();
        BigDecimal monthlyPayment = annuityCoefficient.multiply(amountOfCredit).setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal totalAmountOfCredit = monthlyPayment.multiply(new BigDecimal(durationMounts)).setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal overpaymentAmount = totalAmountOfCredit.subtract(amountOfCredit);
        Map<String, BigDecimal> response = Map.of(
                "Сумма заявки на кредит", amountOfCredit,
                "Количество месяцев погашения кредита", new BigDecimal(durationMounts),
                "Сумма ежемесячного платежа", monthlyPayment,
                "Общая сумма кредита", totalAmountOfCredit,
                "Сумма переплаты за весь период", overpaymentAmount
        );
        assertEquals(response, createCredit.create(amountOfCredit.intValue(), durationMounts));
    }
}