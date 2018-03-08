package de.rs.blackjack.model.bets;

import de.rs.blackjack.model.hands.Hand;

/**
 * Created by Rene Sommerfeld on 06.03.2018.
 */
public class InsuranceBet extends Bet {

    /**
     * the payout multiplier for an insurance bet
     */
    public static final float INSURANCE_PAYOUT = 2.0f;

    /**
     * Creates a new bets with a specified amount
     * @param amount the amount to bets
     */
    public InsuranceBet(float amount) {
        super(amount);
    }

    @Override
    public void evaluate(Hand playerHand, Hand dealerHand) {
        if(dealerHand.hasBlackjack()) {
            status = Status.WON;
            payout = amount * INSURANCE_PAYOUT + amount;
        } else {
            status = Status.LOST;
        }
    }

    public static InsuranceBet create(Bet blackjackBet) {
        return new InsuranceBet(blackjackBet.getAmount() * 0.5f);
    }

}
