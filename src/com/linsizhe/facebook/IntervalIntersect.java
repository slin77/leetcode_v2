package com.linsizhe.facebook;

import java.util.ArrayList;

// https://leetcode.com/problems/interval-list-intersections/submissions/
public class IntervalIntersect {
    public int[][] intervalIntersection(int[][] A, int[][] B) {
        int i = 0;
        int j = 0;
        ArrayList<int[]> out = new ArrayList<>();
        // Two pointers: move next to the one that have front closer.
        while (i < A.length && j < B.length) {
            int[] intersect = getIntersect(A[i], B[j]);
            if (intersect != null) {
                out.add(intersect);
            }
            int iNext = (i + 1) < A.length ? A[i + 1][0] : Integer.MAX_VALUE;
            int jNext = (j + 1) < B.length ? B[j + 1][0] : Integer.MAX_VALUE;
            if (iNext < jNext) {
                i++;
            } else {
                j++;
            }
        }
        int[][] outArr = new int[out.size()][2];
        return out.toArray(outArr);
    }

    public int[] getIntersect(int[] a, int[] b) {
        if (a[1] < b[0] || a[0] > b[1]) {
            return null;
        }
        int[] out = {Math.max(a[0], b[0]), Math.min(a[1], b[1])};
        return out;
    }
}
