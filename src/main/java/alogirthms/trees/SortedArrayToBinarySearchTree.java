package alogirthms.trees;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
 *
 * For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of
 * the two subtrees of every node never differ by more than 1.
 *
 * <PRE>
 *
 * Example:
 *
 * Given the sorted array: [-10,-3,0,5,9],
 *
 * One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:
 *
 *       0
 *      / \
 *    -3   9
 *    /   /
 *  -10  5
 * </PRE>
 *
 * @author tyler
 */
public class SortedArrayToBinarySearchTree {
	private static final Logger logger = LoggerFactory.getLogger("algorithms");

	public static void main(String[] args) {
		SortedArrayToBinarySearchTree solution = new SortedArrayToBinarySearchTree();

		int[] input = new int[] {-10,-3,0,5,9,11,14};
		TreeNode root = solution.sortedArrayToBST(input);
		logger.info("input : " + Arrays.toString(input));
		logger.info("\n" + root);

	}

	public TreeNode sortedArrayToBST(int[] nums) {
		if (nums == null) {
			return null;
		}
		return sortedArrayToBST(nums, 0, nums.length -1);
	}
	/**
	 * This method will only make a recursive call if the result of that call will be a child
	 * node. Another, less efficient, way would be to just make the recursive call and return
	 * null if the min/max bounds are invalid.
	 *
	 * @param nums The sorted array of numbers
	 * @param minBound The minimum bound for deriving the binary tree
	 * @param maxBound The maximum bound for deriving the binary tree
	 * @return A balanced binary tree.
	 */
	public TreeNode sortedArrayToBST(int[] nums, int minBound, int maxBound) {
		int mid = (minBound + maxBound) / 2;
		TreeNode node = new TreeNode(nums[mid]);
		if (minBound <= mid -1) {
			node.left = sortedArrayToBST(nums, minBound, mid - 1);
		}
		if (mid + 1 <= maxBound) {
			node.right = sortedArrayToBST(nums, mid + 1, maxBound);
		}
		return node;
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
