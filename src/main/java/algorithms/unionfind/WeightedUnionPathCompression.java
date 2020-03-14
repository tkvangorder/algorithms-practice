package algorithms.unionfind;

/**
 * This implements the union/find contract with a weighted union and adds path compression.
 * This implementation uses a node to parent map (similar to FastUnion) but it will also keep track of the size of each root's tree.
 * Additionally, the find method will "compress" the path of all elements of a component set to point directly to the root rather than
 * chaining the references together. This means that as find is accessed more often, the performance of this implementation will approach
 * O(1) time complexity.
 *
 * The nodeToParent[] used the node ID as an index into the array and the value points to its parent node. If the
 * parent node is equal to the node ID, it is considered the root/component.
 *
 * @author tyler
 *
 */
public class WeightedUnionPathCompression implements UnionFind {

	private int nodeToParent[];
	private int referenceCount[];

	private int count;

	public WeightedUnionPathCompression(int nodeSize) {
		nodeToParent = new int[nodeSize];
		referenceCount = new int[nodeSize];
		for (int index = 0; index < nodeSize; index++) {
			nodeToParent[index] = index;
			referenceCount[index] = 1;
		}
		count = nodeSize;
	}

	@Override
	public void union(int node1, int node2) {

		int root1 = findComponent(node1);
		int root2 = findComponent(node2);

		if (root1 == root2) {
			return;
		}
		//This logic insures the smaller tree is always placed under the larger tree.
		if (referenceCount[root1] < referenceCount[root2]) {
			nodeToParent[node1] = node2;
			referenceCount[root2] += referenceCount[root1];
		} else {
			nodeToParent[node2] = node1;
			referenceCount[root1] += referenceCount[root2];
		}
		count--;
	}

	@Override
	public int findComponent(int node) {
		if (node == nodeToParent[node]) {
			return node;
		}
		nodeToParent[node] = findComponent(nodeToParent[node]);
		return nodeToParent[node];
	}

	@Override
	public boolean connected(int node1, int node2) {
		return findComponent(node1) == findComponent(node2);
	}

	@Override
	public int count() {
		return count;
	}

}
