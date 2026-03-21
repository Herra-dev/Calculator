import com.herra.back.Calculator;
import com.herra.exception._SyntaxErrorException;

public class App {
    public static void main(String[] args) {
        Calculator calc = new Calculator("9-+-----9");
        
        try {
            calc.testUserInput();
        } catch (_SyntaxErrorException e) {
            e.printStackTrace();
        }

        calc.arrangeUserInput();

    }

}
