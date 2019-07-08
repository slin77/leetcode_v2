package com.linsizhe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.sort;

public class CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> out = new ArrayList();
        sort(candidates);
        Integer[] cur = new Integer[target / candidates[0] + 1];
        backTrack(candidates, target, out, cur, 0, -1, 0);
        return out;
    }


    public void backTrack(int[] candidates, int target, List<List<Integer>> out, Integer[] cur, int curSum, int idx, int start) {
        //System.out.println(Arrays.toString(cur));
        if (curSum > target) {
            return;
        }
        if (idx == cur.length - 1) {
            return;
        }
        if (curSum == target) {
            List<Integer> ans = Arrays.asList(Arrays.copyOfRange(cur, 0, idx + 1));
            out.add(ans);
            //System.out.println("Yo!");
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            Integer candidate = candidates[i];
            cur[idx + 1] = candidate;
            backTrack(candidates, target, out, cur, curSum + candidate, idx + 1, i);// Very import not allowing going back generates combination, v.s permutation (no start) => combination with replacement
            // backTrack(candidates, target, out, cur, curSum + candidate, idx + 1, i + 1); start + 1, combination without replacement
            // with no start -> permutation
            // wiht no start, i + 1 -> permutation with replacement
            cur[idx + 1] = null;
        }
    }
}