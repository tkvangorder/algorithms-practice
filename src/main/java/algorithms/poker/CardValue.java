package algorithms.poker;

import java.util.HashMap;
import java.util.Map;

public enum CardValue {
	
	//				   AKQJT98765432A
	TWO('2'			,0b00000000000010),
	THREE('3'		,0b00000000000100),
	FOUR('4'		,0b00000000001000),
	FIVE('5'		,0b00000000010000),
	SIX('6'			,0b00000000100000),
	SEVEN('7'		,0b00000001000000),
	EIGHT('8'		,0b00000010000000),
	NINE('9'		,0b00000100000000),
	TEN('T'			,0b00001000000000),
	JACK('J'		,0b00010000000000),
	QUEEN('Q'		,0b00100000000000),
	KING('K'		,0b01000000000000),
	//Note: This mask accounts for an extra bit for a "low" ace, we only set the high ace in the mask
	//           The only time we really need to add the low ace is when checking for straights.
	ACE('A'			,0b10000000000000);

	private static CardValue[] charToCardValue = new CardValue['U'];
	private static Map<Integer,CardValue> maskToCardValue = new HashMap<>();
	
	static {
		
		for (CardValue cardValue : values()) {
			charToCardValue[cardValue.value]= cardValue;			
			maskToCardValue.put(cardValue.getMask(), cardValue);
		}
		
	}
	
	private char value;
	private int mask;
	
	private CardValue(char value, int mask) {
		this.value = value;
		this.mask = mask;
	}
	
	public char getValue() {
		return value;
	}

	public int getMask() {
		return mask;
	}
	
	
	public static CardValue valueToEnum(char value) {
		if (value > 'T') {
			throw new  IllegalArgumentException("Unrecognized cardValue");
		}
		
		CardValue result = charToCardValue[value];
		if (result == null) {
			throw new  IllegalArgumentException("Unrecognized cardValue");
		}
		return result;
	}

	public static CardValue valueToEnum(int value) {
		if (value < 1 || value > 14) {
			throw new  IllegalArgumentException("Unrecognized cardValue "  + value);			
		}
		if (value == 1) {
			return ACE;
		} else {
			return values()[value-2];
		}
	}
	public static CardValue maskToEnum(int mask) {
		CardValue cardValue = maskToCardValue.get(mask);
		if (cardValue == null) {
			throw new  IllegalArgumentException("Unrecognized mask: " +  Integer.toBinaryString(mask));
		}
		return cardValue;
	}
	
}
