package com.linsizhe.facebook;

import java.util.*;

// https://leetcode.com/problems/binary-tree-vertical-order-traversal/
public class BinaryTreeVerticalOrderTraveral {
    class Node {
        TreeNode node;
        int col;

        public Node(TreeNode node, int col) {
            this.node = node;
            this.col = col;
        }
    }
    public List<List<Integer>> verticalOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList();
        }
        TreeMap<Integer, List<Integer>> out = new TreeMap();
        Queue<Node> q = new LinkedList();
        q.add(new Node(root, 0));
        while (!q.isEmpty()) {
            Node n = q.poll();
            out.putIfAbsent(n.col, new LinkedList());
            out.get(n.col).add(n.node.val);
            if (n.node.left != null) {
                q.add(new Node(n.node.left, n.col - 1));
            }

            if (n.node.right != null) {
                q.add(new Node(n.node.right, n.col + 1));
            }
        }
        ArrayList<List<Integer>> outArr = new ArrayList();
        for (Map.Entry<Integer, List<Integer>> entry : out.entrySet()) {
            outArr.add(entry.getValue());
        }
        return outArr;
    }
}
