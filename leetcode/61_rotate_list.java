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
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) {
            return head;
        }

        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode p = dummy;

        int len = 0;
        while (p.next != null) {
            p = p.next;
            len++;
        }

        int n = k % len;
        if (n == 0) {
            return head;
        }

        p.next = dummy.next;

        for (int i = 0; i < len - n; i++) {
            p = p.next;
        }

        ListNode newHead = p.next;
        p.next = null;

        return newHead;
    }
}
