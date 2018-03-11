package de.rs.blackjack.model.table;

import de.rs.blackjack.model.cards.Card;
import de.rs.blackjack.model.hands.DealerHand;
import de.rs.blackjack.model.hands.Hand;
import de.rs.blackjack.model.hands.PlayerHand;
import de.rs.blackjack.model.user.Dealer;
import de.rs.blackjack.model.user.User;

/**
 * Created by Rene Sommerfeld on 09.03.2018.
 */
public class DealerBox extends Box {

    public static final int MAX_HAND_COUNT = 1;
    public static final int STAND_ON_SCORE = 17;

    public DealerBox() {
        hands = new DealerHand[MAX_HAND_COUNT];
        hands[FIRST_HAND] = new DealerHand();
        currentActiveHandIndex = 0;
    }

    @Override
    public void occupy(User user) {
        this.user = user;
    }

    @Override
    public boolean addCard(Card card) {

        Hand hand = getCurrentActiveHand();
        int cardCount = hand.cardCount();

        //if the second initial card is drawn
        if(cardCount == Hand.INITIAL_CARD_COUNT - 1) {
           card = Card.putFaceDown(card);
        }

        //adds the card to the hand and calls prepareAvailableNextDecisions
        //in order to find the next available decisions
        return super.addCard(card);
    }

    @Override
    protected void prepareAvailableNextDecisions(Hand currentActiveHand) {
        //clear all previous available next decisions
        availableNextDecisions.clear();

        int cardCount = currentActiveHand.cardCount();
        int score = currentActiveHand.score();

        //if the initial cards are drawn
        if(cardCount >= Hand.INITIAL_CARD_COUNT) {
            //the dealer has to hit if the getScore is below the getScore
            //to stand on
            if(score < STAND_ON_SCORE) {
                availableNextDecisions.add(Decision.FORCED_HIT);
            } else {
                availableNextDecisions.add(Decision.FORCED_STAND);
            }
        }
    }

    @Override
    public void makeNextDecision(Decision nextDecision) {
        if(availableNextDecisions.contains(nextDecision)) {
            this.nextDecision = nextDecision;
        }
    }

    @Override
    public void clear() {
        hands[FIRST_HAND].clear();
    }
}
