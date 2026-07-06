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
    public int[][] spiralMatrix(int m, int n, ListNode head) {
        int[][] matrix = new int[m][n];

        // Fill with -1
        for (int i = 0; i < m; i++) {
            Arrays.fill(matrix[i], -1);
        }

        int top = 0;
        int bottom = m - 1;
        int left = 0;
        int right = n - 1;

        ListNode curr = head;

        while (curr != null && top <= bottom && left <= right) {

            // Left -> Right
            for (int col = left; col <= right && curr != null; col++) {
                matrix[top][col] = curr.val;
                curr = curr.next;
            }
            top++;

            // Top -> Bottom
            for (int row = top; row <= bottom && curr != null; row++) {
                matrix[row][right] = curr.val;
                curr = curr.next;
            }
            right--;

            // Right -> Left
            for (int col = right; col >= left && curr != null; col--) {
                matrix[bottom][col] = curr.val;
                curr = curr.next;
            }
            bottom--;

            // Bottom -> Top
            for (int row = bottom; row >= top && curr != null; row--) {
                matrix[row][left] = curr.val;
                curr = curr.next;
            }
            left++;
        }

        return matrix;
    }
}