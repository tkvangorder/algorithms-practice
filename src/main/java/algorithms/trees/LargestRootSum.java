package algorithms.trees;

import java.util.List;

public class LargestRootSum {

	public static void main(String arguments[]) {
		
	}
    public static long getLargestRootToLeafSum(Node root) {
        /*
          A root-to-leaf path in a tree is a path from a leaf node through all its ancestors
          to the root node inclusively.
          A "root-to-leaf sum" is a sum of the node values in a root-to-leaf path.

          Please implement this method to
          return the largest root-to-leaf sum in the tree.
         */

    	if (root == null) {
    		return 0;
    	}
    	return getLargestRootToLeafSum(root, 0);
    }    

    
    private static long getLargestRootToLeafSum(Node node, long sumOfAncestors) {
    	
    	if (node.children == null || node.children.size() == 0) {
    		return sumOfAncestors;
    	}
    	long maxSum = 0;
    	for (Node child : node.children) {
    		long sum = getLargestRootToLeafSum(child, sumOfAncestors + child.value);
    		if (sum > maxSum) {
    			maxSum = sum;
    		}
    	}
    	return maxSum;
    }
	
    private static class Node {
    	List<Node> children;
    	int value;
    }
}
