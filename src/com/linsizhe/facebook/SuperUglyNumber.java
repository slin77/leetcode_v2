package com.linsizhe.facebook;

// https://leetcode.com/problems/super-ugly-number/
public class SuperUglyNumber {
    public int nthSuperUglyNumber(int n, int[] primes) {
        int[] ptrs = new int[primes.length];
        int[] ugly = new int[n];

        //Arrays.fill(ptrs, 1);

        ugly[0] = 1;
        // HashSet<Integer> uglys = new HashSet();
        // uglys.add(1);
        for (int i = 1; i < n; i++) {
            int minIdx = 0;
            int curMin = Integer.MAX_VALUE;
            for (int j = 0; j < primes.length; j++) {
                int curPrime = ugly[ptrs[j]] * primes[j];
                if (curPrime < curMin) {
                    curMin = curPrime;
                    minIdx = j;
                }
            }
            for (int j = 0; j < ptrs.length; j++) {
                if (ugly[ptrs[j]] * primes[j] == curMin) {
                    ptrs[j]++;
                }
            }
            // ptrs[minIdx]++;
            // Wrong!  This doesn't handle when there are
            // same value on the frontier! If there is a same
            // this only update one of them, then next iteration the value
            // will get chosen again! so we have to update all of them!
            // so all pointers move forward.
            ugly[i] = curMin;
        }
        return ugly[n - 1];
    }
}
