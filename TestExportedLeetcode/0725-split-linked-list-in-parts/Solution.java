class Solution {
    public ListNode[] splitListToParts(ListNode head, int k) {
        ListNode[] result = new ListNode[k];

        int length = 0;
        ListNode curr = head;

        while (curr != null) {
            length++;
            curr = curr.next;
        }

        int baseSize = length / k;
        int extra = length % k;

        curr = head;

        for (int i = 0; i < k; i++) {
            result[i] = curr;

            int partSize = baseSize;

            if (extra > 0) {
                partSize++;
                extra--;
            }

            for (int j = 0; j < partSize - 1; j++) {
                if (curr != null) {
                    curr = curr.next;
                }
            }

            if (curr != null) {
                ListNode nextPart = curr.next;
                curr.next = null;
                curr = nextPart;
            }
        }

        return result;
    }
}