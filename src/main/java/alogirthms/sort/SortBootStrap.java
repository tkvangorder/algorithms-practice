package alogirthms.sort;

import java.util.Arrays;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

public class SortBootStrap {

	private static final int SAMPLE_SIZE = 20_000_000;
	private static final int SORT_ITERATIONS = 20;

	public static void main(String[] args) {

		int[] sample = randomIntegers(SAMPLE_SIZE);

		Stopwatch stopWatch = new Stopwatch();
		for

	}


	/**
	 * Returns an array of random integers from 0 to Integer.MAX_VALUE.
	 * @param size The size of the generated array.
	 * @return an array of random integers.
	 */
	private static int[] randomIntegers(int size) {
		int[] randomIntegers = new int[size];
		for (int index=0; index < size; index++) {
			randomIntegers[index] = StdRandom.uniform(Integer.MAX_VALUE);
		}
		return randomIntegers;
	}

	/**
	 * This method will randomly shuffle all elements of the sample. This method is useful because we do not want
	 * the garbage collector to skew our performance results as we test each algorithm. Rather than allocating a new
	 * array for each sample, we just shuffle the existing array.
	 */
	private <T> void shuffleArray(T[] sample) {
		for (int index=0; index < sample.length; index++) {
			int randomIndex = StdRandom.uniform(sample.length);
			T temp = sample[index];
			sample[index] = sample[randomIndex];
			sample[randomIndex] = temp;
		}
	}


}
