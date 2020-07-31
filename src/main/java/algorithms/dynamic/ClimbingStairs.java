package algorithms.dynamic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * You are climbing a stair case. It takes n steps to reach to the top.
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 * 
 * <PRE>
 * Example 1:
 * 
 * Input: 2
 * Output: 2
 * 
 * Explanation: There are two ways to climb to the top.
 * 1. 1 step + 1 step
 * 2. 2 steps
 * 
 * Example 2:
 * 
 * Input: 3
 * 
 * Output: 3
 * Explanation: There are three ways to climb to the top.
 * 
 * 1. 1 step + 1 step + 1 step
 * 2. 1 step + 2 steps
 * 3. 2 steps + 1 step
 * 
 * Constraints:
 * 
 * 1 <= n <= 45 
 * 
 * </PRE>
 *
 * @author tyler
 */
public class ClimbingStairs {
	private static final Logger logger = LoggerFactory.getLogger("algorithms");
	
	public static void main(String[] args) {
		ClimbingStairs solution = new ClimbingStairs();
		for (int index=1; index <= 45; index++) {
			logger.info(index + " : " + solution.climbStairs(index));
		}
		
	}

	static int[] stairCombinations = new int[45];
	static {
		stairCombinations[0] = 1;
		stairCombinations[1] = 2;
		//The number of possible combinations for a given stair is the sum of the previous two stairs.
		for (int index = 2; index < 45; index++) {
			stairCombinations[index] = stairCombinations[index-2]  + stairCombinations[index-1];
		}
	}

	public int climbStairs(int n) {
    	return stairCombinations[n-1];
    }
}
