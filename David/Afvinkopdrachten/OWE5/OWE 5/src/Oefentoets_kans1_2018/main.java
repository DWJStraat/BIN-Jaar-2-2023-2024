package Oefentoets_kans1_2018;
import headacheRemoval.*;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class main {
    private static createWindow window;
    private static openFile file;
    private static JProgressBar bar;
    private static sequence seq;
    private static JButton openFileButton = new JButton("Open file");
    private static JButton calculateButton = new JButton("Calculate GC%");
    private static JButton browseButton = new JButton("Browse");
    private static JTextField pathField = new JTextField();
    private static JTextField startPosField = new JTextField();
    private static JTextField endPosField = new JTextField();
    private static int startPos;
    private static int endPos;

    private static String path;
    private static String name;
    private static String accessionCode;
    private static String sequence;
    private static JLabel accessionCodeLabel = new JLabel();
    private static JLabel nameLabel = new JLabel();
    public static JPanel canvas = new JPanel();
    public static Graphics graphics = canvas.getGraphics();


    public static void main(String[] args) {
        construct();

    }

    private static void construct() {
        window = new createWindow("Oefentoets kans 1 2018", 800, 330);
        window.add(new JLabel("Path:"), 115, 10, 50, 40);
        window.add(pathField, 160, 10, 500, 40);
        window.add(openFileButton, 10, 10, 100, 40);
        window.add(new JLabel("Start position:"), 10, 60, 100, 40);
        window.add(startPosField, 115, 60, 100, 40);
        window.add(nameLabel, 250, 60, 300, 40);
        window.add(browseButton, 680, 10, 100, 40);
        window.add(new JLabel("End position:"), 10, 110, 100, 40);
        window.add(endPosField, 115, 110, 100, 40);
        window.add(accessionCodeLabel, 250, 110, 300, 40);
        window.add(calculateButton, 10, 160, 150, 40);
        bar = new JProgressBar(0, 1000);
        bar.setStringPainted(true);
        bar.setString("Click \"Browse\" to select a file");
        window.add(bar, 10, 210, 750, 50);
        buildOpenFileButton();
        buildBrowseButton();
        buildCalculateButton();
        window.show();

    }
    private static void buildOpenFileButton() {
        openFileButton.addActionListener(e -> {
            try {
                path = pathField.getText();
                file.readFile(path);
                List<String> content = file.content;
                String header = content.get(0);
                content.remove(0);
                sequence = String.join("", content);
                accessionCode = header.split(" ")[0];
                accessionCodeLabel.setText(accessionCode);
                name = header.replace(accessionCode, "").trim();
                System.out.println(name);
                nameLabel.setText(name);
                seq = new sequence(name, accessionCode, 0, sequence.length(), sequence);
                startPosField.setText(String.valueOf(seq.getBeginPosition()));
                endPosField.setText(String.valueOf(seq.getEndPosition()));
                bar.setString("Click \"Calculate GC%\" to determine the GC% of the sequence");
            }
            catch (Exception ex) {
                window.error("No file selected");
                throw new RuntimeException(ex);
            }
        });
    }

    private static void buildBrowseButton() {
        browseButton.addActionListener(e -> {
            file = new openFile();
            pathField.setText(file.path);
            bar.setString("Click \"Open file\" to confirm your selection.");
        });

    }
    private static void buildCalculateButton() {
        calculateButton.addActionListener(e -> {
            try {
                startPos = Integer.parseInt(startPosField.getText());
                endPos = Integer.parseInt(endPosField.getText());
                if (startPos < 0 || endPos > seq.getSequence().length() || startPos > endPos) {
                    window.error("Invalid positions");
                }
                seq.setBeginPosition(startPos);
                seq.setEndPosition(endPos);
                int gc = (int) (seq.getGcPercentage()*1000);
                System.out.println(gc);
                bar.setValue(gc);
                bar.setString("GC% = " + gc/10.0 + "%");
            }
            catch (Exception ex) {
                window.error("Invalid positions");
                throw new RuntimeException(ex);
            }
        });
    }
}
