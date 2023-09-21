package headacheRemoval;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static java.nio.file.Files.readAllLines;

public class openFile {
    public static JFileChooser fileChooser = new JFileChooser();
    public static File file;
    public static List<String> content;
    public static String name;
    public static String path;
    public static void main(String[] args) {
        if(args == null) {
            fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
        } else {
            fileChooser.setCurrentDirectory(new File(args[0]));
        }
        fileChooser.setDialogTitle("Select a file");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
            path = file.getAbsolutePath();
            name = file.getName();
            readFile();
        } else {
            throw new RuntimeException("No file selected");
        }
    }
    public static void readFile() {
        try {
            content = readAllLines(file.toPath());
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
