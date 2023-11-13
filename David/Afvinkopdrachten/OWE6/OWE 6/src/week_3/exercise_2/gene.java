package week_3.exercise_2;

public class gene implements Comparable<gene>{
    int ID;
    String name;
    String[] alternatives;
    String chromosome;
    String[] location;
    String function;
    String type;
    String description;
    public gene (String line){
        String[] input = line.split("\t");
        ID = Integer.parseInt(input[0]);
        name = input[1];
        alternatives = input[2].split("|");
        chromosome = input[3];
        location = input[4].split("|");
        function = input[5];
        type = input[6];
    }

    public String toStrong(){
        return name;
    }

    @Override
    public int compareTo(gene o) {
        int chromosome1 = Enumerate(chromosome);
        int chromosome2 = Enumerate(o.chromosome);
        return Integer.compare(chromosome1, chromosome2);
    }

    private int Enumerate(String chromosome) {
        int chromosome1;
        switch (chromosome) {
            case "X" -> chromosome1 = 23;
            case "Y" -> chromosome1 = 24;
            case "X|Y" -> chromosome1 = 25;
            case "-" -> chromosome1 = 0;
            default -> chromosome1 = Integer.parseInt(chromosome);
        }
        return chromosome1;
    }
}
