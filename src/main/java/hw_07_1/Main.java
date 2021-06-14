package hw_07_1;

import java.math.BigDecimal;
import java.util.Map;

public class Main {

    public static void main(String[] arg) {
        CreateCreditApplicationService createCredit = new CreateCreditApplicationService();
        Map<String, BigDecimal> credit = createCredit.create(200000, 24);
        credit.forEach((k,v) -> System.out.println(k + " : " + v));
    }
}
