package com.linsizhe.facebook;

//https://leetcode.com/problems/diagonal-traverse/
public class DiagnoalTraverse {
    public int[] findDiagonalOrder(int[][] matrix) {
        if (matrix.length == 0) {
            return new int[0];
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int[] out = new int[m * n];
        int row = 0;
        int col = 0;
        boolean right = true;
        for (int i = 0; i < out.length; i++) {
            if (row < 0 || row == m || col < 0 || col == n) {
                return out;
            }
            out[i] = matrix[row][col];
            if (right) {
                if (row != 0 && col + 1 != n) {
                    row--;
                    col++;
                    // when go up if we have one right we go right and start down,
                    // if no right, we go down and start down.
                } else if (col + 1 != n) {
                    col++;
                    right = false;
                } else {
                    row++;
                    right = false;
                }
            } else {
                if (row + 1 != m && col != 0) {
                    row++;
                    col--;
                    // when go down if have one down we go down and start up,
                    // if no down, we go right and start up.
                } else if (row + 1 != m) {
                    row++;
                    right = true;
                } else {
                    col++;
                    right = true;
                }
            }
        }
        return out;
    }
}
