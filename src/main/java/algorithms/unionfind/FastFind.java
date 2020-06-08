package algorithms.unionfind;

/**
 * This implements the union/find contract with a quick find O(1), but the union will be O(n)
 *
 * The "nodeToComponents[]" uses the node ID as an index into the array where the value is the component that the node belongs to.
 *
 * The find, is a simple, single indexed lookup. However, the union requires that we iterate over the collection, setting component
 * (assigned to node 2, to all the nodes that have the same value as node 1.
 *
 * @author tyler
 *
 */
public class FastFind implements UnionFind {

	private int nodeToComponents[];
	private int count;

	public FastFind(int nodeSize) {
		nodeToComponents = new int[nodeSize];
		for (int index = 0; index < nodeSize; index++) {
			nodeToComponents[index] = 0;
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

		for (int index=0;index < nodeToComponents.length;index++) {
			//in this implementation, the union requires us to iterate over the entire siteToComponment map.
			//Any node that belongs to the component1 must be reassigned to component2. This is arbitrary and could
			//also be reversed.
			if (nodeToComponents[index] == component1) {
				nodeToComponents[index] = component2;
			}
		}
		count--;
	}

	@Override
	public int findComponent(int node) {
		return nodeToComponents[node];
	}

	@Override
	public boolean connected(int node1, int node2) {
		return nodeToComponents[node1] == nodeToComponents[node2];
	}

	@Override
	public int count() {
		return count;
	}

}
