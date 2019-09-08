package com.linsizhe;

// union find!!
// 2D -> flatten 1D for parents.
public class NumOfIsland {
    int rowSize;
    int count = 0;
    public int numIslands(char[][] grid) {

        int m = grid.length;
        if (m == 0) {
            return 0;
        }

        int n = grid[0].length;
        rowSize = n;

        int[] parent = new int[m * n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                parent[getTag(i, j)] = getTag(i, j);
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '0') {
                    continue;
                }
                count++;
                if (i + 1 < m && grid[i + 1][j] == '1') {
                    union(i, j, i + 1, j, parent);
                }
                if (j + 1 < n && grid[i][j + 1] == '1') {
                    union(i, j, i, j + 1, parent);
                }
            }
        }

        return count;
    }

    public int getTag(int i, int j) {
        return i * rowSize + j;
    }

    public int root(int i, int j, int[] parents) {
        int x = getTag(i, j);
        if (x == parents[x]) {
            return x;
        }
        while (x != parents[x]) {
            parents[x] = parents[parents[x]];
            x = parents[x];
        }
        return x;
    }

    public void union(int i, int j, int m, int n, int[] parents) {
        int x = root(i, j, parents);
        int y = root(m, n, parents);

        if (x != y) { // Key point! When parent different, they currently belong to different parents and we preform a
                      // real union
            parents[x] = y;
            count--; // whenver we have real union, total count will -1.
        }
    }
}
