package chapter1.section2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Interval1D;

public class Exercise2IntervalOverlap {

	private static final Logger logger = LoggerFactory.getLogger("algorithms");

	public static void main(String[] args) {

		In input = new In(Exercise2IntervalOverlap.class, "exercise2input.txt");
		int n = input.readInt();
		Interval1D[] intervals = new Interval1D[n];
		for (int index = 0; index < n; index++) {
			double min = input.readDouble();
			double max = input.readDouble();
			intervals[index] = new Interval1D(min, max);
			for (int index2 = 0; index2 < index; index2++) {
				if (intervals[index].intersects(intervals[index2])) {
					logger.info("Interval [" + intervals[index] + "] overlaps with interval [" + intervals[index2]  + "]");
				}
			}
		}
	}
}