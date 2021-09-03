package com.linsizhe.snapchat;

import java.util.*;

//https://leetcode.com/problems/course-schedule-ii/
public class CourseScheduleII {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] in = new int[numCourses];
        HashMap<Integer, List<Integer>> map = new HashMap();
        for (int[] req : prerequisites) {
            map.putIfAbsent(req[1], new ArrayList());
            map.get(req[1]).add(req[0]);
            in[req[0]]++;
        }
        Queue<Integer> zeros = new LinkedList<Integer>();
        for (int i = 0; i < in.length; i++) {
            if (in[i] == 0) {
                zeros.add(i);
            }
        }
        ArrayList<Integer> out = new ArrayList();
        while (!zeros.isEmpty()) {
            int node = zeros.poll();
            out.add(node);
            List<Integer> tos = map.getOrDefault(node, new ArrayList());
            for (int to : tos) {
                in[to]--;
                if (in[to] == 0) {
                    zeros.add(to);
                }
            }
        }
        if (out.size() == numCourses) {
            return out.stream().mapToInt(i -> i).toArray();
        }
        return new int[0];
    }
}
