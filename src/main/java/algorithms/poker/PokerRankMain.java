package algorithms.poker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PokerRankMain {

	private static final Logger logger = LoggerFactory.getLogger("algorithms");

	private static PokerRankMain ranker = new PokerRankMain();
	
	public static void main(String[] args) {
		PokerRankMain ranker = new PokerRankMain();

		//First just some basic testing of the various ranks.
		String[][] hands = {
			{"AC", "TC", "QC", "KC", "JC"},
			{"AC", "AH", "AS", "AD", "JC"},
			{"TC", "TH", "QS", "QD", "QC"},
			{"AC", "2C", "QC", "7C", "3C"},
			{"AC", "2D", "4C", "5H", "3C"},
			{"AC", "KD", "QC", "JH", "TC"},
			{"3C", "3H", "3S", "QD", "JC"},
			{"3C", "3H", "QS", "QD", "JC"},
			{"AC", "AH", "QS", "4D", "JC"},
			{"AC", "8H", "QS", "4D", "JC"},
		};
		for (String[] hand : hands) {
			ranker.logAndRankHand(stringsToCards(hand));
		}
		
		//Texas hold'em rules 5 community cards + 2 for each player (7 players) (over 10 rounds)
		for (int index =0; index < 10; index++) {
			logger.info("----------------------------------------------------------------------------------------------");
			logger.info("Texas Hold'em Round " + (index +1));
			Deck deck = new Deck();
			List<Card> communityCards = deck.drawCards(5);
			logger.info("----------------------------------------------------------------------------------------------");
			logger.info("Community Cards : " + Arrays.toString(communityCards.toArray()));
			logger.info("----------------------------------------------------------------------------------------------");
			for (int playerIndex = 0; playerIndex < 7; playerIndex++) {
				
				ranker.logAndRankTexasHoldem(playerIndex +1, deck.drawCards(2), communityCards);
			}
		}
	}

	
	private static List<Card> stringsToCards(String[] cardStrings) {
		List<Card> cards = new ArrayList<>(cardStrings.length);
		for (String card : cardStrings) {
			CardValue value = CardValue.valueToEnum(card.charAt(0));
			CardSuit suit = CardSuit.valueToEnum(card.charAt(1));
			cards.add(new Card(value, suit));
		}
		return cards;
	}

	private void logAndRankTexasHoldem(int playerNumber, List<Card> playerHand, List<Card> communityCards) {
		List<Card> combined = new ArrayList<> (playerHand);
		combined.addAll(communityCards);
		logger.info("Player : " + playerNumber + " : " + Arrays.toString(playerHand.toArray()) +  " - The rank is : " + ranker.rankHand(combined));
	}
	
	private void logAndRankHand(List<Card> hand) {
		logger.info("Hand : " + Arrays.toString(hand.toArray()) +  " - The rank is : " + ranker.rankHand(hand));
	}

	//
	public HandRank rankHand(List<Card> cards) {

		int clubs= 0;
		int hearts = 0;
		int diamonds = 0;
		int spades = 0;
		for (Card card : cards) {
			
			switch (card.getSuit()) {
				case CLUB :
					clubs = clubs | card.getValue().getMask();
					break;
				case DIAMOND :
					diamonds = diamonds| card.getValue().getMask();
					break;
				case HEART :
					hearts = hearts | card.getValue().getMask();
					break;
				case SPADE:
					spades = spades| card.getValue().getMask();
					break;
				default: 
			}			
		}
		
		if (isStraighFlush(clubs, hearts, diamonds, spades)){
			return HandRank.STRAIGHT_FLUSH;
		}
		if (isFourOfAKind(clubs, hearts, diamonds, spades)){
			return HandRank.FOUR_OF_A_KIND;
		}
		if (isFullHouse(clubs, hearts, diamonds, spades)){
			return HandRank.FULL_HOUSE;
		}
		if (isFlush(clubs, hearts, diamonds, spades)){
			return HandRank.FLUSH;
		}
		
		if (isStraight(clubs, hearts, diamonds, spades)){
			return HandRank.STRAIGHT;
		}
		if (isThreeOfAKind(clubs, hearts, diamonds, spades)){
			return HandRank.THREE_OF_A_KIND;
		}
		if (isTwoPair(clubs, hearts, diamonds, spades)){
			return HandRank.TWO_PAIR;
		}
		if (isPair(clubs, hearts, diamonds, spades)){
			return HandRank.PAIR;
		}
		return HandRank.HIGH_CARD;

	}
	
	/**
	 * Check for a straigh flush by looking for five consecutive 1s in each of our suit bitmaps.
	 * 
	 * @param clubs
	 * @param hearts
	 * @param diamonds
	 * @param spades
	 * @return
	 */
	private boolean isStraighFlush(int clubs, int hearts, int diamonds, int spades) {

		//Add the low ace (if there is a high ace);
		clubs = (clubs & CardValue.ACE.getMask()) > 0?(clubs | 0b1):clubs;
		hearts = (hearts & CardValue.ACE.getMask()) > 0?(hearts | 0b1):hearts;
		diamonds = (diamonds & CardValue.ACE.getMask()) > 0?(diamonds | 0b1):diamonds;
		spades = (spades & CardValue.ACE.getMask()) > 0?(spades | 0b1):spades;
		
		int straight = 0b11111;
	
		int suits[] = {clubs, hearts, diamonds, spades};
		for (int suit : suits ) {
			for (int index = 0; index < 10; index ++) {
				if ((straight & suit) == straight) {
					return true;
				}
				suit = suit >> 1;
			}
		}
		return false;
	}

	/**
	 * Check for a four of a kind, this will be the only case where logically anding all four suits will result in a non-zero result.
	 * 
	 * @param clubs
	 * @param hearts
	 * @param diamonds
	 * @param spades
	 * @return
	 */
	private boolean isFourOfAKind(int clubs, int hearts, int diamonds, int spades) {
		return (clubs & hearts & diamonds & spades) > 0;
	}
	
	private boolean isFullHouse(int clubs, int hearts, int diamonds, int spades) {

		int threeOfKind = (clubs & hearts & diamonds);
		if (threeOfKind == 0) {
			threeOfKind = (clubs & hearts & spades);
		}
		if (threeOfKind == 0) {
			threeOfKind = (clubs & diamonds & spades);
		}
		if (threeOfKind == 0) {
			threeOfKind = (hearts & spades & diamonds);
		}

		if (threeOfKind == 0) {
			return false;
		}
		int compliment =~ threeOfKind;
		return isPair(
				clubs & compliment,
				hearts & compliment,
				diamonds & compliment,
				spades & compliment);		
	}
	private boolean isFlush(int clubs, int hearts, int diamonds, int spades) {
		
		int suits[] = {clubs, hearts, diamonds, spades};
		for (int suit : suits ) {
			if (Integer.bitCount(suit) >= 5) {
				return true;
			}
		}
		return false;
	}

	private boolean isStraight(int clubs, int hearts, int diamonds, int spades) {

		
		int combined = clubs | hearts | diamonds | spades;

		//Add the low ace (if there is a high ace);
		combined = (combined & CardValue.ACE.getMask()) > 0?(combined | 0b1):combined;

		int straight = 0b11111;
		// 0b0000000000001 H
		for (int index = 0; index < 10; index ++) {
			if ((straight & combined) == straight) {
				return true;
			}
			combined = combined >> 1;
		}
		
		return false;
	}
	
	private boolean isThreeOfAKind(int clubs, int hearts, int diamonds, int spades) {
		return
				(clubs & hearts & diamonds) > 0 ||
				(clubs & hearts & spades) > 0 ||
				(hearts & spades & diamonds) > 0;
	}

	private boolean isTwoPair(int clubs, int hearts, int diamonds, int spades) {
		
		//Count all bits across suits first.
		int bitCount =
			Integer.bitCount(clubs) +
			Integer.bitCount(hearts) +
			Integer.bitCount(diamonds) +
			Integer.bitCount(spades);
		int combined = (clubs | hearts | diamonds | spades);		
		return bitCount - Integer.bitCount(combined) == 2;
	}
	
	private boolean isPair(int clubs, int hearts, int diamonds, int spades) {

		//Count all bits across suits first.
		int bitCount =
			Integer.bitCount(clubs) +
			Integer.bitCount(hearts) +
			Integer.bitCount(diamonds) +
			Integer.bitCount(spades);
		//merge the suits and since we double count aces, we need to zero out the low ace.
		int combined = (clubs | hearts | diamonds | spades) & 0b11111111111110;
		
		return bitCount - Integer.bitCount(combined) == 1;
	}
}
