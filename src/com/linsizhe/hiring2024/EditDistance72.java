package com.linsizhe.hiring2024;

import java.util.Arrays;

// https://leetcode.com/problems/edit-distance/
public class EditDistance72 {
    String word1;
    String word2;
    public int minDistance(String word1, String word2) {
        this.word1 = word1;
        this.word2 = word2;
        int[][] memo = new int[word1.length() + 1][word2.length() + 1];
        for (int [] arr: memo) {
            Arrays.fill(arr, -1);
        }
        return dfs(0, 0, memo);
    }

    public int dfs(int i, int j, int[][] memo) {
        if (memo[i][j] != -1) {
            return memo[i][j];
        }

        int res = 0;

        if (i == word1.length() && j == word2.length()) {
            res = 0;
        } else if (i == word1.length()) {
            res = word2.length() - j;
        } else if (j == word2.length()) {
            res = word1.length() - i;
        } else if (word1.charAt(i) == word2.charAt(j)) {
            res =dfs(i + 1, j + 1, memo);
        } else {
            int insert = dfs(i + 1, j, memo);
            int delete = dfs(i, j + 1, memo);
            int replace = dfs(i + 1, j + 1, memo);
            res = Math.min(insert, Math.min(delete, replace)) + 1;
        }

        memo[i][j] = res;
        return memo[i][j];
    }
}
