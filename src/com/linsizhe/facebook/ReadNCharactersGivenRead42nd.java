package com.linsizhe.facebook;

import jdk.jshell.spi.ExecutionControl;

class Read4 {
   int read4(char[] buff) {
       throw new RuntimeException();
   }
}

public class ReadNCharactersGivenRead42nd extends Read4 {
    /**
     * @param buf Destination buffer
     * @param n   Number of characters to read
     * @return    The number of actual characters read
     */
    public int read(char[] buf, int n) {
        char[] buf4 = new char[4];
        int ptr = 0;
        while (ptr < n) {
            int cur = read4(buf4);
            if (cur == 0) {
                return ptr;
            }
            // ptr always point to next idx to read to.
            // we need n chars so last idx is n - 1, so when
            // next point to n we are done.
            // tip: double condition in for loop for early
            // exit under 2nd condition so we can handle
            // overflow.
            for (int i = 0; i < cur && ptr < n; i++) {
                buf[ptr++] = buf4[i];
            }
        }
        return ptr;
    }
}
