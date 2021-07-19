package com.linsizhe.facebook;

public class ConstructBinaryTreeFromString {
    public TreeNode str2tree(String s) {
        return str2tree(s, 0, s.length() - 1);
    }
    // num or num(...) or num(...)(...)
    public TreeNode str2tree(String s, int start, int end) {
        if (end < start) return null;
        int sign = 1;
        int num  = 0;
        int ptr = -1;
        // parse the first number until we hit "(";
        for (int i = start; i <= end; i++) {
            if (s.charAt(i) == '(') {
                ptr = i + 1;
                break;
            }  else if (Character.isDigit(s.charAt(i))) {
                num = num * 10 + s.charAt(i) - '0';
            } else if (s.charAt(i) == '-') {
                sign = -1;
            }
        }
        // there is nothing left after we parse first number.
        if (ptr == -1) return new TreeNode(num * sign);
        TreeNode node = new TreeNode(num * sign);
        int count = 1;
        // ptr is next after first "(", it should be start index of left node.
        int nextStart = ptr;
        while (count != 0) {
            if (s.charAt(ptr) == '(') count++;
            if (s.charAt(ptr) == ')') count--;
            ptr++;
        }
        // ptr is the next of last ')'.
        node.left = str2tree(s, nextStart, ptr - 2);
        // still have something
        if (ptr - 1 < end) {
            node.right = str2tree(s, ptr + 1, end - 1);
        }
        return node;
    }
}
