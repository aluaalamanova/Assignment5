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
    }

}