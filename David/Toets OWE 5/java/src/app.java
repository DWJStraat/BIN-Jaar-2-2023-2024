import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 *  The main class of the program.
 */
public class app {
    private static openFile file;
    private static final JButton browse = new JButton("Browse Files");
    private static final JLabel mostFrequent = new JLabel("Most Frequent:");
    private static final JLabel leastFrequent = new JLabel("Least Frequent:");
    private static final JPanel bar = new JPanel();
    private static final JTextField year = new JTextField();
    private static fileWrapper wrapper;
    private static Integer maxYear;
    private static Integer minYear;

    /**
     * Initializes the program.
     * @param args The arguments passed to the program
     */
    public static void main(String[] args) {
        System.out.println("Hello World!");
        buildWindow();
    }

    /**
     * Builds the window of the program and adds the components. Initializes the action listeners.
     */
    private static void buildWindow(){
        createWindow window = new createWindow("Hello World!", 500, 500);
        browseBuilder();
        yearBuilder();
        window.add(browse, 10, 40, 200, 20);
        window.add(new JLabel("Year:"), 240, 10, 200, 20);
        window.add(year, 240, 40, 200, 20);
        window.add(mostFrequent, 220, 60, 200, 20);
        window.add(leastFrequent, 10, 60, 200, 20);
        window.add(bar, 10, 80, 400, 20);
        window.show();
    }

    /**
     *  Initializes the action listener for the year text field.
     */
    private static void yearBuilder() {
        year.addActionListener(e -> {
            try{
                    if (wrapper == null){
                        throw new NullPointerException("Please select a file first");
                    }
                    int yearInt = Integer.parseInt(year.getText());
                    if(yearInt > maxYear || yearInt < minYear){
                        throw new NumberFormatException("Please enter a valid year");
                    } else {
                        wrapper.setYear(yearInt);
                        barBuilder();
                }

            } catch (NumberFormatException | FileFormatError  ex){
                JOptionPane.showConfirmDialog(null, ex.getMessage(), "Error",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    /**
     *  Initializes the action listener for the browse button.
     */
    private static void browseBuilder(){
        browse.addActionListener(e -> {
            file = new openFile();
            file.readFile();
            openFile(file.content);
        });
    }

    /**
     * Initializes the file wrapper and calculates the frequency of the years.
     * @param content The content of the file, excluding the header
     */
    private static void openFile(List<String> content){
        try {
            wrapper = new fileWrapper(content);
            maxYear = wrapper.getMaxYear();
            minYear = wrapper.getMinYear();
            barBuilder();
        } catch (FileFormatError ex) {
            JOptionPane.showConfirmDialog(null, ex.getMessage(), "Error",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Calculates the frequency of the artists and draws the bar.
     */
    private static void barBuilder() {
        wrapper.calculateFrequency();
        mostFrequent.setText("Most Frequent: " + wrapper.getMostFrequent());
        leastFrequent.setText("Least Frequent: " + wrapper.getLeastFrequent());
        Graphics2D graphics = (Graphics2D) bar.getGraphics();
        graphics.setColor(Color.WHITE);
        int width = bar.getWidth();
        int height = bar.getHeight();
        graphics.fillRect(0, 0, width, height);
        bar.setLayout(new BoxLayout(bar, BoxLayout.X_AXIS));
        Float[] percentages = wrapper.getFrequencyPercentages();
        float maxPercentage = 0;
        for (Float percentage : percentages) {
            maxPercentage += percentage;
        }
        float minRes = maxPercentage / width;
        for(int i = 0; i < percentages.length; i++){
            float percentage = percentages[i];
            if(percentage < minRes){
                    percentages[i] = 0.0f;
            }
        }
        int currentWidth = 0;
        float sumPercentage = 0;
        for(Float percentage: percentages){
            float percentageWidth = percentage / maxPercentage;
            sumPercentage += percentageWidth;
            int barWidth = (int) (width * percentageWidth);
            graphics.setColor(new Color((int)(Math.random() * 0x1000000)));
            graphics.fillRect(currentWidth, 0, barWidth, height);
            currentWidth += barWidth;
        }
        System.out.println(sumPercentage);
    }


}
