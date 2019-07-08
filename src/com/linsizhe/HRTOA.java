package com.linsizhe;

import java.util.Stack;

public class HRTOA {


    private static final long MAX_ALLOW = (long) (Math.pow(2L, 20L) - 1L);

    public static int solution(String S) {
        // write your code in Java SE 8
        String[] ops = S.split("\\s+");
        Stack<Long> stack = new Stack();
        Long last;
        Long num1;
        Long num2;
        Long num, res;
        for (String op : ops) {
            if (op.equals("POP")) {
                if (stack.isEmpty()) {
                    return -1;
                }
                stack.pop();
            } else if (op.equals("DUP")) {
                if (stack.isEmpty()) {
                    return -1;
                }
                last = stack.peek();
                stack.add(last);
            } else if (op.equals("+")) {
                if (stack.isEmpty()) {
                    return -1;
                }
                num1 = stack.pop();
                if (stack.isEmpty()) {
                    return -1;
                }
                num2 = stack.pop();
                res = addOrThrow(num1, num2);
                stack.push(res);
            } else if (op.equals("-")) {
                if (stack.isEmpty()) {
                    return -1;
                }
                num1 = stack.pop();
                if (stack.isEmpty()) {
                    return -1;
                }
                num2 = stack.pop();
                res = num1 - num2;
                if (res < 0) {
                    return -1;
                }
                stack.push(res);
            } else {
                try {
                    num = Long.parseLong(op);
                } catch (NumberFormatException e) {
                    return -1;
                }
                if (num < 0 || num > MAX_ALLOW) {
                    return -1;
                }
                stack.push(num);
            }
        }
        if (stack.isEmpty()) {
            return -1;
        }
        return Math.toIntExact(stack.pop());
    }

    private static Long addOrThrow(Long a, Long b) {
        Long res = a + b;
        if (res > MAX_ALLOW) {
            return -1L;
        }
        return res;
    }

    public static String solution2(String s) {
        char c = s.charAt(0);
        int zero = '0';
        int nine = '9';
        if (c >= 'A' && c <= 'Z') {  // please fix condition
            return "upper";
        } else if (c >= 'a' && c <= 'z') {  // please fix condition
            return "lower";
        } else if (c >= zero && c <= nine) {  // please fix condition
            return "digit";
        } else {
            return "other";
        }
    }

    public static int solution3(int N) {
        int a = 0;
        int b = 1;
        int cur;
        if (N == 0) {
            return a;
        }
        for (int i = 2; i <= N; i++) {
            cur = (a + b) % 1000000;
            a = b;
            b = cur;
        }
        return b;
    }


    public static void main(String[] args) {
        //System.out.println(solution("13 DUP 400000000 POP 5 DUP + DUP + -"));
        //System.out.println(solution2("9sdfsdbbbbccc sdfdf"));
        //System.out.println('0' - 0);
        System.out.println(solution3(47));
        //Character.isDigit('0')
    }
}
