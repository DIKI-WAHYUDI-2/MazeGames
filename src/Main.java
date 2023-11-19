import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Main extends JFrame {

    public int [][] maze = {

            {1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,0,1,0,1,0,1,0,0,0,0,0,1},
            {1,0,1,0,0,0,1,0,1,1,1,0,1},
            {1,0,0,0,1,1,1,0,0,0,0,0,1},
            {1,0,1,0,0,0,0,0,1,1,1,0,1},
            {1,0,1,0,1,1,1,0,1,0,0,0,1},
            {1,0,1,0,1,0,0,0,1,1,1,0,1},
            {1,0,1,0,1,1,1,0,1,0,1,0,1},
            {1,0,0,0,0,0,0,0,0,0,1,0,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1}

    };

    private Stack<Integer> path = new Stack<>();
    private int pathIndex;
    public Main(){
        setTitle("Maze");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(640, 480);
        setLocationRelativeTo(null);

        DepthFirst.searchPath(maze,1,1, path);
        pathIndex = path.size() - 2;
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

//        for (int p = 0; p < path.size(); p += 2) {
//            int pathX = path.get(p);
//            int pathY = path.get(p + 1);
//            g.setColor(Color.BLUE);
//            g.fillRect(pathX * 30, pathY * 30, 30, 30);
//        }

        int pathX = path.get(pathIndex);
        int pathY = path.get(pathIndex + 1);
        g.setColor(Color.RED);
        g.fillOval(pathX * 30, pathY * 30, 30, 30);
    }

    @Override
    protected void processKeyEvent(KeyEvent e) {
        if(e.getID() != KeyEvent.KEY_PRESSED){
            return;
        }
        if (e.getKeyCode() ==  KeyEvent.VK_RIGHT){
            pathIndex -= 2;
            if (pathIndex < 0){
                pathIndex = 0;
            }
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            pathIndex += 2;
            if (pathIndex > path.size() - 2){
                pathIndex = path.size() - 2;
            }
        }
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Main main = new Main();
                main.setVisible(main.rootPaneCheckingEnabled);
            }
        });
    }

}