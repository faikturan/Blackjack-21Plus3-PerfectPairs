package de.rs.blackjack.model.hands;

import de.rs.blackjack.model.cards.Card;

/**
 * Created by Rene Sommerfeld on 06.03.2018.
 *
 * The PlayerHand class a special kind of hands.
 * It is splittable, so if the first two cards are of
 * the same value - a split can be done.
 *
 */
public class PlayerHand extends Hand {

    /**
     * keeps track if this hands is a result
     * of a splitted hands
     */
    private boolean resultOfSplittedHand;

    /**
     * Creates a new player hands
     */
    public PlayerHand() {
        super();
        resultOfSplittedHand = false;
    }

    /**
     * Creates a new player hands from another splitted
     * hands
     * @param resultOfSplittedHand whether or not this hands results from
     *                             a splitted hands
     */
    private PlayerHand(boolean resultOfSplittedHand) {
        super();
        this.resultOfSplittedHand = resultOfSplittedHand;
    }

    /**
     * Returns whether or not the hands can be splitted
     * @return if a split is possible
     */
    public boolean isSplittable() {
        return !resultOfSplittedHand && cardCount() == INITIAL_CARD_COUNT &&
                Card.Matcher.matchesValue(
                        Hand.initialFirstCard(this),
                        Hand.initialSecondCard(this));
    }

    /**
     * Splits this hand into another one containing one cards of this initial hands.
     * @return the table new splitted hands, if its not possible to split result will be null
     */
    public PlayerHand split() {
        if(isSplittable()) {
            resultOfSplittedHand = true;
            PlayerHand splittedHand = new PlayerHand(true);
            splittedHand.addCard(cards.remove(SECOND_CARD));
            return splittedHand;
        }
        return null;
    }

}
