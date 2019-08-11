package com.linsizhe;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

// Greedy + schedule with capacity Similar to MeetingRoom2
// core thought: sort by start time + pq track all current + evacuate by order

// PQ is current running processes (frontier)
public class MeetingRoom2 {
    public int minMeetingRooms(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(interval -> interval[0]));
        PriorityQueue<int[]> pq = new PriorityQueue(50,
                Comparator.<int[]>comparingInt(interval -> interval[1]));
        // Note: java priorityQueue is by default min queue.
        int max = 0;
        for (int i = 0; i < intervals.length; i++) {
            int[] cur = intervals[i];
            if (!pq.isEmpty()) {
                int[] candidate = pq.peek();
                if (candidate[1] > cur[0]) {
                    pq.add(cur); // here we know that if the first finish cannot accommodate, all after it can not either

                } else {
                    // allocate the first finished meeting.
                    pq.poll();
                    pq.add(cur);
                }
            } else {
                pq.add(cur);
            }
            max = Math.max(max, pq.size());
        }
        return max;
    }

    public static void main(String[] args) {
        MeetingRoom2 mr2 = new MeetingRoom2();
        int[][] input = {{0, 30}, {5, 10}, {15, 20}};
        System.out.println(mr2.minMeetingRooms(input));
    }
}
