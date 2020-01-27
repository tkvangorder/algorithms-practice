package alogirthms.arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.princeton.cs.algs4.In;

/**
 * This exercise is to remove duplicates (in-place) for a sorted array.
 *
 * The unique set of numbers are shuffled to the front of the array and
 * the length of the new array are returned. The remaining elements in
 * the array can be ignored.
 *
 * @author tyler
 *
 */
public class RemoveDuplicatesInPlace {

	private static final Logger logger = LoggerFactory.getLogger("algorithms");

	public static void main(String[] arguments) {
		In input = new In(RemoveDuplicatesInPlace.class, "dupsInPlace.txt");
		int[] array = input.readAllInts();

		int length = removeDuplciates(array);
		StringBuilder builder = new StringBuilder("Array Length is [");
		builder.append(length);
		builder.append("] - ");
		for (int index = 0; index < length; index++) {
			if (index > 0) {
				builder.append(",");
			}
			builder.append(array[index]);
		}
		logger.info(builder.toString());

	}

	private static int removeDuplciates(int[] array) {
		int newIndex = 0;

		for (int index = 1; index < array.length; index++) {
			if (array[index] > array[newIndex]) {
				newIndex++;
				array[newIndex] = array[index];
			}
		}
		return (newIndex + 1);
	}


}
