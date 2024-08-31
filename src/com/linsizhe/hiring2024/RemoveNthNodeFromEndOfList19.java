package com.linsizhe.hiring2024;

  class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
// https://leetcode.com/problems/remove-nth-node-from-end-of-list/
public class RemoveNthNodeFromEndOfList19 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        int i = 0;
        int j = 0;

        // fake  node to handle head switch case
        // this is common for all head edge cases!!
        ListNode node = new ListNode(-1);
        node.next = head;

        ListNode curi = node;
        ListNode curj = node;

        if (head.next == null) return null;
        while (curi != null) {
            curi = curi.next;
            i++;
            if (i - j >= n + 1) { // so j is one before the target.
                curj = curj.next;
                j++;
            }
            if (curi.next == null) {
                curj.next = curj.next.next;
                break;
            }
        }
        return node.next;
    }
}
