package com.linsizhe.facebook;

// https://leetcode.com/problems/longest-palindromic-substring/
public class LongestPalindromicSubstring {
    public String longestPalindrome(String s) {

        int[][] dp = new int[s.length()][s.length()];

        int max = 0;
        int ix = 0;
        int jx = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = i; j < s.length(); j++) {
                if (i == j) {
                    dp[i][j] = 1;
                } else if (s.charAt(i) == s.charAt(j)) {
                    if (j - i == 1) {
                        dp[i][j] = 2;
                    } else if (dp[i + 1][j - 1] > 0) {
                        dp[i][j] = dp[i + 1][j - 1] + 2;
                    }
                    max = Math.max(dp[i][j], max);
                    if (max == dp[i][j] && max != 0) {
                        ix = i;
                        jx = j;
                    }
                }
            }
        }
        return s.substring(ix, jx + 1);
    }
}
