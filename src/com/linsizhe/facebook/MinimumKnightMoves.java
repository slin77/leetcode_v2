package com.linsizhe.facebook;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

// https://leetcode.com/problems/minimum-knight-moves/
public class MinimumKnightMoves {
    // Due to symmetry: 1. convert to 1st qurant
    //                  2. Restraint the jump to 1st qurant
    //                  3. For 2. we cannot just do all >0 leave room for one extra jump
    public int minKnightMoves(int x, int y) {
        int[][] dirs = new int[][]{{-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {-2, -1}, {-1, -2}, {1, -2}, {2, -1}};
        Queue<int[]> q = new LinkedList();
        q.offer(new int[]{0, 0});
        int step = 0;
        x = Math.abs(x);
        y = Math.abs(y);
        HashSet<String> visited= new HashSet();
        visited.add("0,0");
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] ptr = q.poll();
                visited.add(ptr[0] + "," + ptr[1]);
                if (x == ptr[0] && y == ptr[1]) return step;
                for (int[] dir : dirs) {
                    int[] nptr = new int[]{ptr[0] + dir[0], ptr[1] + dir[1]};
                    if (x == nptr[0] && y == nptr[1]) return step + 1;
                    if (!visited.contains(nptr[0] + "," + nptr[1]) && nptr[0] >= -1 && nptr[1] >= -1) {
                        q.add(nptr);
                        visited.add(nptr[0] + "," + nptr[1]);
                    }
                }
            }
            step++;
        }
        return -1;
    }
}
