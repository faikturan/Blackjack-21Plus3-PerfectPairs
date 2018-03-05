package de.rs.blackjack.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rene Sommerfeld on 04.03.2018.
 *
 * The Hand class represents a player or dealers hand.
 * It contains all cards of one hand.
 */
public class Hand implements Comparable<Hand> {

    /**
     * contains all cards of one hand
     */
    private List<Card> cards;

    public Hand() {
        cards = new ArrayList<>();
    }

    /**
     * Adds a card to the hand and returns whether or not
     * the hand is below or on a score of 21.
     * @param card the card to add
     * @return whether or not the hand has a score
     * of 21 or below
     */
    public boolean addCard(Card card) {
        cards.add(card);
        return calculateScore() <= 21;
    }

    /**
     * Clears the hand
     */
    public void clear() {
        cards.clear();
    }

    /**
     * Calculates the score of the hand
     * @return the score of the hand
     */
    public int calculateScore() {
        int acesForLessScoreCount = 0;
        int score = 0;

        //adding up the card values
        for(Card card : cards) {
            score += card.getScore();
            if(card.getValue() == Card.Value.ACE && !card.isCountedAsOne()) {
                acesForLessScoreCount++;
            }
        }

        if(score > 21 && acesForLessScoreCount > 0) {
            for(Card card : cards) {
                card.toggleAceValue();
            }
            return calculateScore();
        }

        return score;
    }

    public int cardCount() {
        return cards.size();
    }

    /**
     * Returns whether or not this hand is bust
     * @return if the hand is bust
     */
    public boolean isBust() {
        return calculateScore() > 21;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for(Card card : cards) {
            System.out.println(card);
        }
        return str.toString();
    }

    @Override
    public int compareTo(Hand o) {
        //writing compare to another hand
        return 0;
    }
}
