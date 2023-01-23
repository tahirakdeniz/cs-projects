public class Queue {

    private int size = 0;

    private Node backNode;
    private Node frontNode;

    public Queue() {}

    public void enqueueFront(int val){
        Node node = new Node(val);

        if (size == 0)
            addFirstNode(node);
        else {
            node.setNextNode(frontNode);
            frontNode = node;
        }

        size++;
    }

    public void enqueueMiddle(int val){
        Node node = new Node(val);
        Node middleNode;
        if (size == 0)
            addFirstNode(node);
        else if (size == 1) {
            enqueueFront(val);
        }
        else if(size%2 == 0){
            middleNode = getMiddleNode();
            middleNode.getNextNode().setPreviousNode(node);
            middleNode.setNextNode(node);
        }
        else{
            middleNode = getMiddleNode();
            middleNode.getPreviousNode().setNextNode(node);
            middleNode.setPreviousNode(node);
        }

        size++;
    }

    public void enqueueBack(int val){
        Node node = new Node(val);

        if (size == 0)
            addFirstNode(node);
        else {
            node.setPreviousNode(backNode);
            backNode = node;
        }

        size++;
    }

    public int dequeueFront(){
        if (size == 0)
            return -1;
        else {
            int val = frontNode.getValue();

            if (size == 1)
                deleteLastNode();
            else {
                frontNode = frontNode.getNextNode();
                frontNode.setPreviousNode(null);
            }

            size--;
            return val;
        }
    }

    public int dequeueMiddle(){
        if (size == 0)
            return -1;
        else {
            Node middleNode = getMiddleNode();
            int val = middleNode.getValue();

            if (size == 1)
                deleteLastNode();
            else if(size == 2) {
                frontNode = backNode;
                frontNode.setPreviousNode(null);
            }
            else {
                Node previousNode = middleNode.getPreviousNode();
                middleNode.getPreviousNode().setNextNode(middleNode.getNextNode());
            }

            size--;
            return val;
        }


    }

    public int dequeueBack(){
        if (size == 0)
            return -1;
        else {
            int val = backNode.getValue();

            if (size == 1)
                deleteLastNode();
            else {
                backNode = backNode.getPreviousNode();
                backNode.setNextNode(null);
            }

            size--;
            return val;
        }

    }

    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder("[");

        Node node = frontNode;


        while (node != null) {
            stringBuilder.append(node.getValue()).append(",");
            node = node.getNextNode();
        }

        if (stringBuilder.length() > 2)
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);

        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    private void addFirstNode(Node node){
        frontNode = node;
        backNode = node;
    }

    private void deleteLastNode(){
        frontNode = null;
        backNode = null;
    }

    private Node getMiddleNode(){
        Node node = frontNode;
        int nodeNumber = 0;
        int indexToFind = (int) Math.floor((double) (size-1)/2);

        while(nodeNumber != indexToFind){
            node = node.getNextNode();
            nodeNumber++;
        }

        return node;
    }
}
