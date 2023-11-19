import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MazeGame extends JFrame {
    private static final int ROWS = 10;
    private static final int COLS = 13;
    private JButton[][] buttons;
    private boolean[][] visited;
    private int[][] maze = {
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1},
            {1, 0, 1, 0, 0, 0, 1, 0, 1, 1, 1, 0, 1},
            {1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1},
            {1, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1},
            {1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 0, 0, 1},
            {1, 0, 1, 0, 1, 0, 0, 0, 1, 1, 1, 0, 1},
            {1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9, 0, 1}, // Changed exit to 9
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
    };
    private int delay = 200;

    public MazeGame() {
        super("Maze Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);

        initializeMaze();
        createGUI();

        setVisible(true);

        solveMaze(1, 0); // Start DFS automatically

        // Add key listener for space key
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    solveMaze(1, 0);
                }
            }
        });
        setFocusable(true);
    }

    private void initializeMaze() {
        buttons = new JButton[ROWS][COLS];
        visited = new boolean[ROWS][COLS];

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setBackground(maze[i][j] == 1 ? Color.BLACK : Color.WHITE);
                visited[i][j] = false;
            }
        }

        // Set entrance
        buttons[1][0].setBackground(Color.GREEN);
    }

    private void createGUI() {
        setLayout(new GridLayout(ROWS, COLS));

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                add(buttons[i][j]);
            }
        }
    }

    private void solveMaze(int row, int col) {
        if (row < 0 || col < 0 || row >= ROWS || col >= COLS || visited[row][col] || maze[row][col] == 1) {
            return;
        }

        visited[row][col] = true;
        buttons[row][col].setBackground(Color.CYAN); // DFS visualization effect

        Timer timer = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                solveMaze(row + 1, col); // Move down
                solveMaze(row - 1, col); // Move up
                solveMaze(row, col + 1); // Move right
                solveMaze(row, col - 1); // Move left
            }
        });
        timer.setRepeats(false);
        timer.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MazeGame());
    }
}
