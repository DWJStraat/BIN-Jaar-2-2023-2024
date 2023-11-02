import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static java.nio.file.Files.readAllLines;

/**
 * A class that allows for the opening of a file through a GUI.
 */
public class openFile {
    private final JFileChooser fileChooser = new JFileChooser();
    public File file;
    public List<String> content;
    public String name;
    public String path;

    /**
     * Initializes the file chooser and opens a file.
     */
    public openFile() {
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
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

    /**
     * Initializes the file chooser and opens a file.
     * @param args The path of the file to be opened
     */
    public void main(String[] args) {
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

    /**
     * Reads the contents of the file.
     */
    public void readFile() {

        try {
            content = readAllLines(file.toPath());
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Reads the contents of the file.
     * @param path The path of the file to be opened
     */
    public void readFile(String path) {
        file = new File(path);
        try {
            content = readAllLines(file.toPath());
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Returns the contents of the file.
     * @return The contents off the file
     */
    public List<String> getContent() {
        return content;
    }

}
