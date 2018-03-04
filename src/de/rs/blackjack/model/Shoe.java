package de.rs.blackjack.model;


import java.util.*;

/**
 * Created by Rene Sommerfeld on 04.03.2018.
 *
 *
 */
public class Shoe {

    public static final int CARDS_PER_DECK = 52;

    /**
     *
     */
    private int deckCount;

    /**
     *
     */
    private List<Card> initialStack;

    /**
     *
     */
    private Stack<Card> playingStack;

    /**
     *
     */
    private boolean cuttingCardEnabled;

    /**
     *
     */
    private int cuttingCardIndex;

    /**
     *
     */
    private int cuttingCardIndexPercentage, cuttingCardIndexDeviation;

    /**
     *
     * @param deckCount
     */
    public Shoe(int deckCount) {
        initialStack = new ArrayList<>();
        playingStack = new Stack<>();
        this.deckCount = deckCount;
        this.cuttingCardEnabled = true;
        this.cuttingCardIndex = -1;
        this.cuttingCardIndexPercentage = 65;
        this.cuttingCardIndexDeviation = 10;
        create();
    }

    /**
     *
     */
    public void create() {
        //clears both stacks
        initialStack.clear();
        playingStack.clear();

        //adding the amount of stacks specified by deck calculateScore
        //to the initial stack
        for(int i = 0; i < deckCount; i++) {
            for(Card.Suit suit : Card.Suit.values()) {
                for(Card.Value value : Card.Value.values()) {
                    initialStack.add(new Card(suit, value));
                }
            }
        }
    }

    /**
     *
     */
    public void shuffle() {
        //shuffles the initial stack
        Collections.shuffle(initialStack);

        //calculates the index of the cutting card if it is enabled
        if(cuttingCardEnabled) {
            calculateCuttingCardIndex();
            for(int i = initialStack.size() - 1; i >= 0; i--) {
                if(i == cuttingCardIndex) {
                    playingStack.push(new CuttingCard());
                }
                playingStack.push(initialStack.get(i));
            }
        } else {
            for(int i = initialStack.size() - 1; i >= 0; i--) {
                playingStack.push(initialStack.get(i));
            }
        }
    }

    /**
     *
     * @return
     */
    public Card dealCard() {
        if(playingStack.isEmpty()) {
            return null;
        }
        return playingStack.pop();
    }

    /**
     *
     * @return
     */
    public int cardCount() {
        return playingStack.size();
    }

    /**
     *
     * @return
     */
    public boolean isCuttingCardEnabled() {
        return cuttingCardEnabled;
    }

    /**
     *
     * @return
     */
    public int getCuttingCardIndex() {
        return cuttingCardIndex;
    }

    /**
     *
     */
    private void calculateCuttingCardIndex() {
        Random r = new Random();
        int dir = r.nextInt(cuttingCardIndexDeviation);
        cuttingCardIndex = (int)(initialStack.size() *
                ((cuttingCardIndexPercentage + dir) / 100.0f));
    }

    /**
     *
     * @param cuttingCardIndexPercentage
     */
    public void setCuttingCardIndexPercentage(int cuttingCardIndexPercentage) {
        this.cuttingCardIndexPercentage = cuttingCardIndexPercentage;
    }

    /**
     *
     * @param cuttingCardIndexDeviation
     */
    public void setCuttingCardIndexDeviation(int cuttingCardIndexDeviation) {
        this.cuttingCardIndexDeviation = cuttingCardIndexDeviation;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for(int i = 0; i < initialStack.size(); i++) {
            str.append((i + 1) + ":\t");
            str.append(initialStack.get(i));
            str.append("\n");
        }
        return str.toString();
    }
}
