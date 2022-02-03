package com.linsizhe.amazonOS;

import java.util.HashMap;

public class FourSum2 {
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        HashMap<Integer, Integer> set1 = new HashMap();
        HashMap<Integer, Integer> set2 = new HashMap();

        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums1.length; j++) {
                int n = nums1[i] + nums2[j];
                set1.putIfAbsent(n, 0);
                set1.put(n, set1.get(n) + 1);
            }
        }

        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums1.length; j++) {
                int n = -nums3[i] - nums4[j];
                set2.putIfAbsent(n, 0);
                set2.put(n, set2.get(n) + 1);
            }
        }

        int count = 0;
        for (int num : set1.keySet()) {
            if (set2.containsKey(num))  count += set1.get(num) * set2.get(num);
        }
        return count;

    }
}
