package com.linsizhe.hiring2024;

import java.util.HashMap;

public class CopyListWithRandomPointer138 {
    public Node copyRandomList(Node head) {
        if (head == null) return null;
        HashMap<Node, Node> map = new HashMap();
        Node cur = new Node(head.val);
        Node ptr = head;
        map.put(ptr, cur);
        while (ptr != null) {
            Node next = ptr.next == null ? null : map.getOrDefault(ptr.next, new Node(ptr.next.val));
            // update cache first, in case next and random are the same.
            if (next != null) {
                map.putIfAbsent(ptr.next, next);
            }
            Node rand = ptr.random == null ? null : map.getOrDefault(ptr.random, new Node(ptr.random.val));
            if (rand != null) {
                map.putIfAbsent(ptr.random, rand);
            }
            cur.next = next;
            cur.random = rand;

            map.put(ptr, cur);

            ptr = ptr.next;
            cur = cur.next;
        }
        Node res = map.get(head);
        return map.get(head);
    }
}
