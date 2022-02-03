package com.linsizhe.amazonOS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class FourSum {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        HashSet<List<Integer>> res = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {

                for (List<Integer> two : twosum(nums, j + 1, target - nums[i] - nums[j])) {
                    ArrayList<Integer> cur = new ArrayList();
                    cur.add(nums[i]);
                    cur.add(nums[j]);
                    cur.add(two.get(0));
                    cur.add(two.get(1));
                    res.add(cur);
                }


            }
        }
        return new ArrayList(res);
    }

    public List<List<Integer>> twosum(int[] nums, int start, int target) {
        List<List<Integer>> res = new ArrayList();
        int avg = target / 2;
        if (start == nums.length || nums[start] > avg || nums[nums.length - 1] < avg) {
            return res;
        }
        int lo = start;
        int hi = nums.length - 1;

        while (lo < hi) {
            int curSum = nums[lo] + nums[hi];
            if (curSum < target) {
                lo++;
            } else if (curSum > target) {
                hi--;
            } else {
                res.add(Arrays.asList(nums[lo++], nums[hi--]));
            }
        }

        return res;
    }
}
