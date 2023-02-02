package algorithms.trees.binary;

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
		BinaryTreeNode root = BinaryTreeUtils.listToBinaryTree(50,20,100,null,null,75,150,60,80,125,200,55,70);

		logger.info("------------------------------------------------------------------------------------");
		logger.info("\n" + root);
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
	public boolean isValidBST(BinaryTreeNode root) {
		return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
	}

	public boolean isValidBST(BinaryTreeNode root, Long min, Long max) {
		if (root == null) {
			return true;
		}
		if (root.getValue() <= min || root.getValue() >= max) {
			return false;
		}
		return isValidBST(root.getLeft(), min, (long) root.getValue()) && isValidBST(root.getRight(), (long) root.getValue(), max);
	}
}
