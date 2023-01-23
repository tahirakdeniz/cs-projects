public class Node {
    //Attributes:
    private int value;
    private Node previousNode;
    private Node nextNode;

    //Constructors:
    Node(int value){ this.value = value; }
    Node(){ this.value = 0; }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getPreviousNode() {
        return previousNode;
    }

    public void setPreviousNode(Node previousNode) {
        this.previousNode = previousNode;
        if (previousNode != null)
            previousNode.nextNode = this;
    }

    public Node getNextNode() {
        return nextNode;
    }

    public void setNextNode(Node nextNode) {
        this.nextNode = nextNode;
        if (nextNode != null)
            nextNode.previousNode = this;
    }
}
