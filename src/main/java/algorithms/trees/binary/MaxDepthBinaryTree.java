package algorithms.trees.binary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Given a binary tree, find its maximum depth.
 * <P/>
 * The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
 * <P/>
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
		BinaryTreeNode root = BinaryTreeUtils.listToBinaryTree(1,2,3,null,null,4,5,6,7,8,9,10,11);

		logger.info("------------------------------------------------------------------------------------");
		logger.info("\n" + root);
		logger.info("One-liner Depth : " + solution.maxDepthOneLiner(root));
		logger.info("Depth           : " + solution.maxDepth(root));
		logger.info("Verbose Depth   : " + solution.maxDepthVerbose(root));
	}

	/**
	 * This is a simple one line solution. This will make a recursive call for each null node
	 * and therefore will not be as efficient as it could be.
	 *
	 * @param root Root node
	 * @return maxDepth
	 */
	public int maxDepthOneLiner(BinaryTreeNode root) {
		return root == null ? 0 : java.lang.Math.max(maxDepthOneLiner(root.getLeft()), maxDepthOneLiner(root.getRight())) + 1;

		//		if (root == null) return 0;
		//		return computeMaxDepth(root, 1);
	}

	/**
	 * This is a more efficient solution as it will not recursively call null nodes.
	 *
	 * @param root Root node
	 * @return maxDepth
	 */
	public int maxDepth(BinaryTreeNode root) {
		if (root == null) {
			//Only way this can happen is if the initial call passed in a null.
			return 0;
		}
		int leftDepth = root.getLeft() == null?0:maxDepth(root.getLeft());
		int rightDepth = root.getRight() == null?0:maxDepth(root.getRight());
		return java.lang.Math.max(leftDepth, rightDepth) + 1;
	}

	public int maxDepthVerbose(BinaryTreeNode root) {
		if (root == null) return 0;
		return computeMaxDepth(root, 1);
	}

	/**
	 * A similar, more verbose/explicit implementation where currentDepth is passed as an argument.
	 *
	 * @param node Root node
	 * @param currentDepth current depth of tree
	 * @return maxDepth
	 */
	public int computeMaxDepth(BinaryTreeNode node, int currentDepth) {
		int leftDepth = currentDepth;
		if (node.getLeft() != null) {
			leftDepth = computeMaxDepth(node.getLeft(), currentDepth +1);
		}
		int rightDepth = currentDepth;
		if (node.getRight() != null) {
			rightDepth = computeMaxDepth(node.getRight(), currentDepth +1);
		}
		return Math.max(leftDepth, rightDepth);
	}
}
