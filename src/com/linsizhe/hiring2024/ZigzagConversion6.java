package com.linsizhe.hiring2024;

// https://leetcode.com/problems/zigzag-conversion/
public class ZigzagConversion6 {
    public String convert(String s, int numRows) {
        int block = Math.max(1, numRows + numRows - 2); // for numOfRows == 1 we need at least 1 element  in block
        int x = block == 0 ? 1 : s.length() / block; // number of sections
        if (block != 0 && s.length() % block != 0) x++;
        char[] res = new char[s.length()];
        int ptr = 0;
        // traverse horizontally
        // first scan by row,
        // then scan  by section number
        for (int i = 0; i <  numRows; i++) {
            // j as idx with in each section
            for (int j = 0; j < x; j++) {
                int idx = j * block;// start idx with in each block

                if (i == 0) {// first
                    res[ptr++] = s.charAt(j * block);
                } else if(i == numRows - 1) {//last
                    if  (j * block + numRows - 1 < s.length()) {
                        res[ptr++] = s.charAt(j * block + numRows - 1);
                    }
                } else if (i > 0 && i < numRows - 1) {
                    if (j * block + i < s.length()) {
                        res[ptr++] = s.charAt(j*block + i);
                    }

                    if ((j + 1)*block - i < s.length()) {
                        res[ptr++]  = s.charAt((j + 1)*block - i);
                        // j* block + i + m = block + 2*j*block = (2j + 1) * block
                        //  m = (j  + 1) * block - i
                    }
                }
            }
        }
        return new String(res);
    }
}
