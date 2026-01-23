package deque;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class LinkedListDeque61B<T> implements Deque61B<T>{
    @Override
    public Iterator<T> iterator() {
        return new DLListIterator();
    }

    private class DLListIterator implements Iterator<T> {
        private Node pointer = sentinel.next;

        @Override
        public boolean hasNext() {
            return pointer != sentinel;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            T returnItem = pointer.item;
            pointer = pointer.next;
            return returnItem;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Deque61B<?> deq) {
            if (this == obj) {
                return true;
            }

            if (deq.size() != this.size) {
                return false;
            }

            int index = 0;
            for (T item : this) {
                Object otherItem = deq.get(index);
                if (!item.equals(otherItem)) {
                    return false;
                }
                index++;
            }
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return this.toList().toString();
    }

    public class Node {
        public T item;
        public Node prev;
        public Node next;

        private Node(T item, Node prev, Node next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    private int size;
    private Node sentinel;

    public LinkedListDeque61B() {
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    @Override
    public void addFirst(T x) {
        Node newItem = new Node(x, sentinel, sentinel.next);
        sentinel.next.prev = newItem;
        sentinel.next = newItem;
        size += 1;
    }

    @Override
    public void addLast(T x) {
        Node newItem = new Node(x, sentinel.prev, sentinel);
        sentinel.prev.next = newItem;
        sentinel.prev = newItem;
        size += 1;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        Node pointer = sentinel.next;
        while (pointer != sentinel) {
            returnList.add(pointer.item);
            pointer = pointer.next;
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        return sentinel.next == sentinel;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if (sentinel.next == sentinel) {
            return null;
        }
        T item = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size -= 1;
        return item;
    }

    @Override
    public T removeLast() {
        if (sentinel.next == sentinel) {
            return null;
        }
        T item = sentinel.prev.item;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;

        size -= 1;
        return item;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        Node pointer = sentinel.next;
        for (int i = 0; i < index; i++) {
            pointer = pointer.next;
        }
        return pointer.item;
    }

    @Override
    public T getRecursive(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        Node pointer = sentinel.next;
        pointer = recursiveHelper(pointer, index);
        return pointer.item;
    }

    private Node recursiveHelper(Node current, int index) {
        if(index == 0) {
            return current;
        } else {
            return recursiveHelper(current.next, index - 1);
        }
    }
}
