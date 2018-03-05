package de.rs.blackjack.model;

/**
 * Created by Rene Sommerfeld on 04.03.2018.
 */
public class Test {

    public static void main(String[] args) {

        Shoe shoe = new Shoe(8);
        shoe.create();
        shoe.shuffle();

        System.out.println(shoe);


    }

}
