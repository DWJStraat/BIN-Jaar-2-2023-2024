package week_5;

import java.util.HashMap;

public class hashmaps {
    public HashMap<String, String> map = new HashMap<>();
    public hashmaps(int length){
        if (length < 1){
            throw new RuntimeException("Invalid length");
        }
        else if (length == 1){
            singleMap();
        }
        else if (length == 3){
            tripleMap();
        }
        else{
            multiMap();
        }
    }

    private void singleMap() {
        map.put("A", "Ala");
        map.put("R", "Arg");
        map.put("N", "Asn");
        map.put("D", "Asp");
        map.put("C", "Cys");
        map.put("E", "Glu");
        map.put("Q", "Gln");
        map.put("G", "Gly");
        map.put("H", "His");
        map.put("I", "Ile");
        map.put("L", "Leu");
        map.put("K", "Lys");
        map.put("M", "Met");
        map.put("F", "Phe");
        map.put("P", "Pro");
        map.put("S", "Ser");
        map.put("T", "Thr");
        map.put("W", "Trp");
        map.put("Y", "Tyr");
        map.put("V", "Val");
    }

private void tripleMap() {
        map.put("Asn", "N");
        map.put("Asp", "D");
        map.put("Glu", "E");
        map.put("Gln", "Q");
        map.put("His", "H");
        map.put("Ile", "I");
        map.put("Leu", "L");
        map.put("Lys", "K");
        map.put("Met", "M");
        map.put("Phe", "F");
        map.put("Ser", "S");
        map.put("Thr", "T");
        map.put("Trp", "W");
        map.put("Tyr", "Y");
        map.put("Val", "V");
    }

private void multiMap() {
        map.put("Alanine", "A");
        map.put("Arginine", "R");
        map.put("Asparagine", "N");
        map.put("Aspartic Acid", "D");
        map.put("Cysteine", "C");
        map.put("Glutamic Acid", "E");
        map.put("Glutamine", "Q");
        map.put("Glycine", "G");
        map.put("Histidine", "H");
        map.put("Isoleucine", "I");
        map.put("Leucine", "L");
        map.put("Lysine", "K");
        map.put("Methionine", "M");
        map.put("Phenylalaninen", "F");
        map.put("Proline", "P");
        map.put("Serine", "S");
        map.put("Threonine", "T");
        map.put("Tryptophan", "W");
        map.put("Tyrosine", "Y");
        map.put("Valine", "V");
    }
}
