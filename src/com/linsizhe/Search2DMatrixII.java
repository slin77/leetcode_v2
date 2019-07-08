package com.linsizhe;

public class Search2DMatrixII {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0) {
            return false;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int i = 0, j = n - 1; // top right -> so one direction is smaller, another is larger.
        // Think this as traversing a BST.
        while (i < m && j >= 0) {
            if (matrix[i][j] == target) {
                return true;
            }
            if (matrix[i][j] < target) {
                i++; // all row above it < matrix[i][j];
            } else if (matrix[i][j] > target) {
                j--;
            }
        }
        return false;
    }
}
