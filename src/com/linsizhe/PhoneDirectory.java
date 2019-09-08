package com.linsizhe;

import java.util.LinkedList;
import java.util.Queue;

class PhoneDirectory {

    int[] nums;
    Queue<Integer> released;
    int total;
    int next;

    /** Initialize your data structure here
     @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    public PhoneDirectory(int maxNumbers) {
        nums = new int[maxNumbers];
        total =0;
        released = new LinkedList<Integer>();
        next = 0;
    }

    /** Provide a number which is not assigned to anyone.
     @return - Return an available number. Return -1 if none is available. */
    public int get() {
        if (total == nums.length) {
            return -1;
        }
        if (!released.isEmpty()) {
            int newNum = released.poll();
            nums[newNum] = 1;
            total++;
            return newNum;
        }
        int newNum = next;
        nums[next++] = 1;
        total++;
        return newNum;
    }

    /** Check if a number is available or not. */
    public boolean check(int number) {
        return nums[number] == 0;
    }

    /** Recycle or release a number. */
    public void release(int number) {
        if (nums[number] != 1) {
            return;
        }
        nums[number] = 0;
        total--;
        released.offer(number);
    }
}
