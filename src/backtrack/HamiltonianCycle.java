package backtrack;

import datastructures.Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

public class HamiltonianCycle {
    Stack<Integer> stack = new Stack<Integer>();
    boolean visited[], graph[][];
    ArrayList<Integer> adj[];
    int v;

    HamiltonianCycle (boolean graph[][], int v) {
        this.v = v;
        this.visited = new boolean[v];
        this.graph = graph;
        adj = new ArrayList[v];

        for (int i = 0; i<v; i++) {
            adj[i] = new ArrayList<Integer>();
            for (int j = 0; j<v; j++)
                if (graph[i][j])
                    adj[i].add(j);
        }

        System.out.println ("Adjjacency List :");
        for (int i = 0; i<v; i++)
            System.out.println (adj[i]);
    }

    void getHamiltonianCycle () {
        int from = 0;

        ArrayList<Integer> neighbours = adj[from];

        visited[from] = true;
        stack.push(from);

        for (int neighbour : neighbours) {
            visited[neighbour] = true;
            stack.push(neighbour);

            if (getHamiltonianCycle (neighbour, 2)) {
                System.out.println ("cycle : " + stack);
                return;
            } else {
                visited[neighbour] = false;
                stack.pop();
            }
        }

        System.out.println ("No Hamiltonian Cycle");
    }

    boolean getHamiltonianCycle (int from, int vertex) {
        if (vertex == v) {
            if (graph[from][0]) {
                stack.push(0);
                return true;
            }
        }

        ArrayList<Integer> neighbours = adj[from];
        for (int neighbour : neighbours) {
            if (!visited[neighbour]) {
                visited[neighbour] = true;
                stack.push(neighbour);

                if (getHamiltonianCycle (neighbour, vertex+1)) {
                    return true;
                } else {
                    visited[neighbour] = false;
                    stack.pop();
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        /*
         *  (0)--(1)--(4)
             |   / \   |
             |  /   \  |
             | /     \ |
             (2)------(3)
         */

        boolean adjacencyMatrix[][] = {
                {false, true, true, false, false},
                {true, false, true, true, true},
                {true, true, false, true, false},
                {false, true, true, false, true},
                {false, true, false, true, false}
        };

        HamiltonianCycle hamiltonianCycle = new HamiltonianCycle(adjacencyMatrix, 5);
        hamiltonianCycle.getHamiltonianCycle();
    }
}
