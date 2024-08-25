package com.linsizhe.hiring2024;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// https://leetcode.com/problems/find-k-closest-elements/
public class FindKClosestElement658 {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        // find  first smaller than x
        int l = 0;
        int r = arr.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] > x) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        l = r;
        r = l + 1;
        ArrayList<Integer> res = new ArrayList();
        while (res.size() < k) {
            // boundary check first
            if (r >= arr.length){
                res.add(arr[l--]);
            } else if (l < 0) {
                res.add(arr[r++]);
            } else if (Math.abs(arr[l] - x) == Math.abs(arr[r]  - x)) {
                if (arr[l] > arr[r]) {
                    res.add(arr[r++]);
                } else {
                    res.add(arr[l--]);
                }
            } else if (Math.abs(arr[l] - x) > Math.abs(arr[r]  - x)) {
                res.add(arr[r++]);
            } else {
                res.add(arr[l--]);
            }
        }

        Collections.sort(res);

        return res;
    }
}
