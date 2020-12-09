package com.linsizhe.facebook;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

// https://leetcode.com/problems/random-pick-index/
// Resourvior sampling (generating equal probability for numbers come in as sequence!)
public class RandomPickIndex {
    HashMap<Integer, List<Integer>> numToIdxs;
    Random rand;

    int[] nums;

//     public RandomPickIndex(int[] nums) {
//         numToIdxs = new HashMap<>();
//         rand = new Random();
//         for (int i = 0; i < nums.length; i++) {
//             numToIdxs.putIfAbsent(nums[i], new ArrayList());
//             numToIdxs.get(nums[i]).add(i);
//         }
//     }

//     public int pick(int target) {
//         List<Integer> idxs = numToIdxs.get(target);
//         int pos = rand.nextInt(idxs.size());
//         return idxs.get(pos);
//     }


    // Resourvior sampling (generating equal probability for numbers come in as sequence!)
    // we have n sample ith on is getting picked at 1/i
    // so eventually we get equal probability of picking each of
    // them
    // for example: 1st one is 1 * 1/2 * 2/3 * 3/4 ... (each product is prob of keeping it)
    //              2nd 1/2 * 2/3 ...
    //              3nd 1/3 * 3/4 ...
    public RandomPickIndex(int[] nums) {
        this.nums = nums;
        rand = new Random();
    }

    public int pick(int target) {
        int count = 0;
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                if (rand.nextInt(++count) == 0) { // rand(n) == 0 has 1 / n probability
                    res = i; // prod of using a new one in each iteration is 1 / n. so keep old is n - 1 / n
                }
            }
        }
        return res;
    }
}
