package de.rs.blackjack.model.cards;

/**
 * Created by Rene Sommerfeld on 04.03.2018.
 *
 * Die Card Klasse repräsentiert eine abstrakte Form einer Karte.
 * Jede Karte muss mit einer Farbe und einer Wertigkeit erzeugt werden.
 * Eine Karte ist mit einer anderen Karte in ihrer Rangfolge im Kartendeck
 * vergleichbar.
 */
public abstract class Card implements Comparable<Card> {

    /**
     * Farbe der Karte
     */
    private CardDeck.Suit suit;

    /**
     * Wert der Karte
     */
    private CardDeck.Value value;

    /**
     * gibt an ob die Karte verdeckt oder offen gelegt ist
     */
    private boolean flipped;

    /**
     * Erzeugt eine neue Karte mit einer bestimmten Farbe und Wert
     * @param suit die Farbe der Karte
     * @param value der Wert der Karte
     */
    public Card(CardDeck.Suit suit, CardDeck.Value value) {
        this.suit = suit;
        this.value = value;
        flipped = false;
    }

    /**
     * Gibt die Farbe der Karte zurück
     * @return die Farbe
     */
    public CardDeck.Suit getSuit() {
        return suit;
    }

    /**
     * Gibt den Wert der Karte zurück
     * @return den Wert
     */
    public CardDeck.Value getValue() {
        return value;
    }

    /**
     * Gibt zurück ob die Karte offen oder verdeckt gelegt ist
     * @return ob offen oder verdeckt
     */
    public boolean isFlipped() {
        return flipped;
    }

    /**
     * Gibt den Score der Karte während des Spiels zurück
     * @return den Score der Karte
     */
    public abstract int getScore();

    /**
     * Vergleicht zwei Karten an der Rangfolge im Deck
     * @param otherCard zu vergleichende Karte
     * @return wenn -1 dann ist diese Karte hinter der zu vergleichenden Karte
     *         in der Rangfolge
     *         wenn 1 dann ist diese Karte vor der zu vergleichenden Karte
     *         in der Rangfolge
     *         wenn 0 dann sind beide Karten in der Rangfolge gleich
     */
    @Override
    public int compareTo(Card otherCard) {
        CardDeck.Value otherCardValue = otherCard.getValue();
        if(otherCardValue != null) {
            return value.ordinal() - otherCardValue.ordinal();
        }
        return 0;
    }

    /**
     * Gibt eine String Repräsentation der Karte zurück.
     * @return String Repräsentation der Karte
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        if(!flipped) {
            str.append(suit.getCharacter());
            str.append(" ");
            str.append(value.getCharacter());
        } else {
            str.append("[HIDDEN]");
        }
        return str.toString();
    }

    /**
     * Verdeckt die übergebene Karte
     * @param card die Karte
     * @return die verdeckte Karte
     */
    public static Card putFaceDown(Card card) {
        if(!card.isFlipped()) {
            card.flipped = true;
        }
        return card;
    }

    /**
     * Deckt die übergebene Karte auf
     * @param card die Karte
     * @return die aufgedeckte Karte
     */
    public static Card putFaceUp(Card card) {
        if(card.isFlipped()) {
            card.flipped = false;
        }
        return card;
    }

    /**
     * Klasse zum Vergleichen der Eigenschaften von ein oder mehreren Karten
     */
    public static final class Matcher {

        /**
         * Gibt zurück ob es sich bei den übergebenen Karten um gleiche Karten handelt
         * @param cardsToMatch die Karten
         * @return true wenn alle Karten gleich, false wenn nicht
         */
        public static boolean matches(Card... cardsToMatch) {
            for(int i = 0; i < cardsToMatch.length - 1; i++) {
                if(!Card.Matcher.matchesSuit(cardsToMatch[i], cardsToMatch[i + 1]) ||
                        !Card.Matcher.matchesValue(cardsToMatch[i], cardsToMatch[i + 1])) {
                    return false;
                }
            }
            return true;
        }

        /**
         * Gibt zurück ob es sich bei den übergebenen Karten um Karten mit gleichem Wert handelt
         * @param cardsToMatch die Karten
         * @return true wenn alle Werte der Karten gleich, false wenn nicht
         */
        public static boolean matchesValue(Card... cardsToMatch) {
            for(int i = 0; i < cardsToMatch.length - 1; i++) {
                if(!Card.Matcher.matchesValue(cardsToMatch[i], cardsToMatch[i + 1])) {
                    return false;
                }
            }
            return true;
        }

        /**
         * Gibt zurück ob es sich bei den zwei übergebenen Karten um Karten mit gleichem Wert handelt
         * @param card die erste Karte
         * @param otherCard die zweite Karte
         * @return true wenn alle Werte der Karten gleich, false wenn nicht
         */
        public static boolean matchesValue(Card card, Card otherCard) {
            if(!(card instanceof CuttingCard) && !(otherCard instanceof CuttingCard)) {
                return card.value == otherCard.getValue();
            }
            return false;
        }

        /**
         * Gibt zurück ob es sich bei den übergebenen Karten um Karten mit gleicher Farbe handelt
         * @param cardsToMatch die Karten
         * @return true wenn alle Farben der Karten gleich, false wenn nicht
         */
        public static boolean matchesSuit(Card... cardsToMatch) {
            for(int i = 0; i < cardsToMatch.length - 1; i++) {
                if(!Card.Matcher.matchesValue(cardsToMatch[i], cardsToMatch[i + 1])) {
                    return false;
                }
            }
            return true;
        }

        /**
         * Gibt zurück ob es sich bei den zwei übergebenen Karten um Karten mit gleicher Farbe handelt
         * @param card die erste Karte
         * @param otherCard die zweite Karte
         * @return true wenn alle Farben der Karten gleich, false wenn nicht
         */
        public static boolean matchesSuit(Card card, Card otherCard) {
            if(!(card instanceof CuttingCard) && !(otherCard instanceof CuttingCard)) {
                return card.suit == otherCard.getSuit();
            }
            return false;
        }

        /**
         * Gibt zurück ob es sich bei den zwei übergebenen Karten um Karten mit gleicher Symbolfarbe handelt
         * @param card die erste Karte
         * @param otherCard die zweite Karte
         * @return true wenn alle Symbolfarben der Karten gleich, false wenn nicht
         */
        public static boolean matchesColor(Card card, Card otherCard) {
            if(!(card instanceof CuttingCard) && !(otherCard instanceof CuttingCard)) {
                return card.suit.getColor() == otherCard.getSuit().getColor();
            }
            return false;
        }

        /**
         * Gibt zurück ob es sich bei den zwei übergebenen Karten um zwei aufeinander folgende Karten
         * handelt
         * @param firstCard die erste Karte
         * @param secondCard die zweite Karte
         * @return true wenn aufeinander folgend, false wenn nicht
         */
        public static boolean isConsecutive(Card firstCard, Card secondCard) {
            if(firstCard != null && secondCard != null) {
                return firstCard.compareTo(secondCard) == -1;
            }
            return false;
        }
    }



}
