package deque;

import org.apache.commons.collections.iterators.ArrayListIterator;
import org.checkerframework.checker.units.qual.A;

import java.lang.Math;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrayDeque61B<T> implements Deque61B<T>{
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;
    private int arrayLength;

    public ArrayDeque61B() {
        arrayLength = 8;
        items = (T[]) new Object[arrayLength];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    @Override
    public void addFirst(T x) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextFirst] = x;
        nextFirst = Math.floorMod(nextFirst - 1, arrayLength);
        size += 1;
    }

    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        int i = Math.floorMod(nextFirst + 1, arrayLength);
        int j = 0;
        while(j < size) {
            a[j] = items[i];
            i = Math.floorMod(i + 1, arrayLength);
            j++;
        }
        nextFirst = capacity - 1;
        nextLast = size;
        arrayLength = capacity;
        items = a;
    }

    @Override
    public void addLast(T x) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextLast] = x;
        nextLast = Math.floorMod(nextLast + 1, arrayLength);
        size += 1;
    }

    @Override
    public List toList() {
        List<T> returnList = new ArrayList<>();
        int i = Math.floorMod(nextFirst + 1, arrayLength);
        for (int j = 0; j < size; j++) {
            returnList.add(items[i]);
            i = Math.floorMod(i + 1, arrayLength);
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if ((size <= arrayLength / 4 && arrayLength >= 16) || (size <= arrayLength / 8 && arrayLength < 15)) {
            resize(size * 2);
        }
        if (size == 0) {
            return null;
        }
        T returnItem = items[Math.floorMod(nextFirst + 1, arrayLength)];
        items[Math.floorMod(nextFirst + 1, arrayLength)] = null;
        nextFirst = Math.floorMod(nextFirst + 1, arrayLength);
        size--;
        return returnItem;
    }

    @Override
    public T removeLast() {
        if ((size <= arrayLength / 4 && arrayLength >= 16) || (size <= arrayLength / 8 && arrayLength < 15)) {
            resize(size * 2);
        }
        if (size == 0) {
            return null;
        }
        T returnItem = items[Math.floorMod(nextLast - 1, arrayLength)];
        items[Math.floorMod(nextLast - 1, arrayLength)] = null;
        nextLast = Math.floorMod(nextLast - 1, arrayLength);
        size--;
        return returnItem;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return items[Math.floorMod(nextFirst + index + 1, arrayLength)];
    }

    @Override
    public T getRecursive(int index) {
        throw new UnsupportedOperationException("No need to implement getRecursive for proj 1b");
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayListIterator();
    }

    private class ArrayListIterator implements Iterator<T> {
        private int pos;
        private int index;

        public ArrayListIterator() {
            pos = 0;
            index = Math.floorMod(nextFirst + 1, arrayLength);
        }

        @Override
        public boolean hasNext() {
            return pos < size;
        }

        @Override
        public T next() {
            T returnItem = items[index];
            index = Math.floorMod(index + 1, arrayLength);
            pos++;
            return returnItem;
        }
    }
}