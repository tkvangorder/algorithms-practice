package common;

/**
 * A stop watch that can be started/stopped. This will keep track of a total elapsed time.
 * This class is not thread-safe.
 */
public class StopWatch {

	long totalElapsedTime = 0;
	long startTime = 0;
	boolean running = false;
	public void start() {
		if (running) return;
		running = true;
		startTime = System.currentTimeMillis();
	}

	public void stop() {
		if (!running) return;
		running = false;
		totalElapsedTime += (System.currentTimeMillis() - startTime);
	}

	public long getTotalElapsedTime() {
		if (running) {
			stop();
		}
		return totalElapsedTime;
	}
}
