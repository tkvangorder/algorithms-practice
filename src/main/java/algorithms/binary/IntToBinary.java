package algorithms.binary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IntToBinary {
	private static final Logger logger = LoggerFactory.getLogger(IntToBinary.class);


	public static void main(String[] args) {
		computeBinary(16);
		computeBinary(254);
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
}
