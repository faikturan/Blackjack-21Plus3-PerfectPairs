package de.rs.blackjack.model;

import de.rs.blackjack.model.cards.Card;
import de.rs.blackjack.model.table.CardShoe;
import de.rs.blackjack.model.cards.CuttingCard;
import de.rs.blackjack.model.hands.Hand;
import de.rs.blackjack.model.table.Box;
import de.rs.blackjack.model.table.DealerBox;
import de.rs.blackjack.model.table.PlayerBox;
import de.rs.blackjack.model.user.Dealer;
import de.rs.blackjack.model.user.Player;

/**
 * Created by Rene Sommerfeld on 10.03.2018.
 */
public class BlackjackTable {

    public static final int DECK_COUNT = 8;

    private boolean roundStarted;

    private Box[] boxes;
    private int currentBoxIndex;

    private CardShoe cardShoe;
    private boolean cuttingCardDrawn;

    public BlackjackTable(int playerBoxCount) {
        boxes = new Box[playerBoxCount + 1];
        cardShoe = new CardShoe(DECK_COUNT);
        currentBoxIndex = 0;
        roundStarted = false;
        cuttingCardDrawn = false;
        initializeBoxes();
        initializeCardShoe();
    }

    private void initializeBoxes() {
        for(int i = 0; i < boxes.length - 1; i++) {
            boxes[i] = new PlayerBox();
        }
        boxes[boxes.length - 1] = new DealerBox();
    }

    private void initializeCardShoe() {
        cardShoe.populateCards();
        cardShoe.shuffle();
    }

    private void makeCardShoeChange() {
        initializeCardShoe();
        cuttingCardDrawn = false;
    }

    public boolean addPlayer(Player player) {
        if(!roundStarted) {
            for(int i = 0; i < boxes.length - 1; i++) {
                if(!boxes[i].isOccupied()) {
                    boxes[i].occupy(player);
                    return true;
                }
            }
        }
        return false;
    }

    public void setDealer(Dealer dealer) {
        if(!boxes[boxes.length - 1].isOccupied()) {
            boxes[boxes.length - 1].occupy(dealer);
        }
    }

    public void startNewRound() {
        if(cuttingCardDrawn) {
            makeCardShoeChange();
        } else {
            if(!roundStarted) {
                roundStarted = true;
                dealInitialCards();
            }
        }
    }

    public void nextPlayer() {

    }

    private void dealInitialCards() {
        for(int i = 0; i < Hand.INITIAL_CARD_COUNT; i++) {
            for(Box box : boxes) {
                if(box.isOccupied()) {
                    int cardsToDraw = 1;
                    Card nextCard = null;
                    do {
                        nextCard = cardShoe.dealCard();
                        cardsToDraw--;
                        if(nextCard instanceof CuttingCard) {
                            cuttingCardDrawn = true;
                            cardsToDraw++;
                        }
                    } while(cardsToDraw > 0);
                    box.addCard(nextCard);
                }
            }
        }
    }

    public Box[] getBoxes() {
        return boxes;
    }

    public boolean isCuttingCardDrawn() {
        return cuttingCardDrawn;
    }

    public boolean isRoundStarted() {
        return roundStarted;
    }
}
