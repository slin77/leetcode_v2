package com.linsizhe.hiring2024;

import java.util.Stack;

// https://leetcode.com/problems/min-stack/

// for getting min value at O(1) in time series
// we can always track min so far at each ts
class MinStack {

    class Node {
        public int val;
        public int min_val;
    }

    Stack<Node> s;

    public MinStack() {
        s = new Stack<Node>();
    }

    public void push(int val) {
        Node n  = new  Node();
        n.val = val;
        int min = Integer.MAX_VALUE;
        if (!s.isEmpty()) {
            min = Math.min(val, s.peek().min_val);
        } else {
            min = val;
        }
        n.min_val = min;
        s.push(n);
    }

    public void pop() {
        s.pop();
    }

    public int top() {
        return s.peek().val;
    }

    public int getMin() {
        return s.peek().min_val;
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
