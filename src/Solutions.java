import java.lang.reflect.Array;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by dell on 07-05-2017.
 */
public class Solutions {
    static void print (int path[][], int n) {
        for (int i = 0; i<n; i++) {
            for (int j = 0; j<n; j++)
                System.out.print (path[i][j] + " ");
            System.out.println ();
        }
    }

    static void walk (int x, int y, int path[][], int n) {
        try {
            if (path[x][y] != 0)
                return;
            System.out.println ("BEFORE : " + x + ", " + y);
            print(path, n);
            path[x][y] = 1;
            int paths[][] = new int[n][n];
            for (int i = 0; i<n; i++)
                paths[i] = Arrays.copyOf(path[i], path[i].length);
            walk(x+1, y+1, paths, n);
            System.out.println ("AFTER step 1 : " + x + ", " + y);
            print(paths, n);
            walk(x - 1, y - 1, paths, n);
            System.out.println ("After step 2 : " + x + ", " + y);
            print(path, n);
        } catch (Exception e) {
            System.out.println (e);
        }
    }

    static void walks (int x, int y, int path[][], int n) {

    }

    public static void main(String args[] ) throws Exception {
        Scanner s = new Scanner(System.in);

        int path[][] = {{0, 0}, {0, 0}};
        walk (0, 0, path, 2);
    }
//    12:12AM 12:13AM ---First
//    12:05AM 1:05AM --- First
//    11:15AM 12:15AM --- Second
}