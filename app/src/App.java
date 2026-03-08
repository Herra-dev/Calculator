import java.util.List;

import com.herra.back.Calculator;

public class App {
    public static void main(String[] args) {
        Calculator calc = new Calculator("5**/+5");
        List<Object> obj = calc._get_cListNumber();

        for(Object obs: obj)
            System.out.println(obs);
    }
}
