package common;

import edu.princeton.cs.algs4.Interval1D;
import edu.princeton.cs.algs4.Interval2D;
import edu.princeton.cs.algs4.Point2D;

/**
 *
 * @author tyler
 *
 */
public class Interval2dExtended extends Interval2D {
	private final Point2D minVertice;
	private final Point2D maxVertice;

	public Interval2dExtended(Interval1D x, Interval1D y) {
		super(x, y);
		minVertice = new Point2D(x.min(), y.min());
		maxVertice = new Point2D(x.max(), y.max());
	}

	public boolean contains(Interval2dExtended other) {
		return this.contains(other.minVertice) && this.contains(other.maxVertice);
	}

	public Point2D getMinVertice() {
		return minVertice;
	}

	public Point2D getMaxVertice() {
		return maxVertice;
	}

}
