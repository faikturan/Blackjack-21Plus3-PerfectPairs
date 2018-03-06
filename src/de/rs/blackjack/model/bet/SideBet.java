package de.rs.blackjack.model.bet;

/**
 * Created by Rene Sommerfeld on 06.03.2018.
 */
public abstract class SideBet extends Bet {

    protected String name;

    public SideBet(float amount) {
        super(amount);
    }

    public String getName() {
        return name;
    }

}
