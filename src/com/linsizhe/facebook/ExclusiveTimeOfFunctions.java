package com.linsizhe.facebook;

import java.util.List;
import java.util.Stack;

//https://leetcode.com/problems/exclusive-time-of-functions/
public class ExclusiveTimeOfFunctions {
    public int[] exclusiveTime(int n, List<String> logs) {
        int lastTime = 0;
        int lastTag = -1;
        String lastOp = "";
        int[] out = new int[n];
        Stack<Integer> stack = new Stack();
        for (int i = 0; i < logs.size(); i++) {
            String[] arr = logs.get(i).split(":");
            int tag = Integer.valueOf(arr[0]);
            String op = arr[1];
            int timestamp = Integer.valueOf(arr[2]);
            // start from a process to another process
            // gap is the last process running time
            if (lastOp.equals("start") && op.equals("start")) {
                out[lastTag] += timestamp - lastTime;
                // start from end, must be same process! Note that end
                // is the end time of a slot so need to +1
            } else if (lastOp.equals("start") && op.equals("end")) {
                out[tag] += timestamp - lastTime + 1;
                // a process end switch to another process
                // it is the current process that last gap time
            } else if (lastOp.equals("end") && op.equals("end")) {
                out[tag] += timestamp - lastTime;
                // "end to start" case is the only case we need a stack
                // because from end to start process remain in the stack
                // will be invoked, without tracking what is running
            } else if (lastOp.equals("end") && op.equals("start")) {
                if (!stack.isEmpty()) {
                    out[stack.peek()] += timestamp - lastTime - 1;
                }
            }
            if (op.equals("start")) {
                stack.add(tag);
            } else {
                stack.pop();
            }
            lastTag = tag;
            lastTime = timestamp;
            lastOp = op;
        }
        return out;
    }
}
