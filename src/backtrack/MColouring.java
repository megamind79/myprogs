package backtrack;

import java.util.ArrayList;

/**
 * Created by dell on 08-09-2017.
 */
public class MColouring {
    int m, n;
    ArrayList<ArrayList<Integer>> adjacencyList;

    boolean isSafe (int colours[], int v, int i) {
        ArrayList<Integer> neighbours = adjacencyList.get(v);

        for (int neighbour : neighbours) {
            if (colours[neighbour] == i)
                return false;
        }

        return true;
    }

    void graphColouring () {
        int colors[] = new int[n];

        if (!graphColouring(colors, 0))
            System.out.println ("No solution");
        else
            for (int i = 0; i<n; i++)
                System.out.println ("Vertex " + i + " : " + colors[i]);
    }

    boolean graphColouring (int colours[], int v) {
        if (v == n)
            return true;

        for (int i = 1; i<=m; i++) {
            if (isSafe(colours, v, i)) {
                colours[v] = i;

                if (graphColouring(colours, v+1))
                    return true;

                colours[v] = 0;
            }
        }

        return false;
    }

    void getAdjacencyList(short graph[][]) {
        for (int i = 0; i<n; i++) {
            ArrayList<Integer> neighbours = new ArrayList<Integer>();
            for (int j = 0; j<n; j++) {
                if (graph[i][j] > 0)
                    neighbours.add(j);
            }
            adjacencyList.add(neighbours);
        }
    }

    public static void main(String[] args) {
        MColouring mColouring = new MColouring();
        mColouring.m = 3;
        mColouring.n = 10;
        mColouring.adjacencyList = new ArrayList<ArrayList<Integer>>();
        short graph[][] = {
                {0, 0, 1, 1, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 1, 1, 0, 0, 1, 0, 0},
                {1, 0, 0, 0, 1, 0, 0, 0, 1, 0},
                {1, 1, 0, 0, 0 ,0 ,0, 0, 0, 1},
                {0, 1, 1, 0, 0, 1, 0, 0, 0, 0},
                {0, 0 ,0, 0, 1, 0, 1, 0, 0 ,1},
                {1, 0, 0, 0, 0, 1, 0, 1, 0, 0},
                {0, 1, 0, 0, 0, 0, 1, 0, 1, 0},
                {0, 0, 1, 0, 0, 0, 0, 1, 0, 1},
                {0, 0, 0, 1, 0, 1, 0, 0, 1, 0}
        };

//        short graph[][] = {
//                {0, 1, 1, 1},
//                {1, 0, 1, 0},
//                {1, 1, 0, 1},
//                {1, 0, 1, 0}
//        };

        mColouring.getAdjacencyList(graph);

        for (int i = 0; i<mColouring.n; i++) {
            ArrayList<Integer> neighbours = mColouring.adjacencyList.get(i);
            System.out.println ("Neighbours of " + i + " : " + neighbours);
        }

        mColouring.graphColouring();
    }
}
