package de.rs.blackjack.model.hands;

import de.rs.blackjack.model.cards.AceCard;
import de.rs.blackjack.model.cards.Card;
import de.rs.blackjack.model.cards.CardDeck;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rene Sommerfeld on 04.03.2018.
 * The Hand class represents an abstract hands of a blackjack game.
 * It holds the table or dealers cards, keeping track of the total
 * getScore, and whether or not it is bust, a blackjack or any other event.
 *
 */
public abstract class Hand implements Comparable<Hand> {

    public static final String SCORE_STRING_BUST = "BU";
    public static final String SCORE_STRING_BLACKJACK = "BJ";

    /**
     * index of the first cards
     */
    public static final int FIRST_CARD = 0;

    /**
     * index of the second cards
     */
    public static final int SECOND_CARD = 1;

    /**
     * initial cards count before occupy decisions
     */
    public static final int INITIAL_CARD_COUNT = 2;

    /**
     * perfect hands getScore
     */
    public static final int PERFECT_SCORE = 21;

    /**
     * holds all cards of this hands
     */
    protected List<Card> cards;


    public Hand() {
        cards = new ArrayList<>();
    }


    /**
     * Adds a card to this hands
     * @param card the new card
     * @return returns whether or not the card is added
     */
    public boolean addCard(Card card) {
        return cards.add(card);
    }

    public void clear() {
        cards.clear();
    }

    /**
     * Returns the actual getScore of this hands
     * @return the getScore
     */
    public int score() {
        int highScoreAceCount = 0;
        int score = 0;

        for(Card card : cards) {
            if(!card.isFlipped()) {
                score += card.getScore();
                if (card instanceof AceCard) {
                    AceCard ace = (AceCard) card;
                    if (!ace.isLowScore()) {
                        highScoreAceCount++;
                    }
                }
            }
        }


        if(score > PERFECT_SCORE && highScoreAceCount > 0) {
            for(int i = cards.size() - 1; i >= 0; i--) {
                Card card = cards.get(i);
                if(card instanceof AceCard) {
                    AceCard ace = (AceCard)card;
                    if(!ace.isLowScore()) {
                        ace.setLowScore(true);
                        return score();
                    }
                }
            }
        }

        return score;
    }

    /**
     * Returns the number of cards in this hands
     * @return number of cards
     */
    public int getCardCount() {
        return cards.size();
    }


    /**
     * Returns the cards on the specified index of this
     * hands
     * @param cardIndex the index of the cards
     * @return the cards
     */
    public Card getCard(int cardIndex) {
        if(cardIndex >= 0 && cardIndex < getCardCount()) {
            return cards.get(cardIndex);
        }
        return null;
    }

    /**
     * Returns whether or not this hands is bust
     * @return if its bust
     */
    public boolean isBust() {
        return score() > PERFECT_SCORE;
    }

    /**
     * Returns whether or not this hands is a blackjack
     * @return if its a blackjack
     */
    public boolean hasBlackjack() {
        return getCardCount() == INITIAL_CARD_COUNT && score() == PERFECT_SCORE;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("\n");
        str.append("Card Count:\t").append(getCardCount()).append("\n");
        str.append("####################");
        str.append("\n");
        for(Card card : cards) {
            str.append(card);
            str.append("\n");
        }
        str.append("####################").append("\n");
        str.append("Score:\t").append(score()).append("\n");
        str.append("Is Hand Bust:\t").append(isBust());
        return str.toString();
    }

    public String getScoreString() {
        if(!isBust()) {
            if(hasBlackjack()) {
                return SCORE_STRING_BLACKJACK;
            } else {
                return String.valueOf(score());
            }
        } else {
            return SCORE_STRING_BUST;
        }
    }

    /**
     * Compares this hands against another hands by its getScore
     * @param otherHand the other hands
     * @return the getScore difference in thus hands
     */
    @Override
    public int compareTo(Hand otherHand) {
        return score() - otherHand.score();
    }

    /**
     * Helper method. Returns whether or not the first cards of the specified hands
     * is an ace.
     * @param hand the hands to check
     * @return if the first cards is an ace
     */
    public static boolean isFirstCardAce(Hand hand) {
        return hand.getCard(FIRST_CARD).getValue() == CardDeck.Value.ACE;
    }


    public static Card initialFirstCard(Hand hand) {
        return initialCard(hand, FIRST_CARD);
    }

    public static Card initialSecondCard(Hand hand) {
        return initialCard(hand, SECOND_CARD);
    }

    private static Card initialCard(Hand hand, int index) {
        if(hand != null) {
            if(hand.getCardCount() >= index + 1) {
                return hand.getCard(index);
            }
        }
        return null;
    }


}
