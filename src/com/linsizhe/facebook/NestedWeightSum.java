package com.linsizhe.facebook;


import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// https://leetcode.com/problems/nested-list-weight-sum/submissions/
public class NestedWeightSum {
    interface NestedInteger {

        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger();
        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger();

        // Set this NestedInteger to hold a single integer.
        public void setInteger(int value);

        // Set this NestedInteger to hold a nested list and adds a nested integer to it.
        public void add(NestedInteger ni);

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return null if this NestedInteger holds a single integer
        public List<NestedInteger> getList();
    }
    public int depthSum(List<NestedInteger> nestedList) {
        return getSum(nestedList, 1);
    }

    public int getSum(List<NestedInteger> cur, int idx) {
        int sum = 0;
        for (NestedInteger ne: cur) {
            if (ne.isInteger()) {
                sum += ne.getInteger() * idx;
            } else {
                sum += getSum(ne.getList(), idx + 1);
            }
        }
        return sum;
    }

    // same as leval order traversal!
    public int depthSumBFS(List<NestedWeightSum.NestedInteger> nestedList) {
        Queue<NestedInteger> q = new LinkedList();
        for (NestedInteger ni : nestedList) {
            q.add(ni);
        }
        int level = 1;
        int sum = 0;
        while (!q.isEmpty()) {
            // property of BFS!
            // we can do layer by layer
            // Keep all in queue at the same layer!
            // bfs can do by track layer be current q size
            // pop them all at single interation
            int curLayerSize = q.size();
            for (int i = 0; i < curLayerSize; i++) {
                NestedInteger node = q.poll();
                if (node.isInteger()) {
                    sum += level * node.getInteger();
                } else {
                    for (NestedInteger newNode : node.getList()) {
                        q.offer(newNode);
                    }
                }
            }
            level++;
        }
        return sum;
    }

    }
