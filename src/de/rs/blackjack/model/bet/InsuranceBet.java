package de.rs.blackjack.model.bet;

import de.rs.blackjack.model.hand.Hand;

/**
 * Created by Rene Sommerfeld on 06.03.2018.
 */
public class InsuranceBet extends Bet {

    /**
     * Creates a new bet with a specified amount
     * @param amount the amount to bet
     */
    public InsuranceBet(float amount) {
        super(amount);
    }

    @Override
    public void evaluate(Hand playerHand, Hand dealerHand) {

    }
}
