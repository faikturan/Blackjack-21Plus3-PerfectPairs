package de.rs.blackjack.model.bet;

import de.rs.blackjack.model.hand.Hand;

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
     * hand
     */
    public static final float WINNING_PAYOUT = 1.0f;

    /**
     * Creates a new bet with a specified amount
     * @param amount the amount to bet
     */
    public BlackjackBet(float amount) {
        super(amount);
    }

    /**
     * Evaluates the bet of the player by taking a look at the score
     * of the players hand and the dealers hand.
     * @param playerHand the hand of the player
     * @param dealerHand the hand of the dealer
     */
    @Override
    public void evaluate(Hand playerHand, Hand dealerHand) {
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
