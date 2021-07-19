package com.linsizhe.facebook;

import java.util.HashSet;

// https://leetcode.com/problems/minimum-cost-for-tickets/
public class MinimumCostForTickets {
    public int mincostTickets(int[] days, int[] costs) {
        // need to include 365.
        int[] dp = new int[366];
        HashSet<Integer> set = new HashSet();
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int day : days) {
            set.add(day);
            max = Math.max(max, day);
            min = Math.min(min, day);
        }
        for (int i = min; i <= max; i++) {
            if (!set.contains(i)) {
                dp[i] = getCost(dp, i - 1);
            } else {
                dp[i] = Math.min(getCost(dp, i - 1) + costs[0], Math.min(getCost(dp, i - 7) + costs[1], getCost(dp, i - 30) + costs[2]));
            }
        }
        return dp[max];
    }

    public int getCost(int[] dp, int i) {
        if (i < 0) return 0;
        return dp[i];
    }
}
