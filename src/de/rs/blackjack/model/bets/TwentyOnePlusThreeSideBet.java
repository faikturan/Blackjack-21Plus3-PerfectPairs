package de.rs.blackjack.model.bets;

import de.rs.blackjack.model.cards.Card;
import de.rs.blackjack.model.hands.Hand;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Rene Sommerfeld on 06.03.2018.
 */
public class TwentyOnePlusThreeSideBet extends SideBet {

    public static final String NAME_SUITED_TRIPS = "Suited Trips";
    public static final float SUITED_TRIPS_PAYOUT = 100; // 100 : 1
    public static final String NAME_STRAIGHT_FLUSH = "Straight Flush";
    public static final float STRAIGHT_FLUSH_PAYOUT = 40; // 40 : 1
    public static final String NAME_TRIPS = "Trips";
    public static final float TRIPS_PAYOUT = 30; // 30 : 1
    public static final String NAME_STRAIGHT = "Straight";
    public static final float STRAIGHT_PAYOUT = 10; // 10 : 1
    public static final String NAME_FLUSH = "Flush";
    public static final float FLUSH_PAYOUT = 5; // 5 : 1

    public TwentyOnePlusThreeSideBet(float amount) {
        super(amount);
    }

    @Override
    public void evaluate(Hand playerHand, Hand dealerHand) {
        Card playerFirstCard = playerHand.getCard(0);
        Card playerSecondCard = playerHand.getCard(1);
        Card dealerOpenCard = dealerHand.getCard(0);

        //suited trips
        if(playerFirstCard.matches(playerSecondCard) && playerSecondCard.matches(dealerOpenCard)) {
            status = Status.WON;
            payout = amount * SUITED_TRIPS_PAYOUT + amount;
            return;
        }

        //straight flush
        boolean consecutive = checkConsecutive(playerFirstCard, playerSecondCard, dealerOpenCard);
        boolean flush = checkFlush(playerFirstCard, playerSecondCard, dealerOpenCard);

        if(consecutive && flush) {
            status = Status.WON;
            payout = amount * STRAIGHT_FLUSH_PAYOUT + amount;
            return;
        }

        //trips
        if(playerFirstCard.matchesValue(playerSecondCard) && playerSecondCard.matchesValue(dealerOpenCard)) {
            status = Status.WON;
            payout = amount * TRIPS_PAYOUT + amount;
            return;
        }

        //straight
        if(consecutive) {
            //straight
            status = Status.WON;
            payout = amount * STRAIGHT_PAYOUT + amount;
            return;
        }


        //flush
        if(flush) {
            status = Status.WON;
            payout = amount * FLUSH_PAYOUT + amount;
            return;
        }

        //no twenty one plus three side bets condition apply
        status = Status.LOST;
    }

    private boolean checkFlush(Card playerFirstCard, Card playerSecondCard, Card dealerOpenCard) {
        return playerFirstCard.matchesSuit(playerSecondCard) && playerSecondCard.matchesSuit(dealerOpenCard);
    }

    private boolean checkConsecutive(Card playerFirstCard, Card playerSecondCard, Card dealerOpenCard) {
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(playerFirstCard);
        cards.add(playerSecondCard);
        cards.add(dealerOpenCard);
        Collections.sort(cards);

        for(int i = 0; i < cards.size() - 1; i++) {
            //if both cards aren't consecutive
            if(cards.get(i).compareTo(cards.get(i + 1)) != -1) {
                return false;
            }
        }

        //all cards are consecutive
        return true;
    }
}
