package com.linsizhe.hiring2024;

import java.util.HashMap;

// https://leetcode.com/problems/minimum-operations-to-write-the-letter-y-on-a-grid/
public class MinimumOperationToWriteY {
    public int minimumOperationsToWriteY(int[][] grid) {
        HashMap<Integer, Integer> map1 = new HashMap();
        HashMap<Integer, Integer> map2 = new HashMap();
        int sizeIN = 0;
        int sizeOut = 0;
        for (int i =0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (isY(i, j, grid.length)) {
                    sizeIN++;
                    map1.put(grid[i][j], map1.getOrDefault(grid[i][j], 0) + 1);
                } else {
                    sizeOut++;
                    map2.put(grid[i][j], map2.getOrDefault(grid[i][j], 0) + 1);
                }
            }
        }

        int res = Integer.MAX_VALUE;
        // all possible combinations and see min
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (j != i) {
                    int cur = (sizeIN - map1.getOrDefault(i, 0)) + (sizeOut - map2.getOrDefault(j, 0));
                    res = Math.min(res, cur);
                }
            }
        }

        return res;
    }

    boolean isY(int i, int j, int l) {
        if (i == j && i <= l / 2) {
            return true;
        } else if (i < (l / 2) && (i + j) == (l - 1)) {
            return true;
        } else if (i > (l / 2) && j == l / 2) {
            return true;
        }
        return false;
    }
}
