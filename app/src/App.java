import com.herra.back.Calculator;

public class App {
    public static void main(String[] args) {
        Calculator calc = new Calculator("16/39");
        
        calc._calcul();
        System.out.println(16.0/39.0);
    }

}
