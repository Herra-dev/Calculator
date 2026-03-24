import com.herra.back.DecimalCalculator;
import com.herra.exception._SyntaxErrorException;

public class App {
    public static void main(String[] args) {
        // @SuppressWarnings("unused")
        DecimalCalculator dc = new DecimalCalculator("25)(");
        try {
            dc.testParenthesis();
        } catch (_SyntaxErrorException e) {
            e.printStackTrace();
        }

    }

}
