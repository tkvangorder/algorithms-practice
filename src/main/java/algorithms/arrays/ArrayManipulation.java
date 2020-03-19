package algorithms.arrays;

import java.io.IOException;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import common.FileHelper;

/**
 * HackerRank Problem.
 *
 * <PRE>
 * Starting with a 1-indexed array of zeros and a list of operations, for each operation add a value to each of the array element between two given indices, inclusive. Once all operations have been performed, return the maximum value in your array.
 *
 * For example, the length of your array of zeros n=10. Your list of queries is as follows:
 *  a b k
 *  1 5 3
 *  4 8 7
 *  6 9 1
 *
 *  Add the values of k between the indices a and b inclusive:
 *
 *  index->	 1 2 3  4  5 6 7 8 9 10
 *          [0,0,0, 0, 0,0,0,0,0, 0]
 *          [3,3,3, 3, 3,0,0,0,0, 0]
 *          [3,3,3,10,10,7,7,7,0, 0]
 *          [3,3,3,10,10,8,8,8,1, 0]
 * </PRE>
 *
 *The largest value is 10 after all operations are performed.
 * @author tyler
 */
public class ArrayManipulation {
	private static final Logger logger = LoggerFactory.getLogger("algorithms");
	private static final Scanner scanner = new Scanner(FileHelper.getInputStreamForClassResource(ArrayManipulation.class, "arrayManipulation.txt"));


	/**
	 * This algorithm is actually actually just recording the difference (at the edge of
	 * each query on the array. So the beginning is just summed with the value and the end +1 has the sum subtracted.
	 * This effectively records the rises and dips and then we iterate once over the array setting
	 * index+1 equal to (the previous value summed with the difference in index+1). Clever solution, not something I came up with
	 *
	 * @param n
	 * @param queries
	 * @return
	 */
	static long arrayManipulation(int n, int[][] queries) {

		//First record all differences (on the edge of each command)
		//O(number of queries)
		long max = Long.MIN_VALUE;
		long[] array = new long[n];
		for (int queryIndex = 0; queryIndex < queries.length; queryIndex++) {
			array[queries[queryIndex][0] -1] += queries[queryIndex][2];
			if (queries[queryIndex][1] < array.length) {
				array[queries[queryIndex][1]] -= queries[queryIndex][2];
			}
		}

		//Then compute the index of each element using the previous value summed with the
		//recorded difference: O(n)

		for (int index = 1; index < array.length; index++) {
			array[index] +=array[index-1];
			if (array[index] > max) {
				max = array[index];
			}
		}

		return max;
	}


	public static void main(String[] args) throws IOException {

		String[] nm = scanner.nextLine().split(" ");

		int n = Integer.parseInt(nm[0]);

		int m = Integer.parseInt(nm[1]);

		int[][] queries = new int[m][3];

		for (int i = 0; i < m; i++) {
			String[] queriesRowItems = scanner.nextLine().split(" ");
			scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

			for (int j = 0; j < 3; j++) {
				int queriesItem = Integer.parseInt(queriesRowItems[j]);
				queries[i][j] = queriesItem;
			}
		}

		long result = arrayManipulation(n, queries);
		logger.info("The max value was : " + result);

		scanner.close();
	}

}
