package com.linsizhe.hiring2024;

import java.util.Stack;

// https://leetcode.com/problems/subarray-with-elements-greater-than-varying-threshold
public class SubArrayWithElementGreaterThanThreshold2334 {
    // mono stack: two key elements -> one on top of stack when insert, one to
    // insert when pop it out
    // each will insert once and pop once!

    // mono increasing stack:
    // first smaller element on left: on top of stack when push element * (remember
    // this is property of stack)
    // first smaller element on right: element to insert when pop it out.
    // can find both in single pass!
    // nums[i] is the smallest element from right - left;
    // see: https://leetcode.com/problems/subarray-with-elements-greater-than-varying-threshold/

    // mono descreasing stack:
    // first bigger element on left: on top of stack when push element * (remember
    // this is property of stack)
    // first bigger on right: element to insert when pop it out.
    // can fint both in single pass!
    // nums[i] is the greatest element from right - left;
    public int validSubarraySize(int[] nums, int threshold) {
        Stack<Integer> s = new Stack();
        // mono increasing stack
        // first smaller on left and right --> i is greatest in between
        // if i == threshold / k
        int[] left = new int[nums.length];
        int[] right = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int cur = nums[i];
            while (!s.isEmpty() && nums[s.peek()] > cur) {
                int prevIdx = s.pop();
                right[prevIdx] = i;// first smalller idx of preIdx is i
            }
            if (s.isEmpty()) {
                left[i] = -1;
            } else {
                left[i] = s.peek();
            }
            s.push(i);
        }

        while (!s.isEmpty()) {
            right[s.pop()] = nums.length;
        }

        for (int i  = 0; i < nums.length; i++) {
            int l = right[i] - left[i] - 1;
            if (nums[i] * l > threshold) {
                return l;
            }
        }
        return -1;
    }
}
