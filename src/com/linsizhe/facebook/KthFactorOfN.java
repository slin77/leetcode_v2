package com.linsizhe.facebook;

import java.util.ArrayList;

//https://leetcode.com/problems/the-kth-factor-of-n/
public class KthFactorOfN {
    public int kthFactor(int n, int k) {
        // a b c d e srt(n) e' d' c' b' a'
        // factors come in pair and converge to srt(n)!
        // we find first half then second half is also found!
        int i;
        ArrayList<Integer> mirrors = new ArrayList();// descending
        for (i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                if (n / i != i) mirrors.add(n / i);// sqr root num we dont count twice
                k--;
            }
            if (k == 0) return i;
        }
        if (mirrors.size() < k) return -1;
        return mirrors.get(mirrors.size() - k);
    }
}
