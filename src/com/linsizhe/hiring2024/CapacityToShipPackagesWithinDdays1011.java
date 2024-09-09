package com.linsizhe.hiring2024;

import java.util.Arrays;

// https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/

//NOTE: this solution does not pass!
public class CapacityToShipPackagesWithinDdays1011 {
    public int shipWithinDays(int[] weights, int days) {
        int l = 0;
        int r = Arrays.stream(weights).reduce(Integer::sum).getAsInt();
        while (l <= r) {
            int mid = l + (r - l) / 2;


            // for some reason this calculation is not correct!!!
            int d = 1;// start  with  1  since d++ indicates need next day
            int curLoad = 0;
            for  (int weight : weights)  {
                curLoad += weight;
                if (curLoad > mid) {
                    d++;
                    curLoad = weight;
                }
            }
            if (d <= days) {
                r = mid  - 1;
            } else {
                l = mid + 1;
            }
        }
        return r + 1;
    }
}
