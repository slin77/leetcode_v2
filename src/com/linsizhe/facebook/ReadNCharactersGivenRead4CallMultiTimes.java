package com.linsizhe.facebook;

import java.util.LinkedList;
import java.util.Queue;

// https://leetcode.com/problems/read-n-characters-given-read4-ii-call-multiple-times/
// my idea: use overflow to store if remaining chars is less than read4 returns.
public class ReadNCharactersGivenRead4CallMultiTimes {
    private Queue<Character> overflow = new LinkedList<>();

    /**
     * @param buf Destination buffer
     * @param n   Number of characters to read
     * @return    The number of actual characters read
     */
    public int read(char[] buf, int n) {
        int remain = n;
        int read = 0;
        // read overflow
        while (!overflow.isEmpty() && remain > 0) {
            buf[read++] = overflow.poll();
            remain--;
        }
        char[] read4buff;
        while (remain > 0) {
            read4buff = new char[4];
            int actualRead = read4(read4buff);
            // we have overflow
            if (remain < actualRead) {
                for (int i = 0; i < remain; i++) {
                    buf[read++] = read4buff[i];
                }
                for (int i = remain; i < actualRead; i++) {
                    overflow.add(read4buff[i]);
                }
                return read;
                // remain > actual read, but not enough file left
            } else if (actualRead < 4) {
                for (int i = 0; i < actualRead; i++) {
                    buf[read++] = read4buff[i];
                }
                return read;
            }
            // normal case. keep reading
            for (int i = 0; i < actualRead; i++) {
                buf[read++] = read4buff[i];
                remain--;
            }
        }
        // normal case
        return read;
    }

    // fake method
    int read4(char[] buf) {
        return -1;
    }

    // A better solution that does not require overflow.
    // Keep buff from last time and a buff pointer if anything left!
    private class BetterSolution {
        private int currBuffIdx = 0;
        private int buffCnt = 0;
        private char[] buff = new char[4];

        public int read(char[] buf, int n) {
            int index = 0;
            while (index < n) {
                if (currBuffIdx == 0) {
                    buffCnt = read4(buff);
                }
                if (buffCnt == 0) return index;
                while (index < n && currBuffIdx < buffCnt) {
                    buf[index++] = buff[currBuffIdx++];
                }
                if (currBuffIdx == buffCnt) currBuffIdx = 0;
            }
            return index;
        }
    }
}
