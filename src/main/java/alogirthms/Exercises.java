package alogirthms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Exercises {
	private static final Logger logger = LoggerFactory.getLogger(Exercises.class);


	public static void main(String[] args) {

		relativelyPrimeMatrix(8);
	}



	/**
	 * Compute binary value of number using bit operators.
	 *
	 * @param value
	 */
	public static void computeBinary(int value) {

		String result = "";
		while (value > 0) {
			result = ((value & 1) == 0?"0":"1")+ result;
			value = value >> 1;
		}

		logger.info("" + result);
	}


	/**
	 * Computes an n by n array of booleans, such that n[y][x] = true if y and x are relatively prime.
	 * Relatively prime means the greatest common divisor of the two numbers is 1.
	 * @param n
	 */
	public static void relativelyPrimeMatrix(int n) {
		boolean[][] relativePrime = new boolean[n][n];

		for (int y=0; y < n; y++) {
			StringBuilder row = new StringBuilder(n);
			for (int x=0; x < n; x++) {
				row.append(greatestCommonDivisor(y,x) == 1?"T ": "F ");
			}
			logger.info(row.toString().trim());
		}


	}

	/**
	 * Euclid's algorithm to find the greatest common divisor of two nonnegative integers.
	 */

	public static int greatestCommonDivisor(int a, int b) {
		if (b == 0) return a;
		int r = a % b;
		return greatestCommonDivisor(b, r);
	}
}
