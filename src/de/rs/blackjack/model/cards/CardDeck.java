package de.rs.blackjack.model.cards;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Rene Sommerfeld on 06.03.2018.
 *
 * Die CardDeck Klasse repräsentiert ein Kartendeck mit
 * 52 Spielkarten von A bis K in den Farben Kreuz, Pik, Herz
 * und Karo.
 */
public class CardDeck extends ArrayList<Card> {

    /**
     * die einzelnen Kartenwerte
     */
    public enum Value {
        ACE("A"), TWO("2"), THREE("3"), FOUR("4"), FIVE("5"), SIX("6"), SEVEN("7"), EIGHT("8"),
        NINE("9"), TEN("10"), JACK("J"), QUEEN("Q"), KING("K");

        private String character;

        Value(String character) {
            this.character = character;
        }

        public String getCharacter() {
            return character;
        }
    }

    /**
     * die einzelnen Kartenfarben
     */
    public enum Suit {
        CLUBS('\u2663', Color.BLACK), SPADES('\u2660', Color.BLACK),
        HEARTS('\u2764', Color.RED), DIAMONDS('\u2666', Color.RED);

        public enum Color {
            BLACK, RED
        }

        private char character;
        private Color color;

        Suit(char character, Color color) {
            this.character = character;
            this.color = color;
        }

        public char getCharacter() {
            return character;
        }

        public Color getColor() {
            return color;
        }
    }

    /**
     * Erzeugt ein Kartendeck
     */
    public CardDeck() {
        for(Suit suit : Suit.values()) {
            for(Value value : Value.values()) {
                add(createCard(suit, value));
            }
        }
    }

    /**
     * Erzeugt die jeweilige Kartenklasse mit einem bestimmten
     * Kartenwert und Kartenfarbe.
     * @param suit die Kartenfarbe
     * @param value der Kartenwert
     * @return gibt das erzeugte Kartenobjekt zurück
     */
    private static Card createCard(Suit suit, Value value) {
        switch(value) {
            case JACK:
            case QUEEN:
            case KING:
                return new FaceCard(suit, value);
            case ACE:
                return new AceCard(suit);
            default:
                return new ValueCard(suit, value);
        }
    }

    public static Card createRandom() {
        Random r = new Random();
        return createCard(Suit.values()[r.nextInt(Suit.values().length)], Value.values()[r.nextInt(Value.values().length)]);
    }

}
