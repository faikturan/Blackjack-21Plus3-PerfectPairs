package de.rs.blackjack.model.bets;

/**
 * Created by Rene Sommerfeld on 06.03.2018.
 *
 * Die SideBet Klasse repräsentiert eine abstrakte Form einer Nebenwette,
 * die ein Spieler an einer Box neben seiner Hauptwette platzieren kann.
 * Diese ist komplett unabhängig von der Hauptwette - sprich verliert der
 * Spieler seine Hauptwette kann die Nebenwette trotzdem gewonnen werden.
 */
public abstract class SideBet extends Bet {

    /**
     * der Name der Nebenwette
     */
    private String name;

    /**
     * Erzeugt eine neue Wette von einem festgelegten Wert
     * @param amount den Wert der Wette
     */
    public SideBet(float amount) {
        super(amount);
    }

    /**
     * Setzt den Namen der Nebenwette
     * @param name den Namen
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gibt den Namen der Nebenwette zurück
     * @return den Namen
     */
    public String getName() {
        return name;
    }

}
