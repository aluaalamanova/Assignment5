public class Main {
    public static void main(String[] args) {
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
        System.out.println(binTree.consist(3, "Blush"));
        System.out.println(binTree.consist(9, "Alua"));

    }
}