package de.rs.blackjack.model;


import java.util.*;

/**
 * Created by Rene Sommerfeld on 04.03.2018.
 *
 *
 */
public class Shoe {

    /**
     * Anzahl der Karten in einem Kartendeck
     */
    public static final int CARDS_PER_DECK = 52;

    /**
     * Anzahl der Decks im Kartenschuh
     */
    private int deckCount;

    /**
     * Enthält alle Kartendecks nach dem Mischen
     * bereit zum Spielen
     */
    private List<Card> playingStack;

    /**
     * Erzeugt einen neuen Schuh mit einer speziellen
     * Anzahl von Kartendecks
     * @param deckCount Anzahl von Kartendecks
     */
    public Shoe(int deckCount) {
        playingStack = new ArrayList<>();
        this.deckCount = deckCount;
        create();
    }

    /**
     * Füllt den Schuh mit der vorgeschriebenen Anzahl von Kartendecks.
     * Alle Kartendecks sind anschließend noch in sortierter Form in
     * dem Kartenschuh
     */
    public void create() {

        //Löscht alle vorhandenen Karten aus der Initialkartenliste
        //und dem Ziehstapel
        playingStack.clear();

        //Fügt die angegebene Anzahl von Kartendecks der
        //Initialkartenliste hinzu
        for(int i = 0; i < deckCount; i++) {
            for(Card.Suit suit : Card.Suit.values()) {
                for(Card.Value value : Card.Value.values()) {
                    playingStack.add(new Card(suit, value));
                }
            }
        }
    }

    /**
     * Mischt den Kartenstapel
     */
    public void shuffle() {
        //mischt den Kartenstapel
        Collections.shuffle(playingStack);
    }

    /**
     * Zieht eine Karte vom Kartenstapel
     * @return die nächste Karte vom Kartenstapel
     */
    public Card dealCard() {
        if(cardCount() == 0) {
            return null;
        }
        return playingStack.remove(playingStack.size() - 1);
    }

    /**
     * Gibt die Anzahl der im Kartenstapel befindlichen Karten zurück
     * @return die Anzahl der vorhandenen Karten
     */
    public int cardCount() {
        return playingStack.size();
    }


    /**
     * Gibt den Schuh in einer String Representation
     * zurück. Gibt alle im Kartenstapel befindlichen Karten aus.
     * @return String Representation des Schuhs
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        int cardCount = cardCount();
        for(int i = cardCount - 1; i >= 0; i--) {
            str.append(cardCount - i);
            str.append(":\t");
            str.append(playingStack.get(i));
            str.append("\n");
        }
        return str.toString();
    }
}
