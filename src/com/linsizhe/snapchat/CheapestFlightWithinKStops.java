package com.linsizhe.snapchat;

import java.util.*;

// https://leetcode.com/problems/cheapest-flights-within-k-stops/
public class CheapestFlightWithinKStops {
    // dijkstra: with stop limit
    // If result are out of stops, we keep algorithm running
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {

        HashMap<Integer, HashMap<Integer, Integer>> toMap = new HashMap();
        // steps from src to any node
        int[] steps = new int[n]; // need to keep track of steps
        Arrays.fill(steps, Integer.MAX_VALUE);
        int[] distances = new int[n];
        Arrays.fill(distances, Integer.MAX_VALUE);
        steps[src] = 0;
        distances[src] = 0;

        PriorityQueue<int[]> q = new PriorityQueue<int[]>(Comparator.comparingInt(x -> x[1]));
        q.add(new int[]{src, 0, 0});
        // visited edge
        for (int[] flight: flights) {
            toMap.putIfAbsent(flight[0], new HashMap<Integer, Integer>());
            toMap.get(flight[0]).put(flight[1], flight[2]);
        }

        while (!q.isEmpty()) {
            int[] node = q.poll();
            int step = node[2];
            int curDst = node[1];

            if (step > k + 1) {
                continue;
            }

            if (node[0] == dst) {
                return node[1];
            }

            for (Map.Entry<Integer, Integer> entry :
                    toMap.getOrDefault(node[0], new HashMap<Integer, Integer>()).entrySet()) {
                int newDist = curDst + entry.getValue();
                int newStep = step + 1;
                // traditional dijkstra pruning!
                if (newDist < distances[entry.getKey()]) {
                    q.add(new int[]{entry.getKey(), newDist, newStep});
                    distances[entry.getKey()] = newDist;
                } else if (newStep < steps[entry.getKey()]) {
                    q.add(new int[]{entry.getKey(), newDist, newStep});
                    steps[entry.getKey()] = newStep;
                }
            }
        }
        return -1;

    }
}
