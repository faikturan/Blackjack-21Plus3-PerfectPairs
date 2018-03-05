package de.rs.blackjack.model;


import java.util.*;

/**
 * Created by Rene Sommerfeld on 04.03.2018.
 *
 *
 */
public class Shoe {

    public static final int CARDS_PER_DECK = 52;

    private int deckCount;
    private boolean shuffled;

    private List<Card> playingStack;


    public Shoe(int deckCount) {
        playingStack = new ArrayList<>();
        this.deckCount = deckCount;
        shuffled = false;
        create();

    }

    public void create() {
        playingStack.clear();
        for(int i = 0; i < deckCount; i++) {
            for(Card.Suit suit : Card.Suit.values()) {
                for(Card.Value value : Card.Value.values()) {
                    playingStack.add(new Card(suit, value));
                }
            }
        }
    }

    public void shuffle() {
        if(!shuffled) {
            Collections.shuffle(playingStack);
            shuffled = true;
        }
    }

    public void placeCuttingCard(int index) {
        int cardCount = cardCount();
        if(shuffled && (index >= 0 && index < cardCount)) {
            playingStack.set((cardCount - 1) - index, new CuttingCard());
        }
    }

    public Card dealCard() {
        if(cardCount() == 0) {
            return null;
        }
        return playingStack.remove(playingStack.size() - 1);
    }

    public int cardCount() {
        return playingStack.size();
    }


    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        int cardCount = cardCount();
        for(int i = cardCount - 1; i >= 0; i--) {
            str.append(cardCount - i - 1);
            str.append(":\t");
            str.append(playingStack.get(i));
            str.append("\n");
        }
        return str.toString();
    }
}
