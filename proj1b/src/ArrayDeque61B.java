import org.checkerframework.checker.units.qual.A;

import java.lang.Math;
import java.util.ArrayList;
import java.util.List;

public class ArrayDeque61B<T> implements Deque61B<T>{
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;
    private int arrayLenth;

    public ArrayDeque61B() {
        arrayLenth = 8;
        items = (T[]) new Object[arrayLenth];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    @Override
    public void addFirst(T x) {
        if (size == items.length) {
            resize(size * 2);
            arrayLenth *= 2;
        }
        items[nextFirst] = x;
        nextFirst = Math.floorMod(nextFirst - 1, arrayLenth);
        size += 1;
    }

    private void resize(int capacity) {
        int[] a = new int[capacity];

    }

    @Override
    public void addLast(T x) {
        if (size == items.length) {
            resize(size * 2);
            arrayLenth *= 2;
        }
        items[nextLast] = x;
        nextLast = Math.floorDiv(nextLast + 1, arrayLenth);
        size += 1;
    }

    @Override
    public List toList() {
        List<T> returnList = new ArrayList<>();
        int i = Math.floorMod(nextFirst + 1, arrayLenth);
        while(items[i] != null) {
            returnList.add(items[i]);
            i = Math.floorMod(i + 1, arrayLenth);
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public T removeFirst() {
        return null;
    }

    @Override
    public T removeLast() {
        return null;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index > size) {
            return null;
        }
        return items[Math.floorMod(nextFirst + index, arrayLenth)];
    }

    @Override
    public T getRecursive(int index) {
        return null;
    }
}
