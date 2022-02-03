package com.linsizhe.doordash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CompareMenu {
    public static class Node {
        String key;
        int value;
        boolean active;
        List<Node> children;

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Node)) {
                return false;
            }
            Node other = (Node) obj;
            return this.key.equals(other.key)
                    && this.value == other.value
                    && this.active == other.active;
        }

        public Node(String key, int value, boolean isActive) {
            this.key = key;
            this.value = value;
            this.active = isActive;
            this.children = new ArrayList<>();
        }
    }

    public static int getModified(Node menu1, Node menu2) {
        if (menu1 == null && menu2 == null) {
            return 0;
        }
        int diff = 0;
        if (menu1 == null || menu2 == null || !menu1.equals(menu2)) {
            diff++;
        }

        HashMap<String, Node> children1 = getChildren(menu1);
        HashMap<String, Node> children2 = getChildren(menu2);

        for (String key : children1.keySet()) {
            Node n1 = children1.get(key);
            Node n2 = children2.get(key);
            diff += getModified(n1, n2);
        }

        // 2nd pass just check for missing node in tree1!
        for (String key : children2.keySet()) {
            // wrong!  double count!
//            Node n1 = children2.get(key);
//            Node n2 = children1.getOrDefault(key, new Node());
//            diff += getModified(n1, n2);
            if (!children1.containsKey(key)) {
                diff += getModified(null, children2.get(key));
            }
        }

        return diff;
    }

    public static HashMap<String, Node> getChildren(Node node) {
        HashMap<String, Node> ret = new HashMap<>();
        if (node == null) return ret;
        for (Node n : node.children) {
            ret.put(n.key, n);
        }
        return ret;
    }

    public static void main(String[] args) {
        Node a = new Node("a", 1, true);
        Node b = new Node("b", 2, true);
        Node c = new Node("c", 3, true);
        Node d = new Node("d", 4, true);
        Node e = new Node("e", 5, true);
        Node g = new Node("g", 7, true);

        a.children.add(b);
        a.children.add(c);

        b.children.add(d);
        b.children.add(e);

        //c.children.add(g);

        Node a1 = new Node("a", 1, true);
        Node b1 = new Node("b", 2, true);
        Node c1 = new Node("c", 3, true);
        Node d1 = new Node("d", 4, true);
        Node e1 = new Node("e", 5, true);
        Node f1 = new Node("f", 6, true);
        Node g1 = new Node("g", 7, false);

        a1.children.add(b1);
        a1.children.add(c1);

        b1.children.add(d1);
        //b1.children.add(e1);
        //b1.children.add(f1);

        c1.children.add(e1);

        int count = getModified(a, a1);
        System.out.println("Changed Items are: " + count);
    }
}
