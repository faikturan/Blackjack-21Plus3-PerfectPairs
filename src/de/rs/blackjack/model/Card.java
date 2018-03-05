package de.rs.blackjack.model;

/**
 * Created by Rene Sommerfeld on 04.03.2018.
 */
public class Card {

    public enum Value {
        ACE("A"), TWO("2"), THREE("3"), FOUR("4"), FIVE("5"), SIX("6"), SEVEN("7"), EIGHT("8"),
        NINE("9"), TEN("10"), JACK("J"), QUEEN("Q"), KING("K");

        private String character;

        Value(String character) {
            this.character = character;
        }

    }

    public enum Suit {
        CLUBS('\u2663'), SPADES('\u2660'), HEARTS('\u2764'), DIAMONDS('\u2666');

        private char character;

        Suit(char character) {
            this.character = character;
        }

    }

    private Suit suit;

    private Value value;

    private boolean hidden;

    private boolean countedAsOne;

    public Card(Suit suit, Value value) {
        this.suit = suit;
        this.value = value;
        this.hidden = false;
        this.countedAsOne = true;
    }

    public void toggleAceValue() {
        countedAsOne = !countedAsOne;
    }

    public boolean isCountedAsOne() {
        return countedAsOne;
    }

    public boolean isCuttingCard() {
        return suit == null && value == null;
    }


    public boolean isAce() {
        return value == Value.ACE;
    }

    public boolean isHidden() {
        return hidden;
    }

    public Suit getSuit() {
        return suit;
    }

    public Value getValue() {
        return value;
    }

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
