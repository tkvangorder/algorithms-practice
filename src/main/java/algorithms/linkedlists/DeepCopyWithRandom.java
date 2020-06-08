package algorithms.linkedlists;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * <PRE>
 * </PRE>
 *
 * @author tyler
 */
public class DeepCopyWithRandom {
	private static final Logger logger = LoggerFactory.getLogger("algorithms");

	public static void main(String[] args) {
		DeepCopyWithRandom solution = new DeepCopyWithRandom();
		
		Node input = createLinkedList(new int[][] {{7, -1}, {13,0}, {11,4},{10,2}, {1,0}} );		
		Node copy = solution.copyRandomList(input);

		
		printList(input);
		printList(copy);
		
	}
	
	public Node copyRandomList(Node head) {

		if (head == null) {
			return null;
		}
		
		HashMap<Node, Node> oldToNew = new HashMap<>();
		
		return copyNode(head, oldToNew);
	}

	private Node copyNode(Node old, Map<Node,Node> oldToNew) {
		
		Node copy = oldToNew.get(old);
		if (copy != null) { 
			return copy;
		}
		
		copy = new Node(old.val);
		oldToNew.put(old, copy);
		if (old.next != null) {
			copy.next = copyNode(old.next, oldToNew);
		}
		if (old.random != null) {
			copy.random = copyNode(old.random, oldToNew);
		}
		return copy;
	}
	
	private static Node createLinkedList(int[][] inputs) {
		//First instantiate all nodes in the list.
		Node[] nodes = new Node[inputs.length];
		for (int index = 0; index < inputs.length; index++) {
			nodes[index] = new Node(inputs[index][0]);
			if (index > 0) {
				nodes[index -1].next = nodes[index];
			}
		}
		//Now link the "random" references.
		for (int index = 0; index < inputs.length; index++) {
			if (inputs[index][1] == -1) {
				continue;
			}
			nodes[index].random = nodes[inputs[index][1]];
		}
		return nodes[0];
	}

	
	private static void printList(Node copy) {
		Node current = copy;
		while (current != null) {
			System.out.print(current.toString());
			current = current.next;
			if(current != null) {
				System.out.print(" -> ");
			}
		}
		System.out.println("");
	}

	/**
	 * This class was defined by the leetcode example, it does not follow best practices, in that there are no "getter"
	 * methods defined and the expectation is to use the protected members directly.
	 */
	public static class Node {
		int val;
		Node next;
		Node random;
		
		Node(int val) {
			this.val = val;
			this.next = null;
			this.random = null;
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("{");
			builder.append("id ='" + super.toString() + "'");
			builder.append(", val='" + val + "'");
			if (random != null) {
				builder.append(", random.val='" + random.val + "'");
			}
			builder.append("}");
			return builder.toString();
		}
		
		
	}

}
