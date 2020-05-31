package com.linsizhe.facebook;

import java.util.Stack;

//https://leetcode.com/problems/binary-search-tree-iterator/
public class BinarySearchTreeIterator {
    // 1. all the way left, pop stack, print(visit), pop stack(visit!) right, all the way left
    class BSTIterator {

        Stack<TreeNode> stack;


        public BSTIterator(TreeNode root) {
            stack = new Stack<TreeNode>();
            TreeNode current = root;
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
        }

        /** @return the next smallest number */
        public int next() {
            int toRet = 0;
            TreeNode current = null;
            if (!stack.isEmpty()) {
                current = stack.pop();
                toRet = current.val;
                current = current.right;
            }
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            return toRet;
        }

        /** @return whether we have a next smallest number */
        public boolean hasNext() {
            return !stack.isEmpty();
        }
    }

}
