package hw_07_1;

import hw_07_1.valueObject.AmountOfCredit;
import hw_07_1.valueObject.DurationMounts;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class CreateCreditApplicationService {

    public CreateCreditApplicationService() {
    }

    public Map<String, BigDecimal> create(int amountOfCreditInt, int durationMountsInt) {
        AmountOfCredit amountOfCredit = new AmountOfCredit(amountOfCreditInt);
        DurationMounts durationMounts = new DurationMounts(durationMountsInt);
        CalculateCreditDomainService credit = new CalculateCreditDomainService(amountOfCredit, durationMounts);
        List<BigDecimal> creditList = credit.calculate(amountOfCredit, durationMounts);
        return Map.of(
                "Сумма заявки на кредит", amountOfCredit.getAmountOfCredit(),
                "Количество месяцев погашения кредита", new BigDecimal(String.valueOf(durationMountsInt)),
                "Сумма ежемесячного платежа", creditList.get(0),
                "Общая сумма кредита", creditList.get(1),
                "Сумма переплаты за весь период", creditList.get(2)
        );
    }
}
