package de.rs.blackjack.model;

/**
 * Created by Rene Sommerfeld on 04.03.2018.
 */
public class Test {

    public static void main(String[] args) {

        Shoe shoe = new Shoe(1);
        shoe.create();
        shoe.shuffle();

        System.out.println(shoe);

        Hand hand = new Hand();
        hand.addCard(shoe.dealCard());
        hand.addCard(shoe.dealCard());
        hand.addCard(shoe.dealCard());

        System.out.println(hand);
        System.out.println(hand.calculateScore());
        System.out.println(hand.isBust());


    }

}
