package week_5_2;

import javax.swing.*;

public class frontend {
    static JFrame frame;
    static JTextField input;
    static JLabel output;
    static JButton button;

    public static void main(String[] args) {
        createGUI();
        createInput();
        createOutput();
        createButton();
        frame.setVisible(true);
    }
    public static void createGUI() {
        frame = new JFrame("Translator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setResizable(false);
        frame.setLayout(null);
    }
    public static void createInput() {
        input = new JTextField();
        input.setBounds(50, 50, 400, 25);
        frame.add(input);
    }
    public static void createOutput() {
        output = new JLabel();
        output.setBounds(50, 100, 400, 25);
        frame.add(output);
    }
    public static void createButton() {
        button = new JButton("Run");
        button.setBounds(150, 150, 100, 25);
        button.addActionListener(e -> {
            output.setText("If you see this, something went wrong");
            String inputText = input.getText();
            System.out.println(inputText);
            try {
                backend.main(new String[]{inputText, "nld"});
                String outputText = backend.output;
                System.out.println(outputText);
                output.setText(outputText);
            } catch (Exception ex) {
                if (ex.getMessage().equals("File not found")) {
                    output.setText("File not found");
                } else if (ex.getMessage().equals("NoMatchException")) {
                    output.setText("No match found");
                } else {
                    output.setText("Something went wrong");
                }
            }
        });
        frame.add(button);
    }

}
