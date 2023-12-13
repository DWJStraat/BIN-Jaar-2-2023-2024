package week_6;

import java.util.Arrays;
import java.util.List;

public class graphBuilder {
    private final fasta[] input;
    private final int overlap;
    private List<String> edges = new java.util.ArrayList<>();
    public graphBuilder(fasta[] inputData, int overlapInput){
        input = inputData;
        overlap = overlapInput;
        for (fasta s : inputData){
            comparison(s);
        }

    }
    public void comparison(fasta original) {
        String originalStart = original.start(overlap);
        String originalEnd = original.end(overlap);
        for(fasta s: input){
            if (s == original){
                continue;
            }
            String sStart = s.start(overlap);
            String sEnd = s.end(overlap);
            if (originalStart.equals(sEnd) || originalEnd.equals(sStart)){
                if(edges.contains(original.header + " " + s.header) ||
                        edges.contains(s.header + " " + original.header)){
                    continue;
                }
                else{
                    edges.add(original.header + " " + s.header);
                }
            }

        }
    }

    public String toString(){
        StringBuilder output = new StringBuilder();
        for (String s : edges){
            output.append(s).append("\n");
        }
        return output.toString();
    }




}
