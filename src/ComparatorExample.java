import java.util.*;

/**
 * Created by dell on 21-05-2017.
 */
public class ComparatorExample {
    static class ComparatorClass /*implements Comparator<ComparatorClass>, Comparable<ComparatorClass>*/ {
        int id;
        String name;

        ComparatorClass (int id, String name) {
            this.id = id;
            this.name = name;
        }

//        @Override
//        public int compareTo(ComparatorClass o) {
//            return name.compareTo(o.name);
//        }
//
//        @Override
//        public int compare(ComparatorClass o1, ComparatorClass o2) {
//            //return (o1.id == o2.id?o1.compareTo(o2):(o1.id-o2.id));
//            return o1.id - o2.id;
//        }

        public String toString () {
            return id + " - " + name;
        }
    }

    public static void main (String args[]) {
        int a[][] = {{1, 2}, {3, 5}, {4, 7}, {3, 8}, {1, 1}, {4, 5}, {3, 2}, {6, 4}};

        // sorting 2D array
        Comparator<Integer[]> comparator = new Comparator<Integer[]>() {
            @Override
            public int compare(Integer[] o1, Integer[] o2) {
                return (o1[0] != o2[0]?(o1[0]-o2[0]):(o1[1]-o2[1]));
            }
        };

        Arrays.sort(a, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return (o1[0] != o2[0]?(o1[0]-o2[0]):(o1[1]-o2[1]));
            }
        });

        // or
        // Arrays.sort(a, comparator); // provided a is of type Integer[][]

        for (int i = 0; i<a.length; i++)
            System.out.println (a[i][0] + " - " + a[i][1]);

        ArrayList<ComparatorClass> list = new ArrayList<>();
        list.add(new ComparatorClass(2, "Shiri"));
        list.add(new ComparatorClass(4, "Asdgeri"));
        list.add(new ComparatorClass(2, "Dery"));
        list.add(new ComparatorClass(4, "Shiri"));

        //Collections.sort(list);
        // or after removing Comparator and Comparable implementations from ComparatorClass do this
        Collections.sort(list, new Comparator<ComparatorClass>() {
            @Override
            public int compare(ComparatorClass o1, ComparatorClass o2) {
                return (o1.id != o2.id ? o1.id - o2.id : o1.name.compareTo(o2.name));
            }
        });

        System.out.println (list);
    }
}
