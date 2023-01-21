package algorithms.strings;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This version of fist unique character will allow ANY valid unicode character and return the first non-repeating
 * character.
 * <p>
 * This is O(N) time complexity and O(1) constant space complexity, as it always requires 65K memory.
 */
public class FirstUniqueCharacter2 {

    private static final Logger logger = LoggerFactory.getLogger("algorithms");

    public static void main(String[] args) {
        // I would normally use a logging framework or facade like slf4j. But not available in coderpad.

        String[] inputs = new String[]{
                "aaaabbbbbCCCC",               // No non-repeating should return null.
                "aaaabbbbbcCCCdddddeeeeeF",    // Should return 'c'
                "\uffff",                      // Should not get array out of bounds and return '\uffff'
                "\u0000",                      // Should return '\u0000'
                "!a!aBሴBሴB1212\\",            // Should return '\'
                "+!a!aBሴBሴB1212\\",           // Should return '+'
                "1",                           // Should return `1`
                "",                            // Should return null
                null                           // Should return null
        };

        for (String input : inputs) {
            logger.info("' - Input '" + input + "', Result = '" + firstNonRepeatingCharacter(input) + "'");
        }
    }


    public static Character firstNonRepeatingCharacter(String input) {

        if (input == null || input.isEmpty()) {
            return null;
        }

        //Count character references, note this sacrifices memory for speed, in that we pre-allocate a 65K array
        //of bytes. So 65K x 1 byte.

        //Max size of a char is 16 bit Unicode character, create an array big enough to use the character as a lookup
        //into the array where we count references. To minimize memory, we use a byte which is 8 bits, all we care
        //about is if there is more than one reference, to not overflow the reference count, we simply stop after the
        //count is 2.
        byte[] characterReferences = new byte[65536];

        char[] characters = input.toCharArray();
        for (char c : characters) {
            if (characterReferences[c] < 2) {
                characterReferences[c]++;
            }
        }

        //Now find the first character that is non-repeating.
        for (char c : characters) {
            if (characterReferences[c] == 1) {
                return c;
            }
        }
        return null;
    }
}
