package datastructures;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * Created by dell on 18-08-2017.
 */
public class MST {

    static class Node implements Comparable<Node> {
        int index, key;

        Node (int index) {
            this.index = index;
            this.key = Integer.MAX_VALUE;
        }

        public String toString () {
            return "Node(" + index + ", " + key + ")";
        }


        @Override
        public int compareTo(Node node) {
            if (this.key == node.key)
                return this.index - node.index;
            return this.key - node.key;
        }
    }

    int graph[][], v;
    ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

    void addEdge (int x, int y) {
        ArrayList<Integer> xadj = adj.get(x);
        xadj.add(y);
    }

    void addEdges () {
        for (int i = 0; i<v; i++) {
            adj.add(new ArrayList<Integer>());
            for (int j = 0; j<v; j++)
                if (graph[i][j] != 0)
                    addEdge(i, j);
        }
    }

    void prims () {
        ArrayList<Node> nodes = new ArrayList<>();
        for (int i = 0; i<v; i++) {
            Node node = new Node(i);
            if (i == 0)
                node.key = 0;
            nodes.add(node);
        }

        boolean mstSet[] = new boolean[v];
        int parents[] = new int[v];
        for (int i = 0; i<v; i++)
            parents[i] = -2;
        parents[0] = -1;
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(nodes.get(0));

        while (!priorityQueue.isEmpty()) {
            Node node = priorityQueue.poll();
            int parent = node.index;
            mstSet[parent] = true;
            ArrayList<Integer> neighbours = adj.get(parent);
            System.out.println ("neighbours of " + node + " : " + neighbours);

            for (int neighbour : neighbours) {
                if (mstSet[neighbour])
                    continue;
                Node neighbourNode = nodes.get(neighbour);
                if ((graph[parent][neighbour] > 0) && (neighbourNode.key > graph[parent][neighbour])) {
                    priorityQueue.remove(neighbourNode);
                    neighbourNode.key = graph[parent][neighbour];
                    parents[neighbour] = parent;
                    priorityQueue.add(neighbourNode);
                }
            }
        }

        System.out.println ("Edges in datastructures.MST :");
        for (int i = 1; i<v; i++) {
            System.out.println ("Edge " + i + parents[i] + " : " + graph[i][parents[i]]);
        }
    }

    public static void main(String args[] ) throws Exception {
        // Scanner scanner = new Scanner(System.in);

        MST mst = new MST();
//        mst.v = 5;
//        mst.graph = new int[][]{
//                {0, 10, 20, 0, 0},
//                {10, 0, 30, 5, 0},
//                {20, 30, 0, 15, 6},
//                {0, 5, 15, 0, 8},
//                {0, 0, 6, 8, 0}
//        };
        mst.v = 9;
        mst.graph = new int[][] {
                {0, 4, 0, 0, 0, 0, 0, 8, 0},
                {4, 0, 8, 0, 0, 0, 0, 11, 0},
                {0, 8, 0, 7, 0, 4, 0, 0, 2},
                {0, 0, 7, 0, 9, 14, 0, 0, 0},
                {0, 0, 0, 9, 0, 10, 0, 0, 0},
                {0, 0, 4, 14, 10, 0, 2, 0, 0},
                {0, 0, 0, 0, 0, 2, 0, 1, 6},
                {8, 11, 0, 0, 0, 0, 1, 0, 7},
                {0, 0, 2, 0, 0, 0, 6, 7, 0}
        };
        mst.addEdges();
        mst.prims();
    }
}
