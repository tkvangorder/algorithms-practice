package alogirthms.trees;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Given a binary tree, find its maximum depth.
 *
 * The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
 *
 * Note: A leaf is a node with no children.
 * <PRE>
 * Example:
 *
 * Given binary tree [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 *  return its depth = 3.
 * </PRE>
 *
 * @author tyler
 */
public class MaxDepthBinaryTree {
	private static final Logger logger = LoggerFactory.getLogger("algorithms");

	public static void main(String[] args) {
		MaxDepthBinaryTree solution = new MaxDepthBinaryTree();
		TreeNode root = listToBinaryTree(1,2,3,null,null,4,5,6,7,8,9,10,11);

		logger.info("------------------------------------------------------------------------------------");
		logger.info("\n" + root.toString());
		logger.info("One-liner Depth : " + solution.maxDepth(root));
		logger.info("Depth           : " + solution.maxDepth(root));
		logger.info("Verbose Depth   : " + solution.maxDepthVerbose(root));
	}

	/**
	 * This is a simple one line solution. This will make a recursive call for each null node
	 * and therefore will not be as efficient as it could be.
	 *
	 * @param root
	 * @return maxDepthl
	 */
	public int maxDepthOneLiner(TreeNode root) {
		return root == null ? 0 : java.lang.Math.max(maxDepthOneLiner(root.left), maxDepthOneLiner(root.right)) + 1;

		//		if (root == null) return 0;
		//		return computeMaxDepth(root, 1);
	}

	/**
	 * This is a more efficient solution as it will not recursively call null nodes.
	 *
	 * @param root
	 * @return maxDepthl
	 */
	public int maxDepth(TreeNode root) {
		if (root == null) {
			//Only way this can happen is if the initial call passed in a null.
			return 0;
		}
		int leftDepth = root.left == null?0:maxDepth(root.left);
		int rightDepth = root.right == null?0:maxDepth(root.right);
		return java.lang.Math.max(leftDepth, rightDepth) + 1;
	}

	public int maxDepthVerbose(TreeNode root) {
		if (root == null) return 0;
		return computeMaxDepth(root, 1);
	}

	/**
	 * A similar, more verbose/explicit implementation where currentDepth is passed as an argument.
	 *
	 * @param node
	 * @param currentDepth
	 * @return
	 */
	public int computeMaxDepth(TreeNode node, int currentDepth) {
		int leftDepth = currentDepth;
		if (node.left != null) {
			leftDepth = computeMaxDepth(node.left, currentDepth +1);
		}
		int rightDepth = currentDepth;
		if (node.right != null) {
			rightDepth = computeMaxDepth(node.right, currentDepth +1);
		}
		return Math.max(leftDepth, rightDepth);
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
