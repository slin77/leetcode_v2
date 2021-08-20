package com.linsizhe.facebook;

import java.util.HashSet;

//https://leetcode.com/problems/max-area-of-island/
public class MaxAreaOfIsland {
    static int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    int max = 0;
    int curSize;
    public int maxAreaOfIsland(int[][] grid) {
        HashSet<String> visited = new HashSet();
        for (int i = 0;i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    curSize = 0;
                    dfs(i, j, grid);
                }
            }
        }
        return max;
    }

    void dfs(int i, int j, int[][] grid) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length
                || grid[i][j] != 1) {
            return;
        }
        curSize++;
        // No need to track visited, just marking 0 is enough for this problem
        grid[i][j] = 0;
        max = Math.max(max, curSize);
        for (int[] dir : dirs) {
            dfs(i + dir[0], j + dir[1], grid);
        }
    }
}
