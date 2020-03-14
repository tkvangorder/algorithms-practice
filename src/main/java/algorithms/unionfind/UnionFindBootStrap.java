package algorithms.unionfind;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import common.FileHelper;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Out;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class UnionFindBootStrap {

	private static final Logger logger = LoggerFactory.getLogger("algorithms");

	private static final int numberOfIterations = 5000000;
	private static final int maxRange = 100000;
	public static void main(String[] args) {

		//writeSuperLarge();
		runAllExperiments();
	}

	public static void runAllExperiments() {

		In in = FileHelper.alogDataFile("superLargeUF.txt");
		int n = in.readInt();
		int q[] = new int[n];
		int p[] = new int[n];
		int index = 0;
		while (index < n) {
			p[index] = in.readInt();
			q[index] = in.readInt();
			index++;
		}
		in.close();

		//		logger.info("Starting FastFind");
		//		for (int counter = 0; counter < 100; counter++) {
		//			runExperiment(new FastFind(n), p, q);
		//		}
		//		logger.info("Starting FastUnion");
		//		for (int counter = 0; counter < 100; counter++) {
		//			runExperiment(new FastUnion(n), p, q);
		//		}
		//		logger.info("Starting WeightedUnion");
		//		for (int counter = 0; counter < 100; counter++) {
		//			runExperiment(new WeightedUnion(n), p, q);
		//		}
		//		logger.info("Starting WeithedUnion With Compression");
		//		for (int counter = 0; counter < 100; counter++) {
		//			runExperiment(new WeightedUnionPathCompression(n), p, q);
		//		}
		logger.info("Starting Their WeightedUnion");
		for (int counter = 0; counter < 100; counter++) {
			runWeightedQuickUnion(new WeightedQuickUnionUF(n), p, q);
		}
	}
	public static void runExperiment(UnionFind unionFind, int[] node1List, int[] node2List) {

		int count = node1List.length;
		long startTime = System.currentTimeMillis();
		for (int index = 0; index < count; index++) {
			unionFind.union(node1List[index],node2List[index]);
		}
		double totalTime = System.currentTimeMillis() - startTime;
		logger.info("Union - Total Time : " + totalTime + " - Average Time Per Union : " + (totalTime / count));

		startTime = System.currentTimeMillis();
		for (int index = 0; index < count; index++) {
			unionFind.findComponent(node1List[index]);
		}
		totalTime = System.currentTimeMillis() - startTime;
		logger.info("Find - Total Time : " + totalTime + " - Average Time Per Union : " + (totalTime / count));
	}

	public static void runWeightedQuickUnion(WeightedQuickUnionUF unionFind, int[] node1List, int[] node2List) {

		int count = node1List.length;
		long startTime = System.currentTimeMillis();
		for (int index = 0; index < count; index++) {
			unionFind.union(node1List[index],node2List[index]);
		}
		double totalTime = System.currentTimeMillis() - startTime;
		logger.info("Union - Total Time : " + totalTime + " - Average Time Per Union : " + (totalTime / count));

		startTime = System.currentTimeMillis();
		for (int index = 0; index < count; index++) {
			unionFind.find(node1List[index]);
		}
		totalTime = System.currentTimeMillis() - startTime;
		logger.info("Find - Total Time : " + totalTime + " - Average Time Per Union : " + (totalTime / count));

	}


	public static void writeSuperLarge() {
		Out out = FileHelper.writeAlogDataFile("superLargeUF.txt");
		out.println(numberOfIterations);
		for (int index = 0; index < numberOfIterations; index++) {
			out.print(StdRandom.uniform(maxRange));
			out.print(' ');
			out.print(StdRandom.uniform(maxRange));
			out.println();
		}
		out.close();

	}
}
