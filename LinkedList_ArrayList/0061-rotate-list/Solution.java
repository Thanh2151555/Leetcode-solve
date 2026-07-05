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
    public ListNode rotateRight(ListNode head, int k) {

        if (head == null || head.next == null || k == 0) {
            return head;
        }

        int length = 1;
        ListNode tail = head;

        // dem do dai va tim node cuoi
        while (tail.next != null) {
            tail = tail.next;
            length++;
        }

        k = k % length;

        if (k == 0) {
            return head;
        }

        // bien linked list thanh vong tron
        tail.next = head;

        int stepsToNewTail = length - k;
        ListNode newTail = tail;

        while (stepsToNewTail > 0) {
            newTail = newTail.next;
            stepsToNewTail--;
        }

        ListNode newHead = newTail.next;

        // cat vong tron
        newTail.next = null;

        return newHead;
    }
}