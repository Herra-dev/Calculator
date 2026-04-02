// import com.herra.back.DecimalCalculator;

import java.math.BigDecimal;
import java.math.BigInteger;

public class App {
    public static void main(String[] args) {
        // @SuppressWarnings("unused")
        // DecimalCalculator dc = new DecimalCalculator("-10/-5*2");

        BigDecimal first_number = new BigDecimal("-10");
        BigDecimal second_number = new BigDecimal("3");
        
        BigDecimal exactDivision = new BigDecimal((first_number.toBigInteger().divide(second_number.toBigInteger()).toString()));
        BigDecimal result = first_number.subtract(second_number.multiply(exactDivision));

        System.out.println("result = " + result);

        System.out.println("10%-3 = " + -10%3);
    }
}
