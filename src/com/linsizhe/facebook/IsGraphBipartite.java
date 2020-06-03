package com.linsizhe.facebook;

// A graph is partile iff it does not have old length cycles <=> it is two colorable
// https://leetcode.com/problems/is-graph-bipartite/
public class IsGraphBipartite {
    public boolean isBipartite(int[][] graph) {
        int[] color = new int[graph.length];
        // we need a full scan of starter node since a graph may not be
        // fully connected.
        for (int i = 0 ; i < graph.length; i++) {
            if (color[i] == 0) {
                if (!dfs(graph, i, 1, color)) {
                    return false;
                }
            }
        }
        return true;
    }

    // have a return value instead of a global val
    // record not colorable so we can terminate earlier.
    public boolean dfs(int[][] graph, int node, int inColor, int[] color) {
        if (color[node] != 0) {
            return color[node] == inColor;
        }
        color[node] = inColor;
        int[] neighbors = graph[node];
        int nextColor = inColor == 1 ? -1 : 1;
        for (int i : neighbors) {
            // tips for boolean return value dfs
            if (!dfs(graph, i, nextColor, color)) {
                return false;
            }
        }
        return true;
    }
}
