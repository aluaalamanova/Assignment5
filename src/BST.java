import java.util.ArrayList;
import java.util.List;

/**
 * Binary Search Tree implementation with key-value pairs.
 *
 * @param <K> the key type
 * @param <V> the value type
 */
public class BST<K extends Comparable<K>, V> {
    private Node root;
    private int size;

    private class Node {
        private K key;
        private V value;
        private Node left, right;

        /**
         * Constructs a new node with the given key and value.
         *
         * @param key   the key of the node
         * @param value the value associated with the key
         */
        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    /**
     * Inserts a key-value pair into the BST.
     *
     * @param key   the key to insert
     * @param value the value associated with the key
     */
    public void put(K key, V value) {
        root = putNode(root, key, value);
    }

    // Recursive helper function to insert a key-value pair
    private Node putNode(Node node, K key, V value) {
        if (node == null) {
            size++;
            return new Node(key, value);
        }

        int m = key.compareTo(node.key);
        if (m < 0) {
            node.left = putNode(node.left, key, value);
        } else if (m > 0) {
            node.right = putNode(node.right, key, value);
        } else {
            node.value = value; // Update the value if the key already exists
        }

        return node;
    }

    /**
     * Retrieves the value associated with the given key.
     *
     * @param key the key to search for
     * @return the value associated with the key, or null if the key is not found
     */
    public V get(K key) {
        Node current = root;
        while (current != null) {
            int m = key.compareTo(current.key);
            if (m < 0) {
                current = current.left;
            } else if (m > 0) {
                current = current.right;
            } else {
                return current.value;
            }
        }
        return null; // Key not found
    }

    /**
     * Deletes a key-value pair from the BST.
     *
     * @param key the key to delete
     */
    public void delete(K key) {
        root = deleteNode(root, key);
    }

    // Recursive helper function to delete a key-value pair
    private Node deleteNode(Node node, K key) {
        if (node == null) {
            return null; // Key not found
        }

        int m = key.compareTo(node.key);
        if (m < 0) {
            node.left = deleteNode(node.left, key);
        } else if (m > 0) {
            node.right = deleteNode(node.right, key);
        } else {
            if (node.left == null && node.right == null) {
                return null; // Case 1: Node to be deleted has no children
            } else if (node.left == null) {
                return node.right; // Case 2: Node to be deleted has only right child
            } else if (node.right == null) {
                return node.left; // Case 3: Node to be deleted has only left child
            } else {
                // Case 4: Node to be deleted has two children
                Node successor = findMin(node.right);
                node.key = successor.key;
                node.value = successor.value;
                node.right = deleteNode(node.right, successor.key);
            }
        }
        return node;
    }

    // Helper function to find the node with the minimum key in a subtree
    private Node findMin(Node node) {
        if (node.left == null) {
            return node;
        }
        return findMin(node.left);
    }

    /**
     * Returns an iterable object for in-order traversal of the BST.
     *
     * @return an iterable object for in-order traversal
     */
    public Iterable<Entry<K, V>> iterator() {
        List<Entry<K, V>> keyValues = new ArrayList<>();
        inOrderIterator(root, keyValues);
        return keyValues;
    }
    public boolean consist(K key, V value) {
        boolean contains = consist(root, key, value);
        return contains;
    }
    private boolean consist(Node current, K key, V value) {
        if (current == null) {
            return false;
        } else if (key.equals(current.key)) {
            return true;
        }
        int m = key.compareTo(current.key);
        if (m < 0) {
            return consist(current.left, key, value);
        } else {
            return consist(current.right, key, value);
        }
    }

    // Recursive helper function for in-order traversal
    private void inOrderIterator(Node node, List<Entry<K, V>> keyValues) {
        if (node != null) {
            inOrderIterator(node.left, keyValues);
            keyValues.add(new Entry<>(node.key, node.value));
            inOrderIterator(node.right, keyValues);
        }
    }

    /**
     * Represents a key-value entry in the BST.
     *
     * @param <K> the key type
     * @param <V> the value type
     */
    public class Entry<K, V> {
        private K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K key() {
            return key;
        }

        public V value() {
            return value;
        }
    }
}
