public class MazeSolver {
    private char[][] maze;
    private boolean[][] visited;
    private MazeSolverPanel mazeSolverPanel;

    public MazeSolver(char[][] maze, MazeSolverPanel mazeSolverPanel) {
        this.maze = maze;
        this.visited = new boolean[maze.length][maze[0].length];
        this.mazeSolverPanel = mazeSolverPanel;
    }

    public boolean solveMaze() {
        return depthFirstSearch(0, 0);
    }

    private boolean isValidSpot(int r, int c) {
        if (r >= 0 && r < maze.length && c >= 0 && c < maze[0].length) {
            return maze[r][c] == '.';
        }
        return false;
    }

    private boolean depthFirstSearch(int r, int c) {
        if (isValidSpot(r, c) && !visited[r][c]) {
            visited[r][c] = true;

            if (r == maze.length - 1 && c == maze[0].length - 1) {
                maze[r][c] = '*';
                return true;
            }

            maze[r][c] = '*';
            mazeSolverPanel.repaint();
            sleep();

            if (depthFirstSearch(r - 1, c)) {
                return true;
            }

            if (depthFirstSearch(r, c + 1)) {
                return true;
            }

            if (depthFirstSearch(r + 1, c)) {
                return true;
            }

            if (depthFirstSearch(r, c - 1)) {
                return true;
            }

            maze[r][c] = ' ';
            visited[r][c] = false;
            mazeSolverPanel.repaint();
            sleep();
        }
        return false;
    }


    private void sleep() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}