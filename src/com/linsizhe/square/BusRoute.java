package com.linsizhe.square;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

//https://leetcode.com/problems/bus-routes/
public class BusRoute {
    public int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target) return 0;

        HashMap<Integer, HashSet<Integer>> stopToBus = new HashMap();

        // adj matrix
        // update: don't need adjacency matrix for here actually,
        // we can compute this in place dur BFS..
        int[][] adj = new int[routes.length][routes.length];
        for (int i = 0; i < routes.length; i++) {
            for (int j = 0; j < routes[i].length; j++) {
                int stp = routes[i][j];
                stopToBus.putIfAbsent(routes[i][j], new HashSet<Integer>());
                for (int bus : stopToBus.get(stp)) {
                    adj[bus][i] = 1;
                    adj[i][bus] = 1;
                }
                stopToBus.get(routes[i][j]).add(i);
            }
        }


        HashSet<Integer> starts = stopToBus.get(source);
        HashSet<Integer> ends = stopToBus.get(target);
        if (ends == null) return -1;

        Queue<Integer> q = new LinkedList();
        q.addAll(starts);
        int step = 0;
        HashSet<Integer> visited = new HashSet();
        while (!q.isEmpty()) {
            step++;
            int size = q.size();
            for (int idx = 0; idx < size; idx++) {
                int stop = q.poll();
                visited.add(stop);
                if (ends.contains(stop)) {
                    return step;
                }
                for (int i = 0; i < adj[stop].length; i++) {
                    if (adj[stop][i] == 1 && !visited.contains(i)) {
                        q.add(i);
                    }
                }
            }
        }
        return -1;
    }
}
