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
if(headA ==null || headB ==null ){
    return null;
} 
if(headA  ==  headB){
    return  headA;
}



        ListNode  temp = headA;
        while(temp.next!=null){
            temp=temp.next;

        }
        temp.next = headA;
        ListNode slow = headB;
        ListNode fast = headB;
        while(fast!=null && fast.next!=null){
            slow=slow.next;
            fast = fast.next.next;
            if(slow==fast){
                slow = headB;
        while(slow!=fast){
            slow=slow.next;
            fast = fast.next;
        }
        temp.next = null;
        return slow;
               
            }
        }
        temp.next = null;
        return null;
    }
}