package com.linsizhe.facebook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

// https://leetcode.com/problems/insert-delete-getrandom-o1/
public class InsertDeleteGetRandomO1 {
    //核心思想： 每一个num都有一个lot并且没有空的lot
    // 如何保持没有空的lot？remove后于最后的交换！
    ArrayList<Integer> nums;
    //map的作用，快查val的 idx
    HashMap<Integer, Integer> map;
    Random rand;

    /** Initialize your data structure here. */
    public InsertDeleteGetRandomO1() {
        nums = new ArrayList();
        map = new HashMap();
        rand = new Random();
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (!map.containsKey(val)) {
            nums.add(val);
            map.put(val, nums.size() - 1);
            return true;
        }
        return false;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        ArrayList<Integer> n = nums;
        HashMap<Integer, Integer> m = map;
        if (map.containsKey(val)) {
            int idx = map.get(val);
            if (idx < nums.size() - 1) {
                // exchange the last idx value to removed idx;
                int lastNum = nums.get(nums.size() - 1);
                nums.set(idx, lastNum);
                map.put(lastNum, idx);
            }
            nums.remove(nums.size() - 1);
            map.remove(val);
            return true;
        }
        return false;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        return nums.get(rand.nextInt(nums.size()));
    }
}
