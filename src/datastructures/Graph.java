package datastructures;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by dell on 12-03-2017.
 */
public class Graph {
    public int v;
    public LinkedList<Integer> adj[];
    public boolean visited[];

    public Graph (int v) {
        this.v = v;
        this.adj = new LinkedList[v+1];
        for (int i = 0; i<=v; i++)
            adj[i] = new LinkedList<>();
        visited = new boolean[v+1];
    }

    public void createAdjacencyList (boolean graph[][]) {
        for (int i = 0; i<v; i++) {
            for (int j = 0; j<v; j++) {
                if (graph[i][j])
                    addEdge(i, j);
            }
        }

        System.out.println ("Adjacency List :");
        for (int i = 0; i<v; i++)
            System.out.println (adj[i]);
    }

    // adds a directional edge
    public void addEdge (int x, int y) {
        adj[x].add(y);
    }

    // adds a bi-directional edge
    public void addEdges (int x, int y) {
        addEdge(x, y);
        addEdge(y, x);
    }

    public void resetVisited () {
        Arrays.fill(visited, false);
    }

    public void printBFS (int start) {
        resetVisited();
        Queue<Integer> bfs = new LinkedList<>();
        bfs.add(start);
        visited[start] = true;
        while (!bfs.isEmpty()) {
            int root = bfs.poll();
            System.out.print (root + " ");
            for (int dest : adj[root]) {
                if (!visited[dest]) {
                    bfs.add(dest);
                    visited[dest] = true;
                }
            }
        }
        System.out.println ();
    }

    public void printDFS (int start) {
        resetVisited();
        Stack<Integer> dfs = new Stack<>();
        dfs.add(start);
        visited[start] = true;
        while (!dfs.isEmpty()) {
            int root = dfs.pop();
            System.out.print (root + " ");
            for (int dest : adj[root]) {
                if (!visited[dest]) {
                    dfs.add(dest);
                    visited[dest] = true;
                }
            }
        }
        System.out.println ();
    }

    public boolean hasCycle () {
        resetVisited();
        int start = 0;
        Stack<Integer> dfs = new Stack<>();
        dfs.add(start);
        visited[start] = true;
        while (!dfs.isEmpty()) {
            int root = dfs.pop();
            for (int dest : adj[root]) {
                if (visited[dest])  return true;
                dfs.add(dest);
                visited[dest] = true;
            }
        }
        return false;
    }

    public static void main (String args[]) {
        Graph graph = new Graph(4);

        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);
        graph.addEdge(2, 3);
        graph.addEdge(3, 3);

        System.out.println ("Has cycle : " + graph.hasCycle());
        graph.printBFS(0);
        graph.printDFS(0);


    }
}
