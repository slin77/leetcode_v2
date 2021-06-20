package com.linsizhe.facebook;

// https://leetcode.com/problems/ugly-number-ii/
public class UglyNumberII {
    // public int nthUglyNumber(int n) {
    //     PriorityQueue<Long> pq = new PriorityQueue();
    //     long ugly = 1L;
    //     HashSet<Long> used = new HashSet();
    //     used.add(ugly);
    //     for (int i = 0 ; i < n - 1; i++) {
    //         if (!used.contains(ugly * 2)) {
    //             pq.add(ugly * 2);
    //             used.add(ugly * 2);
    //         }
    //         if (!used.contains(ugly * 3)) {
    //             pq.add(ugly * 3);
    //             used.add(ugly * 3);
    //         }
    //         if (!used.contains(ugly * 5)) {
    //             pq.add(ugly * 5);
    //             used.add(ugly * 5);
    //         }
    //         ugly = pq.poll();
    //     }
    //     return (int) ugly;
    // }

    public int nthUglyNumber(int n) {
        // 2 4  6
        // 3 9  9
        // 5 10 15

        // 1. we know (i-1)th ugly number we know i-th column (so we keep ugly, we know what entire 3 list)
        //        ex. col10 for 2 is ugly(9) * 2,
        // 2. like merge k-sorted lists
        // we choose one from the all head of three. Choose one we move head!

        int pt2 = 0;
        int pt3 = 0;
        int pt5 = 0;
        int[] ugly = new int[n];

        ugly[0] = 1;
        for (int i = 1; i < n; i++) {
            int ft2 = ugly[pt2] * 2;
            int ft3 = ugly[pt3] * 3;
            int ft5 = ugly[pt5] * 5;

            int ug  = Math.min(Math.min(ft2, ft3), ft5);
            ugly[i] = ug;

            // necesssary! To avoid put repeated value in frontier in the ulgy array.
            if (ft2 == ug) {
                pt2++;
            }

            if (ft3 == ug) {
                pt3++;
            }

            if (ft5 == ug) {
                pt5++;
            }
        }
        return ugly[n - 1];
    }
}
