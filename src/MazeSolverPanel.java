import javax.swing.*;
import java.awt.*;

public class MazeSolverPanel extends JPanel implements Runnable {
    private char[][] maze = {
            {'.', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0'},
            {'.', '.', '0', '.', '0', '.', '0', '.', '.', '.', '.', '.', '0'},
            {'0', '.', '0', '.', '.', '.', '0', '.', '0', '0', '0', '.', '0'},
            {'0', '.', '.', '.', '0', '0', '0', '.', '.', '.', '.', '.', '0'},
            {'0', '.', '0', '.', '.', '.', '.', '.', '0', '0', '0', '.', '0'},
            {'0', '.', '0', '.', '0', '0', '0', '.', '0', '.', '.', '.', '0'},
            {'0', '.', '0', '.', '0', '.', '.', '.', '0', '0', '0', '.', '0'},
            {'0', '.', '0', '.', '0', '0', '0', '.', '0', '.', '0', '.', '0'},
            {'0', '.', '.', '.', '.', '.', '.', '.', '.', '.', '0', '.', '0'},
            {'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '.', '.'}
    };

    private MazeSolver mazeSolver;

    public MazeSolverPanel() {
        setPreferredSize(new Dimension(30 * maze[0].length, 30 * maze.length));
    }

    public char[][] getMaze() {
        return maze;
    }

    public void setMazeSolver(MazeSolver mazeSolver) {
        this.mazeSolver = mazeSolver;
    }

    @Override
    synchronized protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        redrawMaze(g);
    }

    void redrawMaze(Graphics g) {
        int w = getWidth() / maze[0].length;
        int h = getHeight() / maze.length;

        for (int j = 0; j < maze[0].length; j++) {
            for (int i = 0; i < maze.length; i++) {
                Color color;
                switch (maze[i][j]) {
                    case '.':
                        color = Color.WHITE;
                        break;
                    case '0':
                        color = Color.BLACK;
                        break;
                    case '*':
                        color = Color.BLUE;
                        break;
                    default:
                        color = Color.WHITE;
                        break;
                }
                g.setColor(color);
                g.fillRect(j * w, i * h, w, h);
                g.setColor(Color.BLACK);
                g.drawRect(j * w, i * h, w, h);
            }
        }
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (mazeSolver.solveMaze()) {
            System.out.println("SOLVED maze");
        } else {
            System.out.println("Could NOT SOLVE maze");
        }

        repaint();
    }
}