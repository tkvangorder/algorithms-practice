package algorithms.arrays;

import java.io.IOException;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import common.FileHelper;

/**
 *
 * <PRE>
 * Given a  2D Array, :
 *
 * 1 1 1 0 0 0
 * 0 1 0 0 0 0
 * 1 1 1 0 0 0
 * 0 0 0 0 0 0
 * 0 0 0 0 0 0
 * 0 0 0 0 0 0
 *
 * We define an hourglass in A to be a subset of values with indices falling in this pattern in arr's graphical representation:
 *
 * a b c
 *   d
 * e f g
 *
 * There are 16 hourglasses in arr, and an hourglass sum is the sum of an hourglass' values. Calculate the hourglass sum for every hourglass in , then print the maximum hourglass sum.
 *
 * For example, given the 2D array:
 *
 * -9 -9 -9  1 1 1
 *  0 -9  0  4 3 2
 * -9 -9 -9  1 2 3
 *  0  0  8  6 6 0
 *  0  0  0 -2 0 0
 *  0  0  1  2 4 0
 *
 *  We calculate the following  hourglass values:
 *
 *  -63, -34, -9, 12, -10, 0, 28, 23, -27, -11, -2, 10, 9, 17, 25, 18
 *
 *  Max == 28
 * </PRE>
 *
 * @author tyler
 */
public class Array2dHourglass {
	private static final Logger logger = LoggerFactory.getLogger("algorithms");


	// Complete the hourglassSum function below.
	static int hourglassSum(int[][] arr) {
		int maxSum = -100;
		for (int rowIndex = 0; rowIndex < (arr.length -2); rowIndex++) {
			for (int columnIndex = 0; columnIndex < (arr[rowIndex].length - 2); columnIndex++) {
				int sum = arr[rowIndex][columnIndex] + arr[rowIndex][columnIndex+1] + arr[rowIndex][columnIndex+2];
				sum += arr[rowIndex+1][columnIndex + 1];
				sum += arr[rowIndex+2][columnIndex] + arr[rowIndex+2][columnIndex+1] + arr[rowIndex+2][columnIndex+2];
				logger.info("Hourglass Sum = " + sum);
				if (sum > maxSum) {
					maxSum = sum;
				}
			}

		}
		return maxSum;
	}

	private static final Scanner scanner = new Scanner(FileHelper.getInputStreamForClassResource(Array2dHourglass.class, "array2dHourglass.txt"));

	public static void main(String[] args) throws IOException {

		int[][] arr = new int[6][6];

		for (int i = 0; i < 6; i++) {
			String[] arrRowItems = scanner.nextLine().split(" ");
			scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

			for (int j = 0; j < 6; j++) {
				int arrItem = Integer.parseInt(arrRowItems[j]);
				arr[i][j] = arrItem;
			}
		}

		int result = hourglassSum(arr);
		logger.info("The max hourglass sum was : " + result);
		scanner.close();
	}
}
