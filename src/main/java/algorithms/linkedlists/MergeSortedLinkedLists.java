package algorithms.linkedlists;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.princeton.cs.algs4.StdRandom;

/**
 * Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.
 *
 * <PRE>
 * Example:
 *
 * Input: 1->2->4, 1->3->4
 * Output: 1->1->2->3->4->4
 *
 * </PRE>
 *
 * @author tyler
 */
public class MergeSortedLinkedLists {
	private static final Logger logger = LoggerFactory.getLogger("algorithms");

	public static void main(String[] args) {
		MergeSortedLinkedLists solution = new MergeSortedLinkedLists();

		ListNode resultingList = solution.mergeTwoLists(null, null);
		resultingList = solution.mergeTwoLists(null, convertToSortedLinkedList(1));
		resultingList = solution.mergeTwoLists(convertToSortedLinkedList(1), null);

		ListNode list1 = generateSortedLinkedList(5);
		ListNode list2 = generateSortedLinkedList(5);

		String originalList1 = linkedListToString(list1);
		String originalList2 = linkedListToString(list2);
		resultingList = solution.mergeTwoLists(list1, list2);

		logger.info("List 1: [" + originalList1 + "] merged with List 2: [" + originalList2
				+ "] == [" + linkedListToString(resultingList) + "].");

		list1 = convertToSortedLinkedList(0,0,1,2,3,4,5);
		list2 = convertToSortedLinkedList(0,1,2,3,4,5,5,5);
		originalList1 = linkedListToString(list1);
		originalList2 = linkedListToString(list2);
		resultingList = solution.mergeTwoLists(list1, list2);

		logger.info("List 1: [" + originalList1 + "] merged with List 2: [" + originalList2
				+ "] == [" + linkedListToString(resultingList) + "].");

	}

	/**
	 * This merge is destructive to the original lists passed in! It does adjusts pointer of the existing lists
	 * to keep the space complexity at O(1).
	 *
	 * @param l1
	 * @param l2
	 * @return
	 */
	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

		if (l1 == null) {
			return l2;
		} else if (l2 == null) {
			return l1;
		}
		ListNode head = null;
		ListNode next = null;

		//We only use this logic we when still have elements in both
		//lists.
		while (l1 != null && l2 != null) {
			//Find the next smallest value between the next element of each
			//list.
			if (l1.val < l2.val) {
				if (next != null) {
					//If there was a previous "next", we set it's next value to our new min.
					next.next = l1;
				}
				next = l1;
				l1 = l1.next;
			} else {
				if (next != null) {
					//If there was a previous "next", we set it's next value to our new min.
					next.next = l2;
				}
				next = l2;
				l2 = l2.next;
			}
			if (head == null) {
				head = next;
			}
		}
		//After exiting our loop, one of our list is exhausted, we can just append the remaining
		//elements of the remaining list
		if (l1 != null) {
			next.next = l1;
		} else if (l2 != null) {
			next.next = l2;
		}

		return head;
	}

	/**
	 * Method to generate our linked list.
	 *
	 * @param numberOfElements Number of elements to generated in the linked list.
	 * @return A new Linked list sorted by the natural order of the values.
	 */
	private static ListNode generateSortedLinkedList(int numberOfElements) {
		if (numberOfElements == 0) {
			return null;
		}

		int[] values = new int[numberOfElements];
		for (int index = 0; index < numberOfElements; index++) {
			//generate values between 10 and 99 (this is just for printing...the values are ALWAYS 2 digits)
			values[index] = StdRandom.uniform(10, 99);
		}
		//And sort them!
		Arrays.sort(values);

		//Now convert to a linked list.
		ListNode head = new ListNode(values[0]);
		ListNode node = head;
		for (int index = 1; index < values.length; index++) {
			node.next = new ListNode(values[index]);
			node = node.next;
		}
		return head;
	}

	/**
	 * Method to generate our linked list.
	 *
	 * @param numberOfElements Number of elements to generated in the linked list.
	 * @return A new Linked list sorted by the natural order of the values.
	 */
	private static ListNode convertToSortedLinkedList(int... values) {

		//Sort the values first.
		Arrays.sort(values);

		//Now convert to a linked list.
		ListNode head = new ListNode(values[0]);
		ListNode node = head;
		for (int index = 1; index < values.length; index++) {
			node.next = new ListNode(values[index]);
			node = node.next;
		}
		return head;
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