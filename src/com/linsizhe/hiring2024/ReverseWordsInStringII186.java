package com.linsizhe.hiring2024;

public class ReverseWordsInStringII186 {
    public void reverseWords(char[] s) {
        int i = 0;
        int j = 0;
        // reverse whole string first
        reverse(s, 0, s.length - 1);
        while (i < s.length) {
            j = i;
            // don't use j + 1 for judegement if boundary is issue
            // last call j wil be at length idx
            while (j < s.length && s[j] != ' ')  j++;
            reverse(s, i, j - 1);
            i = j + 1;
        }
    }

    void reverse(char[] arr, int i, int j) {
        while (i < j) {
            char temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }
    }
}
