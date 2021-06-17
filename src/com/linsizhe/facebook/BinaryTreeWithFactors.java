package com.linsizhe.facebook;

import java.util.Arrays;
import java.util.HashMap;

//https://leetcode.com/problems/binary-trees-with-factors/
public class BinaryTreeWithFactors {
    // Top Down, left we have N choices, Right we have M choises
    // Total M, N.
    // Each left, and right. Choice goes on.
    private static long MOD = (long) Math.pow(10, 9) + 7;
    public int numFactoredBinaryTrees(int[] arr) {
        Arrays.sort(arr);
        HashMap<Integer, Long> count = new HashMap();
        count.put(arr[0], 1L);

        // for each number, increasing, we choose
        // from smaller set, which two can be leaf
        for (int i = 1; i < arr.length; i++) {
            int num = arr[i];
            long cur = 1L;// self

            // count's key are all <= cur arr
            // and leaf could only be chosen from
            // "<="."
            for (int j : count.keySet()) {
                if (num % j == 0 && count.containsKey(num / j)) {
                    cur += count.get(j) * count.get(num / j);
                }
            }
            count.put(num, cur);
        }

        long sum = 0;
        for (int i : count.keySet()) {
            sum = (sum + count.get(i)) % MOD;
        }
        return (int) sum;

    }

}
