package algorithms.trees;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Given a binary tree, determine if it is a valid binary search tree (BST).
 *
 * Assume a BST is defined as follows:
 *
 * The left subtree of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 *
 * <PRE>
 * Example:
 *
 *     2
 *    / \
 *   1   3
 *
 * Input: [2,1,3]
 * Output: true
 *
 * Example:
 *
 *     5
 *    / \
 *   1   4
 *      / \
 *     3   6
 *
 * Input: [5,1,4,null,null,3,6]
 * Output: false
 * Explanation: The root node's value is 5 but its right child's value is 4.
 * </PRE>
 */
public class ValidateBinaryTree {
	private static final Logger logger = LoggerFactory.getLogger("algorithms");

	public static void main(String[] args) {
		ValidateBinaryTree solution = new ValidateBinaryTree();
		TreeNode root = listToBinaryTree(50,20,100,null,null,75,150,60,80,125,200,55,70);

		logger.info("------------------------------------------------------------------------------------");
		logger.info("\n" + root.toString());
		logger.info("Is Valid (Depth)?   : " + solution.isValidBST(root));
	}

	/**
	 * A properly balanced tree means all nodes on the left of a root node must be less than the root's value and
	 * all nodes on the right of a root node must be greater than the root's value. Additionally, because a value
	 * might be "max" integer, this method uses longs so we do not return invalid results when a node is max/min
	 * integer value.
	 *
	 * @param root A binary tree
	 * @return True if the tree is valid binary search tree.
	 */
	public boolean isValidBST(TreeNode root) {
		return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
	}

	public boolean isValidBST(TreeNode root, Long min, Long max) {
		if (root == null) {
			return true;
		}
		if (root.value <= min || root.value >= max) {
			return false;
		}
		return isValidBST(root.left, min, Long.valueOf(root.value)) && isValidBST(root.right, Long.valueOf(root.value), max);
	}

	/**
	 * This uses a breadth first approach to populating a binary tree from an integer iterator.
	 *
	 *
	 *
	 * @param queue
	 * @param root
	 * @return
	 */
	private static TreeNode listToBinaryTree(Integer...source) {
		Iterator<Integer> index = Arrays.asList(source).iterator();
		TreeNode root = new TreeNode(index.next());

		Queue<TreeNode> queue = new ArrayDeque<>(source.length);
		queue.add(root);
		while (index.hasNext()) {
			TreeNode node = queue.remove();
			if (index.hasNext()) {
				Integer value = index.next();
				if (value != null) {
					node.left = new TreeNode(value);
					queue.add(node.left);
				}
			}
			if (index.hasNext()) {
				Integer value = index.next();
				if (value != null) {
					node.right = new TreeNode(value);
					queue.add(node.right);
				}
			}
		}
		return root;
	}

	public static class TreeNode {

		int value;
		TreeNode left;
		TreeNode right;

		public TreeNode(int value) {
			this.value = value;
		}

		@Override
		public String toString() {
			StringBuilder buffer = new StringBuilder(50);
			print(buffer, "", "");
			return buffer.toString();
		}

		private void print(StringBuilder buffer, String prefix, String childrenPrefix) {
			buffer.append(prefix);
			buffer.append(value);
			buffer.append('\n');
			if (left == null) {
				buffer.append(childrenPrefix + "├── null\n");
			} else {
				left.print(buffer, childrenPrefix + "├── ", childrenPrefix + "│   ");
			}
			if (right == null) {
				buffer.append(childrenPrefix + "├── null\n");
			} else {
				right.print(buffer, childrenPrefix + "├── ", childrenPrefix + "│   ");
			}
		}
	}

}
