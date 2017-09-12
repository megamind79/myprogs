package backtrack;

import java.util.ArrayList;
import java.util.Scanner;

public class NQueens {
    static boolean checkValid (int r, int c, int cols[], int n) {
        for (int row = 0; row <r; row++) {
            int col = cols[row];

            if (col == c)
                return false;
            // to check if coming in another queen's diagonal
            if (Math.abs (col - c) == Math.abs (row - r))
                return false;
        }

        return true;
    }

    static boolean placeQueens (int n, int cols[], int r, ArrayList<Integer> result) {
        if (r == n) {
            for (int i : cols) {
                result.add(i);
            }
            return true;
        }

        for (int col = 0; col < n; col++) {
            if (checkValid (r, col, cols, n)) {
                cols[r] = col;
                placeQueens (n, cols, r+1,result);
                if (result.size() > 0)
                    return true;
            }
        }
        return false;
    }

    static void printBoard (int n, ArrayList<Integer> result) {
        for (int i = 0; i<n; i++) {
            int col = result.get(i);
            for (int j = 0; j<n; j++) {
                System.out.print (j == col ? "1 " : "0 ");
            }
            System.out.println();
        }
    }

    public static void main(String args[] ) throws Exception {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();

        int cols[] = new int[n];
        ArrayList<Integer> result = new ArrayList<>();

        if (placeQueens (n, cols, 0, result)) {
            printBoard (n, result);
        } else {
            System.out.print ("Not possible");
        }
    }
}
