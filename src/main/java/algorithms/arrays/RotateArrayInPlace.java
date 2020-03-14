package algorithms.arrays;

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
 * This uses a clever trick of reversing the array,
 * Then reversing the tail of the array starting at the shift value.
 * And finally it reverses the head of the array stopping at the shift value.
 *
 * <PRE>
 * Example: [1,2,3,4,5,6] shift right 3:
 *
 * Reverse the entire array: [6,5,4,3,2,1]
 * Reverse the "tail"      : [6,5,4,1,2,3]
 * Reverse the "head"      : [4,5,6,1,2,3]
 * </PRE>
 *
 * @author tyler
 */
public class RotateArrayInPlace {

	private static final Logger logger = LoggerFactory.getLogger("algorithms");

	public static void main(String[] args) {
		int[] array = new int[] {1,2,3,4,5,6,7};

		rotate(array, 3);

		logger.info("Array : " + ArrayUtils.arrayToString(array));
	}

	public static void rotate(int[] array, int shiftRight) {
		if (array.length == 0 || shiftRight == 0) {
			return;
		}

		shiftRight = shiftRight % array.length;

		reverse(array, 0, array.length - 1);
		reverse(array, shiftRight, array.length -1);
		reverse(array, 0, shiftRight - 1);
	}

	public static void reverse(int[] array, int start, int end) {
		while (start < end) {
			int temp = array[start];
			array[start] = array[end];
			array[end] = temp;
			start++;
			end--;
		}
	}
}


