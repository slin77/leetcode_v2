package com.linsizhe.doordash;

import java.util.Arrays;
import java.util.Comparator;

public class JobScheduling {
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int[][] jobs = new int[startTime.length][3];
        for (int i = 0; i < startTime.length; i++) {
            jobs[i] = new int[]{startTime[i], endTime[i], profit[i]};
        }
        Arrays.sort(jobs, Comparator.<int[]>comparingInt(x -> x[0]));
        // max profit from i to end;
        int[] mem = new int[startTime.length];
        Arrays.fill(mem, -1);
        return getMaxProfit(0, mem, startTime.length, jobs);
    }

    // getMaxProfit when we about to schedule job at position i in sorting order
    public int getMaxProfit(int idx, int[] mem, int total, int[][] jobs) {
        if (idx >= total) {
            return 0;
        }

        if (mem[idx] != -1) {
            return mem[idx];
        }

        int res = Math.max(getMaxProfit(idx + 1, mem, total, jobs), // no schedule idx job
                getMaxProfit(getIdx(jobs[idx][1], jobs), mem, total, jobs) + jobs[idx][2]);
        mem[idx] = res;
        return res;
    }

    // Get next job idx >= elm
    private int getIdx(int elem, int[][] jobs) {
        int start = 0;
        int end = jobs.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;
            int cur = jobs[mid][0];
            if (cur >= elem) {
                end = mid - 1;
            } else if (cur < elem) {
                start = mid + 1;
            }
        }

        return start;
    }
}
