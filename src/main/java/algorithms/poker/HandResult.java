package algorithms.poker;

import java.util.Arrays;
import java.util.List;

import common.Assert;

/**
 * This class is a codified representation of a hand strength after it has been evaluated.
 * 
 *  The result consists of :
 *  
 *  	Hand rank : Full house, flush ,straight, etc
 *     Card Values : Up to the five best cards used to derive the hand rank, in poker lexicographical order. (Ordered from most significant card to least significant card.
 */
public class HandResult implements Comparable<HandResult> {

	public static HandResult LOWEST = new HandResult(HandRank.HIGH_CARD, Arrays.asList(CardValue.TWO, CardValue.THREE, CardValue.FOUR, CardValue.FIVE, CardValue.SEVEN));
	private final HandRank rank;
	private final List<CardValue> cardValues;

	public HandResult(HandRank rank, List<CardValue> cardValues) {
		Assert.notNull(rank, "The hand rank cannot be null");
		Assert.isTrue(cardValues != null & cardValues.size() <= 5, "You must supply between 1 and 5 card ranks");
		
		this.rank = rank;
		this.cardValues = cardValues;
	}
	

	public HandRank getRank() {
		return rank;
	}

	public List<CardValue> getCardValues() {
		return cardValues;
	}

	@Override
	public int compareTo(HandResult other) {
		int value = rank.compareTo(other.getRank());
		if (value == 0) {
			for (int index = 0; index < cardValues.size(); index++) {
				value = cardValues.get(index).compareTo(other.getCardValues().get(index));
				if (value != 0) break;
			}
		}
		return value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((rank == null) ? 0 : rank.hashCode());
		if (cardValues == null) {
			result = prime * result;
		} else {
			for (CardValue value : cardValues) {
				result = prime * result + value.hashCode();
			}
		}
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		HandResult other = (HandResult) obj;

		if (rank != other.rank)
			return false;
		
		return cardValuesEqual(other);
	}


	private boolean cardValuesEqual(HandResult other) {
		if (cardValues == null) {
			if (other.cardValues != null)
				return false;
		} else {
			if (other.cardValues == null || cardValues.size() != other.cardValues.size()) { 
				return false;
			} else {
				for (int index = 0; index < cardValues.size(); index++) {
					if (cardValues.get(index) != other.cardValues.get(index)) {
						return false;
					}
				}
			}
		}
		return true;
	}

	@Override
	public String toString() {
		return rank.toString() + " : " +  Arrays.toString(cardValues.toArray());
	}	
}
