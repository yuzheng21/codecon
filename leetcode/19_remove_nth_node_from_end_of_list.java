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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode p = dummy;
        ListNode q = dummy;
        while (q.next != null) {
            q = q.next;
            if (n > 0) {
                n--;
            } else {
                p = p.next;
            }
        }

        ListNode node = p.next;
        p.next = node.next;
        node.next = null;

        return dummy.next;
    }
}
