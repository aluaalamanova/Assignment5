public class BST <K extends Comparable<K>, V> {
    private Node root;
    private int size;
    private class Node{
        private K key;
        private V value;

        private Node left, right;
        public Node(K key, V value){
            this.key=key;
            this.value=value;
        }
    }
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
            node.value = value; // Update the value if the key already exists
        }

        return node;
    }
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

    private Node findMin(Node node) {
        if (node.left == null) {
            return node;
        }
        return findMin(node.left);
    }


}
