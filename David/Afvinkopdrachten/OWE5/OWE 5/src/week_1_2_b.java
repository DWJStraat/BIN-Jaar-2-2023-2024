import javax.swing.*;

public class week_1_2_b {

    public static void main(String[] args) {
        try {
            JOptionPane.showMessageDialog(null, "Hello " + args[0] + "!");
        } catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Please provide a name as an argument.");
        }
    }
}
