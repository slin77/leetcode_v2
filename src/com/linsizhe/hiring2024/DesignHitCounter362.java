package com.linsizhe.hiring2024;

import java.util.LinkedList;

//  https://leetcode.com/problems/design-hit-counter/
public class DesignHitCounter362 {
    LinkedList<Integer> q; // not need  for queue since time will come in order!

    public DesignHitCounter362() {
        q = new LinkedList<Integer>();
    }

    public void hit(int timestamp) {
        q.add(timestamp);
    }

    public int getHits(int timestamp) {
        while (!q.isEmpty() && timestamp - q.get(0) >= 300) {
            q.remove(0);
        }
        return q.size();
    }
}
