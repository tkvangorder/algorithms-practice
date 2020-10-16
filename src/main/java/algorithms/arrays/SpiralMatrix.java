package algorithms.arrays;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import common.FileHelper;
import edu.princeton.cs.algs4.StdRandom;

/**
 *
 * <PRE>
 * </PRE>
 *
 * @author tyler
 */
public class SpiralMatrix {
	private static final Logger logger = LoggerFactory.getLogger("algorithms");
	private static final Scanner scanner = new Scanner(FileHelper.getInputStreamForClassResource(SpiralMatrix.class, "soultion-input.txt"));

	public static void main(String[] args) {
		int[][] input = generateRandomMatrix(5,5);
		printMatrix(input);
		SpiralMatrix matrix = new SpiralMatrix();
		List<Integer> results = matrix.spiralOrder(input);
		
		logger.info("Results : " + results);
	}

	public List<Integer> spiralOrder(int[][] matrix) {
		if (matrix == null) {
			return Collections.emptyList();
		}
		List<Integer> results = new ArrayList<>(matrix.length * matrix[0].length);
		int yLow = 0;
		int yHigh = matrix.length;
		int xLow = 0;
		int xHigh = matrix[0].length; 

		Direction direction = Direction.RIGHT;
		while (yLow != yHigh && xLow != xHigh) {
			switch (direction) {
				case RIGHT:
					for (int x = xLow; x < xHigh; x++) {
						results.add(matrix[yLow][x]);
					}
					direction = Direction.DOWN;
					yLow++;
					break;
				case DOWN:
					for (int y = yLow; y < yHigh; y++) {
						results.add(matrix[y][xHigh-1]);
					}
					direction = Direction.LEFT;
					xHigh--;
					break;
				case LEFT:
					for (int x = (xHigh -1); x >= xLow; x--) {
						results.add(matrix[yHigh-1][x]);
					}
					direction = Direction.UP;
					yHigh--;
					break;
				case UP:
					for (int y = (yHigh -1); y >= yLow; y--) {
						results.add(matrix[y][xLow]);
					}
					direction = Direction.RIGHT;
					xLow++;
					break;
			}
		}
		return results;
	}

	private enum Direction {
		RIGHT,
		DOWN,
		LEFT,
		UP
	}

	//This is more memory efficient because it uses an int for direction rather than an enum.
	public List<Integer> spiralOrderIntDirection(int[][] matrix) {
		if (matrix == null) {
			return Collections.emptyList();
		}
		List<Integer> results = new ArrayList<>(matrix.length * matrix[0].length);
		int yLow = 0;
		int yHigh = matrix.length;
		int xLow = 0;
		int xHigh = matrix[0].length; 

		//Direction 0 == right, 1 == down, 2 == left, 3 == up
		int direction = 0;
		while (yLow != yHigh && xLow != xHigh) {
			switch (direction) {
				case 0:
					//Right
					for (int x = xLow; x < xHigh; x++) {
						results.add(matrix[yLow][x]);
					}
					//Next direction is down.
					direction = 1;
					yLow++;
					break;
				case 1:
					//Down
					for (int y = yLow; y < yHigh; y++) {
						results.add(matrix[y][xHigh-1]);
					}
					//Next direction is left.
					direction = 2;
					xHigh--;
					break;
				case 2:
					//Left
					for (int x = (xHigh -1); x >= xLow; x--) {
						results.add(matrix[yHigh-1][x]);
					}
					//Next direction is up.
					direction = 3;
					yHigh--;
					break;
				case 3:
					//Up
					for (int y = (yHigh -1); y >= yLow; y--) {
						results.add(matrix[y][xLow]);
					}
					//Next direction is right.
					direction = 0;
					xLow++;
					break;
			}
		}
		return results;
	}
	
	private static int[][] generateRandomMatrix(int height, int width) {
		int[][] matrix = new int[height][width];
		for (int y=0; y < height; y++) {
			for (int x= 0; x < width; x++) {
				matrix[y][x] =  StdRandom.uniform(10, 99); 
			}
		}
		return matrix;
	}

	private static void printMatrix(int[][] matrix) {
		
		logger.info("-------------------------------------------------------------------------");
		for (int y = 0; y < matrix.length; y++) {
			StringBuilder rowString = new StringBuilder();
			for (int x =0; x < matrix[y].length; x++) {
				if (x > 0) {
					rowString.append(" ");
				}
				rowString.append(matrix[y][x]);
			}
			logger.info(rowString.toString());
		}
		logger.info("-------------------------------------------------------------------------");
	}
}
