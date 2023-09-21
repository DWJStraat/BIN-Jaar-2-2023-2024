import headacheRemoval.createWindow;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import static java.nio.file.Files.readAllLines;

public class week_6 {

    public static createWindow window = new createWindow("YANARA", 500, 500);
    public static JPanel panel = new JPanel();
    public static Graphics graphics;
    public static JTextArea info = new JTextArea();
    public static JFileChooser fileChooser = new JFileChooser();
    public static File file;
    public static String content;
    public static String header;
    public static JTextField file_name_field = new JTextField();
    public static JButton open = new JButton("Open");
    public static JButton analyze = new JButton("Analyze");

    public static void main(String[] args) {
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
        System.out.println("Hello world!");
        build();
    }

    public static void build() {
        panel.setBackground(java.awt.Color.WHITE);
        openBuild();
        analyzeBuild();
        window.add(new JLabel("File Name:"), 10, 10, 100, 40);
        window.add(new JLabel("Information:"), 10, 110, 100, 40);
        window.add(new JLabel("Percentage:"), 10, 390, 100, 40);
        window.add(panel, 100, 380, 350, 50);
        window.add(info, 100, 110, 300, 250);
        window.add(file_name_field, 100, 10, 200, 40);
        window.add(open, 300, 10, 90, 40);
        window.add(analyze, 400, 10, 90, 40);
        window.show();
    }

    public static void openBuild() {
        open.addActionListener(e -> {
            int returnVal = fileChooser.showOpenDialog(window.frame);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                file = fileChooser.getSelectedFile();
                file_name_field.setText(file.getName());
                try {
                    content = readAllLines(file.toPath()).toString();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                System.out.println("Opening: " + file.getName() + ".");
            } else {
                System.out.println("Open command cancelled by user.");
            }
        });
    }

    public static void analyzeBuild() {
        analyze.addActionListener(e -> {
            if (file != null) {
                info.setText("Analyzing...");
                System.out.println("Analyzing...");
                runAnalyze();
            } else {
                info.setText("No file selected.");
                System.out.println("No file selected.");
            }
        });
    }
    public static void runAnalyze(){
        readFile();
        System.out.println("Header: " + header);
        int length = content.length();
        int non_polar = 0;
        String[] non_polar_list = {"A", "V", "I", "L", "M", "F", "W", "P", "G"};
        for (String amino_acid : non_polar_list) {
            non_polar += content.length() - content.replace(amino_acid, "").length();
        }
        double non_polar_percentage = (double) non_polar / length * 100;
        double polar_percentage = 100 - non_polar_percentage;
        info.setText("All amino-acids are valid.\n" +
                "Length of protein: " + length +
                "\nNon-polar: " + non_polar_percentage + "%\n"
                + "Polar: " + polar_percentage + "%");
        graphics = panel.getGraphics();
        graphics.setColor(java.awt.Color.WHITE);
        graphics.fillRect(0, 0, 350, 50);
        draw_bar(0, 0, non_polar_percentage, 20, java.awt.Color.RED, "Non-polar");
        draw_bar(0, 30, polar_percentage, 20, java.awt.Color.BLUE, "Polar");

    }

    public static void readFile() {
        try {
            List<String> file_contents = readAllLines(Path.of(file.getAbsolutePath()));
            header = file_contents.get(0);
            file_contents.remove(0);
            content = String.join("", file_contents);
            System.out.println("Reading file: " + file.getName() + ".");
            System.out.println("File content: " + file_contents);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static void draw_bar(int x, int y, double percent, int height, java.awt.Color color, String title) {
        System.out.println("Drawing bar: " + title + ".");
        graphics.setColor(color);
        int length = (int) (percent * 3);
        graphics.fillRect(x, y, length, height);
        graphics.drawString(title, x + length + 10, y + height / 2);
    }
}
