import java.util.*;

/**
 * A class that wraps a file and its contents for the purposes of this application.
 */
public class fileWrapper {

    private final List<String> contents;
    private String[][] parsed;
    public Dictionary<String, Integer> frequency;
    public List<Integer> years;
    private int year = 0;

    /**
     * Initializes the file wrapper.
     * @param FileContents The contents of the file, excluding the header
     * @throws FileFormatError If the file is not in the correct format
     */
    public fileWrapper(List<String> FileContents) throws FileFormatError {
        FileContents.remove(0);
        this.contents = FileContents;
        parse();
    }

    /**
     * Parses the file contents into a 2D array.
     * @throws FileFormatError If the file is not in the correct format
     */
    private void parse() throws FileFormatError {
        parsed = new String[contents.size()][];
        years = new ArrayList<>();
        int counter = 0;
        // Interates over the lines and splits them into collumns
        for (String line : contents) {
            String[] splitted = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
            if(splitted.length == 14) {
                int yearLocal = Integer.parseInt(splitted[3]);

                parsed[counter] = splitted;
                if(!years.contains(yearLocal)){
                        years.add(yearLocal);
                }
                    counter++;

            } else {
                throw new FileFormatError("File is not in the correct format; should have 14 columns instead of "
                        + splitted.length + " columns");
            }
        }
        // Filters in the case of a year being selected
        if(year != 0){
            List<String[]> temp = new ArrayList<>();
            for(String[] line : parsed){
                if(Integer.parseInt(line[3]) == year){
                    temp.add(line);
                }
            }
            parsed = temp.toArray(new String[0][0]);
        }
    }

    /**
     * Calculates the frequency of the artists.
     * @throws NullPointerException If no songs are found for the year selected
     */
    public void calculateFrequency() throws NullPointerException {
        frequency = new Hashtable<>();
        try {
            for(String[] line : parsed){
                String artist = line[1];
                if(frequency.get(artist) == null){
                    frequency.put(artist, 1);
                } else {
                    frequency.put(artist, frequency.get(artist) + 1);
                }
            }
        } catch (NullPointerException e){
            throw new NullPointerException("No songs found");
        }
    }

    /**
     * Gets the most frequent artist.
     * @return The most frequent artist
     */
    public String getMostFrequent(){
        int max = 0;
        String artist = "";
        Enumeration<String> keys = frequency.keys();
        while(keys.hasMoreElements()){
            String key = keys.nextElement();
            if(frequency.get(key) > max){
                max = frequency.get(key);
                artist = key;
            }
        }
        return artist;
    }

    /**
     * Gets the least frequent artist.
     * @return The least frequent artist
     */
    public String getLeastFrequent(){
        int min = Integer.MAX_VALUE;
        String artist = "";
        Enumeration<String> keys = frequency.keys();
        while(keys.hasMoreElements()){
            String key = keys.nextElement();
            if(frequency.get(key) < min){
                min = frequency.get(key);
                artist = key;
            }
        }
        return artist;
    }

    /**
     * Gets the frequency of the artists.
     * @return The frequency of the artists
     */
    public Float[] getFrequencyPercentages(){
        Float[] percentages = new Float[frequency.size()];
        Enumeration<String> keys = frequency.keys();
        int counter = 0;
        while(keys.hasMoreElements()){
            String key = keys.nextElement();
            percentages[counter] = (float)frequency.get(key) / (float)contents.size();
            counter++;
        }
        Arrays.sort(percentages);
        return percentages;
    }

    /**
     * Gets the parsed file contents.
     * @return the parsed file contents
     */
    public String[][] getParsed() {
        return parsed;
    }

    /**
     * Gets the contents of the file, excluding the header.
     * @return The contents of the file, excluding the header
     */
    public List<String> getContents() {
        return contents;
    }

    /**
     * Gets the years found in the file.
     * @return The years in the file
     */
    public List<Integer> getYears() {
        return years;
    }

    /**
     * Gets the maximum year found in the file.
     * @return The maximum year found in the file
     */
    public Integer getMaxYear() {
        if (years.isEmpty()) {
            return null;
        }
        return Collections.max(years);
    }

    /**
     * Gets the minimum year found in the file.
     * @return  The minimum year found in the file
     */
    public Integer getMinYear() {
        if (years.isEmpty()) {
            return null;
        }
        return Collections.min(years);
    }


    /**
     * Sets the year to be filtered on.
     * @param yearInt The year to be filtered on
     * @throws FileFormatError If the file is not in the correct format
     */
    public void setYear(int yearInt) throws FileFormatError {
        this.year = yearInt;
        parse();
    }

    /**
     * Gets the year being filtered on.
     * @return The year being filtered on
     */
    public int getYear() {
        return year;
    }
}


/**
 * An exception thrown when the file is not in the correct format.
 */
class FileFormatError extends Exception {
    /**
     * The message to be displayed
     * @param message The message to be displayed
     */
    public FileFormatError(String message) {
        super(message);
    }
}