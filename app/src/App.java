import com.herra.back.DecimalCalculator;
import com.herra.exception._SyntaxErrorException;

public class App {
    public static void main(String[] args) {
        DecimalCalculator dc = new DecimalCalculator("16/000000001+1/000010000");
        try {
            dc.testParenthesis();
        } catch (_SyntaxErrorException e) {
            e.printStackTrace();
        }   
    }
}
