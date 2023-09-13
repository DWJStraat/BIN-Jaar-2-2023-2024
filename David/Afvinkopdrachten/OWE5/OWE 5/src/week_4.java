import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class Horse extends Paard {
    Horse(String naam, Color kleur, Image plaatje) {
        super(naam, kleur, plaatje);
    }
    }
public class week_4 {
    public static void main(String[] args) {
        race();
    }

    public static JFrame build_screen() {
        JFrame frame = new JFrame("Horse race");
        frame.setSize(500, 500);
        frame.setResizable(false);
        frame.setLayout(null);
        return frame;
    }

    public static void race() {
        Horse buttercup = new Horse("Buttercup", Color.white, null);
        Horse shadowfax = new Horse("Shadowfax", Color.black, null);
        Horse roach = new Horse("Roach", Color.darkGray, null);
        Horse epona = new Horse("Epona", Color.red, null);
        ArrayList<Horse> horses = new ArrayList<>();
        horses.add(buttercup);
        horses.add(shadowfax);
        horses.add(roach);
        horses.add(epona);
        int max_distance = 0;
        int goal = 100;

        JFrame frame = build_screen();

        for (Horse hors: horses) {
            String name = hors.getNaam();
            int y = 25 + (horses.indexOf(hors) * 100);
            JLabel label = new JLabel(name);
            label.setBounds(5, y, 100, 50);
            frame.add(label);
        }
        frame.setVisible(true);
        Graphics g = frame.getGraphics();
        while (max_distance < goal) {
            max_distance = run(horses, max_distance, goal, 300, g, frame);
        }


    }

    public static int run(ArrayList<Horse> horses, int max_distance, int goal, int delay, Graphics g, JFrame frame) {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (Horse hors : horses) {
            hors.loop();
            int width = frame.getWidth();
            int x = (hors.getAfstand()*(width-100))/goal;
            int y = 50 + (horses.indexOf(hors) * 100);
            g.setColor(hors.getKleur());
            g.fillRect(100, y, x, 50);

            if (hors.getAfstand() > max_distance) {
                max_distance = hors.getAfstand();
                if (max_distance >= goal) {
                    System.out.println(hors.getNaam() + " wins!");
                    JOptionPane.showMessageDialog(null, hors.getNaam() + " wins!");
                    break;
                }
            }
        }
        return max_distance;

    }
}
