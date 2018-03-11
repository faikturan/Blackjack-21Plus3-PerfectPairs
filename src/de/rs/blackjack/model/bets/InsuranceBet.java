package de.rs.blackjack.model.bets;

import de.rs.blackjack.model.hands.Hand;

/**
 * Created by Rene Sommerfeld on 06.03.2018.
 *
 * Die InsuranceBet Klasse repräsentiert eine spezialisierte Form einer Wette,
 * die ein Spieler an einer Box platzieren kann. Hierbei handelt es sich um
 * eine Versicherung gegen einen Blackjack bei der Hand des Dealers. Zeigt
 * der Dealer als erste Karte ein Ass, so kann der Spieler mit der InsuranceBet
 * seine bereits platzierte Wette für seine Hand versichern lassen.
 */
public class InsuranceBet extends Bet {

    /**
     * die Auszahlungsquote für eine Versicherung
     */
    public static final float INSURANCE_PAYOUT_MULTIPLIER = 2.0f;

    /**
     * Erzeugt eine neue InsuranceBet aus einer bereits platzierten
     * BlackjackBet.
     * @param bet die BlackjackBet die versichert werden soll
     */
    public InsuranceBet(BlackjackBet bet) {
        super(bet.getAmount() * 0.5f);
    }

    /**
     * Wertet die Wette unter der Begutachtung der Spielerhand und
     * der Dealerhand aus.
     * @param playerHand die Hand des Spielers
     * @param dealerHand die Hand des Dealers
     */
    @Override
    public void evaluate(Hand playerHand, Hand dealerHand) {
        if(dealerHand.hasBlackjack()) {
            status = Status.WON;
            payout = amount * INSURANCE_PAYOUT_MULTIPLIER + amount;
        } else {
            status = Status.LOST;
        }
    }

}
