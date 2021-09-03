package com.linsizhe.snapchat;

import java.util.LinkedList;
import java.util.Queue;

public class ShortestBridge {
    private static int[][] dirs = new int[][]{{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
    public int shortestBridge(int[][] grid) {
        int color = 2;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    dfs(grid, i, j, color++);
                }
            }
        }
        Queue<int[]> q = new LinkedList();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 2) {
                    q.offer(new int[]{i, j});
                }
            }
        }
        int res = 0;
        int[][] visited = new int[grid.length][grid[0].length];
        while (!q.isEmpty()) {
            int n = q.size();
            for (int i = 0; i < n; i++) {
                int[] cur = q.poll();
                for (int[] dir: dirs) {
                    int ix = cur[0] + dir[0];
                    int jx = cur[1] + dir[1];
                    if (ix < 0 || jx < 0 || ix >= grid.length || jx >= grid[0].length || visited[ix][jx] == 1) {
                        continue;
                    }
                    if (grid[ix][jx] == 3) {
                        return res;
                    }
                    if (grid[ix][jx] == 0) {
                        q.offer(new int[]{ix, jx});
                        // we can do visited mark during expansion, this can save one more round!
                        visited[ix][jx] = 1;
                    }
                }
            }
            res++;
        }
        return res;
    }

    public void dfs(int[][] grid, int i, int j, int color) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length) return;
        if (grid[i][j] != 1) return;
        grid[i][j] = color;
        for (int[] dir: dirs) {
            dfs(grid, i + dir[0], j + dir[1], color);
        }
    }
}
