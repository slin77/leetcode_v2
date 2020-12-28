package com.linsizhe.facebook;

import java.util.Stack;

//https://leetcode.com/problems/binary-search-tree-iterator/
public class BinarySearchTreeIterator {
    class BSTIterator {

        Stack<TreeNode> stack;


        // 1. en-stack all the way left
        // 2. top of the stack is next element
        // 3. when pop out, enqueue its right's all the way left. (if its right is null, it does not matter, because top of the queue is still
        // the next smallest.
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
            // Top of the stack is always the next element.
            TreeNode cur = stack.pop();
            TreeNode toReturn = cur;

            // When pop a node. Its right child's tree's leftmost could have next smallest.
            // so enque all its right child's leftmost
            cur = cur.right;
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            return toReturn.val;
        }

        /** @return whether we have a next smallest number */
        public boolean hasNext() {
            return !stack.isEmpty();
        }
    }

}
