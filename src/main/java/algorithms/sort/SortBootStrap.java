package algorithms.sort;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import common.Stopwatch;
import edu.princeton.cs.algs4.StdRandom;

public class SortBootStrap {

	private static final Logger logger = LoggerFactory.getLogger("algorithms");

	private static final int SAMPLE_SIZE = 20_000;
	private static final int SORT_ITERATIONS = 20;

	public static void main(String[] args) {
		//Integer[] sample = randomSmallIntegers(20);
		Integer[] sample = randomIntegers(SAMPLE_SIZE);
		//List<Integer> sampleAsList = Arrays.asList(sample);
		runExperiment(new SelectionSortArray<Integer>(), sample);
		runExperiment(new QuickSortArray<Integer>(), sample);
		//runExperiment(new SelectionSortList<Integer>(), sampleAsList);

	}

	/**
	 * Take an instance of an array sorter and run the sorting algorithm N number of times on the sample.
	 * This experiment will randomly shuffle the values in the array for each iteration rather than generating
	 * a new array each time. This methodology will reduce any garbage collection pressures that may interfere
	 * in the timing of the actual sorting algorithm.
	 *
	 * @param <T>
	 * @param sorter
	 * @param sample
	 */
	private static <T extends Comparable<T>> void runExperiment(ArraySorter<T> sorter, T[] sample) {

		logger.info("-------------------------------------------------------------------------------------");
		logger.info("ArraySorter [" + sorter.getAlgorithm() + "] Sample Size [" + sample.length + "] iterations [" + SORT_ITERATIONS + "]");
		logger.debug("");

		Stopwatch stopwatch = new Stopwatch();
		for (int index=0;index < SORT_ITERATIONS; index++) {
			shuffleArray(sample);
			stopwatch.start();
			sorter.sort(sample);
			long iterationTime = stopwatch.stop();
			logger.debug("Iteration [" + index + "] : Time [" + iterationTime + "ms], Is Sample Sorted? [" + sorter.isSorted(sample) + "]");
		}
		logger.debug("");
		logger.info("ArraySorter [" + sorter.getAlgorithm() + "] - Total Run Time : [" + stopwatch.getTotalElapsedTime() + "ms]");
		logger.info("-------------------------------------------------------------------------------------");
		logger.info("");
	}

	/**
	 * Take an instance of an array sorter and run the sorting algorithm N number of times on the sample.
	 * This experiment will randomly shuffle the values in the array for each iteration rather than generating
	 * a new array each time. This methodology will reduce any garbage collection pressures that may interfere
	 * in the timing of the actual sorting algorithm.
	 *
	 * @param <T>
	 * @param sorter
	 * @param sample
	 */
	private static <T extends Comparable<T>> void runExperiment(ListSorter<T> sorter, List<T> sample) {

		logger.info("-------------------------------------------------------------------------------------");
		logger.info("ListSorter [" + sorter.getAlgorithm() + "] Sample Size [" + sample.size() + "] iterations [" + SORT_ITERATIONS + "]");
		logger.debug("");

		Stopwatch stopwatch = new Stopwatch();
		for (int index=0;index < SORT_ITERATIONS; index++) {
			shuffleList(sample);
			stopwatch.start();
			sorter.sort(sample);
			long iterationTime = stopwatch.stop();
			logger.debug("Iteration [" + index + "] : Time [" + iterationTime + "ms], Is Sample Sorted? [" + sorter.isSorted(sample) + "]");
		}
		logger.debug("");
		logger.info("ListSorter [" + sorter.getAlgorithm() + "] - Total Run Time : [" + stopwatch.getTotalElapsedTime() + "ms]");
		logger.info("-------------------------------------------------------------------------------------");
		logger.info("");
	}

	/**
	 * Returns an array of random integers from 0 to Integer.MAX_VALUE.
	 * @param size The size of the generated array.
	 * @return an array of random integers.
	 */
	private static Integer[] randomIntegers(int size) {
		Integer[] randomIntegers = new Integer[size];
		for (int index=0; index < size; index++) {
			randomIntegers[index] = StdRandom.uniform(Integer.MAX_VALUE);
		}
		return randomIntegers;
	}

	private static Integer[] randomSmallIntegers(int size) {
		Integer[] randomIntegers = new Integer[size];
		for (int index=0; index < size; index++) {
			randomIntegers[index] = StdRandom.uniform(100);
		}
		return randomIntegers;
	}
	
	/**
	 * This method will randomly shuffle all elements of the sample. This method is useful because we do not want
	 * the garbage collector to skew our performance results as we test each algorithm. Rather than allocating a new
	 * array for each sample, we just shuffle the existing array.
	 */
	private static <T> void shuffleArray(T[] sample) {
		for (int index=0; index < sample.length; index++) {
			int randomIndex = StdRandom.uniform(sample.length);
			T temp = sample[index];
			sample[index] = sample[randomIndex];
			sample[randomIndex] = temp;
		}
	}

	/**
	 * This method will randomly shuffle all elements of the sample. This method is useful because we do not want
	 * the garbage collector to skew our performance results as we test each algorithm. Rather than allocating a new
	 * array for each sample, we just shuffle the existing array.
	 */
	private static <T> void shuffleList(List<T>sample) {
		for (int index=0; index < sample.size(); index++) {
			int randomIndex = StdRandom.uniform(sample.size());
			T temp = sample.get(index);
			sample.set(index, sample.get(randomIndex));
			sample.set(randomIndex, temp);
		}
	}


}
