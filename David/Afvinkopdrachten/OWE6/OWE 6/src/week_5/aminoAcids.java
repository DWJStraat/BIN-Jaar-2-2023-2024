package week_5;

import java.util.HashMap;
import java.util.stream.Stream;

public class aminoAcids {
    private String output;
    private String input;

    public aminoAcids(String aminoAcid){
        int aminoLength = aminoAcid.length();
        input = aminoAcid;
        if (aminoLength == 1){
            output = singleAcid();
        }
        else if (aminoLength == 3){
            output = tripleAcid();
        }
        else if (aminoLength > 0){
            output = multiAcid();
        }
        else{
            throw new RuntimeException("Invalid amino acid");
        }
    }

    private String singleAcid(){
        HashMap<String,String> map = new hashmaps(1).map;
        return map.get(input);
    }


    private String tripleAcid(){
        HashMap<String,String> map = new hashmaps(3).map;
        return map.get(input);
    }

    private String multiAcid(){
        HashMap<String,String> map = new hashmaps(input.length()).map;
        return map.get(input);
    }

    public String toString(){
        if (output == null){
            throw new RuntimeException("Invalid amino acid");
        }
        return output;
    }
}
