package com.linsizhe.facebook;

public class BinarySearchAndVariant {

    // first index of element equal to target
    public int search1(int[] input, int target) {
        int left = 0;
        int right = input.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (input[mid] < target) {
                left = mid + 1;
            } else if (input[mid] > target) {
                right = mid - 1;
            } else {
                // bias left, take left
                // left move, take left .....right(left--------.......
                right = mid - 1;
            }
        }
        return left;
    }


    // last index of element equal to target
    public int search2(int[] input, int target) {
        int left = 0;
        int right = input.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (input[mid] < target) {
                left = mid + 1;
            } else if (input[mid] > target) {
                right = mid - 1;
            } else {
                // right)left
                // push right take right
                left = mid  + 1;
            }
        }
        return right;
    }

    // last <= target element
    public int search3(int[] input, int target) {
        int left = 0;
        int right = input.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (input[mid] < target) {
                left = mid + 1;
            } else if (input[mid] > target) {
                right = mid - 1;
            } else {
                // right)left
                // push right take right
                left = mid  + 1;
            }
        }
        return right;
    }

    public static void main(String[] args) {
        int[] input = {0, 1, 1, 1, 2, 2, 2, 3, 3, 3};
        BinarySearchAndVariant bv = new BinarySearchAndVariant();
        System.out.println(bv.search1(input, 3));
        System.out.println(bv.search2(input, 3));
    }
}
