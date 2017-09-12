package backtrack;

import java.util.Scanner;
import java.util.Stack;

/**
 * Print all possible paths in an mxn matrix
 * only right and down movements possible.
 */
public class AllPaths {
    static Stack<Integer> stack = new Stack<>();
    static int m, n;

    static boolean isValid (int x, int y) {
        return (x >= 0) && (x < m) && (y >= 0) && (y < n);
    }

    static void printAllPaths (int mat[][], int x, int y) {
        if (!isValid(x, y))
            return;
        stack.push(mat[x][y]);
        if (x == (m-1) && y == (n-1)) {
            System.out.println (stack);
            stack.pop();
            return;
        }
        printAllPaths(mat, x, y+1);
        printAllPaths(mat, x+1, y);
        stack.pop();
    }

    public static void main(String[] args) {
        int mat[][] = { {1, 2, 3}, {4, 5, 6} };
        m = 2; n = 3;
        printAllPaths(mat, 0, 0);
    }
}
