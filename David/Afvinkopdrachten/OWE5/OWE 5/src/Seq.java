import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The Seq class, which contains the sequence and its properties.
 */
abstract class Seq {
    public abstract void setColor(String symbol);
    public abstract Color getColor(int i);
    static String sequence;
    static int length;
    static String seqname;
    static java.util.List<Color> colors = new ArrayList<>();


    /**
     * The constructor for the Seq class
     * @param seqname The name of the sequence
     * @param sequence The sequence itself
     */
    public Seq(String seqname, String sequence) {

        Seq.seqname = seqname;
        Seq.sequence = sequence;
        setLength();
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
    }

    /**
     * Sets the length of the sequence
     */
    private static void setLength() {
        length = sequence.length();
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


}

class DNA extends Seq {

    /** Returns the color of the nucleotide at the given index
     * @param i The index of the nucleotide
     * @return The color of the nucleotide
     */
    public Color getColor(int i) {
        return colors.get(i);
    }

    @Override
    public void setColor(String nucleotide) {
        switch (nucleotide) {
            case "A", "T" -> colors.add(Color.YELLOW);
            case "C", "G" -> colors.add(Color.RED);
            default -> colors.add(Color.BLACK);
        }
    }

    /**
     * The constructor for the Seq class
     *
     * @param seqname  The name of the sequence
     * @param sequence The sequence itself
     */
    public DNA(String seqname, String sequence) {
        super(seqname, sequence);
        for(int i = 0; i < length; i++){
            setColor(sequence.substring(i, i+1));
        }
    }

    /**
     * @return gc The GC percentage of the sequence
     */
    public static Float getGCperc() {
        /*
        returns the GC percentage of the sequence
        @return gc The GC percentage of the sequence
         */
        long gc = sequence.chars().filter(ch -> ch == 'G' || ch == 'C').count();
        return (float) gc / length;
    }
}

class RNA extends Seq {

    /**
     * @param i The index of the nucleotide
     * @return The color of the nucleotide
     */
    @Override
    public Color getColor(int i) {
        return colors.get(i);
    }

    /**
     * The constructor for the Seq class
     *
     * @param seqname  The name of the sequence
     * @param sequence The sequence itself
     */
    public RNA(String seqname, String sequence) {
        super(seqname, sequence);
        for(int i = 0; i < length; i++){
            setColor(sequence.substring(i, i+1));
        }
    }

    /**
     * @param nucleotide The nucleotide to be colored
     */
    public void setColor(String nucleotide){
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
}


class Protein extends Seq {

    /**
     * @param i The index of the residue
     * @return The color of the residue
     */
    @Override
    public Color getColor(int i) {
        return colors.get(i);
    }

    /**
     * The constructor for the Seq class
     *
     * @param seqname  The name of the sequence
     * @param sequence The sequence itself
     */
    public Protein(String seqname, String sequence) {
        super(seqname, sequence);
        for(int i = 0; i < length; i++){
            setColor(sequence.substring(i, i+1));
        }
    }

    /**
     * @param residue The residue to be colored
     */
    public void setColor(String residue){
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

}