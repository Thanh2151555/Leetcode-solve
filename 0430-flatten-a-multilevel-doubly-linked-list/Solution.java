class Solution {
    public Node flatten(Node head) {
        if (head == null) return null;

        flattenDFS(head);
        return head;
    }

    // Trả về node cuối cùng sau khi flatten từ head
    private Node flattenDFS(Node head) {
        Node curr = head;
        Node tail = head;

        while (curr != null) {
            Node next = curr.next;

            if (curr.child != null) {
                Node childHead = curr.child;
                Node childTail = flattenDFS(childHead);

                // nối curr -> childHead
                curr.next = childHead;
                childHead.prev = curr;
                curr.child = null;

                // nối childTail -> next
                if (next != null) {
                    childTail.next = next;
                    next.prev = childTail;
                }

                tail = childTail;
            } else {
                tail = curr;
            }

            curr = next;
        }

        return tail;
    }
}