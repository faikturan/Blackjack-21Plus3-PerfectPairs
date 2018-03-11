package de.rs.blackjack.model.cards;

/**
 * Created by Rene Sommerfeld on 06.03.2018.
 *
 * Die FaceCard Klasse repräsentiert eine spezialisierte Form einer Karte.
 * Hierbei handelt es sich um die Karten Bube, Dame und König, die als sogenannte
 * Face-Karten einen Score von 10 haben.
 */
public class FaceCard extends Card {

    /**
     * der Score einer FaceCard
     */
    private static final int SCORE = 10;

    /**
     * Erzeugt eine neue FaceCard mit einer bestimmten Kartenfarbe und
     * Kartenwert
     * @param suit die Kartenfarbe
     * @param value der Kartenwert
     */
    public FaceCard(CardDeck.Suit suit, CardDeck.Value value) {
        super(suit, value);
    }

    /**
     * Gibt den Score der Karte zurück
     * @return den Score
     */
    @Override
    public int getScore() {
        return SCORE;
    }
}
