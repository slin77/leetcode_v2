package com.linsizhe.facebook;


// Thinking: 1. need a old -> new link. Then old1 -> old2 ==> new1 -> new2, new1 -> r_old3 -> new3
//           2. but if we have old -> new link... we will lose old1 -> old2 link.(out link is limited!)
//           3. interleve a -> a' -> b -> b'
//           3. then next is next.next, new -> old is next!
// interleave node == add a new outbound link!
// https://leetcode.com/problems/copy-list-with-random-pointer/
public class CopyListWithRandomPointer {
    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
    public Node CopyListWithRandomPointer(Node head) {
        if (head == null) {
            return null;
        }
        Node cur = head;
        Node tmp;
        while (cur != null) {
            tmp = cur.next;
            cur.next = new Node(cur.val);
            cur.next.next = tmp;
            cur = cur.next.next;
        }
        cur = head;
        while (cur != null) {
            if (cur.random != null) {
                cur.next.random = cur.random.next;
            } else {
                cur.next.random = null;
            }
            cur = cur.next.next;
        }
        cur = head;
        Node newCur = head.next;
        while (cur != null) {
            tmp = cur.next;
            cur.next = cur.next.next;
            if (tmp.next != null) {
                tmp.next = tmp.next.next;
            }
            cur = cur.next;
        }
        return newCur;
    }
}
