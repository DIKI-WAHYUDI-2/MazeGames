import javax.swing.*;
import java.awt.*;

public class Coba extends JPanel {

    private int[][] maze;
    private int playerX, playerY;  // Posisi pemain
    private static final int PLAYER_SIZE = 20;
    public Coba(int[][] maze){
        this.maze = maze;

        findPlayerStartPosition();
    }

    private void findPlayerStartPosition() {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                if (maze[i][j] == 0) {
                    playerX = j * PLAYER_SIZE;
                    playerY = i * PLAYER_SIZE;
                    return;
                }
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);

        for (int row = 0; row < maze.length; row++) {
            for (int col = 0; col < maze[0].length; col++) {

                switch (maze[row][col]){
                    case 1:
                        g.setColor(Color.GREEN);
                        g.fillRect(col * PLAYER_SIZE, row * PLAYER_SIZE, PLAYER_SIZE, PLAYER_SIZE);
                        break;
                    case 9:
                        g.setColor(Color.RED);
                        g.fillRect(col * PLAYER_SIZE, row * PLAYER_SIZE, PLAYER_SIZE, PLAYER_SIZE);
                        break;
                    default:
                        g.setColor(Color.WHITE);
                        g.fillRect(col * PLAYER_SIZE, row * PLAYER_SIZE, PLAYER_SIZE, PLAYER_SIZE);
                        break;
                }

                if (maze[row][col] == 0){
                    g.setColor(Color.BLACK);
                    g.drawRect(col * PLAYER_SIZE, row * PLAYER_SIZE, PLAYER_SIZE, PLAYER_SIZE);
                }

            }
        }
        g.setColor(Color.BLUE);
        g.fillRect(playerX, playerY, PLAYER_SIZE, PLAYER_SIZE);
    }

    public static void main(String[] args) {

        int[][] maze = {
                {1,1,1,1,1,1,1,1,1,1,1,1,1},
                {1,0,1,0,1,0,1,0,0,0,0,0,1},
                {1,0,1,0,0,0,1,0,1,1,1,0,1},
                {1,0,0,0,1,1,1,0,0,0,0,0,1},
                {1,0,1,0,0,0,0,0,1,1,1,0,1},
                {1,0,1,0,1,1,1,0,1,0,0,0,1},
                {1,0,1,0,1,0,0,0,1,1,1,0,1},
                {1,0,1,0,1,1,1,0,1,0,1,0,1},
                {1,0,0,0,0,0,0,0,0,0,1,9,1},
                {1,1,1,1,1,1,1,1,1,1,1,1,1}
        };

        Coba coba = new Coba(maze);
        JFrame frame = new JFrame("Game Labirin");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(maze[0].length * PLAYER_SIZE,maze.length * PLAYER_SIZE);
        frame.add(new Coba(maze));
        frame.setVisible(true);
    }
}
