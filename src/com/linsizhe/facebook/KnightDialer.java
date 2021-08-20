package com.linsizhe.facebook;

// https://leetcode.com/problems/knight-dialer/
public class KnightDialer {
    static int[][] dirs = new int[][]{{-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {-2, -1}, {-1, -2}, {1, -2}, {2, -1}};
    static int[][] board = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {-1, 0, -1}};
    int maxN;
    long MOD = (long) Math.pow(10, 9) + 7;
    public int knightDialer(int n) {
        long sum = 0L;
        long[][][] mem = new long[4][3][n + 1];
        maxN = n;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] >= 0) {
                    sum += dfs(i, j, 1, mem);
                }
            }
        }
        return (int) (sum % MOD);
    }

    // meaning: at current i, j with current length (including having current i, j)
    // how many steps can we get
    long dfs(int i, int j, int curN, long[][][] mem) {
        if (curN > maxN) return 0L;
        if (i < 0 || j < 0 || i >= 4 || j >= 3 || (i == 3 && j != 1)) {
            return 0;
        }

        // Must do after validility check! Otherwise will count invalid moves!
        // i, j is valid, and we reach to target length
        if (curN == maxN) return 1L;

        if (mem[i][j][curN] > 0) return mem[i][j][curN];
        long s = 0L;
        for (int[] dir: dirs) {
            s += dfs(i + dir[0], j + dir[1], curN + 1, mem) % MOD;
        }
        mem[i][j][curN] = s;
        return s;
    }
}
