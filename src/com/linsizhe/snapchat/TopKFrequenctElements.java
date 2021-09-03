package com.linsizhe.snapchat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TopKFrequenctElements {
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> freq = new HashMap();
        for (int i : nums) {
            freq.put(i, freq.getOrDefault(i, 0) + 1);
        }
        List<Integer>[] ct = new List[nums.length + 1];
        for (Map.Entry<Integer, Integer> e : freq.entrySet()) {
            if (ct[e.getValue()] == null) {
                ct[e.getValue()] = new ArrayList<Integer>();
            }
            ct[e.getValue()].add(e.getKey());
        }
        int[] out = new int[k];
        int ptr = 0;
        for (int i = ct.length - 1; i >= 0; i--) {
            if (ct[i] != null) {
                for (int j : ct[i]) {
                    out[ptr++] = j;
                    if (ptr == out.length) {
                        return out;
                    }
                }
            }
        }
        return out;
    }
}
