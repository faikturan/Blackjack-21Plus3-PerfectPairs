package de.rs.blackjack.model.table;

import de.rs.blackjack.model.bets.*;
import de.rs.blackjack.model.cards.Card;
import de.rs.blackjack.model.hands.Hand;
import de.rs.blackjack.model.hands.PlayerHand;

/**
 * Created by Rene Sommerfeld on 07.03.2018.
 */
public class PlayerBox extends Box {

    private static final int MAX_BET_COUNT = 4;
    private static final int PERFECT_PAIRS_BET_INDEX = 0;
    private static final int BLACKJACK_BET_INDEX = 1;
    private static final int TWENTY_ONE_PLUS_THREE_BET_INDEX = 2;
    private static final int INSURANCE_BET_INDEX = 3;

    private int currentActiveHandIndex;
    private Bet[] bets;

    public PlayerBox() {
        currentActiveHandIndex = 0;
        bets = new Bet[MAX_BET_COUNT];
    }

    public boolean addBet(Bet bet) {
        if(bet instanceof BlackjackBet) {
            return addBet(bet, BLACKJACK_BET_INDEX);
        } else if(bet instanceof PerfectPairsSideBet) {
            return addBet(bet, PERFECT_PAIRS_BET_INDEX);
        } else if(bet instanceof TwentyOnePlusThreeSideBet) {
            return addBet(bet, TWENTY_ONE_PLUS_THREE_BET_INDEX);
        }
        return false;
    }

    private boolean addBet(Bet bet, int betIndex) {
        if(bets[betIndex] == null) {
            bets[betIndex] = bet;
            return true;
        }
        return false;
    }

    @Override
    protected boolean addCardToActiveHand(Card newCard) {
        return hands.get(currentActiveHandIndex).addCard(newCard);
    }

    public void setAvailableDecisionOptions(Hand dealerHand, boolean checkedForBlackjack) {
        //clear all previous decision options
        availableDecisions.clear();

        PlayerHand playerHand = (PlayerHand)hands.get(currentActiveHandIndex);

        if(playerHand.cardCount() == Hand.INITIAL_CARD_COUNT) {
            //if the dealer has an ace as open card and has not checked for blackjack yet
            //first only options available is to take insurance
            if(Hand.isFirstCardAce(dealerHand) && !checkedForBlackjack) {
                availableDecisions.add(Decision.TAKING_INSURANCE);
                availableDecisions.add(Decision.DENY_INSURANCE);
                return;
            }

            //right away the player is able to decide to hit, stand or double down
            availableDecisions.add(Decision.HIT);
            availableDecisions.add(Decision.STAND);
            availableDecisions.add(Decision.DOUBLE_DOWN);


            //Note: the player hand only allows to split once
            //if the player is able to split the hand
            if(playerHand.isSplittable()) {
                availableDecisions.add(Decision.SPLIT);
            }
        }

    }

    @Override
    public void decide(Decision decision) {
        if(availableDecisions.contains(decision)) {
            decisionMade = decision;
            setDecisionAccepted(true);
        }
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
