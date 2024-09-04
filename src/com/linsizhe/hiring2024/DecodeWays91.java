package com.linsizhe.hiring2024;

// https://leetcode.com/problems/decode-ways/
public class DecodeWays91 {
    int res = 0;
    String s;

    public int numDecodings(String s) {
        // dp = dp[i] + (dp[i - 2] when (i - 1, i) can also form a vaid num)
        // similar to bag problem. num of ways!
        int[] dp = new int[s.length() + 1];
        dp[0] = s.charAt(0) == '0'? 0 : 1;
        for (int i = 1; i < s.length(); i++) {
            int d1 = s.charAt(i)  - '0';
            int d2 = (s.charAt(i - 1) - '0') * 10 + (s.charAt(i) - '0');
            if (d1 > 0 && d1 <= 26) {
                dp[i] += dp[i - 1];
            }
            // form as two digit from previous two
            if (s.charAt(i - 1) != '0' && d2 > 0 && d2 <= 26)  {
                dp[i]  += i  - 2 < 0 ? 1 : dp[i - 2]; // for dp[-1] zero chars but there is still  one way to do!
            }
        }
        return dp[s.length() - 1];
    }
    // TLE!!!
    // public int numDecodings(String s) {
    //     this.s = s;
    //     dfs(0, 0);
    //     return res;
    //     // dp[i] = dp[i - 1] + s[i] + dp[i - 2]
    // }

    // void dfs(int idx, int curVal) {
    //     if (curVal == 0 && s.charAt(idx) == '0') return;
    //     curVal =  curVal * 10 + (s.charAt(idx) - '0');
    //     if (curVal > 26 || curVal < 1) {
    //         return;
    //     }
    //     if (idx == s.length() - 1) {
    //         System.out.println(curVal);
    //         res++;
    //         return;
    //     }
    //     dfs(idx + 1, curVal);
    //     dfs(idx + 1, 0);
    // }
}
