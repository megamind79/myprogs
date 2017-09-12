import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by dell on 27-01-2017.
 */
public class Solution {

    public static int PlayingDisks(int[] input1)
    {
        LinkedList<Integer> disks = new LinkedList<Integer>();
        int max = -1, count = 0;
        for (int i = 0; i<input1.length; i++) {
            if (max < input1[i])
                max = input1[i];
            disks.add(input1[i]);
        }
        //System.out.println (disks);
        Arrays.sort(input1);
        //for (int i = 0; i<input1.length; i++) System.out.print(input1[i] + " ");
        //System.out.println ();

        int i = input1.length-1;
        for (; i>=0; i--) {
            if (input1[i] != max)
                break;
        }

        for (; i>=0; i--) {
            for (int j = 0; j<disks.size()-count; j++) {
                //System.out.println ("j = " + j + " i = " + i + " comparing " + disks.get(j) + " with " + input1[i]);
                if (disks.get(j) == input1[i]) {
                    disks.remove(j);
                    disks.add(input1[i]);
                    count++;
                    //System.out.println ("count : " + count + "disks : " + disks);
                }
            }
        }

        //System.out.println (disks);

        return count;
    }

    public static void main (String args[]) {
        Scanner sc = new Scanner (System.in);

        int inp[] = {3, 1, 3, 2};

        System.out.println (PlayingDisks(inp));
    }
}
