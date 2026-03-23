import com.herra.back.DecimalCalculator;

// import com.herra.back.MyLinkedList;

public class App {
    public static void main(String[] args) {
        
        DecimalCalculator dc = new DecimalCalculator("-000000001-+-1/00009/80000%");
        System.out.println("Output : " + dc.getOutput());
        System.out.println("Can process : " + dc.getAuthorization());
          
    }
}
