package com.linsizhe.facebook;

//https://leetcode.com/problems/squares-of-a-sorted-array/
public class SquaresOfASortedArray {
    // two pointers start from each side.
    public int[] sortedSquares(int[] nums) {
        int i = 0;
        int j = nums.length - 1;
        int ptr = nums.length - 1;
        int[] out = new int[nums.length];
        while (i <= j) { // <= sign must be there otherwise will miss one
            int buf = 0;
            // stop the first ptr when we are >=0;
            if (nums[i] >= 0) {
                buf = nums[j--];
            } else if (-1 * nums[i] >= nums[j]) {
                buf = nums[i++];
            } else {
                buf = nums[j--];
            }
            out[ptr--] = buf * buf;
        }
        return out;
    }
}
