import javax.swing.*;

public class createWindow {
    public static JFrame frame;

    public createWindow(String title, int width, int height) {
        build(title, width, height);
    }

    public static void main(String[] args) {

    }

    public static void build(String title, int width, int height) {
        frame = new JFrame(title);
        frame.setSize(width, height);
        frame.setResizable(false);
        frame.setLayout(null);
    }

    public static void show() {
        frame.setVisible(true);
    }

    public static void add(JComponent component, int position_x, int position_y, int width, int height) {
        frame.add(component);
        component.setBounds(position_x, position_y, width, height);
    }


}
