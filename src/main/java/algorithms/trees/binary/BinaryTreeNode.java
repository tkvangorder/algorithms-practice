package algorithms.trees.binary;

public class BinaryTreeNode {
    private int value;
    private BinaryTreeNode left;
    private BinaryTreeNode right;

    public BinaryTreeNode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public BinaryTreeNode getLeft() {
        return left;
    }

    public void setLeft(BinaryTreeNode left) {
        this.left = left;
    }

    public BinaryTreeNode getRight() {
        return right;
    }

    public void setRight(BinaryTreeNode right) {
        this.right = right;
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
            buffer.append(childrenPrefix).append("├── null\n");
        } else {
            left.print(buffer, childrenPrefix + "├── ", childrenPrefix + "│   ");
        }
        if (right == null) {
            buffer.append(childrenPrefix).append("├── null\n");
        } else {
            right.print(buffer, childrenPrefix + "├── ", childrenPrefix + "│   ");
        }
    }
}

