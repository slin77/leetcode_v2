package com.linsizhe.snapchat;

import java.util.*;

// https://leetcode.com/problems/parallel-courses/
public class ParallelCourses {
    public int minimumSemesters(int n, int[][] relations) {
        HashMap<Integer, List<Integer>> reqs = new HashMap();
        int[] in = new int[n + 1];
        for (int[] relation : relations) {
            reqs.putIfAbsent(relation[0], new ArrayList<Integer>());
            reqs.get(relation[0]).add(relation[1]);
            in[relation[1]]++;
        }
        Queue<Integer> q = new LinkedList();
        HashSet<Integer> take = new HashSet();
        for (int i = 0; i < in.length; i++) {
            if (in[i] == 0) {
                q.add(i);
            }
        }
        int res = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int cur = q.poll();
                take.add(cur);
                for (int out : reqs.getOrDefault(cur, new ArrayList<Integer>())) {
                    in[out]--;
                    if (in[out] == 0) {
                        q.add(out);
                        take.add(out);
                    }
                }
            }
            res++;
        }
        if (take.size() != n + 1) return -1;
        return res;
    }
}
