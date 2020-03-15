package algorithms;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import common.FileHelper;

/**
 *
 * <PRE>
 * </PRE>
 *
 * @author tyler
 */
public class Solution {
	private static final Logger logger = LoggerFactory.getLogger("algorithms");
	private static final Scanner scanner = new Scanner(FileHelper.getInputStreamForClassResource(Solution.class, "soultion-input.txt"));

	public static void main(String[] args) {
		Solution solution = new Solution();
		solution.method();
	}

	public void method() {

	}
}
