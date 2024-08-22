package DataStructures.skipList;

import java.util.Random;

public class SkipList {
    private final int neg = Integer.MIN_VALUE;
    private final int pos = Integer.MAX_VALUE;

    private Node head;
    private Node tail;

    private static int heightOfSkipList = 0;

    Random random = new Random();

    public SkipList() {
        head = new Node(neg);
        tail = new Node(pos);
        head.next = tail;
        tail.prev = head;
    }

    public boolean containsNode(int key) {
        Node node = searchNode(key);
        return node.val == key;
    }

    private Node searchNode(int key) {
        Node node = head;

        while(node.below != null) {
            node = node.below;

            while(key >= node.next.val) {
                node = node.next;
            }
        }

        return node;
    }

    public Node addNode(int key) {
        Node position = searchNode(key);
        Node prevNode;

        int levels = -1;

        if(position.val == key) {
            return position;
        }

        do {
            levels++;

            canIncreaseLevel(levels);

            prevNode = position;

            while(position.above == null) {
                position = position.prev;
            }
            position = position.above;

            prevNode = addAfterPrevNode(position, prevNode, key);
        }
        while(random.nextBoolean() == true);

        return prevNode;
    }

    private void canIncreaseLevel(int levels) {
        if(levels >= heightOfSkipList) {
            heightOfSkipList++;
            addEmptyLevel();
        }
    }

    private void addEmptyLevel() {
        Node newHead = new Node(neg);
        Node newTail = new Node(pos);

        newHead.next = newTail;
        newHead.below = head;
        newTail.prev = newHead;
        newTail.below = tail;

        head.above = newHead;
        tail.above = newTail;

        head = newHead;
        tail = newTail;
    }

    private Node addAfterPrevNode(Node position, Node prevNode, int key) {
        Node newNode = new Node(key);
        Node nodeBelowNewNode = position.below.below;

        setBeforeAndAfterReferences(newNode, prevNode);
        setAboveAndBelowReferences(newNode, position, nodeBelowNewNode, key);

        return newNode;
    }

    private void setBeforeAndAfterReferences(Node newNode, Node prevNode) {
        newNode.next = prevNode.next;
        newNode.prev = prevNode;

        prevNode.next.prev = newNode;
        prevNode.next = newNode;
    }

    private void setAboveAndBelowReferences(Node newNode, Node position, Node nodeBelowNewNode, int key) {
        if(nodeBelowNewNode != null) {
            while(true) {
                if(nodeBelowNewNode.val != key) {
                    nodeBelowNewNode = nodeBelowNewNode.next;
                }
                else {
                    break;
                }
            }

            newNode.below = nodeBelowNewNode;
            nodeBelowNewNode.above = newNode;
        }

        if(position != null) {
            if(position.next.val == key) {
                newNode.above = position.next;
            }
        }
    }

    public boolean removeNode(int key) throws NodeNotFoundException, IllegalKeyValueException{
        if(key == neg || key == pos) {
            throw new IllegalKeyValueException("Key value must be greater than " + neg + " and less than " + pos);
        }
        
        Node node = deleteNode(key);

        if(node == null) {
            throw new NodeNotFoundException("No node with key value "+ key + " present");
        }

        return true;
    }

    private Node deleteNode(int key) {
        Node nodeToRemove = searchNode(key);

        if(nodeToRemove.val != key) {
            return null;
        }

        while(nodeToRemove != null) {
            removeReferencesToNode(nodeToRemove);

            if(nodeToRemove.above != null) {
                nodeToRemove = nodeToRemove.above;
            }
            else {
                break;
            }
        }

        return nodeToRemove;
    }

    private void removeReferencesToNode(Node nodeToRemove) {
        Node nodeBeforeNodeToRemove = nodeToRemove.prev;
        Node nodeAfterNodeToRemove = nodeToRemove.next;

        nodeBeforeNodeToRemove.next = nodeAfterNodeToRemove;
        nodeAfterNodeToRemove.prev = nodeBeforeNodeToRemove;
    }

    public void printSkipList() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nSkipList starting with top-left-most node: ");

        Node startNode = head;

        Node highestLevel = startNode;
        int level = heightOfSkipList;

        while(highestLevel != null) {
            sb.append("\nlevel " + level + ": ");

            while(startNode != null) {
                sb.append(startNode.val);

                if(startNode.next != null) {
                    sb.append("-> ");
                }

                startNode = startNode.next;
            }
            
            highestLevel = highestLevel.below;
            startNode = highestLevel;
            level--;
        }

        System.out.println(sb.toString());
    }
}
