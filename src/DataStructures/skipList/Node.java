package DataStructures.skipList;

public class Node {
    int val;
    Node prev;
    Node next;
    Node below;
    Node above;

    public Node(int key) {
        this.val = key;
        this.prev = null;
        this.next = null;
        this.below = null;
        this.above = null;
    }
}
