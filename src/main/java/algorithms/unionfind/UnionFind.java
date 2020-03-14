package algorithms.unionfind;

/**
 * This is an interface for the union/find algorithm which can be used to solve dynamic connectivity problems.
 * @author tyler
 *
 */
public interface UnionFind {

	void union(int node1, int node2);
	int findComponent(int node);
	boolean connected(int node1, int node2);
	int count();
}
