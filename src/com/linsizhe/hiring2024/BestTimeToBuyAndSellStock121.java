package com.linsizhe.hiring2024;

// https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
public class BestTimeToBuyAndSellStock121 {
    public int maxProfit(int[] prices) {
        int[] dp = new int[prices.length];
        int min = Integer.MAX_VALUE;// min prices we have seen so far
        int profit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] > min) {
                profit = Math.max(profit, prices[i] - min);
            }
            min = Math.min(min, prices[i]);
        }
        return profit;
    }
}
