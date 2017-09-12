package backtrack;

/**
 * Rat maze with -1 as allowed square and 0 as locked square.
 */
public class RatMaze {
    static void print (int path[][], int n) {
        for (int i = 0; i<n; i++) {
            for (int j = 0; j<n; j++)
                System.out.print (path[i][j] == -1?"0":path[i][j]);
            System.out.println ();
        }
    }

    static boolean isValid (int x, int y, int path[][], int n) {
        return (x >= 0) && (x < n) && (y >= 0) && (y < n) && (path[x][y] != 0);
    }

    /**
     * Find a way for the rat to move from left-top corner to right-bottom corner
     * @param x x-coordinate
     * @param y y-coordinate
     * @param path path taken ny the rat
     * @param index rat's step count
     * @param n square size
     * @return true if there's a path, else false
     */
    static boolean walk (int x, int y, int path[][], int index, int n) {
        if (!isValid(x, y, path, n))
            return false;
        path[x][y] = index;
        if (x == (n-1) && y == (n-1)) {
            print(path, n);
            return true;
        }
        boolean value = walk(x, y+1, path, index+1, n) || walk(x+1, y, path, index+1, n);
        if (!value)
            path[x][y] = -1;
        return value;
    }

    /**
     * Find no. of ways for the rat to move from left-top corner to right-bottom corner
     * @param x x-coordinate
     * @param y y-coordinate
     * @param path count of paths taken by the rat from any point to bottom-right corner
     * @param n square size
     * @return count of paths taken by the rat from x, y to bottom-right corner
     */
    static int walks (int x, int y, int path[][], int n) {
        if (!isValid(x, y, path, n))
            return 0;
        if (path[x][y] != -1)
            return path[x][y];
//        if (isValid(x, y, path, n)) {
//            path[x][y+1] = walks(x, y+1, path, n);
//            path[x+1][y] = walks(x+1, y, path, n);
//        }
//        path[x][y] = path;
        if (x == (n-1) && y == (n-1)) {
            path[x][y] = 1;
            // print(path, n);
            return 1;
        }
        path[x][y] = walks(x, y+1, path, n) + walks(x+1, y, path, n);

        if (x == 0 && y == 0) {
            print(path, n);
        }

        return path[x][y];
    }
    
    public static void main(String args[] ) throws Exception {
        long startTime = System.currentTimeMillis();
        int path[][] = {
                {-1, 0, 0, 0},
                {-1, -1, 0, -1},
                {0, -1, 0, 0},
                {-1, -1, -1, -1}};

        int count_paths[][] = {
                {-1,  -1, -1, -1},
                {-1, 0, -1, -1},
                {0, -1, -1, -1},
                {-1,  -1, -1, -1}
        };
        // System.out.println (walk (0, 0, path, 1, 4)?"TRUE":"No solution.");
        System.out.println ("No. of Paths : " + walks(0, 0, path, 4));
        long endTime = System.currentTimeMillis();
        System.out.println ("Time taken : " + (endTime-startTime));
    }
}
