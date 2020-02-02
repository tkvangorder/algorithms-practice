package alogirthms.arrays;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 *
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 *
 * <PRE>
 * Given nums = [2, 7, 11, 15], target = 9,
 *
 * Because nums[0] + nums[1] = 2 + 7 = 9,
 * return [0, 1].
 * </PRE>
 *
 * @author tyler
 */
public class TwoSum {

	private static final Logger logger = LoggerFactory.getLogger("algorithms");

	public static void main(String[] args) {
		//		int[] array = new int[] {0,9,1,3,2,4,4,5,6,7,1};
		//		int target = 16;
		int[] array = new int[] {3,1,1,1,1,1,1,1,3};
		int target = 6;

		int[] twoSumIndexes = twoSum(array, target);

		logger.info("Array      : " + ArrayUtils.arrayToString(array));

		logger.info("array[" + twoSumIndexes[0] + "] = " + array[twoSumIndexes[0]]);
		logger.info("array[" + twoSumIndexes[1] + "] = " + array[twoSumIndexes[1]]);
		logger.info("------------------------------------------------------");
		logger.info("Sum     " + target);
	}

	public static int[] twoSum(int[] nums, int target) {

		//Map each value to its index in original array, we use a hashmap
		Map<Integer,Integer> valueToIndex = new HashMap<>();
		for (int index = 0; index < nums.length; index++) {
			int compliment = target - nums[index];
			if (valueToIndex.containsKey(compliment)) {
				return new int[] {valueToIndex.get(compliment), index};
			}
			valueToIndex.put(nums[index], index);
		}
		throw new IllegalArgumentException("No solution");
	}
}


