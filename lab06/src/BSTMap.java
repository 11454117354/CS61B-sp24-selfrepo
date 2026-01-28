import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B {
    private class Node {
        K key;
        V value;
        Node left;
        Node right;

        Node (K k, V v) {
            key = k;
            value = v;
            left = null;
            right = null;
        }
    }
    private Node root;
    private int size;

    public BSTMap() {
        root = null;
        size = 0;
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map already contains the specified key, replaces the key's mapping
     * with the value specified.
     *
     * @param key
     * @param value
     */
    @Override
    public void put(Object key, Object value) {
        insert(root, (K) key, (V) value);
    }

    /** Is the helper method to insert a node in the right place recursively */
    private Node insert(Node N, K key, V value) {
        if (N == null) {
            size++;
            return new Node(key, value);
        }
        if (key.compareTo(N.key) < 0) {
            N.left = insert(N.left, key, value);
        } else if (key.compareTo(N.key) > 0) {
            N.right = insert(N.right, key, value);
        } else {
            N.value = value;
        }
        return N;
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     *
     * @param key
     */
    @Override
    public Object get(Object key) {
        return null;
    }

    /**
     * Returns whether this map contains a mapping for the specified key.
     *
     * @param key
     */
    @Override
    public boolean containsKey(Object key) {
        return false;
    }

    /**
     * Returns the number of key-value mappings in this map.
     */
    @Override
    public int size() {
        return 0;
    }

    /**
     * Removes every mapping from this map.
     */
    @Override
    public void clear() {

    }

    /**
     * Returns a Set view of the keys contained in this map. Not required for Lab 7.
     * If you don't implement this, throw an UnsupportedOperationException.
     */
    @Override
    public Set keySet() {
        return Set.of();
    }

    /**
     * Removes the mapping for the specified key from this map if present,
     * or null if there is no such mapping.
     * Not required for Lab 7. If you don't implement this, throw an
     * UnsupportedOperationException.
     *
     * @param key
     */
    @Override
    public Object remove(Object key) {
        return null;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator iterator() {
        return null;
    }
}
