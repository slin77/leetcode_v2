package com.linsizhe.facebook;

// https://leetcode.com/problems/serialize-and-deserialize-binary-tree/submissions/
public class SerializeBinaryTree {
    private class MyInt {
        int counter = 0;
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        traverse(root, sb);
        return sb.toString();
    }

    public void traverse(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append('*');
            return;
        }
        sb.append(root.val);
        sb.append(',');
        traverse(root.left, sb);
        traverse(root.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        MyInt counter = new MyInt();
        return deserialize(data, counter);
    }

    public TreeNode deserialize(String data, MyInt counter) {
        if (counter.counter >= data.length()) {
            return null;
        }
        char cur = data.charAt(counter.counter);
        if (cur == '*') {
            return null;
        } else if (cur == ',') {
            counter.counter++;
        }
        int value = parse(data, counter);
        TreeNode node = new TreeNode(value);
        counter.counter++;
        node.left = deserialize(data, counter);
        counter.counter++;
        node.right = deserialize(data, counter);
        return node;
    }

    private int parse(String data, MyInt counter) {
        int sign = 1;
        int curVal = 0;
        while (data.charAt(counter.counter) != ',') {
            char cur = data.charAt(counter.counter++);
            if (cur == '-') {
                sign = -1;
            } else if ('0' <= cur && cur <= '9') {
                curVal = curVal * 10 + (cur - '0');
            }
        }
        return curVal * sign;
    }

}
