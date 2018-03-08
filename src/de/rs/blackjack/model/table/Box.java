package de.rs.blackjack.model.table;

import de.rs.blackjack.model.cards.Card;
import de.rs.blackjack.model.hands.Hand;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rene Sommerfeld on 07.03.2018.
 */
public abstract class Box {

    /**
     * the max number of hands on a box
     */
    public static final int MAX_HAND_COUNT = 2;

    /**
     * all available decisions for a box
     */
    public enum Decision {
        HIT, STAND, DOUBLE_DOWN, SPLIT, TAKING_INSURANCE, DENY_INSURANCE
    }

    /**
     * the user occupying the box
     */
    private User user;

    /**
     * the hands of the box
     */
    protected List<Hand> hands;

    /**
     * current available decisions to choose from in this
     * turn
     */
    protected List<Decision> availableDecisions;

    /**
     * the decision that is made
     */
    protected Decision decisionMade;

    /**
     * if the decision is accepted
     */
    private boolean decisionAccepted;

    public Box() {
        hands = new ArrayList<>(MAX_HAND_COUNT);
        availableDecisions = new ArrayList<>();
        decisionAccepted = false;
    }

    public void setDecisionAccepted(boolean decisionAccepted) {
        this.decisionAccepted = decisionAccepted;
    }

    public boolean isDecisionAccepted() {
        return decisionAccepted;
    }

    public boolean addCard(Card newCard) {
        return addCardToActiveHand(newCard);
    }

    public void occupy(User user) {
        this.user = user;
    }

    public boolean isActive() {
        return user != null;
    }

    public abstract void clear();
    public abstract void decide(Decision decision);
    protected abstract boolean addCardToActiveHand(Card newCard);

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(user.getName());
        for(Hand hand : hands) {
            str.append(hand);
        }
        str.append("\n");
        return str.toString();
    }
}
