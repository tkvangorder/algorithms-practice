package algorithms.strings;

import java.util.ArrayList;
import java.util.List;

// IPv6 is the most recent version of Internet Protocol. IPv6 addresses have 128 bits. The 128 bits of an IPv6 address
// are represented in groups of 16 bits each. Each group is written as four hexadecimal digits and the groups are
// separated by colons (:). An example of this representation is 2001:0db8:0000:0000:0000:ff00:0042:8329. For
// convenience, an IPv6 address may be abbreviated to shorter notations by application of the following rules:
//
// One or more leading zeros from any groups of hexadecimal digits are removed. This is done to all of the leading zeros. For example, the group 0042 is converted to 42.
//
// Consecutive sections of zeros are replaced with a double colon (::). The double colon may only be used once in an address, as multiple uses would render the address indeterminate.
// Consider the IPv6 address 2001:0db8:0000:0000:0000:ff00:0000:8329. Removing all the leading zeros in each group yields 2001:db8:0:0:0:ff00:0:8329.
// After omitting the longest consecutive section of zeros, the result is 2001:db8::ff00:0:8329. The address, 0000:0000:0000:0000:0000:0000:0000:0001, may be abbreviated to ::1 by using both rules.

public class AbbreviateIp6Addresses {

    public static void main(String[] args) {
        String[] inputs = new String[] {
                "2001:0db8:0000:0000:0000:ff00:0042:8329",
                "ffff:0000:ffff:0000:0000:ffff:00ff:0000",
                "ffff:0000:ffff:0000:ffff:0000:0000:0000"

        };

        for (String input : inputs) {
            System.out.println("Original: " + input + " - Result " + abbreviateIp6Address(input));
        }
    }

    static String abbreviateIp6Address(String ip6Address) {

        if (ip6Address == null) {
            throw new IllegalArgumentException("Null IP 6 address.");
        }
        String[] parts = ip6Address.split(":");

        if (parts.length != 8) {
            throw new IllegalArgumentException("Invalid IP 6 address, expecting 8 parts.");
        }

        List<String> abbreviated = new ArrayList<>();

        int zeroCount = 0;
        int maxConsecutiveZeroCount = 0;
        int maxConsecutiveZeroStart = -1;

        for (int partIndex = 0; partIndex < parts.length; partIndex++) {

            String part = parts[partIndex];
            //Assuming each octet is a 4 digit hex number
            if (part.length() != 4) {
                throw new IllegalArgumentException("Invalid octet");
            }

            //First trim the octet.
            String trimmedOctet = "0";
            for (int index = 0; index < part.length(); index++) {
                if (part.charAt(index) != '0') {
                    trimmedOctet = part.substring(index);
                    break;
                }
            }

            abbreviated.add(trimmedOctet);

            //Keep track of where the largest sequence of zeros are in the address.
            if (trimmedOctet.equals("0")) {
                zeroCount++;
            }

            if (zeroCount > 0 && (!trimmedOctet.equals("0")) || partIndex == parts.length -1) {
                if (zeroCount > maxConsecutiveZeroCount) {
                    maxConsecutiveZeroCount = zeroCount;
                    maxConsecutiveZeroStart = partIndex - zeroCount;
                    zeroCount = 0;
                }
            }
        }

        StringBuilder result = new StringBuilder();
        int partIndex = 0;
        while (partIndex < abbreviated.size()) {
            if (!result.isEmpty()) {
                result.append(":");
            }
            if (partIndex == maxConsecutiveZeroStart) {
                partIndex = partIndex + maxConsecutiveZeroCount;
            } else {
                result.append(abbreviated.get(partIndex));
                partIndex++;
            }
        }
        return result.toString();

    }

}