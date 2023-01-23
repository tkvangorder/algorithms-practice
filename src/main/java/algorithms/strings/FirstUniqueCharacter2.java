package algorithms.strings;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * This version of fist unique character will allow ANY valid unicode character and return the first non-repeating
 * character.
 * <p>
 * This is O(N) time complexity and O(1) constant space complexity, as it always requires 65K memory.
 */
public class FirstUniqueCharacter2 {

    private static final Logger logger = LoggerFactory.getLogger("algorithms");

    public static void main(String[] args) {

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


    @SuppressWarnings("DataFlowIssue")
    public static Character firstNonRepeatingCharacter(String input) {
        if (input == null || input.isEmpty()) {
            return null;
        }

        //Max size of a char is 16 bit Unicode character.

        //Count character references, this algorithm tries to balance memory usage while still allowing any unicode
        //character. This takes a page out of Java's compact strings, in that statistically most characters can be
        //represented as 8 bits.
        //
        //Therefore, we use an array of 256 bytes to represent the most common characters. And if a character is outside
        //that range, a HashMap is created on-demand and used for those references.
        byte[] characterReferences = new byte[256];
        Map<Character, Byte> overflow = null;
        char[] characters = input.toCharArray();

        for (char c : characters) {
            if (c < characterReferences.length && characterReferences[c] < 2) {
                characterReferences[c]++;
            } else {
                if (overflow == null) {
                    overflow = new HashMap<>();
                }
                overflow.merge(c, (byte) 1, (b1, b2) -> (byte) 2);
            }
        }

        //Now find the first character that is non-repeating.
        for (char c : characters) {

            if (c < characterReferences.length && characterReferences[c] == 1) {
                return c;
            } else if (c >= characterReferences.length && overflow.get(c) == 1) {
                return c;
            }
        }
        return null;
    }
}
