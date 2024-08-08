package com.linsizhe.hiring2024;

import java.util.LinkedList;
import java.util.Queue;

// https://leetcode.com/problems/shortest-path-in-binary-matrix/?envType=company&envId=facebook&favoriteSlug=facebook-six-months
public class ShortestPathInBinaryMatrix1091 {
    // bfs
    public int shortestPathBinaryMatrix(int[][] grid) {
        int[][] dirs = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}, {1, 1}, {-1, -1}, {1, -1}, {-1, 1}};
        int[] start = {0, 0};
        Queue<int[]> q = new LinkedList();
        if (grid[0][0] == 0) {
            q.offer(start);
        }
        if (grid.length == 1 && grid[0].length == 1 && !q.isEmpty()) {
            return 1;
        }
        int res = 1;
        boolean[][] visited= new boolean[grid.length][grid[0].length];
        visited[0][0] = true;
        while (!q.isEmpty()) {
            int size = q.size();
            res++;
            for (int i = 0; i < size; i++) {
                int[] cur = q.poll();
                for (int dir[] : dirs) {
                    int x = cur[0] + dir[0];
                    int y = cur[1] + dir[1];
                    if (x < 0 || x == grid.length || y < 0 || y == grid[0].length) {
                        continue;
                    }
                    if (grid[x][y] == 0) {
                        if (x == grid.length - 1 && y == grid[0].length -1)  {
                            return res;
                        }
                        if (!visited[x][y]) {
                            q.offer(new int[]{x, y});
                            visited[x][y] = true;
                        }
                    }
                }

            }
        }
        return -1;
    }
}
