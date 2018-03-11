package de.rs.blackjack.model.table;



import de.rs.blackjack.model.cards.Card;
import de.rs.blackjack.model.cards.CardDeck;

import java.util.*;

/**
 * Created by Rene Sommerfeld on 04.03.2018.
 *
 * Die CardShoe Klasse repräsentiert den Kartenschuh in dem
 * sich die Kartendecks befinden. Ein Kartenschuh kann mit beliebig
 * vielen Kartendecks (meist aber 6 - 8 Kartendecks) gefüllt werden.
 */
public class CardShoe {

    /**
     * Anzahl an Kartendecks im Kartenschuh
     */
    private int deckCount;

    /**
     * gibt an ob die Karten im Kartenschuh gemischt worden sind
     */
    private boolean shuffled;

    /**
     * der Kartenstapel
     */
    private List<Card> stack;


    /**
     * Creates a new shoe with a specified number of card decks
     * @param deckCount the number of card decks
     */
    public CardShoe(int deckCount) {
        stack = new ArrayList<>(deckCount *
                CardDeck.Value.values().length * CardDeck.Suit.values().length);
        this.deckCount = deckCount;
        shuffled = false;
        populateCards();
    }

    /**
     * Befüllt den Kartenschuh mit der angegebenen Anzahl von Kartendecks
     */
    public void populateCards() {
        //if shoe is already populated with cards, remove all
        stack.clear();
        for(int i = 0; i < deckCount; i++) {
            stack.addAll(new CardDeck());
        }
        shuffled = false;
    }

    /**
     * Mischt die Karten im Kartenschuh
     */
    public void shuffle() {
        if(!shuffled) {
            Collections.shuffle(stack);
            shuffled = true;
        }
    }

    /**
     * Platziert die Cutting Card an einer bestimmten Stelle im Stapel.
     * Die Cutting Card markiert die Stelle an der ein Schuhwechsel gemacht
     * werden muss.
     * @param index der Index an dem die Cutting Card platziert werden soll
     * @param card die zu platzierende Cutting Card
     */
    public void placeCuttingCard(int index, Card card) {
        int cardCount = cardCount();
        if(shuffled && (index >= 0 && index < cardCount)) {
            stack.set(index, card);
        }
    }

    /**
     * Gibt die oberste Karte aus dem Schuh zurück
     * @return oberste Karte, ansonsten null
     */
    public Card dealCard() {
        if(cardCount() != 0) {
            return stack.remove(0);
        }
        return null;
    }

    /**
     * Gibt die verbleibene Anzahl von Karten im Schuh zurück
     * @return verbleibene Kartenanzahl
     */
    public int cardCount() {
        return stack.size();
    }


    /**
     * Gibt eine String Repräsentation des Schuhs zurück
     * @return String Repräsentation des Schuhs
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        int cardCount = cardCount();
        for(int i = 0; i < cardCount; i++) {
            str.append((i + 1));
            str.append(":\t");
            str.append(stack.get(i));
            str.append("\n");
        }
        return str.toString();
    }
}
