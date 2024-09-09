package com.linsizhe.hiring2024;

// https://leetcode.com/problems/excel-sheet-column-number/
public class ExcelSheetColumnNumber171 {
    // 26nary
    public int titleToNumber(String columnTitle) {
        int res = 0;
        for (int i =0; i < columnTitle.length(); i++) {
            res = res * 26  + (columnTitle.charAt(i) - 'A' + 1);
        }
        return res;
    }
}
