package com.linsizhe.coinbase;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class InterleaveIterator implements Iterator, Iterable {

    List<Iterator> iterators;
    int curIdx;

    public InterleaveIterator(List<Iterator> iterators) {
        this.iterators = iterators;
        this.curIdx = 0;
    }

    @Override
    public boolean hasNext() {
        while (curIdx < iterators.size()) {
            if (iterators.get(curIdx).hasNext()) {
                return true;
            } else {
                curIdx++;
            }
        }
        return false;
    }

    @Override
    public Object next() {
       if (this.hasNext()) {
           return iterators.get(curIdx).next();
       } else {
           throw new NoSuchElementException();
       }
    }

    @Override
    public Iterator iterator() {
        return this;
    }

    public static void main(String[] args) {
        int[] arr1 = new int[] {2};
        int[] arr2 = new int[] {};

        InterleaveIterator ii = new InterleaveIterator(Arrays.asList(Arrays.stream(arr1).iterator(),
                Arrays.stream(arr2).iterator()));

        System.out.println(ii.hasNext());

        for (Object i : ii) {
            System.out.println((int) i);
        }

    }
}
