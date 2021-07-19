package com.linsizhe.facebook;

import java.util.HashMap;

// https://leetcode.com/problems/lru-cache/
public class LRUCache {
    class Node {
        int val;
        Node prev;
        Node next;
        int key;
        public Node(int key, int value) {
            val = value;
            this.key = key;
        }

        public Node() {
        }
    }

    int size;
    int cap;
    Node head;
    Node tail;
    HashMap<Integer, Node> cache;
    // 1. Usage Node as linkedlist node for hashmap entry for quick access
    // 2. DLL so we can add, remove at O(1)
    // 3. Use fake node as DLL head, tail to avoid edge case check!
    public LRUCache(int capacity) {
        size = 0;
        cap = capacity;
        cache = new HashMap();
        // Usage of fake node to avoid edge cases check!
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
    }

    private void removeNode(Node n) {
        n.prev.next = n.next;
        n.next.prev = n.prev;
    }

    private void moveToHead(Node n) {
        removeNode(n);
        addToHead(n);
    }

    private void addToHead(Node n) {
        // 4 pointers to modify!
        n.next = head.next;
        head.next.prev = n;
        n.prev = head;
        head.next = n;
    }

    public int get(int key) {
        if (cache.containsKey(key)) {
            Node hit = cache.get(key);
            moveToHead(hit);
            return hit.val;
        } else {
            return -1;
        }
    }

    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            cache.get(key).val = value;
            moveToHead(cache.get(key));
        } else {
            if (size == cap) {
                cache.remove(tail.prev.key);
                removeNode(tail.prev);
                size--;
            }
            Node n = new Node(key, value);
            addToHead(n);
            cache.put(key, n);
            size++;
        }
    }
}
