package de.rs.blackjack.model.cards;

/**
 * Created by Rene Sommerfeld on 06.03.2018.
 */
public class ValueCard extends Card {


    public ValueCard(CardDeck.Suit suit, CardDeck.Value value) {
        super(suit, value);
    }

    @Override
    public int score() {
        return value.ordinal() + 1;
    }
}
