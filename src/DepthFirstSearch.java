public class DepthFirstSearch {

    private char[][] maze;
    private boolean[][] visited;

    public DepthFirstSearch(char[][] maze) {
        this.maze = maze;
        this.visited = new boolean[maze.length][maze[0].length];
    }

    public boolean solveMaze() {
        return depthFirstSearch(0, 0);
    }

    private boolean isValidSpot(int r, int c) {
        return r >= 0 && r < maze.length && c >= 0 && c < maze[0].length && maze[r][c] == '.';
    }

    private boolean depthFirstSearch(int r, int c) {
        if (isValidSpot(r, c) && !visited[r][c]) {
            visited[r][c] = true;

            if (r == maze.length - 1 && c == maze[0].length - 1) {
                maze[r][c] = '*';
                return true;
            }

            maze[r][c] = '*';
            // (Optional) Add repaint and sleep here if needed

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
            // (Optional) Add repaint and sleep here if needed
        }
        return false;
    }
}
