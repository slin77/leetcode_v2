package com.linsizhe.facebook;

import java.util.List;


// https://leetcode.com/problems/leftmost-column-with-at-least-a-one/
public class LeftMostColumnWithAtLeastOne2nd {

    interface BinaryMatrix {
        public int get(int row, int col);
        public List<Integer> dimensions();
    }

    public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
        List<Integer> dim = binaryMatrix.dimensions();
        int row = dim.get(0);
        int col = dim.get(1);
        int leftmost = col - 1;
        boolean found = false;
        for (int i = 0; i < row; i++) {
            int curLeft = binarySearchRow(binaryMatrix, i, 0, leftmost);
            if (curLeft >= 0 && curLeft < col) {
                leftmost = Math.min(leftmost, curLeft);
                found = true;
            }
        }
        if (!found) {
            return -1;
        }
        return leftmost;
    }

    private int binarySearchRow(BinaryMatrix mat, int row, int start, int end) {
        // 1st equal to target
        while (start <= end) {
            int mid = start + (end - start) / 2;
            int num = mat.get(row, mid);
            if (num == 0) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return start;
    }
}
