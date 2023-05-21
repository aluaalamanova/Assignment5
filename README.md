# Assignment5
### Binary Search Tree implementation with key-value pairs.

Introduction:
--------------
The Binary Search Tree (BST) is a data structure that allows efficient insertion, deletion, and retrieval of key-value pairs. In a BST, each node has a key-value pair, and the keys are organized in a way that allows for quick searching and sorting.

Functions:
---------
### - Insertion: Add a key-value pair to the BST.
```
 public void put(K key, V value) {
        root = putNode(root, key, value);
    }
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
            node.value = value; 
        }

        return node;
    }
```
### - Retrieval: Get the value associated with a given key.
```
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
        return null; 
    }
```
### - Deletion: Remove a key-value pair from the BST.
```
public void delete(K key) {
        root = deleteNode(root, key);
    }
    private Node deleteNode(Node node, K key) {
        if (node == null) {
            return null;
        }
        int m = key.compareTo(node.key);
        if (m < 0) {
            node.left = deleteNode(node.left, key);
        } else if (m > 0) {
            node.right = deleteNode(node.right, key);
        } else {
            if (node.left == null && node.right == null) {
                return null; 
            } else if (node.left == null) {
                return node.right; 
            } else if (node.right == null) {
                return node.left; 
            } else {
                Node successor = findMin(node.right);
                node.key = successor.key;
                node.value = successor.value;
                node.right = deleteNode(node.right, successor.key);
            }
        }
        return node;
    }
    private Node findMin(Node node) {
        if (node.left == null) {
            return node;
        }
        return findMin(node.left);
    }
```
### - In-order Traversal: Iterate over the key-value pairs in ascending order.
```
public Iterable<Entry<K, V>> iterator() {
        List<Entry<K, V>> keyValues = new ArrayList<>();
        inOrderIterator(root, keyValues);
        return keyValues;
    }
    private void inOrderIterator(Node node, List<Entry<K, V>> keyValues) {
        if (node != null) {
            inOrderIterator(node.left, keyValues);
            keyValues.add(new Entry<>(node.key, node.value));
            inOrderIterator(node.right, keyValues);
        }
    }
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
```
Example of usage:
--------
```
BST<Integer, String> binTree = new BST<>();
        binTree.put(1, "Lipstick");
        binTree.put(2, "Highlighter");
        binTree.put(3, "Blush");
        binTree.put(4, "Eye shadow");
        binTree.put(5, "Mascara");
        binTree.delete(1);
        System.out.println(binTree.get(2));
        System.out.println(binTree.get(1));
        for (BST.Entry elem : binTree.iterator()) {
            System.out.println("key is " + elem.key() + " and value is " + elem.value());
        }
// Output:
// Highlighter
// null
// key is 2 and value is Highlighter
// key is 3 and value is Blush
// key is 4 and value is Eye shadow
// key is 5 and value is Mascara
```
