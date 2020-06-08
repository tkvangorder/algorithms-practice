package algorithms.sort;

/**
 * This is an implementation of the quick sort algorithm, The premise of this sort is to pick a "pivot" within the array and then find the correct position
 * within the array and partitioning all lesser elements to the left of the pivot and all greater elements to the right. 
 * 
 * The process is then repeated for the subarray to the left and the subarray to the right and then recursively. The pivot value chosen is some what arbitrary
 * and can be the first element, the last, or middle. In this case, we just use the last element.
 *   
 * @author tyler.vangorder
 *
 */
public class QuickSortArray<T extends Comparable<T>> implements ArraySorter<T> {

	@Override
	public String getAlgorithm() {
		return "QuickSort";
	}

	@Override
	public String getTimeComplexityEstimate() {
		return "O(N^2)";
	}

	@Override
	public void sort(T[] array) {
		if (array == null || array.length < 2) {
			return;
		}
		quickSort(array, 0, array.length -1);
	}

	
	private void quickSort(T[] array, int lowBound, int highBound) {
		if (lowBound >= highBound) {
			return;
		}
		int paritionIndex = partition(array, lowBound, highBound);

		quickSort(array, lowBound, paritionIndex - 1);
		quickSort(array, paritionIndex + 1, highBound);
		
	}

	private int partition(T[] array, int lowBound, int highBound) {
		T pivot = array[highBound];
		int lowIndex = lowBound;
		for (int index = lowBound; index < highBound; index++) {
			T current = array[index];
			if (array[index].compareTo(pivot) < 1) {
				exchange(array, lowIndex, index);
				lowIndex++;
			}
		}
		exchange(array, lowIndex, highBound);
		return lowIndex;
	}

}
