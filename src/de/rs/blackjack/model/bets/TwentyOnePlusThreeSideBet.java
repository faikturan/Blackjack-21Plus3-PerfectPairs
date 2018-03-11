package de.rs.blackjack.model.bets;

import de.rs.blackjack.model.cards.Card;
import de.rs.blackjack.model.hands.Hand;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Rene Sommerfeld on 06.03.2018.
 *
 * Die TwentyOnePlusThreeSideBet Klasse repräsentiert eine spezialisierte Form einer Nebenwette,
 * die ein Spieler an einer Box neben seiner Hauptwette platzieren kann.
 * Spielt ein Spieler eine TwentyOnePlusThreeSideBet Wette dann wettet er darauf das seine
 * zwei Initialkarten seiner Hand und die erste Karte des Dealers eine der folgenden Kombinationen
 * ergeben. Folgende Kombinationen fallen unter diese Wette:
 *
 *  - Suited Trips :    die zwei Initialkarten des Spielers und die erste Karte des Dealers sind gleich
 *                      Auszahlung:  100 : 1
 *  - Straight Flush :  die zwei Initialkarten des Spielers und die erste Karte des Dealers ergeben eine
 *                      Straße in gleicher Farbe
 *                      Auszahlung:  40 : 1
 *  - Trips :           die zwei Initialkarten des Spielers und die erste Karte des Dealers haben die gleiche
 *                      Wertigkeit
 *                      Auszahlung : 30 : 1
 *  - Straight :        die zwei Initialkarten des Spielers und die erste Karte des Dealers ergeben eine
 *                      Straße
 *                      Auszahlung : 10 : 1
 *  - Flush :           die zwei Initialkarten des Spielers und die erste Karte des Dealers sind in der gleichen
 *                      Farbe
 *                      Auszahlung : 5 : 1
 *
 */
public class TwentyOnePlusThreeSideBet extends SideBet {

    /**
     * Name der Suited Trips Nebenwette
     */
    private static final String NAME_SUITED_TRIPS = "Suited Trips";

    /**
     * die Auszahlungsquote der Suited Trips Nebenwette
     */
    private static final float SUITED_TRIPS_PAYOUT = 100; // 100 : 1

    /**
     * Name der Straight Flush Nebenwette
     */
    private static final String NAME_STRAIGHT_FLUSH = "Straight Flush";

    /**
     * die Auszahlungsquote der Straight Flush Nebenwette
     */
    private static final float STRAIGHT_FLUSH_PAYOUT = 40; // 40 : 1

    /**
     * Name der Trips Nebenwette
     */
    private static final String NAME_TRIPS = "Trips";

    /**
     * die Auszahlungsquote der Trips Nebenwette
     */
    private static final float TRIPS_PAYOUT = 30; // 30 : 1

    /**
     * Name der Straight Nebenwette
     */
    private static final String NAME_STRAIGHT = "Straight";

    /**
     * die Auszahlungsquote der Straight Nebenwette
     */
    private static final float STRAIGHT_PAYOUT = 10; // 10 : 1

    /**
     * Name der Flush Nebenwette
     */
    private static final String NAME_FLUSH = "Flush";

    /**
     * die Auszahlungsquote der Flush Nebenwette
     */
    private static final float FLUSH_PAYOUT = 5; // 5 : 1

    public TwentyOnePlusThreeSideBet(float amount) {
        super(amount);
    }

    /**
     * Wertet die Wette unter der Begutachtung der Spielerhand und
     * der Dealerhand aus.
     * @param playerHand die Hand des Spielers
     * @param dealerHand die Hand des Dealers
     */
    @Override
    public void evaluate(Hand playerHand, Hand dealerHand) {
        Card playerFirstCard = playerHand.getCard(0);
        Card playerSecondCard = playerHand.getCard(1);
        Card dealerOpenCard = dealerHand.getCard(0);

        //suited trips
        if(playerFirstCard.matches(playerSecondCard) && playerSecondCard.matches(dealerOpenCard)) {
            status = Status.WON;
            payout = amount * SUITED_TRIPS_PAYOUT + amount;
            setName(NAME_SUITED_TRIPS);
            return;
        }


        boolean consecutive = checkStraight(playerFirstCard, playerSecondCard, dealerOpenCard);
        boolean flush = checkFlush(playerFirstCard, playerSecondCard, dealerOpenCard);

        //straight flush
        if(consecutive && flush) {
            status = Status.WON;
            payout = amount * STRAIGHT_FLUSH_PAYOUT + amount;
            setName(NAME_STRAIGHT_FLUSH);
            return;
        }

        //trips
        if(playerFirstCard.matchesValue(playerSecondCard) && playerSecondCard.matchesValue(dealerOpenCard)) {
            status = Status.WON;
            payout = amount * TRIPS_PAYOUT + amount;
            setName(NAME_TRIPS);
            return;
        }

        //straight
        if(consecutive) {
            //straight
            status = Status.WON;
            payout = amount * STRAIGHT_PAYOUT + amount;
            setName(NAME_STRAIGHT);
            return;
        }


        //flush
        if(flush) {
            status = Status.WON;
            payout = amount * FLUSH_PAYOUT + amount;
            setName(NAME_FLUSH);
            return;
        }

        //no twenty one plus three side bets condition apply
        status = Status.LOST;
    }

    /**
     * Überprüft die Initialkarten der Hand des Spielers und die offene Karte des Dealers
     * auf einen Flush. Gibt zurück ob die drei Karten ein Flush ergeben oder nicht.
     * @param playerFirstCard die erste Karte des Spielers
     * @param playerSecondCard die zweite Karte des Spielers
     * @param dealerOpenCard die offene Karte des Dealers
     * @return gibt zurück ob es sich um einen Flush hält oder nicht
     */
    private boolean checkFlush(Card playerFirstCard, Card playerSecondCard, Card dealerOpenCard) {
        return playerFirstCard.matchesSuit(playerSecondCard) && playerSecondCard.matchesSuit(dealerOpenCard);
    }

    /**
     * Überprüft ob die Initialkarten der Hand des Spielers und die offene Karte des Dealers eine
     * fortlaufende Reihenfolge (eine Straße) ergeben. Gibt zurück ob die Karten fortlaufend sind oder
     * nicht.
     * @param playerFirstCard die erste Karte des Spielers
     * @param playerSecondCard die zweite Karte des Spielers
     * @param dealerOpenCard die offene Karte des Dealers
     * @return gibt zurück ob es sich um fortlaufende Karten handelt
     */
    private boolean checkStraight(Card playerFirstCard, Card playerSecondCard, Card dealerOpenCard) {
        //alle Karten in eine Liste
        List<Card> cards = new ArrayList<>();
        cards.add(playerFirstCard);
        cards.add(playerSecondCard);
        cards.add(dealerOpenCard);

        //Liste sortieren
        Collections.sort(cards);

        //überprüfen ob die jeweils benachbarten Karten in der Liste fortlaufend sind
        for(int i = 0; i < cards.size() - 1; i++) {
            if(cards.get(i).compareTo(cards.get(i + 1)) != -1) {
                //es handelt sich nicht um drei fortlaufende Karten
                return false;
            }
        }

        //es handelt sich um drei fortlaufende Karten
        return true;
    }
}
