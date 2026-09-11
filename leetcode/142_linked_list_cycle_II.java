/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
	public ListNode detectCycle(ListNode head) {
		ListNode s = head;
		ListNode f = head;
		do {
			if (f == null || f.next == null) return null;
			s = s.next;
			f = f.next.next;
		} while (s != f);

		s = head;
		while (s != f) {
			s = s.next;
			f = f.next;
		}

		return s;
	}

}
