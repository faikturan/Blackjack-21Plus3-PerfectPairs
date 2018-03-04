package de.rs.blackjack.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rene Sommerfeld on 04.03.2018.
 *
 * The Hand class represents a player or dealers hand.
 * It contains all cards of one hand.
 */
public class Hand {

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
        int aceCount = 0;
        int score = 0;

        //adding up the card values
        for(Card card : cards) {
            switch(card.getValue()) {
                case ACE:
                    aceCount++;
                    score += 11;
                    break;
                default:
                    score += card.getScore();
            }
        }

        //counts every ace as a one if the hand is already bust
        while(score > 21 && aceCount > 0) {
            score -= 10;
            aceCount--;
        }
        return score;
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
}
