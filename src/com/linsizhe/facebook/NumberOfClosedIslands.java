package com.linsizhe.facebook;

// https://leetcode.com/problems/number-of-closed-islands/

public class NumberOfClosedIslands {
    public int closedIsland(int[][] grid) {
        // first mark connected to border ask closed
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length;j++) {
                if ((i == 0 || i == grid.length - 1 || j == 0 || j == grid[0].length - 1) && grid[i][j] == 0)  {
                    dfs(grid, i, j, 1);
                }
            }
        }
        // number of island.
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length;j++) {
                if (grid[i][j] == 0)  {
                    count++;
                    dfs(grid, i, j, 1);
                }
            }
        }
        return count;

    }

    public void dfs(int[][] grid, int i, int j, int color) {
        if (i >= grid.length || i < 0 || j >= grid[0].length || j < 0) {
            return;
        }
        if (grid[i][j] != 0) return;
        grid[i][j] = color;
        dfs(grid, i + 1, j, color);
        dfs(grid, i, j + 1, color);
        dfs(grid, i - 1, j, color);
        dfs(grid, i, j - 1, color);
    }
}
