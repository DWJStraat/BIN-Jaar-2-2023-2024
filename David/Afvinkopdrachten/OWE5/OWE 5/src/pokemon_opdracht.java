import javax.swing.plaf.synth.SynthTextAreaUI;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
public class pokemon_opdracht {
    private static final ArrayList<pokeclass> pokemon = new ArrayList<>();
    public static String HTML = "<html> <body>";
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner (new File("E:\\GitHub\\BIN-Jaar-2-2023-2024\\David\\Afvinkopdrachten\\OWE5\\OWE 5\\src\\pokemon.csv"))) {
            scanner.useDelimiter(",");
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] columns = line.split(",");
                if (!columns[0].equals("#")) {
                    int id = Integer.parseInt(columns[0]);
                    String name = columns[1];
                    String type1 = columns[2];
                    String type2 = columns[3];
                    int total = Integer.parseInt(columns[4]);
                    int HP = Integer.parseInt(columns[5]);
                    int attack = Integer.parseInt(columns[6]);
                    int defense = Integer.parseInt(columns[7]);
                    int special_attack = Integer.parseInt(columns[8]);
                    int special_defense = Integer.parseInt(columns[9]);
                    int speed= Integer.parseInt(columns[10]);
                    int generation = Integer.parseInt(columns[11]);
                    boolean legendary = Boolean.parseBoolean(columns[12]);
                    pokeclass poke = new pokeclass(id, name, type1, type2,total, HP, attack, defense, special_attack, special_defense, speed, generation, legendary);
                    pokemon.add(poke);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (pokeclass poke : pokemon) {
            HTML += poke.getColoredName();
            poke.printColoredName();
        }
        HTML += "</body></html>";
        System.out.println(HTML);


    }


}

class pokeclass {
    private final int id;
    private final String name;
    private final String type1;
    private final String type2;
    private final int HP;
    private final int attack;
    private final int defense;
    private final int special_attack;
    private final int special_defense;
    private final int speed;
    private final int total;
    private final int generation;
    private final boolean legendary;

    public pokeclass(int id, String name, String type1, String type2, int total, int HP, int attack, int defense, int special_attack, int special_defense, int speed, int generation, boolean legendary) {
        this.id = id;
        this.name = name;
        this.type1 = type1;
        this.type2 = type2;
        this.total = total;
        this.HP = HP;
        this.attack = attack;
        this.defense = defense;
        this.special_attack = special_attack;
        this.special_defense = special_defense;
        this.speed = speed;
        this.generation = generation;
        this.legendary = legendary;
    }
    public Color getColor() {
            int skill = attack+defense;
            int special = special_attack+ special_defense;
            int physical = HP+speed;
            int red = (int) Math.floor(skill/ 1.647058824);
            int green = (int) Math.floor(special/ 1.662745098);
            int blue = (int) Math.floor(physical/ 1.7058823529);
            return new Color(red, green, blue);
    }

    public String getName() {
        return name;
    }

    public void printColoredName(){
        System.out.println("<html><font color=" + getColor() + ">" + name + "</font></html>");
    }

    public String getColoredName(){
        return "<font color=" + getColor() + ">" + name + "</font><br>";
    }
}