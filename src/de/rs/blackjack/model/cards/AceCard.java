package de.rs.blackjack.model.cards;

/**
 * Created by Rene Sommerfeld on 06.03.2018.
 */
public class AceCard extends Card {

    public static final int LOW_SCORE = 1;
    public static final int HIGH_SCORE = 11;

    private boolean lowScore;

    public AceCard(CardDeck.Suit suit) {
        super(suit, CardDeck.Value.ACE);
    }

    public void setLowScore(boolean lowScore) {
        this.lowScore = lowScore;
    }

    public boolean isLowScore() {
        return lowScore;
    }

    @Override
    public int score() {
        return (lowScore) ? LOW_SCORE : HIGH_SCORE;
    }
}
