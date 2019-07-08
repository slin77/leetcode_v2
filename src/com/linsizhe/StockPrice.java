package com.linsizhe;
// https://leetcode.com/problems/best-time-to-buy-and-sell-stock/submissions/
// #
class StockPrice {
    public int maxProfit(int[] prices) {
        int[] diff = new int[prices.length];
        for (int i = 1; i < prices.length; i++) {
            diff[i] =  prices[i] - prices[i - 1];
        }
        // max subarray problem.
        // dp[i] = max(dp[i - 1], 0) + diff[i]
        // 7 1 5 3 6 4
        // 0 -6 4 -2 3 -2
        int totalMax = Integer.MIN_VALUE;
        int curMax = Integer.MIN_VALUE;
        for (int i = 0; i < diff.length; i++) {
            curMax = Math.max(curMax, 0) + diff[i];
            totalMax = Math.max(curMax, totalMax);
        }

        return totalMax < 0 ? 0 : totalMax;
    }
}