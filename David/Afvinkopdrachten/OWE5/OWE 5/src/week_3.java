import javax.swing.*;
import java.util.Objects;

public class week_3 {
    public static void main(String[] args) {
        JFrame frame = build_screen();

    }

    public static JFrame build_screen(){
        JFrame frame= new JFrame("Tic-Tac-Toe - O starts");
        Turn player = new Turn();
        set_board(frame, player);
        frame.setSize(500,500);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setVisible(true);
        return frame;
    }

    public static void set_board(JFrame frame, Turn player){
        Board tictactoe = new Board();
        tictactoe.print_board();
        add_row(99,30,100,frame, player, tictactoe);
        add_row(99,130,100,frame,   player, tictactoe);
        add_row(99,230,100,frame,  player, tictactoe);

    }

    public static void add_row(int x, int y, int length, JFrame frame, Turn player, Board tictactoe){


        for (int i = 0; i < 3; i++) {
            add_button(x,y,frame, player, tictactoe, length);
            x += length;
        }
    }

    public static void add_button(int x, int y, JFrame frame, Turn player, Board tictactoe, int length){
        JButton button = new JButton();
        button.setBounds(x,y,length,length);
        button.addActionListener(e -> {
            frame.setTitle("Tic-Tac-Toe - " + player.turn + "'s turn");
            Turn.main(player);
            button.setText(player.turn);
            button.setEnabled(false);

            tictactoe.add_turn(x/100,y/100,player.turn);
            tictactoe.print_board();
            if(tictactoe.check_win()){
                win(player.turn, frame);
                System.out.println(player.turn + "wins");

            }
        });
        frame.add(button);
    }

    public static void win(String player, JFrame frame) {
        JOptionPane.showMessageDialog(null, player + " wins!");
        frame.dispose();
        build_screen();

    }

}

class Turn {
    public String turn = "X";
    public static void main(Turn player) {
        String turn = player.turn;
        System.out.println(turn);

        if (Objects.equals(turn, "X")){
            player.turn = "O";
        }
        else {
            player.turn = "X";
        }

    }


}

class Board {
    public String[][] board = new String[3][3];
    public Board() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j<3; j++){
                board[i][j] = " ";
            }
        }
    }
    public void print_board(){
        for (int i = 0; i < 3; i++) {
            System.out.println(board[i][0] + board[i][1] + board[i][2]);
        }
    }
    public void add_turn(int x, int y, String turn){
        System.out.println(x + " " + y);
        if (x < 0) {
            x = 0;
        }
        if (y < 0){
            y = 0;
        }

        board[x][y] = turn;
    }
    public boolean check_win(){
        for (int i = 0; i < 3; i++) {
            if (board[i][0].equals(board[i][1]) && board[i][1].equals(board[i][2]) &&
                    !Objects.equals(board[i][0], " ")){
                return true;
            }
            else if (board[0][i].equals(board[1][i]) && board[1][i].equals(board[2][i]) &&
                    !Objects.equals(board[0][i], " ")){
                return true;
            }
        }
        if (board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2]) &&
                !Objects.equals(board[0][0], " ")) {
            return true;
        }
        else return board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0]) &&
                !Objects.equals(board[0][2], " ");
    }
}