package headacheRemoval;
import javax.swing.*;
import java.awt.*;

public class progressBar {
    private int start_x;
    private int start_y;
    private int width;
    private int height;
    private int max_value;
    public int value;
    private JPanel canvas;
    private Graphics g;
    private double percentage;

    public progressBar(int start_x, int start_y, int width, int height, int max_value, JPanel panel) {
        this.start_x = start_x;
        this.start_y = start_y;
        this.width = width;
        this.height = height;
        this.max_value = max_value;
        this.value = 0;
        this.canvas = panel;
        this.g = canvas.getGraphics();
    }

    public void setValue(int value) {
        this.value = value;
        this.percentage = (double) value / (double) max_value;
    }

    public void draw(){
        g.fillRect(start_x, start_y, (int) (width * percentage), height);
    }

    public void update(int value) {
        setValue(value);
        draw();
    }

}
