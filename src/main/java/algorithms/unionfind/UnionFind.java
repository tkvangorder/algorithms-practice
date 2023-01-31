package algorithms.unionfind;

/**
 * This is an interface for the union/find algorithm which can be used to solve dynamic connectivity problems.
 * @author tyler
 *
 * This interface was extracted from the Sedwick/Wayne Algorithms book. The node integers represent a "site ID" and
 * each site may or may not be connected to each other.
 *
 * The following three rules apply node p is connected to node q:
 *
 * p is connected p (Reflexive)
 * p is connected to q and q is connected to p (symmetic)
 * if p is connected to q and q is connected to r then p is connected to r. (transitive)
 *
 * A component is a collection of connected nodes
 */
public interface UnionFind {

	/**
	 * Union is the connection of node1 and node2 to form a component. This will also connect all transitive
	 * connections into the same, single component.
	 *
	 * @param node1 Node 1 to connect
	 * @param node2 Node 2 to connect
	 */
	void union(int node1, int node2);

	/**
	 * Return the component ID for a given node.
	 *
	 * @param node Node
	 * @return component ID for the node.
	 */
	int findComponent(int node);

	/**
	 * Are node1 and node2 connected? (Are they in the same component?)
	 *
	 * @param node1 Node 1
	 * @param node2 Node 2
	 *
	 * @return true if the nodes are connected (belong to the same component)
	 */
	boolean connected(int node1, int node2);

	/**
	 * Return the number of components that exist in the problem.
	 * @return The number of distinct components.
	 */
	int count();
}
