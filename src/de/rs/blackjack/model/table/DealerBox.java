package de.rs.blackjack.model.table;


import de.rs.blackjack.model.cards.Card;
import de.rs.blackjack.model.hands.DealerHand;

/**
 * Created by Rene Sommerfeld on 07.03.2018.
 */
public class DealerBox extends Box {

    public DealerBox(Dealer dealer) {
        occupy(dealer);
        //a dealer only has the options to hit or stand
        availableDecisions.add(Decision.HIT);
        availableDecisions.add(Decision.STAND);
    }

    @Override
    public void occupy(User user) {
        if(!isActive()) {
            super.occupy(user);
            hands.add(new DealerHand());
        }
    }

    @Override
    public boolean addCard(Card newCard) {
        addCardToActiveHand(newCard);
        return true;
    }

    @Override
    protected boolean addCardToActiveHand(Card newCard) {
        return hands.get(0).addCard(newCard);
    }

    @Override
    public void decide(Decision decision) {

    }

    @Override
    public void clear() {
        for(int i = 0; i < hands.size(); i++) {
            hands.get(i).clear();
        }
    }
}
