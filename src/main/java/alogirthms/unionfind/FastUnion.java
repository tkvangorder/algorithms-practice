package alogirthms.unionfind;

/**
 * This implements the union/find contract with a quick union. This implementation *may* have better performance during
 * the union, but is highly dependent on the inputs. The best case may be O(1) the worse case scenario may be O(2N).
 * Similarly the find operations will also be O(2N)
 *
 *
 * The nodeToParent[] used the node ID as an index into the array and the value points to its parent node. If the
 * parent node is equal to the node ID, it is considered the root/component.
 *
 * @author tyler
 *
 */
public class FastUnion implements UnionFind {

	private int nodeToParent[];
	private int count;

	public FastUnion(int nodeSize) {
		nodeToParent = new int[nodeSize];
		for (int index = 0; index < nodeSize; index++) {
			nodeToParent[index] = index;
		}
		count = nodeSize;
	}
	@Override
	public void union(int node1, int node2) {
		int component1 = findComponent(node1);
		int component2 = findComponent(node2);

		if (component1 == component2) {
			return;
		}
		nodeToParent[node1] = node2;
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
