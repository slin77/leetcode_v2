package com.linsizhe.hiring2024;

import java.util.ArrayList;
import java.util.List;

//  https://leetcode.com/problems/intersection-of-three-sorted-array
public class IntersectionOfThreeSortedArray1213 {
    public List<Integer> arraysIntersection(int[] arr1, int[] arr2, int[] arr3) {
        int p1 = 0, p2 = 0, p3 = 0;
        ArrayList<Integer> out = new ArrayList();
        while (p1 < arr1.length && p2 < arr2.length && p3 < arr3.length) { // any of them reach end, no way to have common!
            int i1 = arr1[p1];
            int i2 = arr2[p2];
            int i3 = arr3[p3];

            if (i1 == i2 && i2 == i3) {
                out.add(arr1[p1++]);
                p2++;
                p3++;
            } else { // always move the smallest
                int min = Math.min(i3, Math.min(i1, i2));
                if (min == i1) {
                    p1++;
                } else if (min == i2) {
                    p2++;
                } else {
                    p3++;
                }
            }
        }
        return out;
    }
}
