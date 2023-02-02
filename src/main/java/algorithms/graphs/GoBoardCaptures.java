package algorithms.graphs;

import algorithms.arrays.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.SecureRandom;

/**
 * <PRE>
 *     Given a go board, determine if there are any captures. A capture is when a group of stones is surrounded by the
 *     opposing color.
 *
 *     0 == empty space
 *     1 == Black
 *	   2 == White
 *
 * </PRE>
 *
 * @author tyler
 */
public class GoBoardCaptures {
	private static final Logger logger = LoggerFactory.getLogger("algorithms");

	private static final SecureRandom random = new SecureRandom();

	public static void main(String[] args) {
		GoBoardCaptures solution = new GoBoardCaptures();
		//int[][] board = solution.generateBoard(19);
		int[][] board = new int[][] {
				{1, 1, 1},
				{1, 2, 2},
				{0, 1, 1}
		};
		for (int[] row : board) {
			logger.info(ArrayUtils.arrayToString(row));
		}
		logger.info("Are there any captures? " + solution.areThereCaptures(board));
	}

	//0 == empty space
	//1 == Black
	//2 == White
	public boolean areThereCaptures(int[][] board) {
		boolean[][] visited = new boolean[board.length][board.length];
		for (int y = 0; y < board.length; y++) {
			for (int x = 0; x < board.length; x++) {
				int currentColor = board[y][x];
				if (currentColor == 0 || visited[y][x]) {
					continue;
				}
				if (isCaptured(currentColor, y, x, board, visited)) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean isCaptured(int currentColor, int currentY, int currentX, int[][] board, boolean[][] visited) {

		visited[currentY][currentX] = true;
		int aboveColor = currentY > 0 ? board[currentY - 1][currentX] : -1;
		int belowColor = currentY < board.length -1 ? board[currentY + 1][currentX] : -1;
		int leftColor = currentX > 0 ? board[currentY][currentX - 1] : -1;
		int rightColor = currentX < board.length -1 ? board[currentY][currentX + 1] : -1;

		//Note, we do not short-circuit by returning immediately here because we want to mark all visited nodes.
		boolean captured = aboveColor != 0 && belowColor != 0 && leftColor != 0 && rightColor != 0;
		if (aboveColor == currentColor && !visited[currentY - 1][currentX] && !isCaptured(currentColor, currentY - 1, currentX, board, visited)) {
			captured = false;
		}
		if (belowColor == currentColor && !visited[currentY + 1][currentX] && !isCaptured(currentColor, currentY + 1, currentX, board, visited)) {
			captured = false;
		}
		if (leftColor == currentColor && !visited[currentY][currentX - 1] && !isCaptured(currentColor, currentY, currentX - 1, board, visited)) {
			captured = false;
		}
		if (rightColor == currentColor && !visited[currentY][currentX + 1] && !isCaptured(currentColor, currentY, currentX + 1, board, visited)) {
			captured = false;
		}
		return captured;
	}

	int[][] generateBoard(int gridSize) {
		int[][] board = new int[gridSize][gridSize];

		for (int y = 0; y < gridSize; y++) {
			for (int x = 0; x < gridSize; x++) {
				board[y][x] = random.nextInt(3);
			}
		}
		return board;
	}
}
