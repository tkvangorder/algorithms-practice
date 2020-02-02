package alogirthms.strings;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.
 * <PRE>
 * Examples:
 *
 * s = "leetcode"
 * return 0.
 *
 * s = "loveleetcode",
 * return 2.
 * </PRE>
 *
 * Note: You may assume the string contain only lowercase letter.
 *
 * @author tyler
 */
public class FirstUniqueCharacter {
	private static final Logger logger = LoggerFactory.getLogger("algorithms");
	public static void main(String[] args) {
		String myString = "yaaaaaaaaaaaay";
		int index = new FirstUniqueCharacter().firstUniqChar(myString);
		if (index < 0) {
			logger.info("There are no unique characters in the string.");
		} else {
			logger.info("The first non-repeating character is '" + myString.charAt(index) + "' at index " + index);
		}
	}

	public int firstUniqChar(String s) {

		char[] characters = s.toCharArray();
		//Since the string can only contain lower case letters, we can create a reference count for each character.
		int[] characterReferenceMap = new int[26];
		for (char index: characters) {
			characterReferenceMap[index - 'a']++;
		}
		//And then go through again, and find the first where the reference count is 1.
		for (int index = 0; index < characters.length; index++) {
			if (characterReferenceMap[characters[index]- 'a'] == 1) {
				return index;
			}
		}
		return -1;
	}
}
