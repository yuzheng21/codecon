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
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }

    // iterative
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode dh = new ListNode(0);
        dh.next = head;
        while (head.next != null) {
            ListNode cur = head.next;
            head.next = cur.next;
            cur.next = dh.next;
            dh.next = cur;
        }
        return dh.next;
    }

    // recursive
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode node = reverseList(head.next);
        // head.next is tail now
        head.next.next = head;
        head.next = null;
        return node;
    }

    // recursive
    public ListNode reverseList(ListNode head) {
        if (head == null)
            return null;
        ListNode next = head.next;
        head.next = null;
        return reverse(head, next);
    }

    private ListNode reverse(ListNode head, ListNode next) {
        if (next == null)
            return head;
        ListNode tmp = next.next;
        next.next = head;
        return reverse(next, tmp);
    }
}
