package algorithms.binary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NumberOfBits {
	private static final Logger logger = LoggerFactory.getLogger("algorithms");

	public static void main(String[] args) {
		logger.info("" + getRequiredNumberOfBits(Integer.MAX_VALUE));
	}

	public static int getRequiredNumberOfBits(int N) {

		/*
		 * Please implement this method to return the number of bits which is just
		 * enough to store any integer from 0 to N-1 inclusively for any int N > 0
		 * Example: to store 5 integers from 0 to 4 you need 3 bits: 000, 001, 010, 011,
		 * 100 and 101
		 */
		System.out.println(Integer.toBinaryString(Integer.highestOneBit(N - 1)));
		return Integer.numberOfTrailingZeros(Integer.highestOneBit(N - 1)) + 1;
	}
}
