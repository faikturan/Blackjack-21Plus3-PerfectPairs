package de.rs.blackjack.model.bets;

import de.rs.blackjack.model.hands.Hand;

/**
 * Created by Rene Sommerfeld on 06.03.2018.
 *
 * Die BlackjackBet Klasse repräsentiert eine spezialisierte Form einer Wette,
 * die ein Spieler an einer Box platzieren kann. Hierbei handelt es sich um
 * die Hauptwette die ein Spieler für seine jeweilige Hand platziert.
 */
public class BlackjackBet extends Bet {

    /**
     * gibt die Auszahlungsquote für einen Blackjack an
     */
    public static final float BLACKJACK_PAYOUT_MULTIPLIER = 1.5f;

    /**
     * gibt die Auszahlungsquote für einen normalen Gewinn gegen die
     * Hand des Dealers an
     */
    public static final float WINNING_PAYOUT = 1.0f;

    /**
     * Erzeugt eine neue Wette von einem festgelegten Wert
     * @param amount den Wert der Wette
     */
    public BlackjackBet(float amount) {
        super(amount);
    }

    @Override
    public void evaluate(Hand playerHand, Hand dealerHand) {
        //TODO: Auswerten der Wette bei einem Vergleich der Hand des Spielers mit der vom Dealer
    }

    /**
     * Verdoppelt eine BlackjackBet für einen gespielten Double Down.
     * @param bet die Wette die verdoppelt werden soll
     * @return die verdoppelte Wette
     */
    public static BlackjackBet doubleDown(BlackjackBet bet) {
        if(bet != null) {
            bet.amount *= 2;
        }
        return bet;
    }

}
