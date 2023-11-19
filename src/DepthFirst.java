import java.util.List;
import java.util.Stack;

public class DepthFirst {

    public static boolean searchPath(int[][] maze, int x, int y, Stack<Integer> path){

        if (maze[y][x] == 9){
            path.push(x);
            path.push(y);
            return true;
        }


        if (maze[y][x] == 0){
            maze[y][x] = 2;

            int dx = -1;
            int dy = 0;
            if (searchPath(maze, x + dx, y + dy, path)){
                path.push(x);
                path.push(y);
                return true;
            }

            dx = 1;
            dy = 0;
            if (searchPath(maze, x + dx, y + dy, path)){
                path.push(x);
                path.push(y);
                return true;
            }

            dx = 0;
            dy = -1;
            if (searchPath(maze, x + dx, y + dy, path)){
                path.push(x);
                path.push(y);
                return true;
            }

            dx = 0;
            dy = 1;
            if (searchPath(maze, x + dx, y + dy, path)){
                path.push(x);
                path.push(y);
                return true;
            }

        }
        return false;
    }
}
