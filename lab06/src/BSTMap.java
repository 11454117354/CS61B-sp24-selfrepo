import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

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
        root = insert(root, (K) key, (V) value);
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
        return finder(root, (K) key);
    }

    /** Finds the value corresponding to the key */
    private V finder(Node N, K key) {
        if (N == null) return null;
        if (key.compareTo(N.key) < 0) return finder(N.left, key);
        else if (key.compareTo(N.key) > 0) return finder(N.right, key);
        else return N.value;
    }

    /**
     * Returns whether this map contains a mapping for the specified key.
     *
     * @param key
     */
    @Override
    public boolean containsKey(Object key) {
        return actuallyContainsKey(root, (K) key);
    }

    /** Returns true when key exists, even when value is null */
    private boolean actuallyContainsKey(Node N, K key) {
        if (N == null) return false;
        int cmp = key.compareTo(N.key);
        if (cmp < 0) return actuallyContainsKey(N.left, key);
        else if (cmp > 0) return actuallyContainsKey(N.right, key);
        else return true;
    }

    /**
     * Returns the number of key-value mappings in this map.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Removes every mapping from this map.
     */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Returns a Set view of the keys contained in this map. Not required for Lab 7.
     * If you don't implement this, throw an UnsupportedOperationException.
     */
    @Override
    public Set keySet() {
        TreeSet<K> keySet = new TreeSet<>();
        addKeysInOrder(root, keySet);
        return keySet;
    }

    /** Add all keys in BST to a treeSet */
    private void addKeysInOrder(Node N, TreeSet<K> keys) {
        if (N == null) return;
        addKeysInOrder(N.left, keys);
        keys.add(N.key);
        addKeysInOrder(N.right, keys);
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
        throw new UnsupportedOperationException();
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator iterator() {
        throw new UnsupportedOperationException();
    }
}
