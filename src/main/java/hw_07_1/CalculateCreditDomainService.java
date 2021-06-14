package hw_07_1;

import hw_07_1.valueObgect.AmountOfCredit;
import hw_07_1.valueObgect.DurationMounts;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;

public class CalculateCreditDomainService {
    private final BigDecimal INTEREST_RATE = new BigDecimal("0.11");
    private final BigDecimal MONTHLY_INTEREST_RATE = calculateMonthlyInterestRate(INTEREST_RATE);

    public CalculateCreditDomainService(AmountOfCredit amountOfCredit, DurationMounts durationMounts) {
    }

    private BigDecimal calculateMonthlyInterestRate(BigDecimal INTEREST_RATE) {
        // Находим процентную ставку по кредиту в месяц.
        BigDecimal monthsInYear = new BigDecimal("12");
        return INTEREST_RATE.divide(monthsInYear, MathContext.DECIMAL64).setScale(6, RoundingMode.HALF_EVEN);
    }

    public List<BigDecimal> calculate(AmountOfCredit amountOfCredit, DurationMounts durationMounts) {
        BigDecimal monthlyPayment = calculateMonthlyPayment(amountOfCredit.getAmountOfCredit(), durationMounts.getDurationMounts());
        BigDecimal totalAmountOfCredit = calculateTotalAmountOfCredit(monthlyPayment, durationMounts.getDurationMounts());
        BigDecimal overpaymentAmount = calculateOverPaymentAmount(amountOfCredit, totalAmountOfCredit);
        return List.of(monthlyPayment, totalAmountOfCredit, overpaymentAmount);
    }

    private BigDecimal calculateAnnuityCoefficient(int durationMounts) {
        // Формула расчета коэффициента аннуитета:
        // K = (i * (1 + i) ^ n) / ((1 + i) ^ n - 1), где
        // i - monthlyInterestRate, процентная ставка по кредиту в месяц;
        // n - durationMounts, количество периодов (месяцев) погашения кредита;
        BigDecimal powPart = (new BigDecimal("1").add(MONTHLY_INTEREST_RATE)).pow(durationMounts, MathContext.DECIMAL64);
        BigDecimal multiplyPart = MONTHLY_INTEREST_RATE.multiply(powPart);
        BigDecimal subtractPart = powPart.subtract(new BigDecimal("1"));
        return multiplyPart.divide(subtractPart, MathContext.DECIMAL64);
    }

    private BigDecimal calculateMonthlyPayment(BigDecimal amountOfCredit, int durationMounts) {
        // Формула расчета платежа по аннуитетной схеме:
        // A = K * S, где
        // А – сумма ежемесячного аннуитетного платежа;
        // К - annuityCoefficient, коэффициент аннуитета;
        // S - amountOfCredit, сумма кредита.
        BigDecimal annuityCoefficient = calculateAnnuityCoefficient(durationMounts);
        return annuityCoefficient.multiply(amountOfCredit, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_EVEN);
    }

    private BigDecimal calculateTotalAmountOfCredit(BigDecimal monthlyPayment, int durationMounts) {
        return monthlyPayment.multiply(new BigDecimal(durationMounts)).setScale(2, RoundingMode.HALF_EVEN);
    }

    private BigDecimal calculateOverPaymentAmount(AmountOfCredit amountOfCredit, BigDecimal totalAmountOfCredit) {
        return totalAmountOfCredit.subtract(amountOfCredit.getAmountOfCredit(), MathContext.DECIMAL64);
    }
}