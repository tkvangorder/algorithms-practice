package algorithms.poker;

import lombok.Value;

@Value
public class Card {

	private final CardValue value;
	private final CardSuit suit;
	
	public String toString() {
		return new String(new char[] {value.getValue(), suit.getValue()});
	}
}
