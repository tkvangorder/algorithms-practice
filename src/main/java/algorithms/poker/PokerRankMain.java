package algorithms.poker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import common.Stopwatch;

public class PokerRankMain {

	private static final Logger logger = LoggerFactory.getLogger("algorithms");

	private static final CardValue[][] bitsToRank = precomputeCardValues();
	
	private static PokerRankMain ranker = new PokerRankMain();
	
	private static Stopwatch stopwatch = new Stopwatch();

	public static void main(String[] args) {
		PokerRankMain ranker = new PokerRankMain();

		//First just some basic testing of the various ranks.
		String[][] hands = {
			{"5C", "3C", "2C", "9S", "4C", "JC", "AC"},
			{"4H", "4S", "TD", "9D", "9C", "4D", "9H"},
			{"AC", "TC", "QC", "KC", "JC"},
			{"AC", "AH", "AS", "AD", "JC", "2C"},
			{"TC", "TH", "TS", "QD", "QC"},
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
		
		int numberOfHands = 100000;
		int numberOfPlayers = 7;
		//Texas hold'em rules 5 community cards + 2 for each player (7 players) (over 10 rounds)
		for (int index =0; index < numberOfHands; index++) {
			logger.info("----------------------------------------------------------------------------------------------");
			logger.info("Texas Hold'em Round " + (index +1));
			Deck deck = new Deck();
			List<Card> communityCards = deck.drawCards(5);
			logger.info("----------------------------------------------------------------------------------------------");
			logger.info("Community Cards : " + Arrays.toString(communityCards.toArray()));
			logger.info("----------------------------------------------------------------------------------------------");
			for (int playerIndex = 0; playerIndex < numberOfPlayers; playerIndex++) {
				ranker.logAndRankTexasHoldem(playerIndex +1, deck.drawCards(2), communityCards);
			}
		}
		
		logger.info("Time to rank " + (numberOfHands * numberOfPlayers) + " hands : " + stopwatch.getTotalElapsedTime());
		logger.info("Average per hands : " + (double) stopwatch.getTotalElapsedTime() / (numberOfHands * numberOfPlayers)) ;

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

		logger.info("Player : " + playerNumber + " : " + Arrays.toString(combined.toArray()));

		stopwatch.start();
		HandResult rank = ranker.rankHand(combined);
		stopwatch.stop();
		logger.info("Player : " + playerNumber + " : " + Arrays.toString(playerHand.toArray()) +  " - The rank is : " + rank);
	}
	
	private void logAndRankHand(List<Card> hand) {
		logger.info("Hand : " + Arrays.toString(hand.toArray()) +  " - The rank is : " + ranker.rankHand(hand));
	}

	//
	public HandResult rankHand(List<Card> cards) {

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
		
		HandResult result = isStraighFlush(clubs, hearts, diamonds, spades);
		
		if (result == null) {
			result = isFourOfAKind(clubs, hearts, diamonds, spades);
			if (result == null) {
				result = isFullHouse(clubs, hearts, diamonds, spades);
				if (result == null) {
					result= isFlush(clubs, hearts, diamonds, spades);
					if (result == null) {					
						result = isStraight(clubs, hearts, diamonds, spades);
						if (result == null) {
							result = isThreeOfAKind(clubs, hearts, diamonds, spades);
							if (result == null) {
								result = isThreeOfAKind(clubs, hearts, diamonds, spades);
								if (result == null) {
									result = isTwoPair(clubs, hearts, diamonds, spades);
									if (result == null) {
										isPair(clubs, hearts, diamonds, spades);
										if (result == null) {
											List<CardValue> cardValues = getHighestCardValues((clubs | hearts | diamonds | spades), 5); 
											result = new HandResult(HandRank.HIGH_CARD, cardValues);
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return result;
	}
	
	/**
	 * Check for a straight flush by looking for five consecutive 1s in each of our suit bitmaps.
	 * 
	 * @param clubs
	 * @param hearts
	 * @param diamonds
	 * @param spades
	 * @return
	 */
	private HandResult isStraighFlush(int clubs, int hearts, int diamonds, int spades) {
		
		int straight = 0b11111000000000;
	
		int[] suits = {clubs, hearts, diamonds, spades};
		for (int suit : suits ) {
			if (Integer.bitCount(suit) >=5) {
				//If there are any aces, we need to insure we set the "low" ace when evaluating straights.
				suit = (suit & CardValue.ACE.getMask()) > 0?(suit | 0b1):suit;
				for (int index = 14; index > 4; index --) {
					if ((straight & suit) == straight) {
						return new HandResult(HandRank.STRAIGHT_FLUSH, Arrays.asList(
								CardValue.valueToEnum(index), 
								CardValue.valueToEnum(index - 1),
								CardValue.valueToEnum(index - 2),
								CardValue.valueToEnum(index - 3),
								CardValue.valueToEnum(index -4)));
					}
					straight = straight >> 1;
				}
			}
		}
		return null;
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
	private HandResult isFourOfAKind(int clubs, int hearts, int diamonds, int spades) {
		
		//If we logical "and" all four suits, the only time a non-zero number will be present will be when we have the same card in all of the suits.
		int fourOfAKind = clubs & hearts & diamonds & spades;
		
		if (fourOfAKind > 0) {
			//Lookup the card value for our four of a kind.
			CardValue cardValue = CardValue.maskToEnum(fourOfAKind);
			//exclude our four of kind value and then compute the next highest bit for our fifth card. 
			int otherCards =~ fourOfAKind ;
			otherCards = otherCards & (clubs | hearts | diamonds | spades);			
			CardValue fifthValue = CardValue.maskToEnum(Integer.highestOneBit(otherCards));
			return new HandResult(HandRank.FOUR_OF_A_KIND, Arrays.asList(cardValue, cardValue, cardValue, cardValue, fifthValue));
		}
		return null;
	}
	
	private HandResult isFullHouse(int clubs, int hearts, int diamonds, int spades) {

		int threeOfKind = (clubs & hearts & diamonds) | (clubs & hearts & spades) |  (clubs & diamonds & spades) | (hearts & spades & diamonds);
		if (threeOfKind > 0) {
			threeOfKind = Integer.highestOneBit(threeOfKind) ;
			int compliment =~ threeOfKind;
			clubs = clubs & compliment;
			hearts = hearts & compliment;
			diamonds = diamonds & compliment;
			spades = spades & compliment;
			
			int pair = (clubs & hearts) | (clubs & diamonds) | (clubs & spades) |
					(hearts & diamonds) | (hearts & spades) |
					(diamonds & spades);
			if (pair > 0) {
				pair = Integer.highestOneBit(pair);
				CardValue threeOfKindValue = CardValue.maskToEnum(threeOfKind);
				CardValue pairValue = CardValue.maskToEnum(Integer.highestOneBit(pair));
				return new HandResult(HandRank.FULL_HOUSE, Arrays.asList(
						threeOfKindValue,
						threeOfKindValue,
						threeOfKindValue,
						pairValue,
						pairValue));
			}
		}
		return null;
	}
	private HandResult isFlush(int clubs, int hearts, int diamonds, int spades) {
		
		int[] suits = {clubs, hearts, diamonds, spades};
		for (int suit : suits ) {
			if (Integer.bitCount(suit) >= 5) {
				return new HandResult(HandRank.FLUSH, getHighestCardValues(suit, 5));
			}
		}
		return null;
	}

	private HandResult isStraight(int clubs, int hearts, int diamonds, int spades) {

		
		int combined = clubs | hearts | diamonds | spades;


		if (Integer.bitCount(combined) >=5) {

			int straight = 0b11111000000000;

			//If there are any aces, we need to insure we set the "low" ace when evaluating straights.
			combined = (combined & CardValue.ACE.getMask()) > 0?(combined | 0b1):combined;

			for (int index = 14; index > 4; index --) {
				if ((straight & combined) == straight) {
					return new HandResult(HandRank.STRAIGHT, Arrays.asList(
							CardValue.valueToEnum(index), 
							CardValue.valueToEnum(index - 1),
							CardValue.valueToEnum(index - 2),
							CardValue.valueToEnum(index - 3),
							CardValue.valueToEnum(index -4)));
				}
				straight = straight >> 1;
			}
		}
		
		return null;
	}
	
	private HandResult isThreeOfAKind(int clubs, int hearts, int diamonds, int spades) {

		int threeOfKind = (clubs & hearts & diamonds) | (clubs & hearts & spades) |  (clubs & diamonds & spades) | (hearts & spades & diamonds);
		if (threeOfKind > 0) {
			CardValue threeValue = CardValue.maskToEnum(threeOfKind);
			int otherCards =  (clubs | hearts | diamonds | spades) & ~threeOfKind;
			int fourthMask = Integer.highestOneBit(otherCards);
			CardValue fourth = CardValue.maskToEnum(fourthMask);
			CardValue fifth = CardValue.maskToEnum(Integer.highestOneBit(otherCards &~fourthMask));
			
			return new HandResult(HandRank.THREE_OF_A_KIND, Arrays.asList(
					threeValue,
					threeValue,
					threeValue,
					fourth,
					fifth
					));
		}		
		return null;
	}

	private HandResult isTwoPair(int clubs, int hearts, int diamonds, int spades) {
		
		int pair = (clubs & hearts) | (clubs & diamonds) | (clubs & spades) |
				(hearts & diamonds) | (hearts & spades) |
				(diamonds & spades);
		if (Integer.bitCount(pair) == 2) {
			int firstPairMask = Integer.highestOneBit(pair);
			int secondPairMask = Integer.highestOneBit(pair & ~firstPairMask);
			CardValue firstPair = CardValue.maskToEnum(firstPairMask);
			CardValue secondPair = CardValue.maskToEnum(secondPairMask);			
			CardValue fifth = CardValue.maskToEnum(Integer.highestOneBit((clubs | hearts | diamonds | spades) &~pair));
			return new HandResult(HandRank.TWO_PAIR, Arrays.asList(
					firstPair, firstPair,
					secondPair, secondPair,
					fifth));
		}
		return null;
	}
	
	private HandResult isPair(int clubs, int hearts, int diamonds, int spades) {

		int pair = (clubs & hearts) | (clubs & diamonds) | (clubs & spades) |
				(hearts & diamonds) | (hearts & spades) |
				(diamonds & spades);
		if (pair > 0) {
			int pairMask = Integer.highestOneBit(pair);
			int otherCards = (clubs | hearts | diamonds | spades) & ~pairMask;
			
			CardValue pairValue = CardValue.maskToEnum(pairMask);			
			List<CardValue> cardValues = new LinkedList<>(Arrays.asList(pairValue, pairValue));
			cardValues.addAll(getHighestCardValues(otherCards, 3));
			return new HandResult(HandRank.PAIR, cardValues);
		}
		return null;
	}

	/**
	 * This method will return the highest n number of card values where "n" is numberOfValues;
	 * 
	 * NOTE: If there are less bits set then requested, this method will return as many values as present or an empty list if there are no bits set.
	 * 
	 * @param bits bits representing card values as a 14 bit mask (where the least significant bit (low ace) is ignored.
	 * @param numberOfValues
	 * 
	 * 
	 * @return The highest n number of card values represented in the 14 bit number. 
	 */
	private static List<CardValue> getHighestCardValues(int bits, int numberOfValues) {
		int index = numberOfValues;
		List<CardValue> values = new ArrayList<>(numberOfValues);
		while (bits != 0 && index > 0) {
			int mask = Integer.highestOneBit(bits);
			values.add(CardValue.maskToEnum(mask));
			bits = bits & ~mask;
			index--;
		}
		return values;
	}
	/**
	 * We create a per-computed mapping of a 13bit integer (representing the ranks 2-A) mapped up to he max cards
	 * this evaluator can handle.
	 * 
	 * Note: The least significant bit is never used because it represents the "low" ace in a straight. This means an ace
	 *           is always considered Highest
	 * 
	 * 
	 * 
	 * @return a pre-computed map of bit-encoded CardValues to an array of CardValues sorted from greatest to lowest.
	 */
	private static CardValue[][] precomputeCardValues() {
		CardValue[][] cardValuesMap = new CardValue[8192][8];
		for (int index=0; index < 8192;index++) {
			List<CardValue> values = new ArrayList<>(8);
			if (Integer.bitCount(index) > 8) continue;
			for (int enumIndex = CardValue.values().length -1; enumIndex >=0; enumIndex--) {
				CardValue value = CardValue.values()[enumIndex];
				if ((index & value .getMask()) > 0) {
					values.add(value);
				}
			}
			cardValuesMap[index] = values.toArray(new CardValue[values.size()]);
		}
		
		return cardValuesMap;
	}

}
