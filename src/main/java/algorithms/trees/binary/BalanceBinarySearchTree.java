package algorithms.trees.binary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class BalanceBinarySearchTree {

    private static final Logger logger = LoggerFactory.getLogger("algorithms");

    public static void main(String[] args) {
        BalanceBinarySearchTree solution = new BalanceBinarySearchTree();
        BinaryTreeNode root = BinaryTreeUtils.listToValidBinaryTree(1,2,3,4,5,6,7,8,9,10,11);

        logger.info("--------------------------- Before -------------------------------------------------");
        logger.info("\n" + root);
        logger.info("------------------------------------------------------------------------------------");

        logger.info("--------------------------- After --------------------------------------------------");
        logger.info("\n" + solution.balanceBST(root));
        logger.info("------------------------------------------------------------------------------------");
    }

    public BinaryTreeNode balanceBST(BinaryTreeNode root) {
        List<BinaryTreeNode> inOrderList = new ArrayList<>();
        inOrderTraversal(root, inOrderList);        
        return buildBalancedBST(inOrderList, 0, inOrderList.size() - 1);
    }

    private BinaryTreeNode buildBalancedBST(List<BinaryTreeNode> inOrderList, int low, int high) {
        if (low > high) return null;
        int mid = (low + high) / 2;
        BinaryTreeNode node = inOrderList.get(mid);
        node.setLeft(buildBalancedBST(inOrderList, low, mid - 1));
        node.setRight(buildBalancedBST(inOrderList, mid + 1, high));
        return node;
    }

    public void inOrderTraversal(BinaryTreeNode root, List<BinaryTreeNode> inOrderList) {
        if (root == null) {
            return;
        }

        inOrderTraversal(root.getLeft(), inOrderList);
        inOrderList.add(root);
        inOrderTraversal(root.getRight(), inOrderList);
    }
}
