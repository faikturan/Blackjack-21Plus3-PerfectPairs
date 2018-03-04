package de.rs.blackjack.model;

/**
 * Created by Rene Sommerfeld on 04.03.2018.
 */
public class Card {

    public static enum Value {
        ACE("A"), TWO("2"), THREE("3"), FOUR("4"), FIVE("5"), SIX("6"), SEVEN("7"), EIGHT("8"),
        NINE("9"), TEN("10"), JACK("J"), QUEEN("Q"), KING("K");

        private String character;

        private Value(String character) {
            this.character = character;
        }

    }

    public static enum Suit {
        CLUBS('\u2663'), SPADES('\u2660'), HEARTS('\u2764'), DIAMONDS('\u2666');

        private char character;

        private Suit(char character) {
            this.character = character;
        }

    }

    private Suit suit;
    private Value value;

    public Card(Suit suit, Value value) {
        this.suit = suit;
        this.value = value;
    }

    public boolean isCuttingCard() {
        return suit == null && value == null;
    }

    public boolean isAce() {
        return value == Value.ACE;
    }

    public Suit getSuit() {
        return suit;
    }

    public Value getValue() {
        return value;
    }

    public int getScore() {
        int score = value.ordinal() + 1;
        return (score >= 10) ? 10 : score;
    }

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
