import javax.swing.*;
import java.util.ArrayList;

public class week_5_1 {
    static JTextField input;
    static JLabel output;
    static JButton button;
    private static int center = 100;
    public static void main(String[] args) {
        JFrame frame = createWindow();
        createInput(frame);
        createOutput(frame);
        createButton(frame);
        center = frame.getWidth()/2;
        frame.setVisible(true);
    }
    public static JFrame createWindow() {
        JFrame frame = new JFrame("One2Three");
        frame.setSize(200, 200);
        frame.setResizable(false);
        frame.setLayout(null);
        return frame;
    }

    public static void createInput(JFrame frame) {
        input = new JTextField();
        input.setBounds(center-50, 5, 100, 50);
        frame.add(input);
    }

    public static void createButton(JFrame frame) {
        button = new JButton("Convert");
        button.setBounds(center-50, 55, 100, 50);
        button.addActionListener(e -> {
            String inputText = input.getText();
            System.out.println(inputText);
            try {
                char[] chars = inputText.toCharArray();
                ArrayList<String> output_text = new ArrayList<>();
                for (char aChar : chars) {
                    String character = String.valueOf(aChar).toUpperCase();
                    System.out.println(character);
                    output_text.add(One2Three.one2three(character));
                }
                String output_text_string = String.join("-", output_text);
                output.setText(output_text_string);
            } catch (NotAnAA ex) {
                JDialog dialog = new JDialog(frame, "Error");
                dialog.setSize(200, 200);
                dialog.setResizable(false);
                dialog.setLayout(null);
                JLabel label = new JLabel("Not an amino acid");
                label.setBounds(50, 50, 100, 50);
                dialog.add(label);
                dialog.setVisible(true);
            }
        });

        frame.add(button);

    }

    public static void createOutput(JFrame frame) {
        output = new JLabel();
        output.setBounds(center-50, 105, 100, 50);
        frame.add(output);
    }

}

class One2Three extends Translator{}