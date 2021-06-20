package com.linsizhe.facebook;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

// https://leetcode.com/problems/open-the-lock/submissions/
public class OpenTheLock {
    public int openLock(String[] deadends, String target) {
        Queue<int[]> queue = new LinkedList<>();
        int[] start = new int[]{0, 0, 0, 0, 0};
        queue.add(start);
        HashSet<String> visited = new HashSet();
        for (String s : deadends) {
            visited.add(s);
        }
        if (isDead(start, visited)) return -1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            // everything inside of queue are of the same level for bfs
            //for (int j = 0; j < size; j++) {
            int[] cur = queue.poll();
            visited.add(toStr(cur));
            if (eql(cur, target)) return cur[4];
            for (int i = 0; i < 4; i++) {
                int[] up = Arrays.copyOf(cur, 5);
                up[i] = (up[i] + 1) % 10;
                if (!isDead(up, visited)) {
                    up[4] = cur[4] + 1;
                    queue.offer(up);
                    // It is okay for bfs to add frontier to visited.
                    visited.add(toStr(up));
                }
                int[] down = Arrays.copyOf(cur, 5);
                down[i] = (down[i] + 9) % 10;
                if (!isDead(down, visited)) {
                    down[4] = cur[4] + 1;
                    queue.offer(down);
                    visited.add(toStr(down));
                }
            }
            //}
        }
        return -1;
    }

    public boolean eql(int[] cur, String target) {
        for (int i = 0; i < 4; i++) {
            if (cur[i] != (target.charAt(i) - '0')) return false;
        }
        return true;
    }

    // a.k.a contain
    public boolean isDead(int[] cur, HashSet<String> deadends) {
        String s = toStr(cur);
        return deadends.contains(s);
    }

    public String toStr(int[] cur) {
        return new String(new char[]{(char) (cur[0] + 48), (char) (cur[1] + 48), (char) (cur[2] + 48), (char) (cur[3] + 48)});
    }
}
