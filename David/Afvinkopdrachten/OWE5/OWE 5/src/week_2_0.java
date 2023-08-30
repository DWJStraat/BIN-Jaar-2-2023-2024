import java.text.DecimalFormat;
import java.util.Scanner;

public class week_2_0 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter the amount of widgets you would like to buy");
        int widgets = in.nextInt();
        System.out.println("Please enter the price of the widgets");
        double price = in.nextDouble();
        DecimalFormat df = new DecimalFormat("#.00");
        double total = widgets * price;
        System.out.println("The total cost of your widgets is " + df.format(total));
    }
}
