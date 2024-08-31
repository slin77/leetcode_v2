package com.linsizhe.hiring2024;

import java.util.LinkedList;
import java.util.Queue;

//  https://leetcode.com/problems/shortest-path-in-a-hidden-grid
public class ShortestPathInHiddenGrid1778 {
    /**
     * // This is the GridMaster's API interface.
     * // You should not implement it, or speculate about its implementation
     * class GridMaster {
     *     boolean canMove(char direction);
     *     void move(char direction);
     *     boolean isTarget();
     * }
     */
    interface GridMaster {
          boolean canMove(char direction);
          void move(char direction);
          boolean isTarget();
    }
// dfs to build grid
// bfs to find target
    class Solution {
        int[][] dirs = new int[][]{{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
        char[] m = new char[]{'U', 'D', 'L', 'R'};
        char[] r = new char[]{'D', 'U', 'R', 'L'};
        int min = Integer.MAX_VALUE;
        int w = 50;
        int l = 50;
        public int findShortestPath(GridMaster master) {
            int[][] map = new int[w][l];
            boolean[][] visited = new boolean[w][l];
            map[w/2][w/2] = -1;
            dfs(w/2, l/2, map, 0, master, visited);
            int dist = 0;
            Queue<int[]> q = new LinkedList();
            q.add(new int[]{w/2, l/2});
            while (!q.isEmpty()) {
                int size = q.size();
                dist++;
                for (int i = 0; i < size; i++) {
                    int[] cur = q.poll();
                    map[cur[0]][cur[1]] = 2;
                    for (int[]  dir : dirs) {
                        int x = cur[0] + dir[0];
                        int y = cur[1] + dir[1];
                        if (x >= 0 && x <= l && y >= 0 && y < w && map[x][y] != 2) {
                            if (map[x][y] == 3) {
                                return  dist;
                            }
                            q.add(new int[]{x, y});
                        }
                    }
                }
            }
            return -1;
        }

        // need to track visited separately from grid map!!
        void dfs(int x, int y, int[][] map, int d, GridMaster master, boolean[][] visited) {
            if (master.isTarget()) {
                map[x][y] = 3;
                return;
            }
            for (int i = 0; i < 4; i++) {
                char op = m[i];
                int x1 = dirs[i][0] + x;
                int y1 = dirs[i][1] + y;
                if (master.canMove(op)) {
                    if (x1 >= 0 && x1 < w && y1 >= 0 && y1 < w && !visited[x1][y1] && map[x1][y1] != 2) {
                        master.move(op);
                        visited[x1][y1] = true;
                        map[x1][y1] = 1;// marking, not need to backtrack
                        dfs(x1, y1, map, d + 1, master, visited);
                        //visited[x1][y1] = false;
                        master.move(r[i]);
                    }
                } else {
                    map[x1][y1] = 2;// 2 means blocked
                }
            }
        }
}
}
