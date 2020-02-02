package alogirthms.strings;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * Implement atoi which converts a string to an integer.
 *
 * The function first discards as many whitespace characters as necessary until the first non-whitespace character is found. Then, starting from this character, takes an optional initial plus or minus sign followed by as many numerical digits as possible, and interprets them as a numerical value.
 *
 * The string can contain additional characters after those that form the integral number, which are ignored and have no effect on the behavior of this function.
 *
 * If the first sequence of non-whitespace characters in str is not a valid integral number, or if no such sequence exists because either str is empty or it contains only whitespace characters, no conversion is performed.
 *
 * If no valid conversion could be performed, a zero value is returned.
 *
 * Note:
 *
 * Only the space character ' ' is considered as whitespace character.
 * Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1]. If the numerical value is out of the range of representable values, INT_MAX (231 − 1) or INT_MIN (−231) is returned.
 * <PRE>
 * Example 1:
 *
 * Input: "42"
 * Output: 42
 * Example 2:
 *
 * Input: "   -42"
 * Output: -42
 *
 * Explanation: The first non-whitespace character is '-', which is the minus sign.
 * Then take as many numerical digits as possible, which gets 42.
 *
 * Example 3:
 *
 * Input: "4193 with words"
 * Output: 4193
 * Explanation: Conversion stops at digit '3' as the next character is not a numerical digit.
 *
 * Example 4:
 *
 * Input: "words and 987"
 * Output: 0
 * Explanation: The first non-whitespace character is 'w', which is not a numerical
 * digit or a +/- sign. Therefore no valid conversion could be performed.
 *
 * Example 5:
 *
 * Input: "-91283472332"
 * Output: -2147483648
 *
 * Explanation: The number "-91283472332" is out of the range of a 32-bit signed integer.
 * Thefore INT_MIN (−231) is returned.

 * </PRE>
 *
 * @author tyler
 */
public class ParseInteger {
	private static final Logger logger = LoggerFactory.getLogger("algorithms");

	public static void main(String[] args) {
		String[] stringValues = {
				"-   234",
				"-2147483647",
				"-2147483648",
				"2147483647",
				"++",
				"+-2",
				"+",
				"42",
				"   -42",
				"4193 with words",
				"words and 987",
				"-91283472332"
		};
		ParseInteger parser = new ParseInteger();
		for (String str : stringValues) {
			logger.info(str + " : " + parser.parseInteger(str));
		}
	}

	public int parseInteger(String str) {
		if (str == null || str.isBlank()) {
			return 0;
		}

		char[] characters = str.toCharArray();

		//Chew up white space
		int index = 0;
		while (index < characters.length && characters[index] == ' ') {
			index++;
		}
		//Look for the sign.
		int sign = 1;
		if (index < characters.length) {
			if (characters[index] == '+') {
				index++;
			} else if (characters[index] == '-') {
				sign = -1;
				index++;
			}
		}

		long value = 0;

		for (;index < characters.length; index++) {
			if (!(characters[index] >= '0' && characters[index] <= '9')) {
				break;
			}
			if (sign == 1) {
				value = (value * 10) + (characters[index] - '0');
				if ((int)value != value) {
					return Integer.MAX_VALUE;
				}
			} else {
				value = (value *10) - (characters[index] - '0');
				if ((int)value != value) {
					return Integer.MIN_VALUE;
				}
			}
		}

		return (int)value;
	}
}
