package com.linsizhe.hiring2024;

// use dp get from previous results to see if  current i, j is pand
// https://leetcode.com/problems/palindromic-substrings/
public class PalindromicSubstrings {
    public int countSubstrings(String s) {
        boolean[][] dp = new boolean[s.length()][s.length()];
        int count = 0;
        for (int i = s.length(); i >= 0; i--) {
            for (int j = i; j < s.length(); j++) {
                if (i == j) {
                    dp[i][j] = true;
                }  else if (s.charAt(i) == s.charAt(j)) {
                    // double character case is also base case! It does not require previous check!
                    if (j - i == 1) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j]  = dp[i + 1][j - 1] ? true : false;
                    }
                } else {
                    dp[i][j] = false;
                }
                if (dp[i][j]) count++;
            }
        }
        // 0, 1, 2   01 ->(1, 0), 12 02
        return count;
    }
}
