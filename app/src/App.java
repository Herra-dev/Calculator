// import com.herra.ui.Calculator;

import com.herra.back.DecimalCalculator;

public class App {
    public static void main(String[] args) {
        // Calculator calc = new Calculator();
        // calc.setVisible(true);
        DecimalCalculator calc = new DecimalCalculator("2+0.5+0.05(0)");
        System.out.println(calc.calcul());
    }
}