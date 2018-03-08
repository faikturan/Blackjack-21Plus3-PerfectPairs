package de.rs.blackjack.model.table;

/**
 * Created by Rene Sommerfeld on 07.03.2018.
 */
public abstract class User {

    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
