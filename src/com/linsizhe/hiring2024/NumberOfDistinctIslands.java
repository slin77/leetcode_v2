package com.linsizhe.hiring2024;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

// https://leetcode.com/problems/number-of-distinct-islands/?envType=company&envId=tiktok&favoriteSlug=tiktok-thirty-days
public class NumberOfDistinctIslands {
    int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, -1}, {0, 1}};
    int[][] grid;

    int xm;
    int ym;

    public int numDistinctIslands(int[][] grid) {
        this.grid = grid;
        HashSet<String> set = new HashSet();
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    StringBuilder sb = new StringBuilder();
                    List<int[]> list = new ArrayList();
                    xm = grid.length;// keep track of xm and ym for alignment
                    ym = grid[0].length;
                    dfs(i, j, list);
                    // encode as string
                    for (int[] item : list) {
                        sb.append("|");
                        sb.append(String.valueOf(item[0] - xm));
                        sb.append(",");
                        sb.append(String.valueOf(item[1] - ym));
                    }
                    String str = sb.toString();
                    if (!set.contains(str)) {
                        set.add(str);
                        res++;
                    }
                }
            }
        }
        return res;
    }

    private void dfs(int i, int j, List<int[]> list) {
        list.add(new int[]{i, j});
        grid[i][j] = 0;
        xm = Math.min(i, xm);
        ym = Math.min(j, ym);
        for (int[] dir : dirs) {
            int x = i+dir[0];
            int y = j+dir[1];
            if (x >= 0 && y >= 0 && x < grid.length && y < grid[0].length) {
                if (grid[x][y] == 1) {
                    dfs(x, y, list);
                }
            }
        }
    }
}
