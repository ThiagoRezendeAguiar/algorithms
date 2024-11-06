package trees.binary_tree;

public class BinaryTree<T extends Comparable<T>> {
    private Node<T> root;

    public BinaryTree() {
        this.root = null;
    }

    public boolean search(T data) {
        return search(data, this.root);
    }

    private boolean search(T data, Node<T> node) {
        if (node == null) {
            return false;
        } else if (data.compareTo(node.getData()) == 0) {
            return true;
        } else if (data.compareTo(node.getData()) < 0) {
            return search(data, node.getLeft());
        } else {
            return search(data, node.getRight());
        }
    }

    public void insert(T data) throws Exception {
        this.root = insert(data, this.root);
    }

    private Node<T> insert(T data, Node<T> node) throws Exception {
        if (node == null) {
            node = new Node<>(data);
        } else if (data.compareTo(node.getData()) < 0) {
            node.setLeft(insert(data, node.getLeft()));
        } else if (data.compareTo(node.getData()) > 0) {
            node.setRight(insert(data, node.getRight()));
        } else if (data.compareTo(node.getData()) == 0) {
            throw new Exception("This information already exists!");
        }
        return node;
    }

    public void remove(T data) throws Exception {
        this.root = remove(data, this.root);
    }

    private Node<T> remove(T data, Node<T> node) throws Exception {
        if (node == null) {
            throw new Exception("This information doesn't exists!");
        } else if (data.compareTo(node.getData()) < 0) {
            node.setLeft(remove(data, node.getLeft()));
        } else if (data.compareTo(node.getData()) > 0) {
            node.setRight(remove(data, node.getRight()));
        } else if (node.getRight() == null) {
            node = node.getLeft();
        } else if (node.getLeft() == null) {
            node = node.getRight();
        } else {
            node.setLeft(getBiggerLeft(node, node.getLeft()));
        }
        return node;
    }

    private Node<T> getBiggerLeft(Node<T> parent, Node<T> child){
        if(child.getRight() == null){
            parent.setData(child.getData());
            child = child.getLeft();
        } else {
            child.setRight(getBiggerLeft(parent, child.getRight()));
        }
        return child;
    }

}
