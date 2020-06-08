package algorithms.math;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BalancedCoin {

	private static final Logger logger = LoggerFactory.getLogger("algorithms");

	static Random random = new Random();
	public static void main(String[] args) {
		int iterations = 2000000;
		double trueCount = 0;

		for (int index = 0; index < iterations; index++) {
			if (flipCoin()) {
				trueCount++;
			}
		}

		logger.info("True Percentage = " + (trueCount / iterations));
	}

	/**
	 * The flip coin method should result in a 50/50 "fair" coin flip using the biased method only.
	 * @return
	 */
	private static boolean flipCoin() {

		//The trick here is that the odds of a 1 and 0 are the same as the odds of a 0 and a 1:
		//(.3) * (.7)  = .21 and (.7) * (.3) = .21, the odds of a 1 1  or 0 0 are 1 - .21 -. 21

		boolean value1 = biasedFlip();
		boolean value2 = biasedFlip();


		if (value1 && !value2) {
			//Will reach here with a (.3) *.(.7) = .21 probability
			return true;
		} else if (!value1 && value2){
			//Will reach here with a (.7) *.(.3) = .21 probability
			return false;
		} else {
			//keep flipping until we get one of the two above outcomes.
			return flipCoin();
		}
	}

	/**
	 * A function that returns true 30% of the time.
	 * @return
	 */
	private static boolean biasedFlip() {
		return (random.nextDouble()<.301D)?true:false;
	}
}
