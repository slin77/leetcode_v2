package com.linsizhe.facebook;

import java.util.HashMap;

// https://leetcode.com/problems/dot-product-of-two-sparse-vectors/
public class DotProductOfTwoSparseVectors {
    class SparseVector {

        HashMap<Integer, Integer> values;

        SparseVector(int[] nums) {
            values = new HashMap();
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] != 0 ) {
                    values.put(i, nums[i]);
                }
            }
        }

        // Return the dotProduct of two sparse vectors
        public int dotProduct(SparseVector vec) {
            if (vec.values.size() == 0 || values.size() == 0) {
                return 0;
            }
            // Always use smaller(sparser) to calculate.
            if (vec.values.size() < values.size()) {
                return vec.dotProduct(this);
            }

            int out = 0;
            for (int i : values.keySet()) {
                if (vec.values.containsKey(i)) {
                    out += values.get(i) * vec.values.get(i);
                }
            }
            return out;
        }
    }
}
