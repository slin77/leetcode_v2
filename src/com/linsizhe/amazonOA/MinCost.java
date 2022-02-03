package com.linsizhe.amazonOA;

import java.util.Arrays;
import java.util.HashMap;

public class MinCost {
    int costEvaluation(int n, int[][] connections) {
        int[] parent = new int[n];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < connections.length; i++) {
            union(connections[i][0], connections[i][1], parent);
        }
        int total = 0;
        HashMap<Integer, Integer> clusters  = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int root = getRoot(i, parent);
            clusters.putIfAbsent(root, 0);
            clusters.put(root, clusters.get(root) + 1);
        }
        for (int i : clusters.values()) {
           total += Math.ceil(Math.sqrt(i));
        }
        System.out.println(Arrays.toString(parent));
        return total;
    }

    private int getRoot(int n, int[] parent) {
        while (parent[n] != n) {
            parent[n] = parent[parent[n]];
            n = parent[n];
        }
        return n;
    }

    private void union(int n, int m, int[] parent) {
        // must update all root's root
        int mRoot = getRoot(m, parent);
        int nRoot = getRoot(n, parent);
        parent[nRoot] = mRoot;
    }

    public static void main(String[] args) {
        // case 1
//        int[][] connections = new int[][]{{0, 2}, {1, 2}};
//        MinCost sol = new MinCost();
//        System.out.println(sol.costEvaluation(4,connections));

        // case 1
        int[][] connections = new int[][]{{2, 6}, {3, 5}, {0, 1}, {2, 9}, {5, 6}};
        MinCost sol = new MinCost();
        System.out.println(sol.costEvaluation(10, connections));
    }
}
