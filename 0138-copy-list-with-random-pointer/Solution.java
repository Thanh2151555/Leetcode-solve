
/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/
import java.util.HashMap;
import java.util.Map;

class Solution {
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        Map<Node, Node> map = new HashMap<>();
        Node current = head;
        // tao node moi cho tung node cu 
        while (current != null) {
            map.put(current, new Node(current.val));
            current = current.next;
        }
        current = head;
        // 2. gan next va random cho node moi
        while (current != null) {
            Node copyNode = map.get(current);

            copyNode.next = map.get(current.next);
            copyNode.random = map.get(current.random);
            current = current.next;
        }
        return map.get(head);
    }
}
