package alogirthms;

import edu.princeton.cs.algs4.StdRandom;
import gui.VisualAccumulator;

public class DrawExample {

	public static void main(String[] args) {
		int trials = 1000;
		VisualAccumulator accumulator = new VisualAccumulator(1000, 100);

		for (int index = 0; index < trials; index++) {
			accumulator.addDataValue(StdRandom.uniform(100));
		}
	}

}
