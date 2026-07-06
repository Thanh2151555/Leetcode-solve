class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        int firstCritical = -1;
        int prevCritical = -1;

        int minDistance = Integer.MAX_VALUE;
        int index = 2;

        ListNode prev = head;
        ListNode curr = head.next;

        while (curr != null && curr.next != null) {
            ListNode next = curr.next;

            boolean isMax = curr.val > prev.val && curr.val > next.val;
            boolean isMin = curr.val < prev.val && curr.val < next.val;

            if (isMax || isMin) {
                if (firstCritical == -1) {
                    firstCritical = index;
                } else {
                    minDistance = Math.min(minDistance, index - prevCritical);
                }

                prevCritical = index;
            }

            prev = curr;
            curr = curr.next;
            index++;
        }

        if (firstCritical == prevCritical) {
            return new int[]{-1, -1};
        }

        int maxDistance = prevCritical - firstCritical;

        return new int[]{minDistance, maxDistance};
    }
}