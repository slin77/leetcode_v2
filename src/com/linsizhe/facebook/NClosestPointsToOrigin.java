package com.linsizhe.facebook;

import java.util.Arrays;
import java.util.Comparator;


// https://leetcode.com/problems/k-closest-points-to-origin/
public class NClosestPointsToOrigin {
    // public int[][] kClosest(int[][] points, int k) {
    //     PriorityQueue<int[]> pq = new PriorityQueue(Comparator.<int[]>comparingInt(a -> a[0] * a[0] + a[1] * a[1]));
    //     for (int[] point : points) {
    //         pq.add(point);
    //     }
    //     List<int[]> out = new ArrayList();
    //     for (int i = 0; i < k && !pq.isEmpty(); i++) {
    //         out.add(pq.poll());
    //     }
    //     int[][] outArr = new int[out.size()][2];
    //     out.toArray(outArr);
    //     return outArr;
    // }

    // using quick select!
    // 1. sort
    // 2. heap
    // 3. quick select
    private Comparator<int[]> comparator = Comparator.<int[]>comparingInt(a -> a[0] * a[0] + a[1] * a[1]);
    public int[][] kClosest(int[][] points, int k) {
        int l = 0;
        int r = points.length - 1;
        while (l < r) {
            int pivot = quickSelect(points, l, r);
            if (pivot > k) {
                r = pivot - 1;
            } else if (pivot < k) {
                l = pivot + 1;
            } else {
                break;
            }
        }
        return Arrays.copyOfRange(points, 0, k);
    }

    private int quickSelect(int[][] points, int l, int r) {
        int[] pivot = points[l];
        while (l < r) {
            while (l < r && comparator.compare(pivot, points[r]) <= 0) r--;// 找到右边不符合的第一个 往左边堆
            points[l] = points[r];// l represent the first left available slot, since pivot already recorded
            while (l < r && comparator.compare(pivot, points[l]) >= 0 ) l++;// find the first not obey on the left side, put to right
            points[r] = points[l];// r represent the first right available slot, since origial r already moved to the l
        }
        points[l] = pivot;
        return l;
    }
}
