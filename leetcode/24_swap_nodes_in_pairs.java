/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    // iterative
    public ListNode swapPairs(ListNode head) {
        ListNode dh = new ListNode(0);
        dh.next = head; // in case: [1]
        ListNode p = dh;
        ListNode q = head;
        while (q != null && q.next != null) {
            p.next = q.next;
            q.next = p.next.next;
            p.next.next = q;
            p = q;
            q = q.next;
        }
        return dh.next;
    }

    // recursive
    public ListNode swapPairs(ListNode head) {
        if ((head == null) || (head.next == null))
            return head;
        ListNode n = head.next;
        head.next = swapPairs(head.next.next);
        n.next = head;
        return n;
    }
}
