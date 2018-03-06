package de.rs.blackjack.model.bet;

import de.rs.blackjack.model.card.Card;
import de.rs.blackjack.model.hand.Hand;

/**
 * Created by Rene Sommerfeld on 06.03.2018.
 */
public class PerfectPairsSideBet extends SideBet {

    /**
     * the name of the perfect pair side bet
     */
    private static final String NAME_PERFECT_PAIR = "Perfect Pair";
    /**
     * the payout multiplier for a perfect pair side bet
     */
    private static final float PERFECT_PAIR_PAYOUT = 25; // 25 : 1
    /**
     * the name of the colored pair side bet
     */
    private static final String NAME_COLORED_PAIR = "Colored Pair";
    /**
     * the payout multiplier for the colored pair side bet
     */
    private static final float COLORED_PAIR_PAYOUT = 12; // 12 : 1
    /**
     * the name of the mixed pair side bet
     */
    private static final String NAME_MIXED_PAIR = "Mixed Pair";
    /**
     * the payout multiplier for the mixed pair side bet
     */
    private static final float MIXED_PAIR_PAYOUT = 6; // 6 : 1


    public PerfectPairsSideBet(float amount) {
        super(amount);
    }


    @Override
    public void evaluate(Hand playerHand, Hand dealerHand) {
        Card playerFirstCard = playerHand.getCard(0);
        Card playerSecondCard = playerHand.getCard(1);

        if(playerFirstCard.matchesValue(playerSecondCard)) {
            if(playerFirstCard.matchesSuit(playerSecondCard)) {
                //perfect pair condition
                status = Status.WON;
                payout = amount * PERFECT_PAIR_PAYOUT + amount;
                name = NAME_PERFECT_PAIR;
            } else {
                if(playerFirstCard.matchesColor(playerSecondCard)) {
                    //colored pair condition
                    status = Status.WON;
                    payout = amount * COLORED_PAIR_PAYOUT + amount;
                    name = NAME_COLORED_PAIR;
                } else {
                    //mixed pair condition
                    status = Status.WON;
                    payout = amount * MIXED_PAIR_PAYOUT + amount;
                    name = NAME_MIXED_PAIR;
                }
            }
        } else {
            //no perfect pairs condition apply
            status = Status.LOST;
        }
    }
}
