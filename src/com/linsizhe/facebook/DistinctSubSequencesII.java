package com.linsizhe.facebook;

import java.util.Arrays;

// https://leetcode.com/problems/distinct-subsequences-ii/
public class DistinctSubSequencesII {
    public int distinctSubseqII(String s) {
        int[] last= new int[26];
        int[] dp = new int[s.length() + 1];
        // tip: let dp[0] be ""
        // num for distinct suseq at idx i will be dp[i + 1]
        dp[0] = 1;
        int MOD = (int) Math.pow(10, 9) + 7;
        Arrays.fill(last, -1);
        for (int i = 0; i < s.length(); i++) {
            int lastOccur = last[s.charAt(i) - 'a'];
            // i + 1 represent i!
            dp[i + 1] = dp[i] * 2 % MOD;
            if (lastOccur >= 0) {
                // [seq....] a1......a2
                // we already use a1 to count it once, a2 will be double count
                // need to substract all [seq......] a1 which is dp[idx[a1] - 1]
                dp[i + 1] -= dp[lastOccur + 1 - 1]; // last[idx] + 1 - 1;
            }
            dp[i + 1] %= MOD;
            last[s.charAt(i) - 'a'] = i;
        }

        if (dp[s.length()] < 0) {
            dp[s.length()] += MOD;
        }

        return dp[s.length()] - 1;
    }
}
