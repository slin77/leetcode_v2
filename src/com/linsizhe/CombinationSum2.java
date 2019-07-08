package com.linsizhe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum2 {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> out = new ArrayList();
        Arrays.sort(candidates);
        //System.out.println(Arrays.toString(candidates));
        Integer[] cur = new Integer[target / candidates[0] + 1];
        backTrack(candidates, target, out, cur, 0, -1, 0);
        return out;
    }

    void backTrack(int[] candidates, int target, List<List<Integer>> out, Integer[] cur, int curSum, int idx, int start) {
        //System.out.println(Arrays.toString(cur));
        if (curSum > target) {
            return;
        }
        if (curSum == target) {
            List<Integer> ans = Arrays.asList(Arrays.copyOfRange(cur, 0, idx + 1));
            out.add(ans);
            //System.out.println("Yo!");
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            if (i > start && candidates[i] == candidates[i - 1]) continue; // Skip duplicate at the same level. 
            // can't be i != 0 is not correct, "1" at next level is also skipped. We only skip 1 at the same level. 
            Integer candidate = candidates[i];
            cur[idx + 1] = candidate;
            backTrack(candidates, target, out, cur, curSum + candidate, idx + 1, i + 1);
            cur[idx + 1] = null;
        }
    }
}