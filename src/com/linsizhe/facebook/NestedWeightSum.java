package com.linsizhe.facebook;


import java.util.List;

// https://leetcode.com/problems/nested-list-weight-sum/submissions/
public class NestedWeightSum {
    interface NestedInteger {

        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger();
        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger();

        // Set this NestedInteger to hold a single integer.
        public void setInteger(int value);

        // Set this NestedInteger to hold a nested list and adds a nested integer to it.
        public void add(NestedInteger ni);

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return null if this NestedInteger holds a single integer
        public List<NestedInteger> getList();
    }
    public int depthSum(List<NestedInteger> nestedList) {
        return getSum(nestedList, 1);
    }

    public int getSum(List<NestedInteger> cur, int idx) {
        int sum = 0;
        for (NestedInteger ne: cur) {
            if (ne.isInteger()) {
                sum += ne.getInteger() * idx;
            } else {
                sum += getSum(ne.getList(), idx + 1);
            }
        }
        return sum;
    }
}
