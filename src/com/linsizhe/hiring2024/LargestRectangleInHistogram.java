package com.linsizhe.hiring2024;

import java.util.HashMap;
import java.util.Stack;

//https://leetcode.com/problems/largest-rectangle-in-histogram/
public class LargestRectangleInHistogram {
    // mono stack: two key elements -> one on top of stack when insert, one to
    // insert when pop it out
    // each will insert once and pop once!

    // mono increasing stack:
    // first smaller element on left: on top of stack when push element * (remember
    // this is property of stack)
    // first smaller element on right: element to insert when pop it out.
    // can find both in single pass!

    // mono descreasing stack:
    // first bigger element on left: on top of stack when push element * (remember
    // this is property of stack)
    // first bigger on right: element to insert when pop it out.
    // can fint both in single pass!
    public int largestRectangleArea(int[] heights) {
        // find left and right first element smaller than i, this is the area that i can
        // cover
        Stack<Integer> stack = new Stack();
        HashMap<Integer, Integer> leftMin = new HashMap();
        HashMap<Integer, Integer> rightMin = new HashMap();
        for (int i = 0; i < heights.length; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] > heights[i]) {
                int j = stack.pop();
                rightMin.put(j, i);
            }
            if (stack.isEmpty()) {
                leftMin.put(i, -1);
            } else {
                leftMin.put(i, stack.peek());
            }
            stack.push(i);
        }
        // element still in stack: no more smaller on right
        while (!stack.isEmpty()) {
            int i = stack.pop();
            rightMin.put(i, heights.length);
        }
        int max = Integer.MIN_VALUE;
        for (int i : leftMin.keySet()) {
            max = Math.max(max, heights[i] * (rightMin.get(i) - leftMin.get(i) - 1));
        }
        return max;
    }
}
