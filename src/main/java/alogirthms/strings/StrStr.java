package alogirthms.strings;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * Implement strStr().
 *
 * Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
 *
 * <PRE>
 *
 * Example 1:
 *
 * Input: haystack = "hello", needle = "ll"
 * Output: 2
 *
 * Example 2:
 *
 * Input: haystack = "aaaaa", needle = "bba"
 * Output: -1
 *
 * Clarification:
 *
 * What should we return when needle is an empty string? This is a great question to ask during an interview.
 * For the purpose of this problem, we will return 0 when needle is an empty string. This is consistent to C's
 * strstr() and Java's indexOf().
 *
 * </PRE>
 *
 * So, the most obvious answer to this question in Java is to just use String.indexOf() and be
 * done with this problem.
 *
 * @author tyler
 */
public class StrStr {
	private static final Logger logger = LoggerFactory.getLogger("algorithms");

	public static void main(String[] args) {
		String inputs[][] = new String[][] {
			new String[] {"aaaaaaaaaahello", "hello"},
			new String[] {"", ""},
			new String[] {"", "ab"},
			new String[] {"aaaaaaaaaa", "ab"},
			new String[] {"aaaabaaaaa", "ab"},
			new String[] {"aaaaaaaaaahelloaaaaaaaaaa", "hello"},
			new String[] {"aaaaaaaaaahell", "hello"}
		};
		StrStr indexOf = new StrStr();
		for (String[] input : inputs) {
			int result = indexOf.strStr(input[0], input[1]);
			logger.info( result + " = indexOf[" + input[0] + "], [" + input[1] + "]");
		}
	}

	public int strStr(String haystack, String needle) {
		if (needle.equals("")) {
			return 0;
		}
		char[] haystackChars = haystack.toCharArray();
		char[] needleChars = needle.toCharArray();

		int needleLength = needleChars.length;
		int hayStackLength = haystackChars.length;

		if (needleLength > hayStackLength) {
			return -1;
		}
		for (int index = 0; index <= (hayStackLength - needleLength); index++) {
			boolean matchFound = true;
			for(int index2 = 0; index2 < needleLength; index2++) {
				if (haystackChars[index + index2] != needleChars[index2]) {
					matchFound = false;
					break;
				}
			}
			if (matchFound) {
				return index;
			}
		}
		return -1;
	}
}
