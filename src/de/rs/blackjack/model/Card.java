package de.rs.blackjack.model;

/**
 * Created by Rene Sommerfeld on 04.03.2018.
 */
public class Card {

    /**
     * Listet die einzelnen Kartenwerte auf. Enthält eine String Representation
     * des Kartenwertes jeder einzelnen Karte.
     */
    public enum Value {
        ACE("A"), TWO("2"), THREE("3"), FOUR("4"), FIVE("5"), SIX("6"), SEVEN("7"), EIGHT("8"),
        NINE("9"), TEN("10"), JACK("J"), QUEEN("Q"), KING("K");

        private String character;

        Value(String character) {
            this.character = character;
        }

    }

    /**
     * Listet alle einzelnen Kartenfarben auf. Enthält eine Character Representation
     * in Unicode für die einzelnen Kartenfarben.
     */
    public enum Suit {
        CLUBS('\u2663'), SPADES('\u2660'), HEARTS('\u2764'), DIAMONDS('\u2666');

        private char character;

        Suit(char character) {
            this.character = character;
        }

    }

    /**
     * beschreibt die Kartenfarbe der Karte
     */
    private Suit suit;

    /**
     * beschreibt den Kartenwert der Farbe
     */
    private Value value;

    /**
     * beschreibt ob die Karte verdeckt ist
     */
    private boolean hidden;

    /**
     * beschreibt ob bei einem Ass der Kartenwert als 11
     * oder 1 gezählt werden soll
     */
    private boolean countedAsOne;

    /**
     * Erzeugt eine neue Karte mit einer Kartenfarbe und
     * einem Kartenwert
     * @param suit die Kartenfarbe
     * @param value der Kartenwert
     */
    public Card(Suit suit, Value value) {
        this.suit = suit;
        this.value = value;
        this.hidden = false;
        this.countedAsOne = true;
    }

    /**
     * Ändert den Kartenwert bei einem Ass von den
     * normalen 11 Punkten zu 1 Punkt und andersherum.
     */
    public void toggleAceValue() {
        countedAsOne = !countedAsOne;
    }

    public boolean isCountedAsOne() {
        return countedAsOne;
    }

    /**
     * Gibt zurück ob es sich bei der Karte um eine Cutting Card hält
     * @return ob es sich um eine Cutting Card hält oder nicht
     */
    public boolean isCuttingCard() {
        return suit == null && value == null;
    }

    /**
     * Gibt zurück ob es sich bei der Karte um ein Ass hält
     * @return ob es sich um ein Ass hält oder nicht
     */
    public boolean isAce() {
        return value == Value.ACE;
    }

    /**
     * Gibt zurück ob die Karte verdeckt gespielt ist
     * @return ob die Karte verdeckt ist
     */
    public boolean isHidden() {
        return hidden;
    }

    /**
     * Gibt die Kartenfarbe der Karte zurück
     * @return die Kartenfarbe
     */
    public Suit getSuit() {
        return suit;
    }

    /**
     * Gibt den Kartenwert der Karte als enum zurück
     * @return den Kartenwert
     */
    public Value getValue() {
        return value;
    }

    /**
     * Gibt den aktuell gezählten Kartenwert zurück
     * @return den aktuellen Kartenwert
     */
    public int getScore() {
        switch(value) {
            case ACE:
                if(countedAsOne) {
                   return value.ordinal() + 1;
                } else {
                    return value.ordinal() + 1 + 10;
                }
            default:
                int score = value.ordinal() + 1;
                return (score >= 10) ? 10 : score;
        }
    }

    /**
     * Gibt die Karte als String Representation zurück
     * @return String Representation der Karte
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        if(suit == null && value == null) {
            str.append("CUTTING CARD");
        } else {
            str.append(value.character);
            str.append("\t");
            str.append(suit.character);
        }
        return str.toString();
    }
}
