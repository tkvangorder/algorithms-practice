package alogirthms.sort;

/**
 * An interface that defines the contract required to provide a sort algorithm for an array of
 * Comparable objects
 *
 * @author tyler
 */
public interface ArraySorter<T extends Comparable<T>> {

	/**
	 * Return the name of the sorting algorithm used. This is used by the bootstrap to log sampling results.
	 * @return A simple string that describes the algorithm
	 */
	String getAlgorithm();

	/**
	 * Return the estimated time complexity for the sorting algorithm.
	 * Example: "O(n^2)"
	 *
	 * @return A simple string representing the time complexity.
	 */
	String getTimeComplexityEstimate();

	/**
	 * This method should rearrange the passed in array such that the elements of the list are sequential using
	 * the natural order.
	 *
	 * @param list A list to be sorted.
	 */
	void sort(T[] array);

	/**
	 * A default method that will check if an array is in its natural, sorted order.
	 *
	 * @param array An array of Comparable objects.
	 * @return true if the array is in the natural sorted order.
	 */
	default boolean isSorted(T array[]) {
		for (int index=1; index<array.length;index++) {
			if (less(array[index], array[index-1])) {
				return false;
			}
		}
		return true;
	}

	/**
	 * This method will swap the element at index1 and index2. This method will modify the state
	 * of the array by reference.
	 *
	 * index1 = index2
	 * index2 = index1
	 *
	 *
	 * @param array An array of objects
	 * @param index1 The first index to exchange
	 * @param index2 the second index to exchange.
	 */
	default void exchange(T array[], int index1, int index2) {
		T temp = array[index1];
		array[index1] = array[index2];
		array[index2] = temp;
	}


	/**
	 * Simple method to determine if the first element is less then the second element using the
	 * natural order.
	 *
	 * @param a An object of type T (implementing the Comparable interface).
	 * @param b An object of type T (implementing the Comparable interface).
	 * @return True if the first element (a) is less than the second element (b)
	 */
	default boolean less(T a, T b) {
		return a.compareTo(b) > 0;
	}
}
