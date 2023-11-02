package Oefentoets_kans1_2018;

public class sequence {
    private String name;
    private String accessionCode;
    private int beginPosition;
    private int endPosition;
    private String sequence;

    /**
     * @return The name of the sequence
     */
    public String getName() {
        return name;
    }

    /**
     * @return The accession code of the sequence
     */
    public String getAccessionCode() {
        return accessionCode;
    }

    /**
     * @return The begin position of the sequence
     */
    public int getBeginPosition() {
        return beginPosition;
    }

    /**
     * @return The end position of the sequence
     */
    public int getEndPosition() {
        return endPosition;
    }

    /**
     * @return The sequence
     */
    public String getSequence() {
        return sequence;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAccessionCode(String accessionCode) {
        this.accessionCode = accessionCode;
    }

    public void setBeginPosition(int beginPosition) {
        this.beginPosition = beginPosition;
    }
    public void setEndPosition(int endPosition) {
        this.endPosition = endPosition;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public sequence(String name, String accessionCode, int beginPosition, int endPosition, String sequence) {
        if (!sequence.matches("^[ATCG]+$") || !accessionCode.startsWith(">")) {
            throw new IllegalArgumentException("Invalid file format");
        }
        this.name = name;
        this.accessionCode = accessionCode;
        this.beginPosition = beginPosition;
        this.endPosition = endPosition;
        this.sequence = sequence;
    }

    public float getGcPercentage() {
        int gc = 0;
        for (int i = beginPosition; i < endPosition; i++) {
            if (sequence.charAt(i) == 'G' || sequence.charAt(i) == 'C') {
                gc++;
            }
            else if (sequence.charAt(i) != 'A' && sequence.charAt(i) != 'T') {
                throw new IllegalArgumentException("Invalid sequence");
            }
        }
        return (float) gc / endPosition-beginPosition;
    }

    public boolean isDna() {
        return sequence.matches("^[ATCG]+$");
    }

}
