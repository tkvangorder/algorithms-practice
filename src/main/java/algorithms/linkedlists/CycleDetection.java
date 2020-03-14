package algorithms.linkedlists;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.princeton.cs.algs4.StdRandom;

/**
 * Given a linked list, determine if it has a cycle in it.
 *
 * This class has two implementations, one that keeps track of visitors and one that uses
 * a two-pointer approach.
 *
 * @author tyler
 */
public class CycleDetection {
	private static final Logger logger = LoggerFactory.getLogger("algorithms");

	public static void main(String[] args) {
		CycleDetection solution = new CycleDetection();

		ListNode list = generateCyclicLinkedList(10,3);
		logger.info("Visitors [" + linkedListToString(list) + "] - " + solution.hasCycleUsingVistors(list));
		logger.info("Pointers [" + linkedListToString(list) + "] - " + solution.hasCycleTwoPointers(list));
		list = generateCyclicLinkedList(1,0);
		logger.info("Visitors [" + linkedListToString(list) + "] - " + solution.hasCycleUsingVistors(list));
		logger.info("Pointers [" + linkedListToString(list) + "] - " + solution.hasCycleTwoPointers(list));
		list = generateCyclicLinkedList(5,4);
		logger.info("Visitors [" + linkedListToString(list) + "] - " + solution.hasCycleUsingVistors(list));
		logger.info("Pointers [" + linkedListToString(list) + "] - " + solution.hasCycleTwoPointers(list));
		list = generateCyclicLinkedList(5,0);
		logger.info("Visitors [" + linkedListToString(list) + "] - " + solution.hasCycleUsingVistors(list));
		logger.info("Pointers [" + linkedListToString(list) + "] - " + solution.hasCycleTwoPointers(list));
		list = generateCyclicLinkedList(5,-1);
		logger.info("Visitors [" + linkedListToString(list) + "] - " + solution.hasCycleUsingVistors(list));
		logger.info("Pointers [" + linkedListToString(list) + "] - " + solution.hasCycleTwoPointers(list));
	}

	/**
	 * This method is using a set to keep track of visited nodes.
	 *
	 * @param The head of the linked list.
	 * @return true if the list has a cycle in it.
	 */
	public boolean hasCycleUsingVistors(ListNode head) {
		Set<ListNode> visited = new HashSet<>();

		while (head != null) {
			if (visited.contains(head)) {
				return true;
			}
			visited.add(head);
			head = head.next;
		}
		return false;
	}

	/**
	 * This method is using two pointers, one that advances twice the rate of the other.
	 * In the case of a cycle, at some point the two pointers will equal each other.
	 *
	 * This has a time complexity of O(N/2) but has O(1) space complexity
	 *
	 * @param The head of the linked list.
	 * @return true if the list has a cycle in it.
	 */
	public boolean hasCycleTwoPointers(ListNode head) {

		if (head == null) return false;

		ListNode slow = head;
		ListNode fast = head.next;

		while (fast != null) {
			if (slow == fast) {
				return true;
			}
			slow = slow.next;
			fast = fast.next==null?null:fast.next.next;
		}
		return false;
	}

	/**
	 * Method to generate a linked list of values where the tail is then pointing back to an existing element in the list.
	 *
	 * @param numberOfElements Number of elements to generated in the linked list.
	 * @param cycleElementIndex The index in the list (relative to the head) where the tail will point.
	 * @return A new Linked list sorted by the natural order of the values.
	 */
	private static ListNode generateCyclicLinkedList(int numberOfElements, int cycleElementIndex) {
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
		ListNode cycleElement = null;

		if (cycleElementIndex == 0) {
			cycleElement = node;
		}

		for (int index = 1; index < values.length; index++) {
			node.next = new ListNode(values[index]);
			node = node.next;
			if (cycleElementIndex == index) {
				cycleElement = node;
			}
		}

		node.next = cycleElement;
		return head;
	}


	private static String linkedListToString(ListNode node) {
		Set<ListNode> visitedNodes = new HashSet<>();
		if (node == null) {
			return "null";
		}
		StringBuffer buffer = new StringBuffer("" + node.val);
		visitedNodes.add(node);
		node = node.next;
		while (node != null) {
			if (visitedNodes.contains(node)) {
				buffer.append("-> cycle -> " + node.val);
				break;
			}
			buffer.append("->" + node.val);
			visitedNodes.add(node);
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