package algorithms.math;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Exercise1ShortestDistance {

	public static void main(String[] args) {
		int n = 20;

		Point2D[] points = new Point2D[n];
		drawAndCreatePoints(points);

		StdOut.printf("The shortest distance is: %.3f", calculateShortestDistance(points));
	}

	private static void drawAndCreatePoints(Point2D[] points) {
		StdDraw.setCanvasSize(1024, 512);
		StdDraw.setPenRadius(.004);
		StdDraw.setXscale(0, 1);
		StdDraw.setYscale(0, 1);

		for (int i = 0; i < points.length; i++) {
			double pointX = StdRandom.uniform();
			double pointY = StdRandom.uniform();

			Point2D point = new Point2D(pointX, pointY);
			StdDraw.point(point.x(), point.y()); //The exercise doesn't require drawing, but it adds a nice touch

			points[i] = point;
		}
	}

	/**
	 * This relies on the Point2D.distanceTo() method. This method uses Pythagorean Theorem:
	 *
	 * Distance == square root of (deltaX^2 + deltaY^2)
	 *
	 * Time Complexity == O(n^2) because of the two nested loops.
	 *
	 *
	 * @param points
	 * @return
	 */
	private static double calculateShortestDistance(Point2D[] points) {
		if (points.length < 2) {
			return 0.0;
		}
		double shortestDistance = Double.MAX_VALUE;
		double currentDistance;

		Point2D first = null;
		Point2D second = null;
		for (int i = 0; i < points.length - 1; i++) {
			for (int j = i + 1; j < points.length; j++) {
				currentDistance = points[i].distanceTo(points[j]);

				if (currentDistance < shortestDistance) {
					first = points[i];
					second = points[j];
					shortestDistance = currentDistance;
				}
			}
		}
		StdDraw.setPenColor(StdDraw.GREEN);
		StdDraw.line(first.x(), first.y(), second.x(), second.y());
		return shortestDistance;
	}
}