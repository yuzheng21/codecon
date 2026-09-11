/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
// solution 1, clean version
public class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode p = head;
        while (p != null) {
            ListNode q = p.next;
            while (q != null && p.val == q.val) {
                q = q.next;
            }
            p.next = q;
            p = q;
        }
        return head;
    }
}

// // solution 2
// public class Solution {
//     public ListNode deleteDuplicates(ListNode head) {
//         if (head == null || head.next == null) return head;
// 
//         ListNode p = head;
//         ListNode q = head.next;
//         while (q != null) {
//             if (p.val != q.val) {
//                 p.next = q;
//                 p = q;
//             }
//             q = q.next;
//         }
//         // handle the tail
//         p.next = null;
// 
//         return head;
//     }
// }
