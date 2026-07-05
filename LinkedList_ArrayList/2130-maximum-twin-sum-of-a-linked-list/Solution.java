class Solution {
    public int pairSum(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode secondHalf = reverse(slow);

        ListNode firstHalf = head;
        int maxSum = 0;

        while (secondHalf != null) {
            int sum = firstHalf.val + secondHalf.val;
            maxSum = Math.max(maxSum, sum);

            firstHalf = firstHalf.next;
            secondHalf = secondHalf.next;
        }

        return maxSum;
    }

    private ListNode reverse(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;

        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        return prev;
    }
}