package algorithms.csv;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CsvParserTest {

  @Test
  void emptyString() {
    String input = "";
    List<String> results = CsvParser.parse(input);
    assertThat(results).isEmpty();
  }

  @Test
  void noQuotesWithWhiteSpace() {
    String input = "  apples, oranges,   some other fruits  ";
    List<String> results = CsvParser.parse(input);
    assertThat(results).containsExactly("apples", "oranges", "some other fruits");
  }

  @Test
  void quotesNonQuotesEmpties() {
    String input = "\"\",, ,\"\"";
    List<String> results = CsvParser.parse(input);
    assertThat(results).containsExactly("", "", "", "");
  }

  @Test
  void mixedQuotesNonQuotes() {
    String input = "  apples, \"oranges\",\"\", \"fred \", some other fruits  ";

    List<String> results = CsvParser.parse(input);
    assertThat(results).containsExactly("apples", "oranges", "", "fred", "some other fruits");
  }

  @Test
  void mixedQuotedCommas() {
    String input = "  apples, \"oranges\",\"\", \"fred, sam, sally \", some other fruits  ";

    List<String> results = CsvParser.parse(input);
    assertThat(results).containsExactly("apples", "oranges", "", "fred, sam, sally", "some other fruits");
  }


  @Test
  void parseErrorUnevenQuotes() {
    String input = "  apples, oranges,\"\", \"fred, sam, sally, some other fruits  ";

    assertThatThrownBy(() -> CsvParser.parse(input))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("Invalid CSV format");

  }

  @Test
  void parseEscapedQuotes() {
    String input = "\"Atlantis, The Lost Continent\"";

    List<String> results = CsvParser.parse(input);
    assertThat(results).containsExactly("Atlantis, The Lost Continent");
  }

}
