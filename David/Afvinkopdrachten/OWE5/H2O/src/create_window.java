import javax.swing.*;
import java.awt.*;

public class create_window {
    public static void main(String[] args) {
        JFrame frame = new JFrame("H2O");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new JLabel("Hello World"), BorderLayout.CENTER);
        frame.pack();
        frame.setSize(300, 300);
        frame.setVisible(true);
        JPanel panel = new JPanel();
        panel.setBackground(Color.white);
        frame.add(panel);

    }
}
