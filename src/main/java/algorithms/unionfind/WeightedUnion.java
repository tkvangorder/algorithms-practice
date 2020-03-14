package algorithms.unionfind;

/**
 * This implements the union/find contract with a weighted union. This implementation uses a node to parent map (similar to FastUnion)
 * but it will also keep track of the size of each root's tree. It will always place the smaller tree under the larger tree to keep the
 * forest "balanced". This results in both the find and the union having an O(log N) complexity.
 *
 * The nodeToParent[] used the node ID as an index into the array and the value points to its parent node. If the
 * parent node is equal to the node ID, it is considered the root/component.
 *
 * @author tyler
 *
 */
public class WeightedUnion implements UnionFind {

	private int nodeToParent[];
	private int referenceCount[];

	private int count;

	public WeightedUnion(int nodeSize) {
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
			referenceCount[root2]++;
		} else {
			nodeToParent[node2] = node1;
			referenceCount[root1]++;
		}
		count--;
	}

	@Override
	public int findComponent(int node) {
		int index = node;
		while (node != nodeToParent[index]) {
			node = nodeToParent[index];
		}
		return node;
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
