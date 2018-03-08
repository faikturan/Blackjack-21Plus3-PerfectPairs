package de.rs.blackjack.model.bets;

import de.rs.blackjack.model.hands.Hand;

/**
 * Created by Rene Sommerfeld on 06.03.2018.
 *
 * The Bet class represents an abstract bets a player can occupy.
 * It keeps track of its amount, whether or not it is won by the
 * player and how much the payout is going to be.
 */
public abstract class Bet {

    /**
     * all status a bets can have
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
     * the status of this bets
     */
    protected Status status;

    /**
     * Creates a new bets with a specified amount
     * @param amount the amount to bets
     */
    public Bet(float amount) {
        this.amount = amount;
        this.status = Status.NONE;
    }

    /**
     * Returns whether or not the bets is won
     * @return if its won
     */
    public boolean isWon() {
        return status == Status.WON;
    }

    /**
     * Returns whether or not the bets is lost
     * @return if its lost
     */
    public boolean isLost() {
        return status == Status.LOST;
    }

    /**
     * Returns whether or not the bets is evaluated
     * @return if its evaluated
     */
    public boolean isEvaluated() {
        return status != Status.NONE;
    }

    /**
     * Returns the payout if this bets is evaluated to a winning status
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
     * Evaluates the bets.
     * @param playerHand the hands of the player
     * @param dealerHand the hands of the dealer
     */
    public abstract void evaluate(Hand playerHand, Hand dealerHand);

}
