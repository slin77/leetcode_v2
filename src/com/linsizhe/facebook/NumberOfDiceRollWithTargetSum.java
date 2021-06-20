package com.linsizhe.facebook;

import java.util.HashMap;

// https://leetcode.com/problems/number-of-dice-rolls-with-target-sum/
public class NumberOfDiceRollWithTargetSum {
    HashMap<String, Integer> memo = new HashMap();
    int MOD = 1000000000 + 7;
    public int numRollsToTarget(int d, int f, int target) {
        if (d == 0 && target == 0) {
            return 1;
        }
        if (d == 0 || target == 0) {
            return 0;
        }

        String key = d + " " + target;// pay attention to key!
        if (memo.containsKey(key)) return memo.get(key);
        int res = 0;
        for (int i = 1; i <= f; i++) {
            if (i <= target) {
                res = (res +  numRollsToTarget(d - 1, f, target - i)) % MOD;
            }
        }
        memo.put(key, res);
        return res;
    }
}
