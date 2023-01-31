package algorithms.graphs;

import java.util.Arrays;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and
 * is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all
 * surrounded by water.
 * 
 * <PRE>
 * Example 1:
 * 
 * Input:
 * 
 * 11110
 * 11010
 * 11000
 * 00000
 * 
 * Output: 1
 * 
 * -----------
 * 
 *  Example 2:
 * 
 * Input:
 * 
 * 11000
 * 11000
 * 00100
 * 00011
 * 
 * Output: 3
 * </PRE>
 *
 * @author tyler
 */
public class NumberOfIslands {
	private static final Logger logger = LoggerFactory.getLogger("algorithms");

	private static final Random random = new Random();
	
	public static void main(String[] args) {
		NumberOfIslands solution = new NumberOfIslands();

		char[][] grid = new char[][] {
				new char[] {'1','1','1','1','0'},
				new char[] {'1','1','0','1','0'},
				new char[] {'1','1','0','0','0'},
				new char[] {'0','0','0','0','0'},
		};
    	printGrid(grid);
    	printNumberOfIslands(solution.numIslands(grid));
    	
    	grid = generateRandomGird(10,10);
    	printGrid(grid);
    	printNumberOfIslands(solution.numIslands(grid));    	
	}

    public int numIslands(char[][] grid) {
    	if (grid.length == 0  || grid[0].length == 0) {
    		return 0;
    	}
    	int islandCount = 0;
    	for (int x = 0; x < grid[0].length; x++)  {
        	for (int y = 0; y < grid.length; y++)  {
        		if (grid[y][x] == '1') {
        			islandCount++;
        			//This is destructive to the grid, we mark visited as '0'
        			depthFirstTraversal(grid,y, x);
        		}
        	}
    	}
        return islandCount;
    }

    private void depthFirstTraversal(char[][] grid, int x, int y) {
    	if (x < 0 || x >= grid[0].length || y < 0 || y >= grid.length) {
    		return;
    	}
    	if (grid[x][y] == '1') {
    		grid[x][y] = '0';
    		depthFirstTraversal(grid, x -1, y);
    		depthFirstTraversal(grid, x +1, y);
    		depthFirstTraversal(grid, x, y + 1);
    		depthFirstTraversal(grid, x, y -1);
    	}
	}

	private static char[][] generateRandomGird(int width, int height) {
    	char[][] grid = new char[width][height];
    	for (int x = 0; x < width; x++)  {
        	for (int y = 0; y < height; y++)  {
        		if (random.nextBoolean()) {
        			grid[x][y] = '1';
        		} else {
        			grid[x][y] = '0';        			
        		}
        	}
    	}
    	return grid;
    }
    private static void printGrid(char[][] grid) {
    	for (char[] row : grid) {
        	logger.info(Arrays.toString(row));    		
    	}
    }
    
    private static void printNumberOfIslands(int result) {
		logger.info("");
		logger.info("Number of Islands : " + result);
		logger.info("-------------------------------------");
    }

}

