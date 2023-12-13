package week_6;

public class fasta {
    public fasta (String input){
        if (input.isEmpty()){
            throw new RuntimeException("Invalid input");
        }
        else if (input.charAt(0) != '>'){
            throw new RuntimeException("Invalid input");
        }
        else{
            read(input);
        }
    }

    public String header;
    public String sequence;

    private void read(String input){
        String[] lines = input.split("\n");
        header = lines[0];
        StringBuilder seq = new StringBuilder();
        for (int i = 1; i < lines.length; i++){
            seq.append(lines[i]);
        }
        sequence = seq.toString();
    }

    public String toString(){
        return sequence;
    }

    public String substring(int start, int end){
        return sequence.substring(start, end);
    }

    public String start(int length){
        return sequence.substring(0, length);
    }

    public String end(int length){
        return sequence.substring(sequence.length() - length);
    }

    public int length(){
        return sequence.length();
    }
}
