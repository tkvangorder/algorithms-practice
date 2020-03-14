package algorithms.arrays;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Given an array, rotate the array to the right by k steps, where k is non-negative.
 * <PRE>
 * Input: [1,2,3,4,5,6,7] and k = 3
 * Output: [5,6,7,1,2,3,4]
 * Explanation:
 * rotate 1 steps to the right: [7,1,2,3,4,5,6]
 * rotate 2 steps to the right: [6,7,1,2,3,4,5]
 * rotate 3 steps to the right: [5,6,7,1,2,3,4]
 * </PRE>
 *
 * This solution copies the last n bytes of the array where n is equal to the actual shift size.
 * It then walks backwards through the array shifting the values.
 * Finally, it copies the buffered values from the end to the beginning of the array.
 *
 * This solution is very quick at the cost of memory where memory == O(n + shift size)
 *
 * @author tyler
 *
 *
 *
 */
public class RotateArrayFast {

	private static final Logger logger = LoggerFactory.getLogger("algorithms");

	public static void main(String[] args) {
		int[] array = new int[] {0,1,2,3,4,5,6,7,8,9};

		rotate(array, 8);

		logger.info("Array : " + ArrayUtils.arrayToString(array));
	}

	public static void rotate(int[] array, int shiftRight) {
		if (array.length == 0) {
			return;
		}

		int actualShift = shiftRight % array.length;
		int buffer[] = Arrays.copyOfRange(array, array.length - actualShift, array.length);
		for (int index = array.length - 1; index >= actualShift; index--) {
			array[index] = array[index-actualShift];
		}
		for (int index = 0; index < actualShift; index++) {
			array[index] = buffer[index];
		}
	}
}


