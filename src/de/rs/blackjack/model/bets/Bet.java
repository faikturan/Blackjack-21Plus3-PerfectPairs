package de.rs.blackjack.model.bets;

import de.rs.blackjack.model.hands.Hand;

/**
 * Created by Rene Sommerfeld on 06.03.2018.
 *
 * Die Bet Klasse repräsentiert eine abstrakte Wette die
 * ein Spieler an einer Box platzieren kann. Wenn eine Wette ausgewertet
 * wird, verfügt sie über einen entsprechenden Status der markiert ob
 * sie gewonnen oder verloren wurde.
 */
public abstract class Bet {

    /**
     * alle Status die eine Wette haben kann
     */
    public enum Status {
        NONE, WON, LOST
    }

    /**
     * gibt den Wert der Wette an
     */
    protected float amount;

    /**
     * gibt im Falle eines Gewinns die Auszahlung an
     */
    protected float payout;

    /**
     * gibt den aktuellen Status der Wette an
     */
    protected Status status;

    /**
     * Erzeugt eine neue Wette von einem festgelegten Wert
     * @param amount den Wert der Wette
     */
    public Bet(float amount) {
        this.amount = amount;
        this.status = Status.NONE;
    }

    /**
     * Gibt den Wert der Wette zurück
     * @return den Wert
     */
    public float getAmount() {
        return amount;
    }

    /**
     * Gibt den Status der Wette zurück
     * @return den Status
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Gibt die Auszahlung der Wette zurück
     * @return die Auszahlung der Wette
     */
    public float payout() {
        if(status == Status.NONE || status == Status.LOST) {
            return 0;
        } else {
            return payout;
        }
    }

    /**
     * Wertet die Wette unter der Begutachtung der Spielerhand und
     * der Dealerhand aus.
     * @param playerHand die Hand des Spielers
     * @param dealerHand die Hand des Dealers
     */
    public abstract void evaluate(Hand playerHand, Hand dealerHand);

}
