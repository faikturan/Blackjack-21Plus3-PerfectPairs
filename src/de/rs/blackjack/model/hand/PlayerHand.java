package de.rs.blackjack.model.hand;

import de.rs.blackjack.model.card.Card;

/**
 * Created by Rene Sommerfeld on 06.03.2018.
 *
 * The PlayerHand class a special kind of hand.
 * It is splittable, so if the first two cards are of
 * the same value - a split can be done.
 *
 */
public class PlayerHand extends Hand {

    /**
     * keeps track if this hand is a result
     * of a splitted hand
     */
    private boolean resultOfSplittedHand;

    /**
     * Creates a new player hand
     */
    public PlayerHand() {
        super();
        resultOfSplittedHand = false;
    }

    /**
     * Creates a new player hand from another splitted
     * hand
     * @param resultOfSplittedHand whether or not this hand results from
     *                             a splitted hand
     */
    private PlayerHand(boolean resultOfSplittedHand) {
        super();
        this.resultOfSplittedHand = resultOfSplittedHand;
    }

    @Override
    public void addCard(Card card) {
        //adds the card to the hand
        super.addCard(card);

        //if this hand is a result of a splitted hand and its first card is an ace
        //make sure that the hand is only able to draw one card
        if(resultOfSplittedHand && Hand.isFirstCardAce(this) && cardsAllowedToDraw > 1) {
            cardsAllowedToDraw = 1;
        }
    }

    /**
     * Returns whether or not the hand can be splitted
     * @return if a split is possible
     */
    public boolean isSplittable() {
        return !resultOfSplittedHand && cardCount() == INITIAL_CARD_COUNT &&
                cards.get(0).matchesValue(cards.get(1));
    }

    /**
     * Splits this hand into another one containing one card of this initial hand.
     * @return the players new splitted hand, if its not possible to split result will be null
     */
    public PlayerHand split() {
        //check if this hand is splittable
        if(isSplittable()) {
            resultOfSplittedHand = true;
            //if the first card is an ace, both hands should only be able to draw
            //one another card
            if(Hand.isFirstCardAce(this)) {
                cardsAllowedToDraw = 1;
            }
            //add one card of this initial hand to the splitted one
            PlayerHand splittedHand = new PlayerHand(true);
            splittedHand.addCard(cards.remove(SECOND_CARD));
            return splittedHand;
        }
        //if not split possible
        return null;
    }

}
