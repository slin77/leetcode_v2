package com.linsizhe.facebook;

// https://leetcode.com/problems/read-n-characters-given-read4-ii-call-multiple-times/
public class ReadNCharactersGivenRead4II2nd {
    int buf4ptr = 0;
    char[] buf4 = new char[4];
    int upper = 0;

    /**
     * @param buf Destination buffer
     * @param n   Number of characters to read
     * @return    The number of actual characters read
     */
    public int read(char[] buf, int n) {
        int idx = 0;

        while (idx < n) {
            int fptr = buf4ptr;
            if (buf4ptr == 0) {
                upper = read4(buf4);
            }
            int u = upper;
            // nothing left
            if (upper == 0) return idx;
            for (int i = buf4ptr; i < upper && idx < n; i++) {
                buf[idx++] = buf4[buf4ptr++];
            }
            if (buf4ptr == upper) {
                buf4ptr = 0;
            }
        }
        return idx;
    }

    private int read4(char[] buf) {
        return -1;
    }
}
