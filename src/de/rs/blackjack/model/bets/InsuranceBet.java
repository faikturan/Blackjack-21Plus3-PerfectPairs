package de.rs.blackjack.model.bets;

import de.rs.blackjack.model.hands.Hand;

/**
 * Created by Rene Sommerfeld on 06.03.2018.
 */
public class InsuranceBet extends Bet {

    /**
     * Creates a new bets with a specified amount
     * @param amount the amount to bets
     */
    public InsuranceBet(float amount) {
        super(amount);
    }

    @Override
    public void evaluate(Hand playerHand, Hand dealerHand) {

    }
}
