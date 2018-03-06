package de.rs.blackjack.model.bet;

import de.rs.blackjack.model.hand.Hand;

/**
 * Created by Rene Sommerfeld on 06.03.2018.
 *
 * The Bet class represents an abstract bet a player can take.
 * It keeps track of its amount, whether or not it is won by the
 * player and how much the payout is going to be.
 */
public abstract class Bet {

    /**
     * all status a bet can have
     */
    public enum Status {
        NONE, WON, LOST
    }

    /**
     * the amount a player bets
     */
    protected float amount;

    /**
     * the payout if the player wins
     */
    protected float payout;

    /**
     * the status of this bet
     */
    protected Status status;

    /**
     * Creates a new bet with a specified amount
     * @param amount the amount to bet
     */
    public Bet(float amount) {
        this.amount = amount;
        this.status = Status.NONE;
    }

    /**
     * Returns whether or not the bet is won
     * @return if its won
     */
    public boolean isWon() {
        return status == Status.WON;
    }

    /**
     * Returns whether or not the bet is lost
     * @return if its lost
     */
    public boolean isLost() {
        return status == Status.LOST;
    }

    /**
     * Returns whether or not the bet is evaluated
     * @return if its evaluated
     */
    public boolean isEvaluated() {
        return status != Status.NONE;
    }

    /**
     * Returns the payout if this bet is evaluated to a winning status
     * @return the payout
     */
    public float payout() {
        if(status == Status.NONE || status == Status.LOST) {
            return 0;
        } else {
            return payout;
        }
    }

    /**
     * Evaluates the bet.
     * @param playerHand the hand of the player
     * @param dealerHand the hand of the dealer
     */
    public abstract void evaluate(Hand playerHand, Hand dealerHand);

}
