package com.linsizhe.facebook;

import java.util.Arrays;
import java.util.HashSet;

// intersect of 2 arrays.
public class IntersectionOfTwoArrays {
    public int[] intersection(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = 0;
        int j = 0;
        HashSet<Integer> out = new HashSet<>();
        while(i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                i++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            } else {
                // we can update 2 pointers since they are equal!
                out.add(nums1[i]);
                i++;
                j++;
            }
        }
        int[] arr = new int[out.size()];
        i = 0;
        for (int num : out) {
            arr[i++] = num;
        }
        return arr;
    }

    // using HashMap.
     public int[] intersection2(int[] nums1, int[] nums2) {
         HashSet<Integer> set1 = new HashSet<>();
         HashSet<Integer> out = new HashSet<>();
         for (int num : nums1) {
             set1.add(num);
         }
         for (int num : nums2) {
             if (set1.contains(num)) {
                 out.add(num);
             }
         }
         // note that HashSet can not directly use toArray()!
         int[] arr = new int[out.size()];
         int i = 0;
         for (int num : out) {
             arr[i++] = num;
         }
         return arr;
     }

     // there is another binary search solution
     // idea: sort one of array and do binary search for each of another
     // N(LogN)
}
