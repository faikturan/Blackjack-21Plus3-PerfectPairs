package de.rs.blackjack.model.card;


import de.rs.blackjack.model.card.Card;
import de.rs.blackjack.model.card.CardDeck;
import de.rs.blackjack.model.card.CuttingCard;

import java.util.*;

/**
 * Created by Rene Sommerfeld on 04.03.2018.
 *
 *
 */
public class Shoe {

    private int deckCount;
    private boolean shuffled;

    private List<Card> stack;


    public Shoe(int deckCount) {
        stack = new ArrayList<>();
        this.deckCount = deckCount;
        shuffled = false;
        create();

    }

    public void create() {
        stack.clear();
        for(int i = 0; i < deckCount; i++) {
            stack.addAll(new CardDeck());
        }
    }

    public void shuffle() {
        if(!shuffled) {
            Collections.shuffle(stack);
            shuffled = true;
        }
    }

    public void placeCuttingCard(int index) {
        int cardCount = cardCount();
        if(shuffled && (index >= 0 && index < cardCount)) {
            stack.set((cardCount - 1) - index, new CuttingCard());
        }
    }

    public Card deal() {
        if(cardCount() == 0) {
            return null;
        }
        return stack.remove(stack.size() - 1);
    }

    public int cardCount() {
        return stack.size();
    }


    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        int cardCount = cardCount();
        for(int i = cardCount - 1; i >= 0; i--) {
            str.append(cardCount - i - 1);
            str.append(":\t");
            str.append(stack.get(i));
            str.append("\n");
        }
        return str.toString();
    }
}
