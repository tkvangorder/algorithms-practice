package common;

/**
 * A stop watch that can be started/stopped. This will keep track of a total elapsed time.
 * This class is not thread-safe.
 */
public class Stopwatch {

	long totalElapsedTime = 0;
	long startTime = 0;
	public void start() {
		startTime = System.currentTimeMillis();
	}

	public long stop() {
		long total = (System.currentTimeMillis() - startTime);
		totalElapsedTime += total;
		return total;
	}

	public long getTotalElapsedTime() {
		return totalElapsedTime;
	}

	public void reset() {
		totalElapsedTime = 0;
	}
}
