package de.rs.blackjack.model.table;

/**
 * Created by Rene Sommerfeld on 07.03.2018.
 */
public class Player extends User {

    private float balance;

    public Player(String name, float initialBalance) {
        super(name);
        this.balance = initialBalance;
    }

}
