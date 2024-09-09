package com.linsizhe.hiring2024;

import java.util.Arrays;

// https://leetcode.com/problems/minimum-time-to-complete-trips/
public class MinimumTimeToCompleteTrips2187 {
    // binary search to find from 0 -> max value need.
    public long minimumTime(int[] time, int totalTrips) {
        int max = Arrays.stream(time).reduce(Integer::max).getAsInt();
        long l = 0;
        long r = (long) totalTrips * max;
        // find first greater than or equal
        // r + 1
        while (l <= r) {
            long mid = l + (r - l) / 2;
            long trips = 0;
            for (int i = 0; i < time.length; i++) {
                trips += mid / time[i];
            }
            if (trips >= totalTrips) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return r + 1;
    }
}
