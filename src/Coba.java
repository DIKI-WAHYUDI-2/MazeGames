import javax.swing.*;
import java.awt.*;

public class Coba extends JPanel {

    private int[][] maze;
    public Coba(int[][] maze){
        this.maze = maze;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);

        for (int row = 0; row < maze.length; row++) {
            for (int col = 0; col < maze[0].length; col++) {

                switch (maze[row][col]){
                    case 1:
                        g.setColor(Color.GREEN);
                        g.fillRect(col * 30, row * 30, 30, 30);
                        break;
                    case 9:
                        g.setColor(Color.RED);
                        g.fillRect(col * 30, row * 30, 30, 30);
                        break;
                    default:
                        g.setColor(Color.WHITE);
                        g.fillRect(col * 30, row * 30, 30, 30);
                        break;
                }

                if (maze[row][col] == 0){
                    g.setColor(Color.BLACK);
                    g.drawRect(col * 30, row * 30, 30, 30);
                }
            }
        }
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
        frame.setSize(100,100);
        frame.add(coba);
        frame.setVisible(true);
    }
}
