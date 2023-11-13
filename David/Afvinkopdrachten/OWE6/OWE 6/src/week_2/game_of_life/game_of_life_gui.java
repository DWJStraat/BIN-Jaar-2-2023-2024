package week_2.game_of_life;

import headacheRemoval.createWindow;

import javax.swing.*;

import java.awt.*;


public class game_of_life_gui extends Thread {
    private static int resolution = 10;
    private static createWindow window = new createWindow("Game of Life", 300, 200);
    private static JPanel canvas = new JPanel();
    private static Graphics2D graphics;
    private static JTextField seed = new JTextField();
    private static JTextField x = new JTextField();
    private static JTextField y = new JTextField();
    private static game_of_life game;

    public static void main(String[] args) {
        build();
    }
    private static void build(){
        JButton start = new JButton("Start");
        start.addActionListener(e -> {
            try {
                generate();
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        });
        window.add(new JLabel("Seed: "), 10, 0, 100, 20);
        window.add(seed, 10, 20, 200, 20);
        window.add(new JLabel("X: "), 10, 40, 100, 20);
        window.add(x, 10, 60, 100, 20);
        window.add(new JLabel("Y: "), 10, 80, 100, 20);
        window.add(y, 10, 100, 100, 20);
        window.add(start, 10, 130, 100, 20);
        window.show();
    }

    private static void generate() throws InterruptedException {
        int width = Integer.parseInt(x.getText());
        int height = Integer.parseInt(y.getText());
        window = new createWindow("Game of Life", width*resolution, height*resolution);
        window.add(canvas, 0, 0, width*resolution, height*resolution);
        window.show();
        game();
    }

    private static void game() throws InterruptedException {
        game = new game_of_life(Integer.parseInt(x.getText()), Integer.parseInt(y.getText()), Long.parseLong(seed.getText()));
        graphics =  (Graphics2D) canvas.getGraphics();
        boolean living = true;
        while(living) {
            game.round();
            boolean[][] board = game.getBoard();
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    int pos_x = i * resolution;
                    int pos_y = j * resolution;
                    if (board[i][j]) {
                        graphics.setColor(Color.BLACK);

                    } else {
                        graphics.setColor(Color.WHITE);
                    }
                    graphics.fillRect(pos_x, pos_y, resolution, resolution);
                }
            }
            System.out.println(game.getAlive_count());

        }

    }


}
