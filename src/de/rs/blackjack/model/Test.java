package de.rs.blackjack.model;


import de.rs.blackjack.model.card.Shoe;
import de.rs.blackjack.model.hand.PlayerHand;

/**
 * Created by Rene Sommerfeld on 04.03.2018.
 */
public class Test {

    public static void main(String[] args) {

        Shoe shoe = new Shoe(8);
        shoe.create();
        shoe.shuffle();

        PlayerHand hand = new PlayerHand();

        for(int i = 0; i < 4; i++) {
            hand.addCard(shoe.deal());
        }

        System.out.println(hand);



    }

}
