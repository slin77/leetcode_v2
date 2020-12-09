package com.linsizhe.facebook;

import java.util.LinkedList;
import java.util.Queue;

// level order traverse: top left first dequeue!
// https://leetcode.com/problems/check-completeness-of-a-binary-tree/
public class CheckCompletenessOfBinaryTree {
    public boolean isCompleteTree(TreeNode root) {
        Queue<TreeNode> q = new LinkedList();
        q.add(root);
        boolean nullFound = false;
        while (!q.isEmpty()) {
            // level order traverse: what come after must be your right. Or childern leaves.
            // so in this case if we see a NULL node. There should be no non-null on right or child pos
            TreeNode cur = q.poll();
            if (cur == null) {
                nullFound = true;
            } else if (nullFound) {
                return false;
            } else {
                // tip: enqueue null node to serve as sentinel node.
                q.add(cur.left);
                q.add(cur.right);
            }
        }
        return true;
    }
}
