package deque;

import deque.ArrayDeque61B;

import java.util.Comparator;

public class MaxArrayDeque61B<T> extends ArrayDeque61B<T> {
    private Comparator<T> comparator;

    public MaxArrayDeque61B(Comparator<T> c) {
        super();
        comparator = c;
    }

    public T max() {
        if (this.isEmpty()) {
            return null;
        }
        T maxItem = this.get(0);
        for (int i = 0; i < this.size(); i++) {
            T current = this.get(i);
            if (comparator.compare(current, maxItem) > 0) {
                maxItem = current;
            }
        }
        return maxItem;
    }

    public T max(Comparator<T> c) {
        if (this.isEmpty()) {
            return null;
        }
        T maxItem = this.get(0);
        for (int i = 0; i < this.size(); i++) {
            T current = this.get(i);
            if (c.compare(current, maxItem) > 0) {
                maxItem = current;
            }
        }
        return maxItem;
    }
}