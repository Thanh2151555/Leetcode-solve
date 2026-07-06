/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null || headB == null){
            return null;
        }
        ListNode poiterA = headA;
        ListNode poiterB = headB;
        while(poiterA != poiterB){

            if(poiterA == null){
                poiterA = headB;
            }else{
                poiterA = poiterA.next;
            }

            if(poiterB == null){ 
                poiterB = headA; 
            }else{
                poiterB = poiterB.next; 
            }
        }
        return poiterA;
    }
}