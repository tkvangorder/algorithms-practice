package algorithms.poker;

/**
 * This represents the hand rank from least significant (High Card) to most significant (Straight Flush). 
 * 
 * NOTE: The order of the enum constants determines which rank is "higher" than another. This relies on the native compareTo method of the enum class.
 */
public enum HandRank {

	HIGH_CARD,
	PAIR,
	TWO_PAIR,
	THREE_OF_A_KIND,
	STRAIGHT,
	FLUSH,
	FULL_HOUSE,
	FOUR_OF_A_KIND,
	STRAIGHT_FLUSH;	
}
