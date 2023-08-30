import javax.swing.*;
import java.awt.*;

public class bonus {
    public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("Hello world!");
        frame.setResizable(false);
        frame.setUndecorated(true);
        removeMinMaxClose(frame);
        JLabel text = new JLabel("Hello world!");
        frame.add(text);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(new ImageIcon("src\\icon.png").getImage());
        frame.setSize(300, 300);
        frame.setVisible(true);
        frame.getContentPane().setBackground(Color.BLUE);
        frame.getContentPane().setBackground(Color.RED);


    }


    public static void removeMinMaxClose(Component comp)
    {
        try {
            if (comp instanceof AbstractButton) {
                comp.getParent().remove(comp);
            }
            if (comp instanceof Container) {
                Component[] comps = ((Container) comp).getComponents();
                try {
                    comp = comps[1];
                    System.out.println(comp.toString());
                    removeMinMaxClose(comp);
                } catch (Exception e) {
                    comp = comps[0];
                    removeMinMaxClose(comp);
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}