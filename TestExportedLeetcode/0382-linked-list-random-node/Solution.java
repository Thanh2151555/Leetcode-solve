import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int val) { this.val = val; }
 * }
 */
class Solution {

    private List<Integer> values;
    private Random random;

    public Solution(ListNode head) {

        values = new ArrayList<>();
        random = new Random();

        while (head != null) {
            values.add(head.val);
            head = head.next;
        }
    }

    public int getRandom() {
        int index = random.nextInt(values.size());
        return values.get(index);
    }
}