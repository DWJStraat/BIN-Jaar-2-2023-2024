import javax.swing.*;
import java.awt.*;

public class H2O {
    public static void main (String[] args) {
        build_screen();
    }

    public static void build_screen(){
        JFrame frame= new JFrame("H2O");
        frame.add(canvas());
        text_box(100, "X", frame);
        text_box(200, "Y", frame);
        button(300, "Add", frame);
        frame.setBackground(Color.darkGray);
        frame.setSize(500,650);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    public static Component canvas(){
        JPanel canvas=new JPanel();
        canvas.setBounds(0,0,500,500);
        canvas.setBackground(Color.white);
        return canvas;
    }

    public static void button(int x, String label, JFrame screen){
        JButton button = new JButton(label);
        button.setBounds(x,550,100,20);
        button.addActionListener(e -> {
            int x1 = Integer.parseInt(((JTextField) screen.getContentPane().getComponent(1)).getText());
            int y = Integer.parseInt(((JTextField) screen.getContentPane().getComponent(3)).getText());
            add_molecule(x1,y, 100, (JPanel) screen.getContentPane().getComponent(0));
        });
        screen.add(button);
    }


    public static void text_box(int x, String label, JFrame screen){
        JTextField text_box = new JTextField();
        JLabel label1 = new JLabel(label);
        text_box.setBounds(x,550,50,20);
        label1.setBounds(x,530,100,20);
        screen.add(text_box);
        screen.add(label1);
    }

    public static void add_molecule(int x, int y, int distance, JPanel canvas){
        Graphics g = canvas.getGraphics();
        g.setColor(Color.white);
        g.fillRect(0,0,500,500);
        g.setColor(Color.black);
        g.drawLine(x, y+25, x+distance+38, y+25);

        //The simple H atom
        add_atom(x+distance+38,y+12,g,Color.blue,26);
        //The H atom that gives me a headache
        add_hydrogen(x,y,g,26,distance);
        //O atom
        add_atom(x,y,g,Color.red,50);

    }

    public static void add_hydrogen(int oxygen_x, int oxygen_y, Graphics g, int size, int distance){
        int x = (int) (oxygen_x + distance * Math.sin(16));
        int y = (int) (oxygen_y + distance * Math.cos(16));
        g.setColor(Color.black);
        g.drawLine(oxygen_x+25, oxygen_y+25, x+13, y+13);
        add_atom(x,y,g,Color.blue,size);
    }

    public static void add_atom(int x, int y, Graphics g, Color color, int size){
        g.setColor(color);
        g.drawOval(x,y,size,size);
        g.fillOval(x,y,size,size);
    }
}

