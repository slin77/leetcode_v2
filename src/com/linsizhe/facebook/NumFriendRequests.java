package com.linsizhe.facebook;

//   // 1 + 2:  (0.5 a + 7, a] b  since 0.5a + 7 < a => a > 14
// b <=100 or a >=100

// lessons learned:
// 1.histogram for counter # of occurance
// 2. cummulative sum for counter value in range of histogram cum[i] - cum[j] for (j, i]
// 2.1 integer case we can just use divide if 0.5n case can exist!
// 3. note for exclude self

// https://leetcode.com/problems/friends-of-appropriate-ages/
public class NumFriendRequests {
    public int numFriendRequests(int[] ages) {
        int[] ageCount = new int[121];// 1 - 120 histogram
        int[] cumSum = new int[121];
        for (int i : ages) ageCount[i]++;
        cumSum[0] = ageCount[0];
        for (int i = 1; i < cumSum.length; i++) {
            cumSum[i] = ageCount[i] + cumSum[i - 1];
        }
        int res = 0;
        for (int i = 15; i < 121; i++) {
            // each possible age
            // check how many "b" in its range
            // exclude self
            int countB = (cumSum[i] - cumSum[i / 2 + 7]) - 1;
            // exclude self since a is in (o.5a, a] when ageCount[i]
            // has nonzero value this means we have to exclude self once!
            // zero case is 0 anyways!

            // note here left bound is "(" so 1.x we can ignore x part
            res += countB * ageCount[i];// exclude
        }
        return res;
    }
}
