public class Stack {

    private int capacity;
    private int size = 0;
    private Node headNode;

    public Stack(int capacity){
        this.capacity = capacity;
    }

    public void push(int t){
        Node node = new Node(t);
        node.setPreviousNode(headNode);
        headNode = node;
        size++;
    }

    public int pop(){
        int value = headNode.getValue();
        headNode = headNode.getPreviousNode();
        size--;
        return value;
    }

    public int top(){
        return headNode.getValue();
    }

    public boolean isFull(){
        return size == capacity;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }

    @Override
    public String toString() {
        StringBuilder outputStringBuilder = new StringBuilder();

        Node node = headNode;
        while (node != null) {
            outputStringBuilder.append(node.getValue());
            node = node.getPreviousNode();
        }

        return outputStringBuilder.toString();
    }
}
