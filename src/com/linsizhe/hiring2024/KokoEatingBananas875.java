package com.linsizhe.hiring2024;

import java.util.Arrays;

public class KokoEatingBananas875 {
    // binary search:
    // first smaller r
    // first greater l
    // first equal or greater r + 1 *
    // first equal or smaller l - 1
    public int minEatingSpeed(int[] piles, int h) {
        int total = Arrays.stream(piles).reduce(Integer::sum).getAsInt();
        int r = Arrays.stream(piles).reduce(Integer::max).getAsInt();
        int l = total / h;
        Arrays.sort(piles);
        // get first smaller hour
        while (l <= r) {
            int mid = l + (r - l) / 2;
            int hour = getNum(piles, mid);
            if (hour <= h) { // speed too fast, reduce r
                r = mid - 1;
            } else {
                l = mid + 1; // speed too slow, increase l
            }
        }
        return r + 1; // r is first smaller, next is first greater than equal!
    }

    int getNum(int[] piles, int speed) {
        int l = 0;
        int r = piles.length - 1;

        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (piles[mid] < speed) {
                l = mid + 1;
            } else {
                r = mid - 1;;
            }
        }
        // r is first idx < speed
        int res = Math.max(r + 1, 0);
        for (int i = r + 1; i < piles.length; i++) {
            res += Math.ceil((double) piles[i] / speed);
        }
        return res;
    }
}
