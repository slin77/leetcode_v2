package com.linsizhe.facebook;

// https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-iii/
public class LowestCommonAncestorOfBSTIII {

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node parent;
    }

    // Smarter:
    // find common points of two linkedlist, like two racer race, they will converge
    // a will go A -> LCA -> root -> B -> LCA
    // b will go B -> LCA -> root -> A -> LCA
    // By reaching root set start node be another's start this way they get rid of path difference!
    public Node lowestCommonAncestor(Node p, Node q) {
        Node a = p;
        Node b = q;
        while (a != b) {
            a = a == null ? q : a.parent;
            b = b == null ? p : b.parent;
        }

        return a;
    }

//     public Node lowestCommonAncestor(Node p, Node q) {
//         int pDepth = getDepth(p);
//         int qDepth = getDepth(q);

//         if (pDepth > qDepth) {
//             for (int i = 0; i < pDepth - qDepth; i++) {
//                 p = p.parent;
//             }
//         } else {
//             for (int i = 0; i < qDepth - pDepth; i++) {
//                 q = q.parent;
//             }
//         }

//         while (p.parent != null || q.parent != null) {
//             int pVal = p.val;
//             int qVal = q.val;
//             if (p == q) return p;
//             if (p.parent != null) p = p.parent;
//             if (q.parent != null) q = q.parent;
//         }

//         return p;
//     }

//     int getDepth(Node n) {
//         Node cur = n;
//         int depth = 0;
//         while (cur.parent != null) {
//             cur = cur.parent;
//             depth++;
//         }
//         return depth;
//     }
}
