package com.linsizhe;

public class ReverseStringInsideParent {
    public String reversePattern(String input) {
        int start =  -1;
        int end = -1;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '(') {
                start = i;
                break;
            }
        }
        for (int i = input.length() - 1; i >= 0; i--) {
            if (input.charAt(i) == ')') {
                end = i;
                break;
            }
        }

        if (start < 0) {
            return input;
        }

        // We use the fact that reversePattern only affect parts inside of "()" s. Done
        return input.substring(0, start) + reverse(reversePattern(input.substring(start + 1, end))) + input.substring(end + 1);
        // ab(cd(ef)) => ab(cdfe) => abefdc
        //      reverse
    }

    public String reverse(String string) {
        StringBuilder sb = new StringBuilder();
        for (int i = string.length() - 1; i >= 0; i--) {
            sb.append(string.charAt(i));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        ReverseStringInsideParent rp = new ReverseStringInsideParent();
        System.out.println(rp.reversePattern("ab(cd(ef))"));
        System.out.println(rp.reversePattern("ab(cd)"));
    }

}
