class Solution {
    public ListNode removeZeroSumSublists(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        Map<Integer, ListNode> map = new HashMap<>();

        int prefixSum = 0;
        ListNode curr = dummy;

        while (curr != null) {
            prefixSum += curr.val;
            map.put(prefixSum, curr);
            curr = curr.next;
        }

        prefixSum = 0;
        curr = dummy;

        while (curr != null) {
            prefixSum += curr.val;
            curr.next = map.get(prefixSum).next;
            curr = curr.next;
        }

        return dummy.next;
    }
}