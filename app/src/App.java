// import com.herra.back.DecimalCalculator;


public class App {
    public static void main(String[] args) {
        // @SuppressWarnings("unused")
        // DecimalCalculator dc = new DecimalCalculator("-10/-5*2");

        String str = new String("8.5");
        if(str.matches("[0-9]++|[0-9]++\\p{Punct}[0-9]++"))
            System.out.println("same");
        else
            System.out.println("Different");
    }
}
