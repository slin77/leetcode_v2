package com.linsizhe.facebook;


import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/diameter-of-n-ary-tree/
public class DiameterOfNaryTree {
    class Node {
        public int val;
        public List<Node> children;


        public Node() {
            children = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            children = new ArrayList<Node>();
        }

        public Node(int _val,ArrayList<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    int max;

    public int diameter(Node root) {
        max = 0;
        dfs(root);
        return max;
    }

    // get deepest
    public int dfs(Node root) {
        int val = root.val;
        if (root == null) return 0;
        if (root.children.isEmpty()) return 1;

        int first = -1;
        int second = first;
        for (Node node : root.children) {
            int depth = dfs(node);
            // template for getting 1st, 2nd
            if (depth >= first) {
                second = first;
                first = depth;
                // dont skip this case!!!!
            } else if (depth >= second) {
                second = depth;
            }
        }

        first = Math.max(0, first);
        second = Math.max(0, second);
        max = Math.max(max, first + second);

        return first + 1;
    }
}
