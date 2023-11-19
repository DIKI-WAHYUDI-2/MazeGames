public class Maze {

    public static void main(String[] args) {
        char[][] maze = {
                {'.', '.', '.', '0', '0', '0', '0', '0', '0', '0'},
                {'0', '0', '.', '.', '.', '0', '.', '.', '.', '0'},
                {'0', '0', '.', '0', '.', '0', '.', '0', '.', '0'},
                {'.', '.', '.', '0', '.', '0', '.', '0', '.', '0'},
                {'.', '0', '0', '0', '.', '.', '.', '0', '.', '0'},
                {'.', '.', '.', '.', '0', '0', '0', '.', '.', '0'},
                {'.', '0', '0', '.', '.', '.', '0', '.', '.', '0'},
                {'.', '.', '.', '0', '0', '.', '0', '0', '.', '.'},
                {'0', '0', '.', '0', '0', '.', '.', '.', '0', '0'},
                {'0', '0', '0', '0', '0', '0', '0', '.', '.', '.'},
        };

        print(maze);

        if(traverse(maze, 0, 0)) {
            System.out.println("SOLVED maze");
        } else {
            System.out.println("could NOT SOLVE maze");
        }

        print(maze);
    }

    private static void print(char[][] maze) {
        System.out.println("-----------------------");
        for(int x = 0; x < 10; x++) {
            System.out.print("| ");
            for(int y = 0; y < 10; y++) {
                System.out.print(maze[x][y] + " ");
            }
            System.out.println("|");
        }
        System.out.println("-----------------------");
    }

    public static boolean isValidSpot(char[][] maze, int r, int c) {
        if(r >= 0 && r < 10 && c >= 0 && c < 10) {
            return maze[r][c] == '.';
        }
        return false;
    }

    public static boolean traverse(char[][] maze, int r, int c) {

        if(isValidSpot(maze, r, c)) {
            //it is a valid spot
            if(r == 9 && c == 9) {
                return true;
            }

            maze[r][c] = '*';

            //up
            boolean returnValue = traverse(maze, r - 1, c);


            //right
            if(!returnValue) {
                returnValue = traverse(maze, r, c + 1);
            }

            //down
            if(!returnValue) {
                returnValue = traverse(maze, r + 1, c);
            }

            //left
            if(!returnValue) {
                returnValue = traverse(maze, r, c - 1);
            }

            if(returnValue) {
                System.out.println(r + ", " + c);
                maze[r][c] = ' ';
            }
            return returnValue;
        }

        return false;
    }
}