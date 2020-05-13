package com.linsizhe.facebook;

import java.util.Comparator;
import java.util.PriorityQueue;

// 3 solutions to top K problems:
// 1 sort: NlogN
// 2 Heap: NlogK
public class KClosestPointToOrigin {
    public int[][] kClosest(int[][] points, int K) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(points.length, Comparator.<int[]>comparingInt(x -> (x[0] * x[0] + x[1] * x[1])).reversed());
        for (int[] point: points) {
            pq.add(point);
        }
        int[][] out = new int[Math.min(points.length, K)][2];
        for (int i = 0; i < Math.min(points.length, K); i++) {
            out[i] = pq.poll();
        }
        return out;
    }
}
