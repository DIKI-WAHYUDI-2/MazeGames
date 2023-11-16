import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;

public class MazeGame extends JPanel {
    private int playerX, playerY;  // Posisi pemain
    private static final int PLAYER_SIZE = 20;
    private int[][] maze;  // Labirin

    public MazeGame(int[][] maze) {
        this.maze = maze;

        // Cari posisi awal pemain
        findPlayerStartPosition();

        // Tambahkan KeyListener ke JPanel
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                handleKeyPress(e);
            }
        });
        setFocusable(true);
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

    private void handleKeyPress(KeyEvent e) {
        int keyCode = e.getKeyCode();
        int moveAmount = PLAYER_SIZE;

        int newPlayerX = playerX;
        int newPlayerY = playerY;

        switch (keyCode) {
            case KeyEvent.VK_UP:
                newPlayerY -= moveAmount;
                break;
            case KeyEvent.VK_DOWN:
                newPlayerY += moveAmount;
                break;
            case KeyEvent.VK_LEFT:
                newPlayerX -= moveAmount;
                break;
            case KeyEvent.VK_RIGHT:
                newPlayerX += moveAmount;
                break;
        }

        // Cek apakah langkah tersebut valid
        if (isValidMove(newPlayerX, newPlayerY)) {
            playerX = newPlayerX;
            playerY = newPlayerY;
        }

        // Panggil repaint() untuk memperbarui tampilan
        repaint();
    }

    private boolean isValidMove(int x, int y) {
        // Cek apakah posisi baru di dalam batas labirin dan tidak bertabrakan dengan tembok
        return x >= 0 && x < getWidth() && y >= 0 && y < getHeight() &&
                maze[y / PLAYER_SIZE][x / PLAYER_SIZE] != 1;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Gambar labirin
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                if (maze[i][j] == 1) {
                    // Gambar tembok
                    g.setColor(Color.BLACK);
                    g.fillRect(j * PLAYER_SIZE, i * PLAYER_SIZE, PLAYER_SIZE, PLAYER_SIZE);
                } else if (maze[i][j] == 9) {
                    // Gambar tujuan
                    g.setColor(Color.RED);
                    g.fillRect(j * PLAYER_SIZE, i * PLAYER_SIZE, PLAYER_SIZE, PLAYER_SIZE);
                }
            }
        }

        // Gambar pemain
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

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Maze Game");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(maze[0].length * PLAYER_SIZE, maze.length * PLAYER_SIZE);
            frame.add(new MazeGame(maze));
            frame.setVisible(true);
        });
    }
}

