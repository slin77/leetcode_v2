package com.linsizhe.facebook;

// https://leetcode.com/problems/insert-into-a-sorted-circular-linked-list/
// Two passes.
// First pass find the max node. Then start find right position
// from next of max node
// Circular linkedlist: (bad) the head can point to anywhere
//                      (good) not need special handling for head/tail since it is circular
public class InsertIntoSortedCircularLinkedList {

    class Node {
        public int val;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _next) {
            val = _val;
            next = _next;
        }
    }

    public Node insert(Node head, int insertVal) {
        Node newNode = new Node(insertVal);
        if (head == null) {
            newNode.next = newNode;
            return newNode;
        }

        // move cur to the max value in the circle
        Node cur = head;
        // this is not true since head can point to any place in the list
        while (cur.next != head && cur.val <= cur.next.val) {
            cur = cur.next;
        }

        Node max = cur;

        // if all nodes are smaller than new value. We put it after max node.
        if (max.val < insertVal) {
            newNode.next = max.next;
            max.next = newNode;
            // else find right place to insert it, we are gurantee to find it
        } else {
            while (cur.next != max && cur.next.val <= insertVal) {
                cur = cur.next;
            }
            newNode.next = cur.next;
            cur.next = newNode;
        }

        // Not need special handling for head/tail in circular linkedList!!!!
        // // Tail case
        // if (cur.next == head) {
        //     cur.next = newNode;
        //     newNode.next = head;
        // // New node be insert before head
        // } else if (cur == head && cur.val > insertVal) {
        //     newNode.next = head;
        //     while (cur.next != head) cur = cur.next;
        //     cur.next = newNode;
        //     //head = newNode;
        // } else {
        //     newNode.next = cur.next;
        //     cur.next = newNode;
        // }
        return head;
    }
}
