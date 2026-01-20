import com.herra.back.Calculator;

public class App {
    public static void main(String[] args) {
        Calculator calc = new Calculator("((5+6(9*6))(2*3)+1)");
        //(3((2*3)+1))
        //3((2*3)+1)
        //(2*3)+1
        //2*3
        //9+((3((2*3)+1)))
        calc._calcul();
    }

}
