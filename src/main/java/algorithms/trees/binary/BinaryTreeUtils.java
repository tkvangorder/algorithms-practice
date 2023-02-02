package algorithms.trees.binary;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Queue;

public class BinaryTreeUtils {

    /**
     * Given a list of integers, create a binary tree, which may not be valid.
     *
     * @param source list of integers
     * @return The root node of the binary tree
     */
    public static BinaryTreeNode listToBinaryTree(Integer...source) {
        Iterator<Integer> index = Arrays.asList(source).iterator();
        BinaryTreeNode root = new BinaryTreeNode(index.next());

        Queue<BinaryTreeNode> queue = new ArrayDeque<>(source.length);
        queue.add(root);
        while (index.hasNext()) {
            BinaryTreeNode node = queue.remove();
            if (index.hasNext()) {
                Integer value = index.next();
                if (value != null) {
                    node.setLeft(new BinaryTreeNode(value));
                    queue.add(node.getLeft());
                }
            }
            if (index.hasNext()) {
                Integer value = index.next();
                if (value != null) {
                    node.setRight(new BinaryTreeNode(value));
                    queue.add(node.getRight());
                }
            }
        }
        return root;
    }

    /**
     * Given a list of integers, create a valid binary tree.
     *
     * @param source list of integers
     * @return The root node of the valid binary tree
     */
    public static BinaryTreeNode listToValidBinaryTree(Integer...source) {
        Iterator<Integer> index = Arrays.asList(source).iterator();
        BinaryTreeNode root = new BinaryTreeNode(index.next());

        while (index.hasNext()) {
            Integer value = index.next();
            BinaryTreeNode node = root;

            while (true) {
                if (value < node.getValue() && node.getLeft() != null) {
                    node = node.getLeft();
                } else if (value > node.getValue() && node.getRight() != null) {
                    node = node.getRight();
                } else {
                    break;
                }
            }
            if (value < node.getValue()) {
                node.setLeft(new BinaryTreeNode(value));
            } else if (value > node.getValue()) {
                node.setRight(new BinaryTreeNode(value));
            }
        }
        return root;
    }

}
