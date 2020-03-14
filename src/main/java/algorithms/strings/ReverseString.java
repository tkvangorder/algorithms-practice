package algorithms.strings;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Write a function that reverses a string. The input string is given as an array of characters char[].
 *
 * Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
 *
 * You may assume all the characters consist of printable ascii characters.
 *
 * <PRE>
 * Example 1:
 *
 * Input: ["h","e","l","l","o"]
 * Output: ["o","l","l","e","h"]
 *
 * Example 2:
 *
 * Input: ["H","a","n","n","a","h"]
 * Output: ["h","a","n","n","a","H"]
 * </PRE>
 *
 * This algorithm is O(n/2) where n is the length of the string.
 * @author tyler
 */
public class ReverseString {

	private static final Logger logger = LoggerFactory.getLogger("algorithms");

	public static void main(String[] args) {
		char[] myString = "Yoy".toCharArray();
		logger.info("String Before: "  + new String(myString));
		new ReverseString().reverseString(myString);
		logger.info("String After: "  + new String(myString));

	}

	public void reverseString(char[] myString) {
		int startIndex = 0;
		int endIndex = myString.length - 1;
		while(startIndex <= endIndex) {
			char tmp = myString[startIndex];
			myString[startIndex] = myString[endIndex];
			myString[endIndex] = tmp;
			startIndex++;
			endIndex--;
		}
	}
}


