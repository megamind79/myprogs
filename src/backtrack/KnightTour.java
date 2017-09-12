package backtrack;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Print a way to cover all squares of an nxn chess board only once.
 * Maybe need a way to cover more than 7x7 chess board
 */
public class KnightTour {
    static int[][] resetMatrix (int n) {
        int path[][] = new int[n][n];
        for (int i = 0; i<n; i++)
            for (int j = 0; j<n; j++)
                path[i][j] = -1;

        return path;
    }

    static void print (int path[][], int n) {
        for (int i = 0; i<n; i++) {
            for (int j = 0; j<n; j++)
                System.out.print (path[i][j] + " ");
            System.out.println ();
        }
    }

    static boolean walk (int x, int y, int path[][], int index, int n) {
        if (!((x >= 0) && (x < n) && (y >= 0) && (y < n))) {
            return false;
        }
        // System.out.println (x + " - " + y + " : " + index);
        try {
            if (index == (n*n)) {
                print(path, n);
                return true;
            }
            if (path[x][y] != -1)
                return false;
            path[x][y] = index;
//            int new_path[][] = new int[n][n];
//            for (int i = 0; i<n; i++)
//                new_path[i] = Arrays.copyOf(path[i], n);
//            boolean value = (walk(x+1, y+2, new_path, index+1, n) || walk(x-1, y+2, new_path, index+1, n) ||
//                    walk(x+1, y-2, new_path, index+1, n) || walk(x-1, y-2, new_path, index+1, n) ||
//                    walk(x+2, y+1, new_path, index+1, n) || walk(x+2, y-1, new_path, index+1, n) ||
//                    walk(x-2, y+1, new_path, index+1, n) || walk(x-2, y-1, new_path, index+1, n));
            boolean value = (walk(x+1, y+2, path, index+1, n) || walk(x-1, y+2, path, index+1, n) ||
                    walk(x+1, y-2, path, index+1, n) || walk(x-1, y-2, path, index+1, n) ||
                    walk(x+2, y+1, path, index+1, n) || walk(x+2, y-1, path, index+1, n) ||
                    walk(x-2, y+1, path, index+1, n) || walk(x-2, y-1, path, index+1, n));
            if (!value)
                path[x][y] = -1;
            return value;
        }catch (Exception e) {
            return false;
        }
    }
    public static void main(String args[] ) throws Exception {
        Scanner s = new Scanner(System.in);

        int n = s.nextInt();
        long startTime = System.currentTimeMillis();
        int path[][] = resetMatrix(n);
        long endTime = System.currentTimeMillis();
        System.out.println (walk (0, 0, path, 0, n)?"TRUE":"No solution." + " time taken : " + (endTime-startTime));
    }
}
