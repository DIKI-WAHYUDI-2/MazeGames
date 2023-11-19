import javax.swing.*;
import java.awt.*;
import java.util.Stack;

public class MazeSolve extends JFrame {

    private static int[][] maze = {

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

    private static Stack<int[]> pathStack = new Stack<>();

    public MazeSolve(){
        setTitle("Maze");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(640, 480);
        setLocationRelativeTo(null);

        depthFirstSearch(maze,1,1);
    }

    private static void print(int[][] maze){
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                System.out.print(maze[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static boolean isValidMove(int[][] maze, int x, int y){
        if (x >= 0 && x < maze.length && y >= 0 && y < maze[0].length){
            return maze[x][y] == 0 || maze[x][y] == 9;
        }
        return false;
    }

    private static boolean depthFirstSearch(int[][] maze, int x, int y){
        if (isValidMove(maze, x, y)){

            if (maze[x][y] == 9){
                printPath();
                maze[x][y] = 2;
                return true;
            }

            maze[x][y] = 2;
            pathStack.push(new int[]{x,y});

            //moveUp
            boolean moveUp = depthFirstSearch(maze,x - 1,y);

            //moveDown
            if (!moveUp){
                moveUp = depthFirstSearch(maze, x + 1, y);
            }

            //moveRight
            if (!moveUp){
                moveUp = depthFirstSearch(maze, x, y + 1);
            }

            //moveLeft
            if (!moveUp){
                moveUp = depthFirstSearch(maze, x, y - 1);
            }
            pathStack.pop();
            return moveUp;
        }

        return false;
    }

    private static void markShortestPath() {
        // Tandai jalur terpendek dengan warna lain (misalnya, hijau)
        for (int[] step : pathStack) {
            if (maze[step[0]][step[1]] == 2) {
                maze[step[0]][step[1]] = 3; // Ganti nilai 2 menjadi 3
            }
        }
    }

    private static void printPath() {
        System.out.println("Solution Path:");
        for (int[] step : pathStack) {
            System.out.println("(" + step[0] + ", " + step[1] + ")");
        }
        System.out.println("--------------------");
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.translate(100,100);

        for (int row = 0; row < maze.length; row++) {
            for (int col = 0; col < maze[0].length; col++) {
                Color color;
                switch (maze[row][col]) {
                    case 1:
                        color = Color.BLACK;
                        break;
                    case 0:
                        color = Color.WHITE;
                        break;
                    case 9:
                        color = Color.RED;
                        break;
                    case 2:
                        color = Color.BLUE;
                        break;
                    default:
                        color = Color.WHITE;
                        break;
                }
                g.setColor(color);
                g.fillRect(col * 30, row * 30, 30, 30);
                g.setColor(Color.BLACK);
                g.drawRect(col * 30, row * 30, 30, 30);
            }
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MazeSolve main = new MazeSolve();
                main.setVisible(main.rootPaneCheckingEnabled);
            }
        });
    }

}
