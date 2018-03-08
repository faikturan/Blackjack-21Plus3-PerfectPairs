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

    @Override
    public boolean addCard(Card card) {
        //adds the cards to the hands
        boolean added = super.addCard(card);

        //if this hands is a result of a splitted hands and its first cards is an ace
        //make sure that the hands is only able to draw one cards
        if(resultOfSplittedHand && Hand.isFirstCardAce(this) && cardsAllowedToDraw > 1) {
            cardsAllowedToDraw = 1;
        }

        return added;
    }

    /**
     * Returns whether or not the hands can be splitted
     * @return if a split is possible
     */
    public boolean isSplittable() {
        return !resultOfSplittedHand && cardCount() == INITIAL_CARD_COUNT &&
                cards.get(0).matchesValue(cards.get(1));
    }

    /**
     * Splits this hands into another one containing one cards of this initial hands.
     * @return the table new splitted hands, if its not possible to split result will be null
     */
    public PlayerHand split() {
        //check if this hands is splittable
        if(isSplittable()) {
            resultOfSplittedHand = true;
            //if the first cards is an ace, both hands should only be able to draw
            //one another cards
            if(Hand.isFirstCardAce(this)) {
                cardsAllowedToDraw = 1;
            }
            //add one cards of this initial hands to the splitted one
            PlayerHand splittedHand = new PlayerHand(true);
            splittedHand.addCard(cards.remove(SECOND_CARD));
            return splittedHand;
        }
        //if not split possible
        return null;
    }

}
