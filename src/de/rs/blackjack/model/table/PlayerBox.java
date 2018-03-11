package de.rs.blackjack.model.table;

import de.rs.blackjack.model.bets.*;
import de.rs.blackjack.model.cards.Card;
import de.rs.blackjack.model.hands.DealerHand;
import de.rs.blackjack.model.hands.Hand;
import de.rs.blackjack.model.hands.PlayerHand;
import de.rs.blackjack.model.user.User;

/**
 * Created by Rene Sommerfeld on 09.03.2018.
 */
public class PlayerBox extends Box {

    public static final int SECOND_HAND = 1;

    public static final int MAX_HAND_COUNT = 2;
    public static final int MIN_DOUBLE_DOWN_SCORE = 9;


    private static final int MAX_BET_COUNT = 4;
    private static final int PERFECT_PAIRS_BET_INDEX = 0;
    private static final int BLACKJACK_BET_INDEX = 1;
    private static final int TWENTY_ONE_PLUS_THREE_BET_INDEX = 2;
    private static final int INSURANCE_BET_INDEX = 3;

    private Bet[] bets;

    public PlayerBox() {
        hands = new PlayerHand[MAX_HAND_COUNT];
        hands[FIRST_HAND] = new PlayerHand();
        bets = new Bet[MAX_BET_COUNT];
        currentActiveHandIndex = 0;
    }

    @Override
    public void occupy(User user) {
        this.user = user;
    }

    @Override
    public void clear() {
        for(int i = 0; i < hands.length; i++) {
            if(hands[i] != null) {
                hands[i].clear();
            }
        }
        //clear the second hand
        hands[SECOND_HAND] = null;
    }

    @Override
    protected void prepareAvailableNextDecisions(Hand currentActiveHand) {
        //clear all previous available next decisions
        availableNextDecisions.clear();

        //get current card count and score of the players hand
        PlayerHand playerHand = (PlayerHand)currentActiveHand;
        int cardCount = playerHand.cardCount();
        int score = playerHand.score();

        if(cardCount >= Hand.INITIAL_CARD_COUNT) {
            //if the player has a blackjack or perfect score or is bust
            //the player is forced to stand
            if(playerHand.hasBlackjack() || score == Hand.PERFECT_SCORE || playerHand.isBust()) {
                availableNextDecisions.add(Decision.FORCED_STAND);
            } else {
                //adds the normal default decisions a player has
                availableNextDecisions.add(Decision.STAND);
                availableNextDecisions.add(Decision.HIT);

                //if the player hand is splittable add split as decision
                if(playerHand.isSplittable()) {
                    availableNextDecisions.add(Decision.SPLIT);
                }

                //if the player hand has a min double down score
                //add double down as decision
                if(score >= MIN_DOUBLE_DOWN_SCORE) {
                    availableNextDecisions.add(Decision.DOUBLE_DOWN);
                }
            }

        }

    }

    @Override
    public void makeNextDecision(Decision nextDecision) {
        if(availableNextDecisions.contains(nextDecision)) {
            this.nextDecision = nextDecision;
        }
    }

    public boolean addBet(Bet bet) {
        if(bet instanceof PerfectPairsSideBet) {
            return addBet(bet, PERFECT_PAIRS_BET_INDEX);
        } else if(bet instanceof BlackjackBet) {
            return addBet(bet, BLACKJACK_BET_INDEX);
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

    public void evaluateSideBets(DealerHand dealerHand) {
        evaluateBet(dealerHand, PERFECT_PAIRS_BET_INDEX);
        evaluateBet(dealerHand, TWENTY_ONE_PLUS_THREE_BET_INDEX);
    }

    public void evaluateInsuranceBet(DealerHand dealerHand) {
        evaluateBet(dealerHand, INSURANCE_BET_INDEX);
    }

    public void evaluateBlackjackBet(DealerHand dealerHand) {
        evaluateBet(dealerHand, BLACKJACK_BET_INDEX);
    }

    private void evaluateBet(DealerHand dealerHand, int betIndex) {
        if(bets[betIndex] != null) {
            bets[betIndex].evaluate(hands[0], dealerHand);
            //TODO: add the payout to players balance
            float payout = bets[betIndex].payout();
        }
    }

    private boolean takeInsurance() {
        if(bets[BLACKJACK_BET_INDEX] != null) {
            return addBet(InsuranceBet.create(bets[BLACKJACK_BET_INDEX]), INSURANCE_BET_INDEX);
        }
        return false;
    }

}
