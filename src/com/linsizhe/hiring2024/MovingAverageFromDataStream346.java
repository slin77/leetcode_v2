package com.linsizhe.hiring2024;

import java.util.LinkedList;
import java.util.Queue;

// https://leetcode.com/problems/moving-average-from-data-stream/?envType=company&envId=facebook&favoriteSlug=facebook-six-months
public class MovingAverageFromDataStream346 {
    int total = 0;
    int size;
    Queue<Integer> q;

    public MovingAverageFromDataStream346(int size) {
        this.size  = size;
        q = new LinkedList<Integer>();
    }

    public double next(int val) {
        if (q.size() == size) {
            this.total -= q.poll();
        }
        q.add(val);
        this.total += val;
        return (double) total  / Math.min(size, q.size());
    }
}
