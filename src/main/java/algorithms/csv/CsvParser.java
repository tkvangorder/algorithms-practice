package algorithms.csv;

import java.util.ArrayList;
import java.util.List;

/**
 * There are third-party libraries that can be used to parse CSV files, Jackson, OpenCSV, and Apache Commons CSV etc.
 * But for this exercise we will just build a simple CSV parser.
 */
public class CsvParser {

  public static List<String> parse(String line) {
    if (line == null || line.isEmpty()) {
      return List.of();
    }

    char[] chars = line.toCharArray();
    List<String> elements = new ArrayList<>();
    ParserState state = ParserState.FIND_ELEMENT;
    int elementStart = 0;
    for (int i = 0; i < chars.length; i++) {
      switch (state) {
        case FIND_ELEMENT:
          if (Character.isWhitespace(chars[i])) {
            continue;
          } else if (chars[i] == '"') {
            elementStart = i + 1;
            state = ParserState.FIND_END_QUOTE;
          } else if (chars[i] == ',') {
            elements.add(new String(chars, elementStart, i - elementStart).trim());
            elementStart = i + 1;
          } else {
            elementStart = i;
            state = ParserState.FIND_END_COMMA;
          }
          break;
        case FIND_END_QUOTE:
          if (chars[i] == '"' && peekForQuote(chars, i+1)) {
            // A double quote is treated as an escaped quote
            i = i + 1;
          } else if (chars[i] == '"') {
            elements.add(new String(chars, elementStart, i - elementStart).trim().replace("\"\"", "\""));
            state = ParserState.FIND_COMMA;
          }
          break;
        case FIND_END_COMMA:
          if (chars[i] == ',') {
            elements.add(new String(chars, elementStart, i - elementStart).trim());
            state = ParserState.FIND_ELEMENT;
          }
          break;
        case FIND_COMMA:
          if (chars[i] == ',') {
            state = ParserState.FIND_ELEMENT;
            elementStart = i+1;
          }
          break;
      }
    }
    if (state == ParserState.FIND_END_COMMA) {
      elements.add(new String(chars, elementStart, chars.length - elementStart).trim());
    } else if (state == ParserState.FIND_END_QUOTE) {
      throw new IllegalArgumentException("Invalid CSV format");
    }
    return elements;
  }

  enum ParserState {
    FIND_ELEMENT,
    FIND_END_QUOTE,
    FIND_END_COMMA,
    FIND_COMMA,
  }

  private static boolean peekForQuote(char[] chars, int i) {
    return (i < chars.length && chars[i] == '"');
  }
}

