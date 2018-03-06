package de.rs.blackjack.model.cards;


/**
 * Created by Rene Sommerfeld on 04.03.2018.
 *
 *
 *
 */
public class CuttingCard extends Card {

    public static final int SCORE = -1;

    public CuttingCard() {
        super(null, null);
    }

    @Override
    public int score() {
        return SCORE;
    }
}
