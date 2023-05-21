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

}
