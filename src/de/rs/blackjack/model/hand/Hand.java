package de.rs.blackjack.model.hand;

import de.rs.blackjack.model.card.AceCard;
import de.rs.blackjack.model.card.Card;
import de.rs.blackjack.model.card.CardDeck;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rene Sommerfeld on 04.03.2018.
 * The Hand class represents an abstract hand of a blackjack game.
 * It holds the players or dealers cards, keeping track of the total
 * score, and whether or not it is bust, a blackjack or any other event.
 *
 */
public abstract class Hand implements Comparable<Hand> {

    /**
     * index of the first card
     */
    public static final int FIRST_CARD = 0;

    /**
     * index of the second card
     */
    public static final int SECOND_CARD = 1;

    /**
     * initial card count before take decisions
     */
    public static final int INITIAL_CARD_COUNT = 2;

    /**
     * perfect hand score
     */
    public static final int PERFECT_SCORE = 21;

    /**
     * holds all cards of this hand
     */
    protected List<Card> cards;

    /**
     * describes how many cards this hand is allowed to draw
     */
    protected int cardsAllowedToDraw;

    public Hand() {
        cards = new ArrayList<>();
        cardsAllowedToDraw = Integer.MAX_VALUE;
    }


    /**
     * Adds a card to this hand but only if its allowed
     * @param card the new card
     */
    public void addCard(Card card) {
        if(isAllowedToDraw()) {
            cardsAllowedToDraw--;
            cards.add(card);
            if(isBust() || hasBlackjack()) {
                cardsAllowedToDraw = 0;
            }
        }
    }

    /**
     * Returns the actual score of this hand
     * @return the score
     */
    public int score() {
        int highScoreAceCount = 0;
        int score = 0;

        //scoring of all cards
        for(Card card : cards) {
            score += card.score();
            if(card instanceof AceCard) {
                AceCard ace = (AceCard)card;
                if(!ace.isLowScore()) {
                    highScoreAceCount++;
                }
            }
        }

        //if the perfect score is already exceeded and the hand
        //is containing aces, then set every ace if necessary
        //to its low score
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
     * Returns the number of cards in this hand
     * @return number of cards
     */
    public int cardCount() {
        return cards.size();
    }

    /**
     * Returns the card on the specified index of this
     * hand
     * @param cardIndex the index of the card
     * @return the card
     */
    public Card getCard(int cardIndex) {
        if(cardIndex >= 0 && cardIndex < cardCount()) {
            return cards.get(cardIndex);
        }
        return null;
    }

    /**
     * Returns whether or not this hand is bust
     * @return if its bust
     */
    public boolean isBust() {
        return score() > PERFECT_SCORE;
    }

    /**
     * Returns whether or not this hand is allowed to draw another
     * cards
     * @return if allow to draw
     */
    public boolean isAllowedToDraw() {
        return cardsAllowedToDraw > 0;
    }

    /**
     * Returns whether or not this hand is a blackjack
     * @return if its a blackjack
     */
    public boolean hasBlackjack() {
        return cardCount() == INITIAL_CARD_COUNT && score() == PERFECT_SCORE;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("\n");
        str.append("Card Count:\t").append(cardCount()).append("\n");
        str.append("####################");
        str.append("\n");
        for(Card card : cards) {
            str.append(card);
            str.append("\n");
        }
        str.append("####################").append("\n");
        str.append("Score:\t").append(score()).append("\n");
        str.append("Cards Allowed To Draw:\t").append(cardsAllowedToDraw).append("\n");
        str.append("Is Hand Bust:\t").append(isBust());
        return str.toString();
    }

    /**
     * Compares this hand against another hand by its score
     * @param otherHand the other hand
     * @return the score difference in thus hands
     */
    @Override
    public int compareTo(Hand otherHand) {
        return score() - otherHand.score();
    }

    /**
     * Helper method. Returns whether or not the first card of the specified hand
     * is an ace.
     * @param hand the hand to check
     * @return if the first card is an ace
     */
    public static boolean isFirstCardAce(Hand hand) {
        return hand.getCard(FIRST_CARD).getValue() == CardDeck.Value.ACE;
    }



}
