public class Solver {
    private char[][] result;
    private Position start, end;
    private int[][] maze;

    public Solver(int[][] maze, Position start, Position end) {
        this.maze = maze;
        this.start = start;
        this.end = end;
        result = new char[maze.length][maze[0].length];
    }

    public char[][] getResult() {
        return result;
    }

    public char solver() {
        result[start.getX()][start.getY()] = 'S';
        result[end.getX()][end.getY()] = 'E';
        for (int i = 0; i < maze.length; i++)
            for (int j = 0; j < maze[i].length; j++)
                if (maze[i][j] == 1)
                    result[i][j] = '#';

            char succ = path_finder(maze, start);

        for (int i = 0; i < maze.length; i++)
            for (int j = 0; j < maze[i].length; j++)
                if (result[i][j] == 'N')
                    result[i][j] = ' ';
        result[start.getX()][start.getY()] = 'S';
        result[end.getX()][end.getY()] = 'E';

        return succ;
    }

    public void print_result() {
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private char path_finder(int[][] maze, Position pos) {

        if (result[pos.getY()][pos.getX()] == 'E')
            return 'X';    //Found the end.
        else {
            char c = 0;
            result[pos.getY()][pos.getX()] = 'V';

            int offset = pos.getY() + 1;
            if (offset >= maze.length) offset = 0;

            char south = result[offset][pos.getX()];
            if (south == 0 || south == 'E') {
                //Go south
                c = path_finder(maze, new Position(offset, pos.getX()));
                result[offset][pos.getX()] = c;
                if (c == 'X') return 'X';
            }

            offset = pos.getX() + 1;
            if (offset >= maze[0].length) offset = 0;

            char east = result[pos.getY()][offset];
            if (east == 0 || east == 'E') {
                //Go east
                c = path_finder(maze, new Position(pos.getY(), offset));
                result[pos.getY()][offset] = c;
                if (c == 'X') return 'X';
            }

            offset = pos.getX() - 1;
            if (offset < 0) offset = maze[0].length - 1;

            char west = result[pos.getY()][offset];
            if (west == 0 || west == 'E') {
                //Go west
                c = path_finder(maze, new Position(pos.getY(), offset));
                result[pos.getY()][offset] = c;
                if (c == 'X') return 'X';
            }

            offset = pos.getY() - 1;
            if (offset < 0) offset = maze.length - 1;

            char north = result[offset][pos.getX()];
            if (north == 0 || north == 'E') {
                //Go north
                c = path_finder(maze, new Position(offset, pos.getX()));
                result[offset][pos.getX()] = c;
                if (c == 'X') return 'X';
            }

        }

        return 'N';

    }
}