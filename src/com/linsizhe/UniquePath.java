package com.linsizhe;

// Solution 1: colored algorithm
// class Solution {
//     public int uniquePaths(int m, int n) {
//         int[][] path = new int[m][n];
//         path[0][0] = 1;
//         for (int x = 0; x < m; x++) {
//             for (int y = 0; y < n; y++) {
//                 if (x + 1 < m) {
//                     path[x + 1][y] = path[x][y] + path[x + 1][y];
//                 }
//                 if (y + 1 < n) {
//                   path[x][y + 1] = path[x][y + 1] + path[x][y];
//                 }
//             }
//         }
//         return path[m - 1][n - 1];
//     }
// }

import java.util.Arrays;

// DP dp[x][y] = dp[x - 1][y] + dp[x][y - 1]
class UniquePath {
    public int uniquePaths(int m, int n) {
        int[][] path = new int[m][n];
        path[0][0] = 1;
        for (int x = 0; x < m; x++) {
            for (int y = 0; y < n; y++) {
                if (x == 0 && y == 0) continue; // dp[0][0 = 1] dont miss!
                int up = (x - 1) >= 0 ? path[x - 1][y] : 0;
                int left = (y - 1) >= 0 ? path[x][y - 1] : 0;
                path[x][y] = up + left;
                //System.out.println("x:" + x + "y:" + y + "is" +  path[x][y]);
            }
        }
        return path[m - 1][n - 1];
    }

    public static void main(String[] args) {
        int[][] test = new int[3][4];
        System.out.println(
                Arrays.deepToString(test)
        );
    }
}