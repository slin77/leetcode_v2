package com.linsizhe.facebook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// https://leetcode.com/problems/subarray-sum-equals-k/
// get cumulative array
// then it is just a variation of two sum (difference).
public class SubArrayEqualK {
    public int subarraySum(int[] nums, int k) {
        int[] cum = new int[nums.length];
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            cum[i] = sum;
        }
        HashMap<Integer, List<Integer>> map = new HashMap();
        // better way: we can do 2 sum build and check in 1 iteration
        // from left to right
        // so what we get from map would always been previous idxs

        // here it goes:
        //        int counter = 0;
        //        for (int i = 0; i < cum.length; i++) {
        //            if (cum[i] == k) counter++;
        //            counter += map.getOrDefault(cum[i] - k, 0);
        //            map.put(cum[i], map.getOrDefault(cum[i], 0) + 1);
        //        }
        for (int i = 0; i < cum.length; i++) {
            map.putIfAbsent(cum[i], new ArrayList());
            map.get(cum[i]).add(i);
        }
        int counter = 0;
        for (int i = cum.length - 1; i >= 0; i--) {
            if (cum[i] == k) counter++;
            int need = cum[i] - k;
            if (map.containsKey(need)) {
                for (Integer idx : map.get(need)) {
                    if (idx < i) {
                        counter++;
                    }
                }
            }
        }
        return counter;
    }
}
