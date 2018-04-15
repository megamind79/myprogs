package MyHelperClasses;

public class Node {
    /**
     * Node in a Tree
     */
    public int data;
    public Node left, right;

    public Node (int data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Node("+data+")";
    }

    @Override
    public boolean equals(Object node) {
        return ((Node)node).data == data;
    }

    @Override
    public int hashCode () {
        return Integer.hashCode(data);
    }
}
