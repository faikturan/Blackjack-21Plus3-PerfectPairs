package de.rs.blackjack.model.bets;

import de.rs.blackjack.model.hands.Hand;

/**
 * Created by Rene Sommerfeld on 06.03.2018.
 */
public class BlackjackBet extends Bet {

    /**
     * describes the payout multiplier for a blackjack
     */
    public static final float BLACKJACK_PAYOUT = 1.5f;

    /**
     * describes the payout for a normal win against the dealers
     * hands
     */
    public static final float WINNING_PAYOUT = 1.0f;

    /**
     * Creates a new bets with a specified amount
     * @param amount the amount to bets
     */
    public BlackjackBet(float amount) {
        super(amount);
    }

    @Override
    public void evaluate(Hand playerHand, Hand dealerHand) {
        //TODO : adding the case of a hand is already bust
        if(playerHand.hasBlackjack() && !dealerHand.hasBlackjack()) {
            status = Status.WON;
            payout = amount * BLACKJACK_PAYOUT + amount;
        } else if(!playerHand.hasBlackjack() && !dealerHand.hasBlackjack()) {
            if(playerHand.compareTo(dealerHand) > 0) {
                status = Status.WON;
                payout = amount * WINNING_PAYOUT + amount;
            } else if(playerHand.compareTo(dealerHand) == 0) {
                status = Status.WON;
                payout = amount * WINNING_PAYOUT;
            } else {
                status = Status.LOST;
            }
        } else if(playerHand.hasBlackjack() && dealerHand.hasBlackjack()) {
            status = Status.WON;
            payout = amount * WINNING_PAYOUT;
        } else {
            status = Status.LOST;
        }
    }
}
