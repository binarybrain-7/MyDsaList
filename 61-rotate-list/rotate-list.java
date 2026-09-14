/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {

    // public ListNode reverseList(ListNode head) {
    //     if (head == null || head.next == null) {
    //         return head;
    //     }

    //     ListNode prev = null;
    //     ListNode curr = head;
    //     while (curr != null) {
    //         ListNode front = curr.next;
    //         curr.next = prev;
    //         prev = curr;
    //         curr = front;

    //     }
    //     return prev;
    // }

    public ListNode rotateRight(ListNode head, int k) {
    if (head == null || head.next == null || k == 0) {
        return head;
    }

    // 1. Calculate the total length and find the tail node
    ListNode temp = head;
    int count = 1;
    while (temp.next != null) {
        count++;
        temp = temp.next;
    }

    // 2. Normalize k
    k = k % count;
    if (k == 0) return head;

    // 3. Find the new tail: (count - k - 1) steps from head
    ListNode newTail = head;
    for (int i = 0; i < count - k - 1; i++) {
        newTail = newTail.next;
    }

    // 4. Rearrange pointers
    ListNode newHead = newTail.next;
    newTail.next = null;
    temp.next = head;

    return newHead;
}
}