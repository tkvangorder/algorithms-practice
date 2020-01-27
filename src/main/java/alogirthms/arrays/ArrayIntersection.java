package alogirthms.arrays;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Given two arrays, write a function to compute their intersection.
 *
 * <PRE>
 * Example 1:
 *
 * Input: nums1 = [1,2,2,1], nums2 = [2,2]
 * Output: [2,2]
 *
 * Example 2:
 *
 * Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * Output: [4,9]
 *
 * Note:
 *
 * Each element in the result should appear as many times as it shows in both arrays.
 * The result can be in any order.
 *
 * </PRE>
 *
 * This solution firsts sorts the two arrays. O(log N)
 *
 * Then it iterates over both arrays (O(N)) So time complexity is O(N log N)
 *
 * @author tyler
 *
 *
 *
 */
public class ArrayIntersection {

	private static final Logger logger = LoggerFactory.getLogger("algorithms");

	public static void main(String[] args) {
		int[] array1 = new int[] {0,9,1,3,2,4,4,5,6,7,1};
		int[] array2 = new int[] {0,4,1,1,5,9,1};

		int[] intersection = intersect(array1, array2);
		logger.info("Array 1      : " + ArrayUtils.arrayToString(array1));
		logger.info("Array 2      : " + ArrayUtils.arrayToString(array2));
		logger.info("Intersection : " + ArrayUtils.arrayToString(intersection));

	}

	public static int[] intersect(int[] array1, int[] array2) {
		if (array1.length == 0 || array2.length == 0) {
			return new int[0];
		}

		int intersection[] = new int[array1.length];
		Arrays.sort(array1);
		Arrays.sort(array2);

		int index1 = 0;
		int index2 = 0;
		int size = 0;
		while (index1 < array1.length && index2 < array2.length) {
			if (array1[index1] < array2[index2]) {
				index1++;
			} else if (array1[index1] > array2[index2]) {
				index2++;
			} else {
				intersection[size] = array1[index1];
				index1++;
				index2++;
				size++;
			}
		}
		return Arrays.copyOfRange(intersection, 0, size);
	}
}


