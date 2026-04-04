import java.math.BigDecimal;
import java.math.RoundingMode;

// import com.herra.back.DecimalCalculator;


public class App {
    public static void main(String[] args) {
        // @SuppressWarnings("unused")
        // DecimalCalculator dc = new DecimalCalculator("9/8/7/6/5/4/3/2/1");
       
        BigDecimal bd1 = new BigDecimal("10");
        BigDecimal bd2 = new BigDecimal("3");
        BigDecimal result = bd1.divide(bd2, 20, RoundingMode.HALF_UP);
        result = result.scaleByPowerOfTen(8);

        System.out.println("result = " + result);
    }
}
