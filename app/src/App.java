// import com.herra.ui.Calculator;

import com.herra.back.DecimalCalculator;

public class App {
    public static void main(String[] args) {
        // Calculator calc = new Calculator();
        // calc.setVisible(true);
        DecimalCalculator calc = new DecimalCalculator("9/9");
        calc.calcul();
        System.out.println("can proceed : " + calc.getAuthorization());
        System.out.println("output: " + calc.getOutput());
    }
}