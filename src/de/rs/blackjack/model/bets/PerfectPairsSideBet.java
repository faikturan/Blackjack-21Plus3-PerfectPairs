package de.rs.blackjack.model.bets;

import de.rs.blackjack.model.cards.Card;
import de.rs.blackjack.model.hands.Hand;

/**
 * Created by Rene Sommerfeld on 06.03.2018.
 *
 * Die PerfectPairsSideBet Klasse repräsentiert eine spezialisierte Form einer Nebenwette,
 * die ein Spieler an einer Box neben seiner Hauptwette platzieren kann.
 * Spielt ein Spieler eine PerfectPairsSideBet Wette dann wettet er darauf das seine
 * zwei Initialkarten seiner Hand ein Paar zeigen. Als Paar sind zwei Karten mit gleicher
 * Wertigkeit zu verstehen. Folgende Paarkonstellationen fallen unter diese Wette:
 *
 *  - Perfect Pair : zwei vollkommen identische Karten gleicher Wertigkeit. Auszahlung: 25 : 1
 *  - Colored Pair : zwei Karten mit gleicher Farbe und gleicher Wertigkeit. Auszahlung: 12 : 1
 *  - Mixed Pair : zwei Karten mit unterschiedlicher Farbe und gleicher Wertigkeit. Auszahlung: 6 : 1
 *
 */
public class PerfectPairsSideBet extends SideBet {

    /**
     * Name der Perfect Pair Nebenwette
     */
    private static final String NAME_PERFECT_PAIR = "Perfect Pair";
    /**
     * die Auszahlungsquote der Perfect Pair Nebenwette
     */
    private static final float PERFECT_PAIR_PAYOUT = 25; // 25 : 1
    /**
     * Name der Colored Pair Nebenwette
     */
    private static final String NAME_COLORED_PAIR = "Colored Pair";
    /**
     * die Auszahlungsquote der Colored Pair Nebenwette
     */
    private static final float COLORED_PAIR_PAYOUT = 12; // 12 : 1
    /**
     * Name der Mixed Pair Nebenwette
     */
    private static final String NAME_MIXED_PAIR = "Mixed Pair";
    /**
     * die Auszahlungsquote der Mixed Pair Nebenwette
     */
    private static final float MIXED_PAIR_PAYOUT = 6; // 6 : 1


    /**
     * Erzeugt eine neue Wette von einem festgelegten Wert
     * @param amount den Wert der Wette
     */
    public PerfectPairsSideBet(float amount) {
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
        Card playerFirstCard = Hand.initialFirstCard(playerHand);
        Card playerSecondCard = Hand.initialSecondCard(playerHand);

        if(playerFirstCard.matchesValue(playerSecondCard)) {
            if(playerFirstCard.matchesSuit(playerSecondCard)) {
                //perfect pair condition
                status = Status.WON;
                payout = amount * PERFECT_PAIR_PAYOUT + amount;
                setName(NAME_PERFECT_PAIR);
            } else {
                if(playerFirstCard.matchesColor(playerSecondCard)) {
                    //colored pair condition
                    status = Status.WON;
                    payout = amount * COLORED_PAIR_PAYOUT + amount;
                    setName(NAME_COLORED_PAIR);
                } else {
                    //mixed pair condition
                    status = Status.WON;
                    payout = amount * MIXED_PAIR_PAYOUT + amount;
                    setName(NAME_MIXED_PAIR);
                }
            }
        } else {
            //no perfect pairs condition apply
            status = Status.LOST;
        }
    }
}
