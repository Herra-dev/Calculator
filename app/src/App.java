import com.herra.back.Calculator;

public class App {
    public static void main(String[] args) {
        Calculator calc = new Calculator("16/3");
        
        calc._calcul();
        System.out.println(16.0/3.0);
    }

}
