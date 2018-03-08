package de.rs.blackjack.model.cards;



import java.util.*;

/**
 * Created by Rene Sommerfeld on 04.03.2018.
 *
 * The CardShoe class holds all playing cards of the
 * game.
 */
public class CardShoe {

    /**
     * the number of card decks to play with
     */
    private int deckCount;

    /**
     * describes if the cards in the shoe are shuffled
     */
    private boolean shuffled;

    /**
     * the stack
     */
    private List<Card> stack;


    /**
     * Creates a new shoe with a specified number of card decks
     * @param deckCount the number of card decks
     */
    public CardShoe(int deckCount) {
        stack = new ArrayList<>();
        this.deckCount = deckCount;
        shuffled = false;
        populateCards();
    }

    /**
     * Populates the shoe with the specified number of
     * card decks.
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
     * Shuffles the shoe
     */
    public void shuffle() {
        if(!shuffled) {
            Collections.shuffle(stack);
            shuffled = true;
        }
    }

    /**
     * Places the cutting card at the specified index
     * of the card stack
     * @param index the index to place the cutting card at
     */
    public void placeCuttingCard(int index) {
        int cardCount = cardCount();
        if(shuffled && (index >= 0 && index < cardCount)) {
            stack.set(index, new CuttingCard());
        }
    }

    /**
     * Deals the top card of the stack
     * @return the top card, otherwise null
     */
    public Card dealCard() {
        if(cardCount() != 0) {
            return stack.remove(0);
        }
        return null;
    }

    /**
     * Returns the number of cards remaining
     * @return the number of cards
     */
    public int cardCount() {
        return stack.size();
    }


    /**
     * Returns a string representation of the shoe
     * @return string representation
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
