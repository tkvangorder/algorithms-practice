package chapter1.section3;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import common.Assert;
import edu.princeton.cs.algs4.StdRandom;

/**
 * This class manages a collection of counters and keeps track of a min/max value across all of the managed counters.
 *
 * This implementation keeps track of the counter values themselves via an array of ints and then use a reference counting system for the values.
 * This means that while the counters values are incremented at O(1), we still use a sorted map to order the values -> numnber of references.
 * This means that adjustCounter is O(log n) as is the getMin/getMax calls.
 */
public class MinMaxCounterManager {

	private static final Logger logger = LoggerFactory.getLogger("algorithms");


	public static void main(String[] args) {
		int numberOfCounters = 200;

		MinMaxCounterManager counterManager = new MinMaxCounterManager(numberOfCounters);

		for (int index = 0; index < 100000; index++) {
			counterManager.adjustCounterAmount(StdRandom.uniform(numberOfCounters), StdRandom.uniform(-10, 10));
		}
		logger.info("The tracked minimum was [" + counterManager.minValue() + "]");
		logger.info("The tracked maximum was [" + counterManager.maxValue() + "]");

		counterManager.dumpReferenceCounts();
	}

	private final SortedMap<Integer, Integer> valueReferenceMap;
	private final int[] counters;

	public MinMaxCounterManager(int numberOfCounters) {
		valueReferenceMap = new TreeMap<>();
		counters = new int[numberOfCounters];
		valueReferenceMap.put(0, numberOfCounters);
	}

	public int adjustCounterAmount(int counterNumber, int amount) {
		Assert.isTrue(counterNumber > -1 && counterNumber < counters.length,
				"The counter number must be from 0 to " + counters.length);

		if (amount == 0) {
			return counters[counterNumber];
		}

		int previousValue = counters[counterNumber];
		counters[counterNumber] = previousValue + amount;
		decrementValueReferenceCount(previousValue);
		incrementValueReferenceCount(counters[counterNumber]);
		return counters[counterNumber];
	}

	private void decrementValueReferenceCount(int value) {
		Integer referenceCount = valueReferenceMap.get(value);
		if (referenceCount == null || referenceCount < 1) {
			throw new IllegalStateException("The reference count for value [" + value + "] is in an invalid state.");
		}

		if (referenceCount.intValue() == 1) {
			valueReferenceMap.remove(value);
		} else {
			valueReferenceMap.put(value, referenceCount.intValue() - 1);
		}
	}

	private void incrementValueReferenceCount(int value) {
		int referenceCount = valueReferenceMap.getOrDefault(value, 0);
		valueReferenceMap.put(value, referenceCount + 1);
	}

	public int maxValue() {
		return valueReferenceMap.lastKey();
	}
	public int minValue() {
		return valueReferenceMap.firstKey();
	}

	public void dumpCounters() {
		for(int index = 0; index < counters.length; index++) {
			logger.info("Counter [" + index + "] value [" + counters[index] + "]");
		}
	}
	public void dumpReferenceCounts() {
		for(Map.Entry<Integer, Integer> entry : valueReferenceMap.entrySet()) {
			logger.info("Value [" + entry.getKey() + "] reference count [" + entry.getValue() + "]");
		}
	}

}
