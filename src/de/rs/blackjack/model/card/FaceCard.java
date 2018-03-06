package de.rs.blackjack.model.card;

/**
 * Created by Rene Sommerfeld on 06.03.2018.
 */
public class FaceCard extends Card {

    public static final int SCORE = 10;

    public FaceCard(CardDeck.Suit suit, CardDeck.Value value) {
        super(suit, value);
    }

    @Override
    public int score() {
        return SCORE;
    }
}
