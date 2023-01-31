package algorithms.arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;


/**
 * Given a target string, find every pair combination, that when concatenated together will equal that target.
 *
 * Example: Target = "7777" and array has "777", "7", "77", "77" ("777" + "7", "7" + "777", "77" + "77", and "77" + "77") = 4
 * Example: Target = "1234" and array has "123", "4", "34", "12" ("123" + "4" and "12" + "34") = 2
 */
public class CountTargetPairs {
    private static final Logger logger = LoggerFactory.getLogger("algorithms");

    private static class TestCase {
        String[] inputs;

        String target;

        private TestCase(String[] inputs, String target) {
            this.target = target;
            this.inputs = inputs;
        }

        @Override
        public String toString() {
            return ArrayUtils.arrayToString(inputs) + " - Target = \"" + target + "\"";
        }
    }

    public static void main(String[] args) {
        TestCase[] testCases = new TestCase[] {
                new TestCase(new String[] {"777","7","77","77"}, "7777"),
                new TestCase(new String[] {"123","4","34", "12"}, "1234"),
                new TestCase(new String[] {"1", "1", "1"}, "11")
        };

        for (TestCase testCase : testCases) {
            logger.info(testCase + " - Count = " + countPairs(testCase.inputs, testCase.target));
        }

    }

    // This algorithm is O(N Log(N)), it first counts each string value contained in the array, if two elements are the same value, the count will be 2.
    // It then iterates over the list again, where if the "target" starts with a given "part", it then looks up the remaining substring to get a count of
    // how many combinations complete the target. Note, if the "part" and the remaining substring are the same, we need to subtract one from the count.

    public static int countPairs(String[] nums, String target) {

        int count = 0;
        Map<String, Integer> partCounts = new HashMap<>();

        for (String part : nums) {
            partCounts.merge(part, 1, (p1, p2) -> p1 + 1);
        }

        for (String part: nums) {
            if (!target.startsWith(part)) continue;
            String subTarget = target.substring(part.length());

            Integer targetCount = partCounts.get(subTarget);
            if (targetCount == null) {
                continue;
            }
            if (part.equals(subTarget)) {
                count = count + targetCount -1;
            } else {
                count = count + targetCount;
            }
        }
        return count;
    }

}
