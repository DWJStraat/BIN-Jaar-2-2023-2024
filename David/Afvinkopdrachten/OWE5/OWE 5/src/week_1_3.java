import javax.swing.*;

public class week_1_3 {
    public static void main(String[] args) {
        JTextField name = new JTextField();
        JTextField age = new JTextField();
        JTextField height = new JTextField();
        JTextField weight = new JTextField();
        Object[] message = {
            "Name:", name,
            "Age:", age,
            "Height:", height,
            "Weight:", weight
        };
        int option = JOptionPane.showConfirmDialog(null, message, "Enter your information", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            JOptionPane.showMessageDialog(null, "Hello " + name.getText() + "!");
            JOptionPane.showMessageDialog(null, "You are " + age.getText() + " years old!");
            JOptionPane.showMessageDialog(null, "You are " + height.getText() + " cm tall!");
            JOptionPane.showMessageDialog(null, "You weigh " + weight.getText() + " kg!");
        }
    }
}
