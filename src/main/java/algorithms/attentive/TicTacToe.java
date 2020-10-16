package algorithms.attentive;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Tic-tac-toe is played by two players A and B on a 3 x 3 grid.
 * 
 * Here are the rules of Tic-Tac-Toe:
 * 
 * Players take turns placing characters into empty squares (" ").
 * 
 * The first player A always places "X" characters, while the second player B always places "O" characters.
 * "X" and "O" characters are always placed into empty squares, never on filled ones. The game ends when
 * there are 3 of the same (non-empty) character filling any row, column, or diagonal. The game also ends
 * if all squares are non-empty. No more moves can be played if the game is over.
 * 
 * Given an array moves where each element is another array of size 2 corresponding to the row and column
 * of the grid where they mark their respective character in the order in which A and B play.
 * 
 * Return the winner of the game if it exists (A or B), in case the game ends in a draw return "Draw", if there are still movements to play return "Pending".
 * 
 * You can assume that moves is valid (It follows the rules of Tic-Tac-Toe), the grid is initially empty and A will play first. * 
 * 
 * <PRE>
 * Input: moves = [[0,0],[2,0],[1,1],[2,1],[2,2]]
 * Output: "A"
 * Explanation: "A" wins, he always plays first.
 * "X  "    "X  "    "X  "    "X  "    "X  "
 * "   " -> "   " -> " X " -> " X " -> " X "
 * "   "    "O  "    "O  "    "OO "    "OOX"
 * </PRE>
 *
 * @author tyler
 */
public class TicTacToe {
	private static final Logger logger = LoggerFactory.getLogger("algorithms");

	public static void main(String[] args) {
		TicTacToe instance = new TicTacToe();
		
		int moves[][] = new int[][] {
				new int[] {0,0},
				new int[] {2,0},
				new int[] {1,1},
				new int[] {2,1},
				new int[] {2,2}
			};
		logger.info(instance.tictactoe(moves));
	}

	/**
	 * This solution uses a bitmap to represent the board. It keeps track of player a and player b's moves
	 * by converting the two digit move into a bit and then doing a logic "or" on the player's spots.
	 * 
	 * The bits in the integer are arranged as follows: 
	 * 
	 * 012
	 * 345
	 * 678
	 * 
	 * @param moves Moves from both players
	 * @return "A" if player A wins, "B" if player B wins, "Draw" if there is no winner, or "Pending" if there were not at least 9 moves. 
	 */
	public String tictactoe(int[][] moves) {
		int aSpots = 0;
		int bSpots = 0;

		//Player A always starts.
		int moveCount = 0;
		boolean playerA = true;
		for (int[] move : moves) {
			//First digit represents the "row" the second represents the "column". We just use a simple lookup table to map the resulting index into the proper "bit"
			int moveBit = bitIndex[move[0] * 3 + move[1]];

			//Logical and the bit into the existing player's moves
			if (playerA) {
				aSpots = aSpots | moveBit;
			} else {
				bSpots = bSpots | moveBit;
			}
			moveCount++;
			if (moveCount > 4) {
				//We only need to start looking for winning conditions once at least one player has 3 moves in play.
				for (int winMask : winMasks) {
					//We just iterate over the winning masks and see if any of them are a match for the current player making the move.
					if (playerA) {
						if ((winMask & aSpots) == winMask) {
							return "A";
						}
					} else if ((winMask & bSpots) == winMask) {
						return "B";
					}
				}
			}
			//Alternate players for next move.
			playerA = !playerA;
		}
		
		if (moveCount == 9) {
			//If the board is full, we have a draw.
			return "Draw";
		} else {
			//Otherwise, the game is pending.
			return "Pending";
		}
	}

	//012
	//345
	//678

	/**
	 * A lookup table where the index is a number between 0 and 9 and the result is an integer with the correct bit set.
	 */
	private int[] bitIndex = {
		//876543210
		0b000000001,
		0b000000010,
		0b000000100,
		0b000001000,
		0b000010000,
		0b000100000,
		0b001000000,
		0b010000000,
		0b100000000,
	};

	/**
	 * This masks represent each possible winning condition
	 */
	private static int[] winMasks = new int [] {
		//876543210
		0b111000000, //First Row
		0b000111000, //Second Row
		0b000000111, //Third Row
		0b100100100, //First Column
		0b010010010, //Second Column
		0b001001001, //Third Column
		0b100010001, //Diagnal 1
		0b001010100  //Diagnal 2
	};
}
