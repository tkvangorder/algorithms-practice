package algorithms.arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.princeton.cs.algs4.In;

/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * Design an algorithm to find the maximum profit. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times).
 *
 * Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).
 *
 * <PRE>
 * Input: [7,1,5,3,6,4]
 * Output: 7
 *
 * Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
 *             Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
 * </PRE>
 * @author tyler
 *
 */
public class MaxStockProfit {

	private static final Logger logger = LoggerFactory.getLogger("algorithms");

	public static void main(String[] args) {
		In input = new In(RemoveDuplicatesInPlace.class, "maxStockProfit.txt");
		int[] array = input.readAllInts();

		int profit = maxProfit(array);

		logger.info("Array : " + ArrayUtils.arrayToString(array));
		logger.info("Max Profit = " + profit);
	}

	public static int maxProfit(int[] prices) {
		if (prices.length == 0) {
			return 0;
		}

		int maxProfit = 0;
		for (int index = 1; index < prices.length; index++) {
			if (prices[index] > prices[index-1]) {
				maxProfit += prices[index] - prices[index-1];
			}
		}
		return maxProfit;
	}
}


