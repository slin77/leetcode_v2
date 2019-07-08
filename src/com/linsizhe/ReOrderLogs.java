package com.linsizhe;

import java.util.*;

public class ReOrderLogs {

    public List<List> getPrime(int max, List<List<Integer>> forward, List<List<Integer>> ret) {
        Collections.sort(forward, (a, b) -> a.get(1) - b.get(1));
        Collections.sort(ret, (a, b) -> a.get(1) - b.get(1));
        int forLen, retLen;
        forLen = forward.size();
        retLen = ret.size();
        int l = 0;
        int r = retLen - 1;
        int diff = Integer.MAX_VALUE;
        while (l <= forLen && r >= 0) {
            int sum = forward.get(l).get(1) + ret.get(r).get(1);
            if (max - sum < diff)    {
                diff = max- sum;
            }
            if (sum > max) {
                l++;
            }
        }
        HashMap<Integer, Integer> fd = new HashMap<>();
        HashMap<Integer, Integer> rt = new HashMap<>();
        return null;
    }

}
