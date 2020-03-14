package algorithms.linkedlists;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Reverse a singly linked list.
 *
 * <PRE>
 * Example:
 *
 * Input: 1->2->3->4->5->NULL
 * Output: 5->4->3->2->1->NULL
 *
 * There are two implementations of this algorithm :
 *
 * A linked list can be reversed either iteratively or recursively.
 *
 * The iterative approach has a time complexity of O(N) and a space complexity of O(1) because it flips pointers in-place.
 * The recursive approach has a time complexity of O(N) and a space complecity of O(n) because of the memory allocated on the stack frames.
 *
 * </PRE>
 *
 * @author tyler
 */
public class ReverseLinkedList {
	private static final Logger logger = LoggerFactory.getLogger("algorithms");

	public static void main(String[] args) {
		ReverseLinkedList solution = new ReverseLinkedList();
		ListNode original = generateLinkedList(5);
		logger.info("Original : " + linkedListToString(original));
		ListNode reverse = solution.reverseList(original);
		logger.info("reverse using iteration : " + linkedListToString(reverse));

		original = generateLinkedList(5);
		reverse = solution.reverseListRecusive(original);
		logger.info("reverse using recursion : " + linkedListToString(reverse));

	}

	public ListNode reverseList(ListNode head) {

		ListNode reverseNode = null;
		ListNode index = head;
		while (index != null) {
			ListNode next = index.next;
			index.next = reverseNode;
			reverseNode = index;
			index = next;
		}

		return reverseNode;

	}
	public ListNode reverseListRecusive(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		//We recurse all the way to the end of the list and (ultimately)
		//we will always return the last element.
		ListNode nestedReverse = reverseListRecusive(head.next);
		//We now need to "fix" the list as we recurse back, by flipping the order.
		head.next.next = head;
		//It is safe to set the next to null (as this is what we want on the
		//last element) and this will be overwritten with the correct element
		//as we walk back up the call stack.
		head.next = null;
		return nestedReverse;
	}

	/**
	 * Method to generate our linked list.
	 *
	 * @param numberOfElements Number of elements to generated in the linked list.
	 * @return A new Linked list.
	 */
	private static ListNode generateLinkedList(int numberOfElements) {
		ListNode node = null;
		for (int index = numberOfElements; index > 0; index--) {
			ListNode newNode = new ListNode(index);
			newNode.next = node;
			node = newNode;
		}
		return node;
	}

	private static String linkedListToString(ListNode node) {
		if (node == null) {
			return "null";
		}
		StringBuffer buffer = new StringBuffer("" + node.val);
		node = node.next;
		while (node != null) {
			buffer.append("->" + node.val);
			node = node.next;
		}
		return buffer.toString();
	}

	/**
	 * This class was defined by the leetcode example, it does not follow best practices, in that there are no "getter"
	 * methods defined and the expectation is to use the protected members directly.
	 */
	public static class ListNode {
		int val;
		ListNode next;
		ListNode(int x) {
			val = x;
		}
	}

}
