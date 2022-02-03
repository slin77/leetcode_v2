package com.linsizhe.amazonOA;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class PrimeAirPair {
    public static List<int[]> getPair(int[][] a, int[][] b, int target) {
        Arrays.<int[]>sort(a, Comparator.comparingInt(x -> x[1]));
        Arrays.<int[]>sort(b, Comparator.comparingInt(x -> x[1]));

        int i = 0;
        int j = b.length - 1;

        List<int[]> out = new ArrayList<>();
        int curMax = 0;

        while (i < a.length || j >= 0) {
            int curSum = a[Math.min(a.length - 1, i)][1] + b[Math.max(0, j)][1];
            if (curSum > target) {
                if (j == 0) break;
                j--;
            } else if (curSum <= target) {
                if (curSum == curMax) {
                    out.add(new int[]{a[i][0], b[j][0]});
                } else if (curSum > curMax) {
                    out = new ArrayList<>();
                    out.add(new int[]{a[i][0], b[j][0]});
                    curMax = curSum;
                }
                if (i == a.length) break;
                i++;
            }
        }

        return out;
    }

    public static void main(String[] args) {
        int[][] a = new int[][]{{1, 3000}, {2, 5000}, {3, 7000}, {4, 10000}};
        int[][] b = new int[][]{{1, 2000}, {2, 3000}, {3, 4000}, {4, 5000}};
        int target = 10000;

        System.out.println(Arrays.deepToString(getPair(a, b, target).toArray()));

//        int[][] a = new int[][]{{1, 2000}, {2, 4000}, {3, 6000}};
//        int[][] b = new int[][]{{1, 2000}};
//        int target = 7000;
//
//        System.out.println(Arrays.deepToString(getPair(a, b, target).toArray()));
    }
}
