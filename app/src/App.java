// import com.herra.back.DecimalCalculator;


public class App {
    public static void main(String[] args) {
        // @SuppressWarnings("unused")
        // DecimalCalculator dc = new DecimalCalculator("-10/-5*2");

        String str = new String(".");
        if(str.matches("\\p{Punct}"))
            System.out.println("same");
        else
            System.out.println("Different");
    }
}
