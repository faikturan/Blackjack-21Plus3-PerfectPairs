package de.rs.blackjack.model.hand;

import de.rs.blackjack.model.card.Card;

/**
 * Created by Rene Sommerfeld on 06.03.2018.
 *
 * The DealerHand represents a special hand.
 * This hand is only able to draw cards to a specific
 * score. Once it has hit this mark, no additional cards
 * can be drawn.
 */
public class DealerHand extends Hand {

    /**
     * the score to stand on if it is reached
     */
    public static final int STANDS_ON_SCORE = 17;

    public DealerHand() {

    }

    @Override
    public void addCard(Card card) {
        super.addCard(card);
        if(score() >= STANDS_ON_SCORE) {
            cardsAllowedToDraw = 0;
        }
    }

}
