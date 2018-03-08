package de.rs.blackjack.model.table;

import de.rs.blackjack.model.cards.Card;
import de.rs.blackjack.model.hands.PlayerHand;

/**
 * Created by Rene Sommerfeld on 07.03.2018.
 */
public class PlayerBox extends Box {

    private int currentActiveHandIndex;

    public PlayerBox() {
        currentActiveHandIndex = 0;
    }

    @Override
    protected boolean addCardToActiveHand(Card newCard) {
        boolean added = hands.get(currentActiveHandIndex).addCard(newCard);
        if(added) {
            
        }
        return added;
    }

    @Override
    public boolean decide(Decision decision) {
        return true;
    }

    @Override
    public void occupy(User user) {
        if(!isActive()) {
            super.occupy(user);
            hands.add(new PlayerHand());
        }
    }

    @Override
    public void clear() {

        //clear every card of the hands
        for(int i = 0; i < hands.size(); i++) {
            hands.get(i).clear();
        }

        //if player has a splitted hand remove it
        if(hands.size() == MAX_HAND_COUNT) {
            hands.remove(MAX_HAND_COUNT - 1);
        }
        currentActiveHandIndex = 0;
    }
}
