/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    // solution 1: recursively
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode p = head.next;
        if (head.val == p.val) {
            while (p != null && head.val == p.val) {
                p = p.next;
            }
            return deleteDuplicates(p);
        } else {
            head.next = deleteDuplicates(p);
            return head;
        }
    }

    // solution 2: iteratively
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode p = dummy;
        ListNode q = head.next;

        while (q != null) {
            if (p.next.val != q.val) {
                p = p.next;
                q = q.next;
            } else {
                while (q != null && p.next.val == q.val) {
                    q = q.next;
                }
                p.next = q;
                if (q != null) {
                    q = q.next;
                }
            }
        }

        return dummy.next;
    }
}

