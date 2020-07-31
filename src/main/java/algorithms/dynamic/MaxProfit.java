package algorithms.dynamic;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * 
 * If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock), design an algorithm to find the maximum profit.
 * <PRE>
 *
 * Note that you cannot sell a stock before you buy one.
 * Example 1:
 * 
 * Input: [7,1,5,3,6,4]
 * Output: 5
 * Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
 *   Not 7-1 = 6, as selling price needs to be larger than buying price.
 *   
 *   Example 2:
 *   
 *  Input: [7,6,4,3,1]
 *  Output: 0
 *  
 *  Explanation: In this case, no transaction is done, i.e. max profit = 0.
 * </PRE>
 *
 * @author tyler
 */
public class MaxProfit {
	private static final Logger logger = LoggerFactory.getLogger("algorithms");

	public static void main(String[] args) {
		MaxProfit solution = new MaxProfit();
		int[] input = new int[] {7,1,5,3,6,4};
		logger.info("Max Profit : " +  solution.maxProfit(input) + " - " + Arrays.toString(input));
		input = new int[] {7,6,4,3,1};
		logger.info("Max Profit : " +  solution.maxProfit(input) + " - " + Arrays.toString(input));
	}

    public int maxProfit(int[] prices) {

    	int minPrice = Integer.MAX_VALUE;
    	int maxProfit = 0;
    	if (prices != null) {
	    	for (int index = 0; index < prices.length; index++) {
	    		if (prices[index] < minPrice) {
	    			minPrice  = prices[index];
	    		} else if (prices [index] - minPrice > maxProfit) {
	    			maxProfit = prices [index] - minPrice;
	    		}
	    	}
    	}
    	return maxProfit;
    }
}
