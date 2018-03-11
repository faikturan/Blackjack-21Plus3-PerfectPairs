package de.rs.blackjack.model.cards;


/**
 * Created by Rene Sommerfeld on 04.03.2018.
 *
 * Die CuttingCard Klasse repräsentiert eine spezialisierte Form einer Karte.
 * Hierbei handelt es sich um eine Markierungskarte, die einen Schuhwechsel signalisiert.
 */
public class CuttingCard extends Card {

    /**
     * Score der Cutting Card
     */
    private static final int SCORE = Integer.MIN_VALUE;

    /**
     * Erzeugt eine Cutting Card. Eine Cutting Card hat keine
     * Kartenfarbe und keinen Kartenwert.
     */
    public CuttingCard() {
        super(null, null);
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
