package com.linsizhe.facebook;

public class FirstBadVersion {
    public int firstBadVersion(int n) {
        int start = 1;
        int end = n;
        // 1 1 1 1 1 2 2 2 2 2
        //
        while (start < end) { // start while condition for binary search!!! memorize!
            int mid = start + (end - start) / 2; // avoid overflow version of (start + end) / 2
            if (isBadVersion(mid)) {
                end = mid; // binary search would have end = mid - 1
                // but here we use end = mid
                // if we know that if we only have 2 branch
                // and the one we want to find is in the branch we are going to
                // srink start, end will eventually converge to that value after
                // the while loop
            } else {
                start = mid + 1;
            }
        }
        return start;
    }

    /* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */
    private boolean isBadVersion(int n) {
        return false;
    }
}

