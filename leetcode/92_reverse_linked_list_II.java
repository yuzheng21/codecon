/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode dh = new ListNode(0);
        dh.next = head;
        ListNode p = dh;
        for (int i = 1; i < m; i++)
            p = p.next;

        // reverse between p.next with length n - m + 1
        ListNode q = p.next;
        for (int i = m; i < n; i++) {
            ListNode node = q.next;
            q.next = node.next;
            node.next = p.next;
            p.next = node;
        }

        return dh.next;
    }
}
