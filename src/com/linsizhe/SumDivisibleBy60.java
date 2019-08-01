package com.linsizhe;

import java.util.HashMap;

// https://leetcode.com/problems/pairs-of-songs-with-total-durations-divisible-by-60/
// use mod for divisibility problems.
class SumDivisibleBy60 {
    public int numPairsDivisibleBy60(int[] time) {
        int[] mods = new int[time.length];
        for (int i = 0; i < time.length; i++) {
            mods[i] = time[i] % 60;
        }
        HashMap<Integer, Integer> diffs = new HashMap();
        for (int i = 0; i < time.length; i++) {
            diffs.put(mods[i], diffs.getOrDefault(mods[i], 0) + 1);
        }
        int total = 0;
        for (int i = 0 ; i < time.length;i++) {
            int num = mods[i];
            // 30, 0, not count itself
            if (num == 30) {
                total += Math.abs(diffs.getOrDefault(30, 0) - 1);
            } else if (num == 0) {
                total += Math.abs(diffs.getOrDefault(0, 0) - 1);
            } else {
                total += diffs.getOrDefault(60 - num, 0);
            }
        }
        return total / 2; // i, i -> j, i  count as just one. 
    }
}
