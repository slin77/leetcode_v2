package com.linsizhe.coinbase;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class StepIterator implements Iterator<Integer>, Iterable<Integer> {
    private int start;
    private int step;
    private int end;

    public StepIterator(int start, int step, int end) {
        this.start = start;
        this.step = step;
        this.end = end;
    }

    @Override
    public Iterator<Integer> iterator() {
        return this;
    }

    @Override
    public boolean hasNext() {
        return start <= end;
    }

    @Override
    public Integer next() {
        if (hasNext()) {
            int res = start;
            start += step;
            return res;
        }
        throw new NoSuchElementException();
    }

    public static void main(String[] args) {
        StepIterator si = new StepIterator(1, 0, 2);

        si.forEach(System.out::println);
    }
}
