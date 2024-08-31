package com.linsizhe.hiring2024;

//https://leetcode.com/problems/climbing-stairs/description
public class ClimbingStair70 {
    public int climbStairs(int n) {
        if (n  == 0 | n == 1) return 1;
        if (n == 2) return 2;
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i < n + 1; i++) {
            dp[i] = dp[i - 1]  + dp[i - 2];
        }
        return dp[n];
    }
}
