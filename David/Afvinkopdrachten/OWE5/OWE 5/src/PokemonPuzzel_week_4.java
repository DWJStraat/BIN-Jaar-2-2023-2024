/**
 * PokemonPuzzel Java code voor Kahoot! Week 4 objecten
 */

public class PokemonPuzzel_week_4 {
    // Reconstructed from java theory class.
    public static void main(String[] args) {
        Pokemon p1 = new Pokemon("Ivysaur", 60, 62);
        Pokemon p2 = new Pokemon("Charizard", 78 , 84);
        Pokemon p3 = new Pokemon("Charizard", 78, 130);
        Pokemon p4 = new Pokemon();
        System.out.println(" 1." + Pokemon.aantal);
        System.out.println(" 2." + p1.getNaam());
        System.out.println(" 3." + p2.getAttack());
        String s1 = new String("Charizard");
        System.out.println(" 4." + (p3.getNaam() == s1));
        System.out.println(" 5." + (p2.getAttack() == p3.getAttack()));
        Pokemon p5 = p1;
        System.out.println(" 6." + (p1 == p5));
        System.out.println(" 7." + p4.getNaam());
        p4.setNaam("Sandslash");
        System.out.println(" 8." + p4.getNaam());
        for (int i = 0; i < 7; i++) {
            p1 = new Pokemon();
        }
        System.out.println(" 9." + Pokemon.aantal);
        System.out.println("10." + p5.getId());
        System.out.println();
    }
}

class Pokemon {
    public static int aantal = 0;
    private String naam;
    private int id, attack, defense;

    public Pokemon() {
        this("Bulbasaur", 45, 49);
    }

    public Pokemon(String naam, int attack, int defense) {
        this.naam = naam;
        this.attack = attack;
        this.defense = defense;
        aantal++;
        id = aantal;
    }

    public String getNaam() {
        return naam;
    }
    public void setNaam(String naam) {
        this.naam = naam;
    }
    public int getAttack() {
        return attack;
    }
    public void setAttack(int attack) {
        this.attack = attack;
    }
    public int getDefense() {
        return defense;
    }
    public void setDefense(int defense) {
        this.defense = defense;
    }
    public int getId() {
        return id;
    }
}
