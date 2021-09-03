package com.linsizhe.snapchat;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

//https://leetcode.com/problems/meeting-rooms-ii/
public class MeetingRoomII {
    public int minMeetingRooms(int[][] intervals) {
        Arrays.sort(intervals, Comparator.<int[]>comparingInt(x -> x[0]));
        PriorityQueue<int[]> q = new PriorityQueue(Comparator.<int[]>comparingInt(x -> x[1]));
        int max = 0;
        for (int i = 0; i < intervals.length; i++) {
            int[] cur = intervals[i];
            if (q.isEmpty()) {
                q.add(cur);
            } else if (q.peek()[1] > cur[0]) {
                q.offer(cur);
            } else {
                if (q.peek()[1] <= cur[0]) {
                    q.poll();
                }
                q.offer(cur);
            }
            max = Math.max(max, q.size());
        }
        return max;
    }
}
