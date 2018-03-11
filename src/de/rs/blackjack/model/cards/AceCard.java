package de.rs.blackjack.model.cards;

/**
 * Created by Rene Sommerfeld on 06.03.2018.
 *
 * Die AceCard Klasse repräsentiert eine spezialisierte Form einer Karte.
 * Hierbei handelt es sich um ein Ass. Da das Ass in seinem Score variieren
 * kann, liefert diese Klasse Methoden um das im Score als 1 oder 11 zu zählen.
 */
public class AceCard extends Card {

    /**
     * der niedrige Score eines Ass
     */
    private static final int LOW_SCORE = 1;

    /**
     * der hohe Score eines Ass
     */
    private static final int HIGH_SCORE = 11;

    /**
     * gibt an ob es sich um ein Ass mit niedrigen Score handelt
     */
    private boolean lowScore;

    /**
     * Erzeugt eine Ass Karte in einer bestimmten Farbe
     * @param suit die Kartenfarbe
     */
    public AceCard(CardDeck.Suit suit) {
        super(suit, CardDeck.Value.ACE);
    }

    /**
     * Setzt ob es sich um ein Ass mit niedrigen Score handelt
     * @param lowScore ob es sich um niedrigen Score handelt
     */
    public void setLowScore(boolean lowScore) {
        this.lowScore = lowScore;
    }

    /**
     * Gibt zurück ob es sich um ein Ass mit niedrigen Score handelt
     * @return ob niedriger Score
     */
    public boolean isLowScore() {
        return lowScore;
    }

    /**
     * Gibt den Score der Karte zurück
     * @return den Score
     */
    @Override
    public int getScore() {
        return (lowScore) ? LOW_SCORE : HIGH_SCORE;
    }
}
