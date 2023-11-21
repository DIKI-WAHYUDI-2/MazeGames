import javax.swing.*;
import java.awt.*;

public class Maze extends JPanel implements Runnable {

    private static char[][] maze = {
            {'.','0','0','0','0','0','0','0','0','0','0','0','0'},
            {'.','.','0','.','0','.','0','.','.','.','.','.','0'},
            {'0','.','0','.','.','.','0','.','0','0','0','.','0'},
            {'0','.','.','.','0','0','0','.','.','.','.','.','0'},
            {'0','.','0','.','.','.','.','.','0','0','0','.','0'},
            {'0','.','0','.','0','0','0','.','0','.','.','.','0'},
            {'0','.','0','.','0','.','.','.','0','0','0','.','0'},
            {'0','.','0','.','0','0','0','.','0','.','0','.','0'},
            {'0','.','.','.','.','.','.','.','.','.','0','.','0'},
            {'0','0','0','0','0','0','0','0','0','0','0','.','.'}
    };

    private static boolean[][] visited;

    private int sleepTime = 2000; // Waktu tunggu setiap langkah (dalam milidetik)
    private int blockSize = 30; // Ukuran setiap sel dalam piksel
    private int columns = maze[0].length;
    private int rows = maze.length;

    public Maze() {
        setPreferredSize(new Dimension(blockSize * columns, blockSize * rows));
        visited = new boolean[rows][columns];
        new Thread(this).start();
    }

    @Override
    synchronized protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        redrawMaze(g);
    }

    void redrawMaze(Graphics g) {
        int w = getWidth() / columns; // Lebar setiap sel
        int h = getHeight() / rows; // Tinggi setiap sel

        for (int j = 0; j < columns; j++) {
            for (int i = 0; i < rows; i++) {
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
            Thread.sleep(1000); // Tunggu sejenak sebelum memulai
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Mulai penelusuran dari titik awal (0, 0)
        if (depthFirstSearch(0, 0)) {
            System.out.println("SOLVED maze");
        } else {
            System.out.println("Could NOT SOLVE maze");
        }

        repaint();
    }

    public boolean isValidSpot(int r, int c) {
        if (r >= 0 && r < rows && c >= 0 && c < columns) {
            return maze[r][c] == '.';
        }
        return false;
    }

    public boolean depthFirstSearch(int r, int c) {

        if (isValidSpot(r, c) && !visited[r][c]) {
            visited[r][c] = true;

            if (r == rows - 1 && c == columns - 1) {
                maze[r][c] = '*';
                return true;
            }

            maze[r][c] = '*';
            repaint();
            sleep();  // Let the repaint take effect

            // Up
            if (depthFirstSearch(r - 1, c)) {
                return true;
            }

            // Right
            if (depthFirstSearch(r, c + 1)) {
                return true;
            }

            // Down
            if (depthFirstSearch(r + 1, c)) {
                return true;
            }

            // Left
            if (depthFirstSearch(r, c - 1)) {
                return true;
            }

            maze[r][c] = ' '; // Backtrack
            visited[r][c] = false;
            repaint();
            sleep();  // Let the repaint take effect
        }
        return false;
    }

    private void sleep() {
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        JFrame window = new JFrame("Maze Solver");
        window.setContentPane(new Maze());
        window.setSize(400, 400); // Atur ukuran jendela sesuai kebutuhan
        window.setLocation(120, 80);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }
}
