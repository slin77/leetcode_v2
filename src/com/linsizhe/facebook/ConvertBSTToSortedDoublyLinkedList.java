package com.linsizhe.facebook;

// https://leetcode.com/problems/convert-binary-search-tree-to-sorted-doubly-linked-list/
public class ConvertBSTToSortedDoublyLinkedList {

    class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val,Node _left,Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    };

    public Node treeToDoublyList(Node root) {
        if (root == null) return null;
        Node leftHead = treeToDoublyList(root.left);
        Node rightHead = treeToDoublyList(root.right);

        // make root also doubly circular so link will also work
        // there.
        root.left = root;
        root.right = root;

        return link(link(leftHead, root), rightHead);
    }

    public Node link(Node leftHead, Node rightHead) {
        if (leftHead == null) {
            return rightHead;
        }

        if (rightHead == null) {
            return leftHead;
        }

        Node leftTail = leftHead.left;
        Node rightTail = rightHead.left;

        leftTail.right = rightHead;
        rightHead.left = leftTail;

        rightTail.right = leftHead;
        leftHead.left = rightTail;

        // Stupiiiid we already doublely! Easily find tail!
//         while (cur.right != leftHead) {
//             cur = cur.right;
//         }

//         cur.right = rightHead;
//         rightHead.left = cur;

//         cur = rightHead.left;

//         while (cur.right != rightHead) {
//             cur = cur.right;
//         }

//         cur.right = leftHead;
//         leftHead.left = cur;

        return leftHead;
    }
}
