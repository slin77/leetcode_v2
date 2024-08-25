package com.linsizhe.hiring2024;

// https://leetcode.com/problems/closest-prime-numbers-in-range/?envType=company&envId=tiktok&favoriteSlug=tiktok-thirty-days
public class ClosestPrimeInRange {
    public int[] closestPrimes(int left, int right) {
        boolean[] mark = new boolean[right + 1];
        for (int i = 2; i < mark.length; i++) {
            if (!mark[i] && i < 46341) { // since 46341^2 will overflow
                for (int j = i * i; j < mark.length; j+=i) { // start from i * 1!
                    mark[j] = true;
                }
            }
        }
        int[] cur = new int[2];
        cur[0] = -1;
        cur[1] = -1;
        int x = -1;
        int y = -1;
        int min = right + 1;
        int mx = -1;
        int my = -1;
        for (int i = Math.max(left, 2); i <= right; i++) {
            if (!mark[i]) {
                if (x == -1) {
                    x = i;
                } else if (y == -1) {// dont forget to update mx, my in this case
                    y = i;
                    min = y - x;
                    mx = x;
                    my = y;
                } else {
                    x = y;
                    y = i;
                    if (y - x < min) {
                        mx = x;
                        my = y;
                        min = y - x;
                    }
                }
            }
        }
        return new int[]{mx, my};
    }
}
