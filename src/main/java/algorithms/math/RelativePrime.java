package algorithms.math;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RelativePrime {
	private static final Logger logger = LoggerFactory.getLogger(RelativePrime.class);


	public static void main(String[] args) {
		relativelyPrimeMatrix(8);
	}

	/**
	 * Computes an n by n array of booleans, such that n[y][x] = true if y and x are relatively prime.
	 * Relatively prime means the greatest common divisor of the two numbers is 1.
	 * @param n
	 */
	public static void relativelyPrimeMatrix(int n) {

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
