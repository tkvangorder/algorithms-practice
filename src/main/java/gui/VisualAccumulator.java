package gui;

import edu.princeton.cs.algs4.StdDraw;

/**
 * Simple accumulator that graphs each point and the mean. The X value is from 0 to the max value, the Y value is the number of trials from 0 to n.
 */
public class VisualAccumulator {

	private double sum;
	private int values = 0;

	public VisualAccumulator(int trials, double max) {
		StdDraw.setXscale(0, trials);
		StdDraw.setYscale(0, max);
		StdDraw.setPenRadius(.005);
	}

	public void addDataValue(double value) {
		values++;
		sum += value;
		StdDraw.setPenColor(StdDraw.DARK_GRAY);
		StdDraw.point(values, value);
		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.point(values, mean());
	}

	double mean() {
		return sum / values;
	}
}
