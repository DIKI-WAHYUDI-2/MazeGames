import javax.swing.*;
import java.awt.*;
import java.util.Stack;

public class MazeS extends JFrame {

    private static final int SIZE = 10;
    private static final int CELL_SIZE = 40;
    private static final int SLEEP_TIME = 100;

    private int[][] maze = {
            {0, 1, 0, 0, 0, 1, 0, 1, 0, 0},
            {0, 1, 1, 1, 1, 1, 0, 1, 1, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
            {0, 1, 1, 1, 1, 1, 1, 1, 1, 0},
            {0, 1, 0, 0, 0, 1, 0, 0, 0, 0},
            {0, 1, 1, 1, 0, 1, 1, 1, 1, 0},
            {0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
            {0, 1, 1, 1, 0, 1, 1, 1, 1, 0},
            {0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
            {0, 1, 1, 1, 1, 1, 1, 1, 1, 0}
    };

    private int[][] solution;
    private Stack<Point> path;

    private Point start = new Point(0, 0);
    private Point finish = new Point(SIZE - 1, SIZE - 1);

    public MazeS() {
        setTitle("Maze Solver");
        setSize(CELL_SIZE * SIZE, CELL_SIZE * SIZE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        solution = new int[SIZE][SIZE];
        path = new Stack<>();

        solveMaze(start.x, start.y);

        MazePanel mazePanel = new MazePanel();
        add(mazePanel);

        setVisible(true);
    }

    private boolean solveMaze(int x, int y) {
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE || maze[x][y] == 1 || solution[x][y] == 1) {
            return false;
        }

        path.push(new Point(x, y));
        solution[x][y] = 1;

        try {
            Thread.sleep(SLEEP_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (x == finish.x && y == finish.y) {
            animateSolution();
            return true;
        }

        if (solveMaze(x + 1, y) || solveMaze(x, y + 1) || solveMaze(x - 1, y) || solveMaze(x, y - 1)) {
            return true;
        }

        // Backtrack
        path.pop();
        solution[x][y] = 0; // Reset the solution for backtracked cell
        return false;
    }

    private void animateSolution() {
        for (Point point : path) {
            try {
                Thread.sleep(SLEEP_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            solution[point.x][point.y] = 2; // Mark the solution path
            repaint();
        }
    }

    private class MazePanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    if (maze[i][j] == 1) {
                        g.setColor(Color.BLACK);
                    } else if (solution[i][j] == 1) {
                        g.setColor(Color.BLUE);
                    } else if (solution[i][j] == 2) {
                        g.setColor(Color.GREEN);
                    } else if (i == start.x && j == start.y) {
                        g.setColor(Color.RED); // Warna start
                    } else if (i == finish.x && j == finish.y) {
                        g.setColor(Color.ORANGE); // Warna finish
                    } else {
                        g.setColor(Color.WHITE);
                    }

                    g.fillRect(j * CELL_SIZE, i * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                    g.setColor(Color.BLACK);
                    g.drawRect(j * CELL_SIZE, i * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MazeS());
    }
}
