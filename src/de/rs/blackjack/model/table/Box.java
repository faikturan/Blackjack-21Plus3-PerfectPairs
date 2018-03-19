package de.rs.blackjack.model.table;

import de.rs.blackjack.model.cards.Card;
import de.rs.blackjack.model.hands.Hand;
import de.rs.blackjack.model.user.User;

import java.util.*;


/**
 * Created by Rene Sommerfeld on 09.03.2018.
 *
 * The Box class represents one seat at a blackjack table. It can be used
 * by an user whether it is a player or even the dealer.
 */
public abstract class Box {

    /**
     * the index of the first hand of the box
     */
    public static final int FIRST_HAND = 0;

    /**
     * decisions an user can make while playing
     */
    public enum Decision {
        HIT, FORCED_HIT, STAND, FORCED_STAND, SPLIT, DOUBLE_DOWN, TAKE_INSURANCE, DENY_INSURANCE
    }

    protected User user;

    protected int currentActiveHandIndex;
    protected Hand[] hands;

    protected Set<Decision> availableNextDecisions;
    protected Decision nextDecision;

    public Box() {
        availableNextDecisions = new HashSet<>();
    }

    public abstract void occupy(User user);

    public abstract void clear();
    protected abstract void prepareAvailableNextDecisions(Hand currentActiveHand);
    public abstract void makeNextDecision(Decision nextDecision);

    public boolean addCard(Card card) {
        Hand hand = getCurrentActiveHand();
        boolean added = false;
        if(hand != null) {
            added = hand.addCard(card);
            prepareAvailableNextDecisions(hand);
        }
        return added;
    }

    public Hand getCurrentActiveHand() {
        return hands[currentActiveHandIndex];
    }

    public boolean isOccupied() {
        return user != null;
    }

    public Decision getNextDecision() {
        return nextDecision;
    }

    public Set<Decision> getAvailableNextDecisions() {
        return availableNextDecisions;
    }

    public boolean hasMadeNextDecision() {
        return nextDecision != null;
    }

    public Hand[] getHands() {
        return hands;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append((isOccupied()) ? user.getName() : "");
        str.append(":\n\n");
        for(int i = 0; i < hands.length; i++) {
            Hand hand = hands[i];
            if(hand != null) {
                str.append("Hand ").append(i + 1);
                str.append(hand);
            }
        }
        str.append("\n");
        str.append(Arrays.toString(availableNextDecisions.toArray()));
        str.append("\n");
        return str.toString();
    }
}
