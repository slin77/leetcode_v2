package com.linsizhe.facebook;

import java.util.Arrays;

// https://leetcode.com/problems/next-permutation/
public class NextPermutation {
    // knowledge: 1. descending number is at its max form
    //            2. ascending is at its smallest form.
    //            3. get first number the can swap with bigger on its right!
    //            4. after swap, right of the num still the max form
    //            5. after swap, the num is already bigger no matter what is on the right
    //            6. let right shrink from max form to min form. we keep the gap minimum.
    public void nextPermutation(int[] nums) {
        int[] occur = new int[101];
        Arrays.fill(occur, -1);
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            occur[nums[i]] = i;
            max = Math.max(max, nums[i]);
        }
        for (int i = nums.length - 1; i >= 0; i--) {
            // this can be optimized by finding the first descending num from right.
            for (int j = nums[i] + 1; j <= max; j++) {
                // we found a number that has any number bigger on its right (before it's section already max form)
                // after swap, from start to the swapped number's indx, we can keep the list bigger
                // now we make rest as small as possible
                // note that rest is now descending (at it's max form).
                // descending make it min form, so we shrink it the most!
                if (occur[j] > i) {
                    swap(nums, i, occur[j]); // min number of swap
                    reverse(nums, i + 1, nums.length - 1);
                    return;
                }
            }
        }
        // then it is at its max, greatest num at first idx.
        // smallest at last idx.
        reverse(nums, 0, nums.length - 1);
    }

    public void reverse(int[] nums, int i, int j) {
        while (i < j) {
            swap(nums, i++, j--);
        }
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
