package com.linsizhe.facebook;

import java.util.LinkedList;
import java.util.Queue;

// https://leetcode.com/problems/course-schedule/
public class CourseSchedule {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[][] adj = new int[numCourses][numCourses];
        int[] indegree = new int[numCourses];
        for (int[] pr : prerequisites) {
            if (adj[pr[1]][pr[0]] == 0) {
                adj[pr[1]][pr[0]] = 1;
                indegree[pr[0]]++;
            }
        }
        Queue<Integer> q = new LinkedList();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
            }
        }
        int count = 0;
        while (!q.isEmpty()) {
            int c = q.poll();
            count++;
            for (int i = 0; i < numCourses; i++) {
                if (adj[c][i] == 1) {
                    indegree[i]--;
                    if (indegree[i] == 0) {
                        q.add(i);
                    }
                }
            }
        }
        return count == numCourses;
    }
}
