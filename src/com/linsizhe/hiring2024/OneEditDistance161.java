package com.linsizhe.hiring2024;

import java.util.Arrays;

// https://leetcode.com/problems/one-edit-distance/description/
public class OneEditDistance161 {
    String s;
    String t;
    public boolean isOneEditDistance(String s, String t) {
        if (s.isEmpty()) {
            return t.length() == 1;
        }

        if (t.isEmpty()) {
            return s.length() == 1;
        }
        int[][] memo = new int[s.length() + 1][t.length() + 1];
        for (int i = 0; i < memo.length; i++) {
            Arrays.fill(memo[i], -1);
        }
        this.s = s;
        this.t = t;
        int res = dfs(0, 0, memo);
        return res == 1;
    }

    int dfs(int i, int j, int[][] memo) {
        if (memo[i][j]  != -1) {
            return memo[i][j];
        }
        int res = -1;
        if  (i == s.length() && j == t.length()) {
            res = 0;
        } else if (i == s.length()) {
            res = t.length() - j;
        } else if (j == t.length()) {
            res = s.length() - i;
        } else if (s.charAt(i)  == t.charAt(j)) {
            res = dfs(i + 1, j + 1, memo);
        } else {
            res = 1 + Math.min(dfs(i + 1,  j, memo),
                    Math.min(dfs(i + 1, j + 1, memo),
                            dfs(i, j  + 1, memo)));
        }

        memo[i][j]  = res;
        return memo[i][j];
    }
}
