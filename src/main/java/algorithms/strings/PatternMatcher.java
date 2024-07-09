package algorithms.strings;

/*

Write a function with the following signature:

     boolean isMatch(String pattern, String word)

This function returns true if (and only if)
word is a complete match for pattern.

If pattern contains only letters, this function should just
do a string comparison:

  isMatch("guitar", "guitar") => true
  isMatch("guitar", "guitars") => false

If pattern contains a number, we'll treat it as a wildcard. In the
number's position, a matching word can contain any letters, as
long as there are as many letters as the number.

isMatch("3", "aaa") => true
isMatch("3", "aa") => false
isMatch("g3ar", "guitar") => true
isMatch("g3ar", "gtar") => false

The expected functionality can be summarized in a table:

pattern  |   word   | output
===========================
guitar   | guitar   | true
guitar   | guitars  | false
3        | aaa      | true
3        | aa       | false
g3ar     | guitar   | true
g3ar     | gtar     | false

 */

public class PatternMatcher {
  public static void main(String[] args) {
    // TODO
  }

  private static boolean isMatch(String pattern, String word) {
    // TODO
    return true; // TODO
  }
}



