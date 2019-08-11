package com.linsizhe;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

// Greedy + schedule with capacity Similar to MeetingRoom2
// core thought: sort by start time + pq track all current + evacuate by order
public class CarPooling {
    public boolean carPooling(int[][] trips, int capacity) {
        Arrays.sort(trips, Comparator.comparingInt(trip -> trip[1]));
        PriorityQueue<int[]> pq = new PriorityQueue(Comparator.<int[]>comparingInt(trip -> trip[2]));
        int curCap = 0;
        for (int i = 0; i < trips.length; i++) {
            int[] cur = trips[i];
            if (pq.isEmpty()) {
                pq.add(cur);
                curCap += cur[0];
            } else {
                int[] candidate = pq.peek();
                while (candidate != null && candidate[2] <= cur[1]) {
                    // keep polling passengers that stoped prior to it out.
                    pq.poll();
                    curCap -= candidate[0];
                    candidate = pq.peek();
                }
                pq.add(cur);
                curCap += cur[0];
            }
            if (curCap > capacity) {
                return false;
            }
        }
        return true;
    }
}
