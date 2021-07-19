package com.linsizhe.facebook;

// https://leetcode.com/problems/toeplitz-matrix/
public class ToeplitzMatrix {
    // key check first row, then check first column
    // they are the wings of the matrix for all diagnals.
    public boolean isToeplitzMatrix(int[][] matrix) {
        //first row
        for (int j = 0; j < matrix[0].length; j++) {
            int i = 0;
            // dont increment outer i!
            int aj = j;
            while (i < matrix.length && aj < matrix[0].length) {
                if (i > 0 && matrix[i][aj] != matrix[i - 1][aj - 1]) {
                    return false;
                }
                i++;
                aj++;
            }
        }

        // first col
        for (int i = 1; i < matrix.length; i++) {
            int j = 0;
            int ai = i;
            while (ai < matrix.length && j < matrix[0].length) {
                // j as indicator for boundary check
                if (j > 0 && matrix[ai][j] != matrix[ai - 1][j - 1]) {
                    return false;
                }
                ai++;
                j++;
            }
        }
        return true;
    }
}
