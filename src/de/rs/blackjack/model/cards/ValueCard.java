package de.rs.blackjack.model.cards;

/**
 * Created by Rene Sommerfeld on 06.03.2018.
 *
 * Die ValueCard Klasse repräsentiert eine spezialisierte Form einer Karte.
 * Hierbei handelt es sich um die Karten 2 bis 10, die keinen speziellen Score
 * annehmen. Die Wertigkeit der Karte ist gleich der Score der Karte.
 */
public class ValueCard extends Card {


    /**
     * Erzeugt eine neue ValueCard mit einer bestimmten Kartenfarbe und
     * Kartenwert
     * @param suit die Kartenfarbe
     * @param value der Kartenwert
     */
    public ValueCard(CardDeck.Suit suit, CardDeck.Value value) {
        super(suit, value);
    }

    /**
     * Gibt den Score der Karte zurück
     * @return den Score
     */
    @Override
    public int getScore() {
        return getValue().ordinal() + 1;
    }
}
