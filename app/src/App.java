// import java.util.regex.Matcher;
// import java.util.regex.Pattern;

import com.herra.back.DecimalCalculator;
import com.herra.exception._SyntaxErrorException;

public class App {
    public static void main(String[] args) {
        DecimalCalculator dc = new DecimalCalculator("16/000000002");
        try {
            dc.testParenthesis();
        } catch (_SyntaxErrorException e) {
            e.printStackTrace();
        }   
        
        // Pattern p = Pattern.compile("aaaaab");
        // Matcher m = p.matcher("aaaaab");
        // boolean b = m.matches();

        // if(b) System.out.println("true");
        // else System.out.println("false");
    }
}
