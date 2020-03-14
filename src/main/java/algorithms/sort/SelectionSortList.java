package algorithms.sort;

import java.util.List;

public class SelectionSortList<T extends Comparable<T>> implements ListSorter<T> {

	@Override
	public String getAlgorithm() {
		return "SelectionSort";
	}

	@Override
	public String getTimeComplexityEstimate() {
		return "O((N^2)/2)";
	}

	@Override
	public void sort(List<T> sample) {
		int length = sample.size();
		for (int index = 0; index < length; index++) {
			int minIndex = index;
			for (int index2 = index+1; index2 < length; index2++) {
				if (less(sample.get(index2), sample.get(minIndex))) {
					minIndex = index2;
				}
			}
			exchange(sample, index, minIndex);
		}
	}


}
