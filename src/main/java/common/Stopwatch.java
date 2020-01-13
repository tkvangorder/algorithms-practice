package common;

/**
 * A stop watch that can be started/stopped. This will keep track of a total elapsed time.
 * This class is not thread-safe.
 */
public class Stopwatch {

	long totalElapsedTime = 0;
	long startTime = 0;
	boolean running = false;
	public void start() {
		if (running) return;
		running = true;
		startTime = System.currentTimeMillis();
	}

	public long stop() {
		if (!running) {
			return 0;
		}
		running = false;
		long total = (System.currentTimeMillis() - startTime);
		totalElapsedTime += total;
		return total;
	}

	public long getTotalElapsedTime() {
		if (running) {
			stop();
		}
		return totalElapsedTime;
	}

	public void reset() {
		running = false;
		totalElapsedTime = 0;
	}
}
