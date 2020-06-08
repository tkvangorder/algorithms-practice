package algorithms.intervals;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import common.Interval2dExtended;
import edu.princeton.cs.algs4.Interval1D;
import edu.princeton.cs.algs4.Interval2D;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

public class Exercise3Interval2DOverlap {

	private static final Logger logger = LoggerFactory.getLogger("algorithms");

	public static void main(String[] args) {
		int numberOfIntervals = 10;
		double min = 3.5;
		double max = 9.9;

		//Set drawing dimensions
		StdDraw.setCanvasSize(1024, 512);
		StdDraw.setPenRadius(.005);
		StdDraw.setXscale(min, max);
		StdDraw.setYscale(min, max);

		Interval2dExtended[] intervals = new Interval2dExtended[numberOfIntervals];

		Set<Interval2D> containedIntervals = new HashSet<>();
		for (int index = 0; index < intervals.length; index++) {
			StdDraw.setPenColor();
			intervals[index] = new Interval2dExtended(
					getRandomInterval(min,max),
					getRandomInterval(min,max)
					);
			StdDraw.setPenColor(StdDraw.BLACK);
			intervals[index].draw();

			for (int index2 = 0; index2 < index; index2++) {
				if (intervals[index].intersects(intervals[index2])) {

					if (intervals[index].contains(intervals[index2])) {
						StdDraw.setPenColor(StdDraw.ORANGE);
						intervals[index2].draw();
						containedIntervals.add(intervals[index2]);
					} else if (intervals[index2].contains(intervals[index])) {
						StdDraw.setPenColor(StdDraw.ORANGE);
						intervals[index].draw();
						containedIntervals.add(intervals[index]);
					} else {
						StdDraw.setPenColor(StdDraw.GREEN);
						if (!containedIntervals.contains(intervals[index])) {
							intervals[index].draw();
						}
						if (!containedIntervals.contains(intervals[index2])) {
							intervals[index2].draw();
						}
					}
				}
			}
		}
	}

	private static Interval1D getRandomInterval(double min, double max) {
		double intervalMin = StdRandom.uniform(min,max);
		return new Interval1D(intervalMin, StdRandom.uniform(intervalMin,max));
	}
}