package de.rs.blackjack.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rene Sommerfeld on 04.03.2018.
 *
 */
public class Hand implements Comparable<Hand> {

    public static final int PERFECT_SCORE = 21;

    private List<Card> cards;

    public Hand() {
        cards = new ArrayList<>();
    }


    public boolean addCard(Card card) {
        if(!isBust()) {
            cards.add(card);
        }
        return score() <= PERFECT_SCORE;
    }


    public void clear() {
        cards.clear();
    }

    public int score() {
        int acesForLessScoreCount = 0;
        int score = 0;

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
            return score();
        }

        return score;
    }

    public int cardCount() {
        return cards.size();
    }


    public boolean isBust() {
        return score() > PERFECT_SCORE;
    }


    public boolean isBlackjack() {
        return cardCount() == 2 && score() == PERFECT_SCORE;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for(Card card : cards) {
            str.append(card);
            str.append("\n");
        }
        return str.toString();
    }

    @Override
    public int compareTo(Hand otherHand) {
        if(!isBlackjack() && !otherHand.isBlackjack()) {
            return score() - otherHand.score();
        } else if(isBlackjack() && !otherHand.isBlackjack()) {
            return 1;
        } else if(!isBlackjack() && otherHand.isBlackjack()) {
            return -1;
        } else {
            return 0;
        }
    }
}
