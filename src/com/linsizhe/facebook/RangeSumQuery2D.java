package com.linsizhe.facebook;

public class RangeSumQuery2D {
    int[][] m;

    public RangeSumQuery2D(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                int up = i > 0 ? matrix[i - 1][j] : 0;
                int left = j > 0 ? matrix[i][j - 1] : 0;
                int topc = (i > 0 && j > 0) ? matrix[i - 1][j  -1] : 0;
                matrix[i][j] = up + left - topc + matrix[i][j];
            }
        }

        m = matrix;
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int[][] n = m;

        int d = m[row2][col2];
        int a = row1 - 1 >= 0 ? m[row1 - 1][col2] : 0;
        int b = col1 - 1 >= 0 ? m[row2][col1 - 1] : 0;
        int top = (col1 > 0 && row1 > 0) ? m[row1 - 1][col1 - 1] : 0;
        return d - a - b + top;
    }
}
