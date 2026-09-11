/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode partition(ListNode head, int x) {
        ListNode smallHead = new ListNode(0);
        ListNode bigHead = new ListNode(0);
        ListNode p = smallHead;
        ListNode q = bigHead;

        while (head != null) {
            // keep next node
            ListNode tmp = head.next;
            head.next = null;

            if (head.val < x) {
                p.next = head;
                p = p.next;
            } else {
                q.next = head;
                q = q.next;
            }
            
            // move head forward
            head = tmp;
        }
        p.next = bigHead.next;

        return smallHead.next;
    }
}
