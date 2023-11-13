package week_2.game_of_life;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;

public class game_of_life {
    private Random random;
    private boolean[][] board;
    private int x;
    private int y;
    private int alive_count;
    public game_of_life(int x_in, int y_in, long seed) {
        x = x_in;
        y = y_in;
        setup_board(seed);

    }

    private void setup_board(long seed){
        random = new Random(seed);
        board = new boolean[x][y];
        for(int i = 0; i < x; i++){
            for(int j = 0; j < y; j++) {
                boolean state = random.nextBoolean();
                board[i][j] = state;
            }
        }
    }

    public void round(){
        boolean [][] alive_neighbours = new boolean[x][y];
        int[][] neighbours = new int[x][y];
        alive_count = 0;
        for(int i=0;i<x;i++){
            for(int j=0;j<y;j++){
                boolean status = board[i][j];
                int alive = get_alive_neighbours(i,j);
                neighbours[i][j] = alive;
                if(status){
                    alive_neighbours[i][j] = alive == 2 || alive == 3;
                    alive_count++;
                }
                else {
                    alive_neighbours[i][j] = alive == 3;
                }
            }
        }
        System.out.println(Arrays.deepToString(neighbours));
        board = alive_neighbours;
    }

    private int get_alive_neighbours(int xPos, int yPos){
        int alive = 0;
        for(int i = xPos-1; i < xPos+1; i++){
            for(int j = yPos-1; j < yPos+1; j++){
                if((i == xPos && j == yPos) || i < 0 || j < 0 || i > x-1 || j > y-1){
                    continue;
                }

                if(board[i][j]){
                    alive++;
                }

            }
        }
        return alive;
    }

    public String exportBoard(){
        StringBuilder output = new StringBuilder();
        for(int i = 0; i < x; i++){
            for(int j = 0; j < y; j++) {
                output.append(board[i][j] ? "1" : "0");
            }
            output.append("\n");
        }
        return output.toString();
    }
    public boolean[][] getBoard(){
        return board;
    }
    public int getAlive_count(){
        return alive_count;
    }

}
