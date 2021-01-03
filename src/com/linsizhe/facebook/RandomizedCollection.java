package com.linsizhe.facebook;

import java.util.*;

// https://leetcode.com/problems/insert-delete-getrandom-o1-duplicates-allowed/
public class RandomizedCollection {
    private HashMap<Integer, Node> idToVal;
    private HashMap<Integer, List<Integer>> valToIds;
    private LinkedList<Node> live;
    private int size = 0;
    private int id = 0;
    Random rand;

    private class Node {
        int val;
        int idx;

        public Node(int val, int idx) {
            this.val = val;
            this.idx = idx;
        }

        @Override
        public String toString() {
            return "(val: " + val + ", idx:" + idx + ")";
        }
    }


    /** Initialize your data structure here. */
    public RandomizedCollection() {
        this.idToVal = new HashMap();
        this.live = new LinkedList();
        this.valToIds = new HashMap();
        rand = new Random();
    }

    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        Node node = new Node(val, live.size());
        idToVal.put(id, node);
        live.add(node);

        boolean contains = valToIds.containsKey(val);
        valToIds.putIfAbsent(val, new ArrayList());
        valToIds.get(val).add(id);
        size++;
        id++;
        return !contains;
    }

    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        // HashMap<Integer, Node> idToVal = this.idToVal;
        // HashMap<Integer, List<Integer>> valToIds = this.valToIds;
        // LinkedList<Node> live = this.live;
        // int size = this.size;
        // int id = this.id;

        List<Integer> ids = valToIds.get(val);
        if (ids == null || ids.size() == 0) {
            return false;
        }
        int idToRemove = ids.get(0);
        ids.remove(0);
        // exchange it with the last one, and remove last one.
        // so index do not have to update
        Node removed = idToVal.remove(idToRemove);
        // note here that all idx after this removed need to -1
        // but we can exchange this with the last one, and remove last one instead!
        if (removed.idx < live.size() - 1) {
            Node last = live.get(live.size() - 1);
            last.idx = removed.idx;
            live.add(removed.idx, last);
            // linkedlist add will shift next element to next
            live.remove(removed.idx + 1);
        }
        live.remove(live.size() - 1);
        size--;
        return true;
    }

    /** Get a random element from the collection. */
    public int getRandom() {
        int num = rand.nextInt(live.size());
        return live.get(num).val;
    }
}
