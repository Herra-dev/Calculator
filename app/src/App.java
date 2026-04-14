// import com.herra.ui.Calculator;

import com.herra.back.DecimalCalculator;

public class App {
    public static void main(String[] args) {
        // Calculator calc = new Calculator();
        // calc.setVisible(true);
        DecimalCalculator calc = new DecimalCalculator("(9)2");
        System.out.println(calc.calcul());
    }
}



// bug found: (89-6)0 returns 83 instead of 0