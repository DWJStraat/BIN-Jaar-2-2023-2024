package week_2;

public class week_2 {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        game_of_life game = new game_of_life(10, 10, 1);
        System.out.println(game.exportBoard());
        System.out.println("-----");
        game.round();
        System.out.println(game.exportBoard());


    }
}
