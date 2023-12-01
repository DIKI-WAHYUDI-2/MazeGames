import javax.swing.*;

public class App {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame window = new JFrame("Maze Solver");
            MazeSolverPanel mazeSolverPanel = new MazeSolverPanel();
            MazeSolver mazeSolver = new MazeSolver(mazeSolverPanel.getMaze(), mazeSolverPanel);

            mazeSolverPanel.setMazeSolver(mazeSolver);

            window.setContentPane(mazeSolverPanel);
            window.setSize(400, 400);
            window.setLocation(120, 80);
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setVisible(true);

            new Thread(mazeSolverPanel).start();
        });
    }
}