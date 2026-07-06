class Solution {
    public ListNode reverseEvenLengthGroups(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode prevGroupTail = dummy;
        ListNode curr = head;

        int groupSize = 1;

        while (curr != null) {
            int count = 0;
            ListNode temp = curr;

            while (count < groupSize && temp != null) {
                temp = temp.next;
                count++;
            }

            if (count % 2 == 0) {
                ListNode prev = temp;
                ListNode node = curr;

                for (int i = 0; i < count; i++) {
                    ListNode next = node.next;
                    node.next = prev;
                    prev = node;
                    node = next;
                }

                ListNode oldHead = curr;
                prevGroupTail.next = prev;
                prevGroupTail = oldHead;
                curr = temp;
            } else {
                for (int i = 0; i < count; i++) {
                    prevGroupTail = curr;
                    curr = curr.next;
                }
            }

            groupSize++;
        }

        return dummy.next;
    }
}