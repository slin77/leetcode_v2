package com.linsizhe.coinbase;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class FlatArrayIterator<E> implements Iterator<E>, Iterable<E> {

    private E[][] data;
    private int i;
    private int j;

    public FlatArrayIterator(E[][] data) {
        this.data = data;
        i = 0;
        j = 0;
    }

    @Override
    public boolean hasNext() {
        while (i < data.length) {
            if (j < data[i].length) {
                return true;
            } else {
                i++;
                j = 0;
            }
        }
        return false;
    }

    @Override
    public E next() {
        if (this.hasNext()) {
            return data[i][j++];
        }
        throw new NoSuchElementException();
    }

    @Override
    public Iterator<E> iterator() {
        return this;
    }

    public static void main(String[] args) {
        Integer[][] arrs = new Integer[][] {{1, 2, 4, 5, 6}, {}, {9, 9, 9, 9}};

        FlatArrayIterator<Integer> fi = new FlatArrayIterator<>(arrs);

        for (int i : fi) {
            System.out.println(i);
        }
    }
}
