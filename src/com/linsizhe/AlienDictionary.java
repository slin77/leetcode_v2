package com.linsizhe;

import java.util.*;

// Toposort: useful for telling overall ordering from 2-2 compares.
// slides: https://courses.cs.washington.edu/courses/cse326/03wi/lectures/RaoLect20.pdf
class AlienDictionary {
    public class Node {
        char c;
        int inDegree;
        List<Node> to;
        public Node(char c) {
            this.c = c;
            this.inDegree = 0;
            this.to = new ArrayList<Node>();
        }
    }
    public String alienOrder(String[] words) {
        int wordNum = words.length;
        if (wordNum == 0) {
            return "";
        }
        int maxLength = 0;
        for (String word : words) {
            if (word.length() > maxLength) maxLength = word.length();
        }
        HashMap<Character, Node> nodes = new HashMap();
        HashSet<String> edges = new HashSet();

        // Init all nodes with 0 indegree and empty output!
        // Import to handle isolated nodes. Those nodes will
        // be at head of queue first.
        // Also convenients for later calculation.
        for (int i = 0; i < words.length; i++) {
            for (char c : words[i].toCharArray()) {
                nodes.putIfAbsent(c, new Node(c));
            }
        }
        // Get words' character (u, v) edges.
        // 2-2 compare, first different characters will tell order for two...
        for (int i = 0; i < wordNum - 1; i++) {
            String curWord = words[i];
            String nextWord = words[i + 1];
            for (int m = 0; m < Math.min(curWord.length(), nextWord.length()); m++ ) {
                if (curWord.charAt(m) != nextWord.charAt(m)) {
                    char[] arr = {curWord.charAt(m), nextWord.charAt(m)};
                    edges.add(new String(arr));
                    break;
                }
            }
        }
        // Build graph from edges. Keep record of their indegree(for toposort), out(for queue).
        for (String edge : edges) {
            char from = edge.charAt(0);
            char to = edge.charAt(1);
            Node fromNode = nodes.get(from);
            Node toNode = nodes.get(to);
            toNode.inDegree++;
            fromNode.to.add(toNode);
        }

        Collection<Node> nodeList = nodes.values();
        LinkedList<Node> queue = new LinkedList();
        StringBuilder out = new StringBuilder();
        // TopoSort part: keep queue of 0 in degree. dequeue and update indegree of
        // its output nodes. Keep going..
        for (Node n : nodeList) {
            if (n.inDegree == 0) {
                queue.add(n);
            }
        }
        while (!queue.isEmpty()) {
            Node n = queue.poll();
            out.append(n.c);
            for (Node toNode : n.to) {
                toNode.inDegree--;
                if (toNode.inDegree == 0) {
                    queue.add(toNode);
                }
            }
        }
        if (out.length() == nodeList.size()) {
            return out.toString();
        }
        return "";
    }
}