import headacheRemoval.createWindow;
import headacheRemoval.openFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.*;
import javax.swing.*;
import java.awt.*;

public class week_7 {
    /**
    *   The main application. Allows for the entry of a FASTA-style file and displays both the sequence
     *   and a visual representation of the sequence.
     */
    public static createWindow window = new createWindow("SEQVIS", 500, 500);
    public static Seq seq;
    public static JButton open = new JButton("Open");
    public static JTextField file_name_field = new JTextField();
    public static JTextArea sequence_field = new JTextArea();
    static {
        sequence_field.setLineWrap(true);
    }
    public static JPanel visual = new JPanel();
    public static Graphics graphics;
    public static List<Color> colors;
    public static void main(String[] args) {
        /*
            The main method, which is called when the program is run and initiates the GUI.
         */
        System.out.println("Hello world!");
        build();
    }

    private static void build() {
        /*
          Builds the GUI
         */
        openBuild();
        visual.setBackground(Color.WHITE);
        JScrollPane seq_field = new JScrollPane(sequence_field);
        window.add(new JLabel("File Name:"), 10, 10, 100, 40);
        window.add(new JLabel("Sequence:"), 50, 70, 100, 40);
        window.add(file_name_field, 110, 10, 200, 40);
        window.add(seq_field, 50, 110, 400, 250);
        window.add(visual, 10, 370, 470, 80);
        window.add(open, 350, 10, 100, 40);
        window.show();
    }

    private static void openBuild() {
        /*
            Builds the open button
         */
        open.addActionListener(e -> {
            // open the file and read the contents
            openFile.main(null);
            List<String> content = openFile.content;
            String seqname = content.get(0);
            content.remove(0);
            String sequence = String.join("", content);
            content.add(0, seqname);
            // create a new Seq object
            seq = new Seq(seqname, sequence);
            // set the text fields
            file_name_field.setText(openFile.name);
            String text  = String.join("\n", openFile.content);
            sequence_field.setText(text);
            // set the colors
            colors = seq.colors;
            // clear the visual panel
            graphics = visual.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, visual.getWidth(), visual.getHeight());
            // draw the colors
            for(int i = 0; i<= seq.length; i++) {
                int width = 1;
                graphics.setColor(colors.get(i));
                graphics.fillRect(i*width, 0, 10, 80);
            }
            // empty the colors list
            colors.clear();
        });
    }
}

