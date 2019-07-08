package com.linsizhe;

import java.util.*;

class CourseSchedule {
    class Node {
        int num;
        int inDegree;
        ArrayList<Node> nextNodes;

        Node(int num) {
            this.num = num;
            inDegree = 0;
            nextNodes = new ArrayList();
        }
    }
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // Instead we can just use 2 array of to store "Node" one for indegree, one for next.
        HashMap<Integer, Node> nodes = new HashMap();
        // init. For: 1. isolated points 2. easier calculation.
        for (int i = 0; i < numCourses; i++) {
            nodes.putIfAbsent(i, new Node(i));
        }
        for (int[] edge : prerequisites) {
            Node fromNode = nodes.get(edge[1]);
            Node toNode = nodes.get(edge[0]);
            fromNode.nextNodes.add(toNode);
            toNode.inDegree++;
        }
        Collection<Node> allNodes = nodes.values();
        LinkedList<Node> queue = new LinkedList();
        for (Node n : allNodes) {
            if (n.inDegree == 0) {
                queue.add(n);
            }
        }
        int[] out = new int[numCourses];
        int idx = 0;
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            out[idx++] = cur.num;
            for (Node next : cur.nextNodes) {
                next.inDegree--;
                if (next.inDegree == 0) {
                    queue.add(next);
                }
            }
        }
        // array.length is always return the original size(numCourses).
        if (idx != numCourses) {
            return new int[0];
        }
        return out;
    }
}