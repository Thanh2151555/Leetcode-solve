/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) {
 *         this.val = val;
 *         this.next = next;
 *     }
 * }
 */
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode fast = dummy;
        ListNode slow = dummy;

        // fast di truoc n + 1 buoc
        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }

        // cung di cho den khi fast = null
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // xoa node thu n tu cuoi
        slow.next = slow.next.next;

        return dummy.next;
    }
}