import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The Seq class, which contains the sequence and its properties.
 */
public class Seq {
    static String sequence;
    static int length;
    static String type;
    static String seqname;
    static java.util.List<Color> colors = new ArrayList<>();
    private static final Pattern DNA = Pattern.compile("^[ATCG]+$");
    private static final Pattern RNA = Pattern.compile("^[AUCG]+$");
    private static final Pattern PROTEIN = Pattern.compile("^[ARNDCEQGHILKMFP^STWYV]+$");

    /**
     * The constructor for the Seq class
     * @param seqname The name of the sequence
     * @param sequence The sequence itself
     */
    public Seq(String seqname, String sequence) {

        Seq.seqname = seqname;
        Seq.sequence = sequence;
        setLength();
        setType();
        setColors();
    }

    /**
     * The main method, which is called when the program is run and initiates the GUI.
     * @param args The command line arguments. The first argument is the name of the sequence, the second is the sequence itself.
     */
    public static void Main(String[] args){
        /*
            The main method, sets up the object.
         */
        seqname = args[0];
        sequence = args[1].toUpperCase();
        setLength();
        setType();
        setColors();
    }

    /**
     * Sets the length of the sequence
     */
    private static void setLength() {
        length = sequence.length();
    }

    /**
     * Sets the type of the sequence
     * @throws IllegalArgumentException if the sequence is not DNA, RNA, or protein
     */
    private static void setType() {
        // determine the type of the sequence
        Matcher DNA_match = DNA.matcher(sequence);
        Matcher RNA_match = RNA.matcher(sequence);
        Matcher PROTEIN_match = PROTEIN.matcher(sequence);
        if(DNA_match.find()) {
            type = "DNA";
        } else if(RNA_match.find()) {
            type = "RNA";
        } else if(PROTEIN_match.find()) {
            type = "PROTEIN";
        } else {
            throw new IllegalArgumentException("NoValidSeq");
        }
    }

    /**
     * Sets the colors of the sequence
     * @throws IllegalArgumentException if the sequence is not DNA, RNA, or protein
     */
    public static void setColors() {
        // set the colors of the sequence
        for(int i = 0; i <= length-1; i++) {
            String letter = sequence.substring(i, i+1);
            if(DNA.matcher(sequence).find()) {
                dnaColor(letter);
            }
            if(RNA.matcher(sequence).find()) {
                rnaColor(letter);
            }
            if(PROTEIN.matcher(sequence).find()) {
                protColor(letter);
            } else {
                throw new IllegalArgumentException("Invalid sequence type; " + sequence+ "\n type: '" + type + "'");
            }
        }
    }

    /**
     * Determines the colors of the nucleotide and adds them to the colors list
     * @param nucleotide The nucleotide to be colored
     */
    private static void dnaColor(String nucleotide){
        /*
        determines the colors of the nucleotide and adds them to the colors list
        @param nucleotide The nucleotide to be colored
         */
        switch (nucleotide) {
            case "A", "T" -> colors.add(Color.YELLOW);
            case "C", "G" -> colors.add(Color.RED);
            default -> colors.add(Color.BLACK);
        }
    }

    /**
     * Determines the colors of the nucleotide and adds them to the colors list
     * @param nucleotide the nucleotide to be colored
     */
    private static void rnaColor(String nucleotide){
        /*
            Determines the colors of the nucleotide and adds them to the colors list
            @param nucleotide The nucleotide to be colored
         */
        switch (nucleotide) {
            case "A", "U" -> colors.add(Color.BLUE);
            case "C", "G" -> colors.add(Color.RED);
            default -> colors.add(Color.BLACK);
        }
    }

    /**
     * Determines the colors of the residue and adds them to the colors list
     * @param residue The residue to be colored
     */
    private static void protColor(String residue){
        /*
        determines the colors of the residue and adds them to the colors list
        @param residue The residue to be colored
         */
        switch (residue) {
            case "D", "E", "R", "K", "H" -> colors.add(Color.BLUE);
            case "N", "Q", "S", "T", "Y" -> colors.add(Color.GREEN);
            default -> colors.add(Color.gray);
        }
    }

    /**
     * Returns the sequence
     * @return sequence The sequence
     */
    public static String getSeq() {
        /*
            returns the sequence
           @return sequence
         */
        return sequence;
    }

    /**
     * Sets the sequence
     * @param seq the sequence to be set
     */
    public static void setSeq(String seq) {
        /*
         sets the sequence
         @param seq The sequence to be set
         */
        sequence = seq;
    }

    /**
     * Returns the length of the sequence
     * @return the length of the sequence
     */
    public static int getLength() {
        /*
            returns the length of the sequence
            @return length
         */
        return length;
    }

    /**
     * Returns the color of a specific nucleuotide/residue
     * @param i the index of the nucleotide/residue
     * @return the color of the nucleotide/residue
     */
    public static Color getColor(int i){
        /*
        returns the color at index i
        @param i The index of the color
        @return the color at index i
         */
        return colors.get(i);
    }

    /**
     * Returns the colored list of the sequence
     * @return colors The list of colors
     */
    public static List<Color> getColorList() {
        /*
        returns the list of colors
        @return colors The list of colors
         */
        return colors;
    }

    /**
     * Returns the GC% of the sequence
     * @return The GC percentage of the sequence, provided it is DNA
     * @throws IllegalArgumentException if the sequence is not DNA
     */
    public static Float getGCperc() {
        /*
        returns the GC percentage of the sequence
        @return gc The GC percentage of the sequence
         */
        if (PROTEIN.matcher(sequence).find()) {
            throw new IllegalArgumentException("Invalid sequence type; " + sequence+ "\n type: '" + type + "'");
        }
        long gc = sequence.chars().filter(ch -> ch == 'G' || ch == 'C').count();
        return (float) gc / length;
    }
}