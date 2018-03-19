package de.rs.blackjack.model;


import de.rs.blackjack.model.cards.CardDeck;
import de.rs.blackjack.model.hands.Hand;
import de.rs.blackjack.model.hands.PlayerHand;
import de.rs.blackjack.model.table.Box;
import de.rs.blackjack.model.table.DealerBox;
import de.rs.blackjack.model.table.PlayerBox;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Created by Rene Sommerfeld on 12.03.2018.
 */
public class BlackjackGameModel extends Observable {

    List<Box> boxes;

    public BlackjackGameModel() {
        boxes = new ArrayList<>();
        boxes.add(new PlayerBox());
        boxes.get(0).addCard(CardDeck.createRandom());
        boxes.get(0).addCard(CardDeck.createRandom());

        Hand[] hands = boxes.get(0).getHands();
        hands[1] = new PlayerHand();
        hands[1].addCard(CardDeck.createRandom());
        hands[1].addCard(CardDeck.createRandom());
        hands[1].addCard(CardDeck.createRandom());


        boxes.add(new PlayerBox());
        boxes.get(1).addCard(CardDeck.createRandom());
        boxes.get(1).addCard(CardDeck.createRandom());

        boxes.add(new PlayerBox());
        boxes.get(2).addCard(CardDeck.createRandom());
        boxes.get(2).addCard(CardDeck.createRandom());

        boxes.add(new DealerBox());
        boxes.get(3).addCard(CardDeck.createRandom());
        boxes.get(3).addCard(CardDeck.createRandom());

    }

    public int getBoxCount() {
        return boxes.size();
    }

    public Box getBox(int index) {
        return boxes.get(index);
    }


    public void notifyChanges(Object argument) {
        setChanged();
        if(argument != null) {
            notifyObservers(argument);
        } else {
            notifyObservers();
        }
    }

    public void notifyChanges() {
        notifyChanges(null);
    }

}
