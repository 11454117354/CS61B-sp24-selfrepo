import org.apache.bcel.classfile.ArrayElementValue;

import java.util.List;
import java.util.ArrayList;

public class LinkedListDeque61B<T> implements Deque61B<T>{
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
        if (sentinel.next == sentinel) {
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
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
        if (index <= 0) {
            return null;
        }
        Node pointer = sentinel.next;
        for (int i = 0; i < index - 1; i++) {
            if (pointer.next == sentinel) {
                return null;
            }
            pointer = pointer.next;
        }
        return pointer.item;
    }

    @Override
    public T getRecursive(int index) {
        return null;
    }
}
