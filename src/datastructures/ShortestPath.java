package datastructures;

import java.util.ArrayList;

public class ShortestPath {

    class Edge {
        int source, destination, weight;

        Edge (int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }

    int adjacencyMatrix[][], distance[], parent[], n;
    boolean visited[];

    ShortestPath () {
        n = 9;
        adjacencyMatrix = new int[][]{{0, 4, 0, 0, 0, 0, 0, 8, 0},
                {4, 0, 8, 0, 0, 0, 0, 11, 0},
                {0, 8, 0, 7, 0, 4, 0, 0, 2},
                {0, 0, 7, 0, 9, 14, 0, 0, 0},
                {0, 0, 0, 9, 0, 10, 0, 0, 0},
                {0, 0, 4, 14, 10, 0, 2, 0, 0},
                {0, 0, 0, 0, 0, 2, 0, 1, 6},
                {8, 11, 0, 0, 0, 0, 1, 0, 7},
                {0, 0, 2, 0, 0, 0, 6, 7, 0}
        };
        visited = new boolean[n];
        distance = new int[n];
        parent = new int[n];
        for (int i = 0; i<n; i++)
            distance[i] = Integer.MAX_VALUE;
    }

    // For Dijkstra's algorithm
    int findMinDistanceIndex () {
        int minDistance = Integer.MAX_VALUE;
        int minIndex = -1;
        for (int i = 0; i<n; i++) {
            if (!visited[i] && (distance[i] < minDistance)) {
                minDistance = distance[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

    /**
     * returns the shortest paths from source to all vertices
     * Complexity : O(VLogV) - with Fibonacci Heap else O(V2)
     * @param s source
     */
    void dijkstras (int s) {
        distance[s] = 0;
        parent[s] = -1;

        for (int j = 0; j<n; j++) {
            int index = findMinDistanceIndex();
            visited[index] = true;

            for (int i = 0; i<n; i++) {
                if (!visited[i] && (distance[index] <Integer.MAX_VALUE) && (adjacencyMatrix[index][i] != 0) &&
                        (distance[index] + adjacencyMatrix[index][i] < distance[i])) {
                    distance[i] = distance[index] + adjacencyMatrix[index][i];
                    parent[i] = index;
                }
            }
        }
    }

    void printPath (int s) {
        if (s == -1)
            return;
//        if (parent[s] == -1) {
//            System.out.print (s + " ");
//            return;
//        }
        printPath(parent[s]);
          System.out.print (s + " ");
    }

    void printShortestPathFromSource (int s) {
        for (int i = 0; i<n; i++) {
            System.out.println ("Path from " + s + " to " + i + " : " + distance[i]);
            printPath(i);
            System.out.println ();
        }
    }

    void printDistanceFromSource (int s) {
        System.out.println ("Distance from source : " + s);
        for (int i = 0; i<n; i++)
            System.out.println (i + " : " + distance[i]);
    }

    // For Floyd Warshall algorithm

    /**
     * prints the shortest paths between all vertices
     * Complexity : O(V3)
     */
    void floydWarshall () {
        int INF = -1;
        int dist[][] = new int[n][n];
        for (int i = 0; i<n; i++) {
            for (int j = 0; j<n; j++) {
                if (i == j)
                    continue;
                if (adjacencyMatrix[i][j] == 0)
                    dist[i][j] = INF;
                else
                    dist[i][j] = adjacencyMatrix[i][j];
            }
        }

        for (int i = 0; i<n; i++) {
            for (int j = 0; j<n; j++)
                System.out.print (dist[i][j] + " ");
            System.out.println ();
        } System.out.println ();

        for (int k = 0; k<n; k++) {
            for (int i = 0; i<n; i++) {
                for (int j = 0; j<n; j++) {
                    if ((dist[i][k] > 0) && (dist[k][j] > 0) && ((dist[i][k] + dist[k][j] < dist[i][j]) || (dist[i][j] < 0)))
                        dist[i][j] = dist[i][k] + dist[k][j];
                }
            }
        }

        for (int i = 0; i<n; i++) {
            for (int j = 0; j<n; j++)
                System.out.print (dist[i][j] + " ");
            System.out.println ();
        }
    }

    // for Bellman-Ford algorithm
    /**
     * prints shortest path weights from source to all vertices
     * for each edge uv -> if (distance(v) > distance(u) + weight(u-v)), distance(v) = distance(u) + weight(u-v)
     */
    void bellmanFord (int source) {
        distance[source] = 0;
        parent[source] = -1;

        ArrayList<Edge> edges = new ArrayList<>();
        for (int i = 0; i<n; i++) {
            for (int j = 0; j<n; j++) {
                if (adjacencyMatrix[i][j] > 0) {
                    Edge edge = new Edge(i, j, adjacencyMatrix[i][j]);
                    edges.add(edge);
                }
            }
        }

        for (int i = 0; i<n; i++) {
            for (Edge edge : edges) {
                int src = edge.source;
                int dest = edge.destination;
                int weight = edge.weight;

                if ((distance[src] != Integer.MAX_VALUE) && (distance[dest] > distance[src] + weight)) {
                    distance[dest] = distance[src] + weight;
                    parent[dest] = src;
                }
            }
        }
    }

    public static void main(String args[]) {
        ShortestPath shortestPath = new ShortestPath();
//        shortestPath.dijkstras(0);
        //shortestPath.bellmanFord(0);
//        shortestPath.printDistanceFromSource(0);
        shortestPath.floydWarshall();
        //shortestPath.printShortestPathFromSource(0);
    }
}
