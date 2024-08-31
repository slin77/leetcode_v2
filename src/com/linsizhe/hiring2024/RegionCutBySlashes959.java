package com.linsizhe.hiring2024;

// https://leetcode.com/problems/regions-cut-by-slashes/description/
public class RegionCutBySlashes959 {
    // convert to number of islands
    // to 9 dots
    int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int regionsBySlashes(String[] grid) {
        int[][] map = new int[grid[0].length() * 3][grid.length * 3];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length(); j++) {
                // center
                int x = i * 3 + 1;
                int y = j * 3 + 1;
                if (grid[i].charAt(j) == '/') {  // ---> x
                    map[x + 1][y - 1] = 1;       // |
                    // y
                    map[x][y] = 1;
                    map[x - 1][y + 1] = 1;
                } else if (grid[i].charAt(j) == '\\') {
                    map[x - 1][y  - 1] = 1;
                    map[x][y] = 1;
                    map[x + 1][y + 1] = 1;
                }
            }
        }
        int res = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] == 0) {
                    res++;
                    dfs(map, i, j);
                }
            }
        }
        return res;
    }

    void dfs(int[][] grid, int i, int j) {
        if (grid[i][j] == 1) return;
        grid[i][j] = 1;
        for (int[] dir : dirs) {
            int x = i + dir[0];
            int y = j + dir[1];
            if (x >= 0 && x < grid.length && y >=0 && y < grid[0].length) {
                dfs(grid, x, y);
            }
        }
    }
}
