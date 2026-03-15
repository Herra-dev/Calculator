// import com.herra.back.Calculator;

public class App {
    public static void main(String[] args) {
        // @SuppressWarnings("unused")
        // Calculator calc = new Calculator("-9*+99.51)((+-+-8))");
        quedalle();
        System.out.println("after quedalle()");
    }

    public static void quedalle() {
        try {
            System.out.println(7/0);
            System.out.println("Ex");
        }catch(ArithmeticException e) {
            e.printStackTrace();
        }
        
        System.out.println("exception !!!");
    }
}
