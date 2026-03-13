import com.herra.back.Calculator;

public class App {
    public static void main(String[] args) {
        Calculator calc = new Calculator("-(9*+99.5(+-+-8))");
        calc._calcul(calc._separeInput());
    }
}
