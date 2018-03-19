package de.rs.blackjack.view.renderer;

import de.rs.blackjack.model.cards.Card;
import de.rs.blackjack.model.hands.Hand;
import de.rs.blackjack.view.CardSprite;

import java.awt.*;

/**
 * Created by Rene Sommerfeld on 18.03.2018.
 */
public class HandsRenderer {

    private static final float MAX_OVERLAY_X = 65;
    private static final float MAX_OVERLAY_Y = 65;
    private static final float MAX_OVERLAY_AT_COUNT = 2.5f;

    private Hand[] hands;
    private int startX, startY;
    private boolean spreadingHorizontal, spreadingVertical, horizontalOverlaying, verticalOverlaying;

    public HandsRenderer(int startX, int startY, Hand... hands) {
        this.hands = hands;
        this.startX = startX;
        this.startY = startY;
    }

    public void setOverlaying(boolean overlaying) {
        setHorizontalOverlaying(overlaying);
        setVerticalOverlaying(overlaying);
    }

    public void setHorizontalOverlaying(boolean horizontalOverlaying) {
        this.horizontalOverlaying = horizontalOverlaying;
    }

    public void setVerticalOverlaying(boolean verticalOverlaying) {
        this.verticalOverlaying = verticalOverlaying;
    }

    public void setSpreadingHorizontal(boolean spreadingHorizontal) {
        this.spreadingHorizontal = spreadingHorizontal;
    }

    public void setSpreadingVertical(boolean spreadingVertical) {
        this.spreadingVertical = spreadingVertical;
    }

    public void render(Graphics g) {
        renderHands(g);
    }

    private void renderHands(Graphics g) {
        int horizontalSpreadingFlag = (spreadingHorizontal) ? 1 : 0;
        int horizontalOverlay = (horizontalOverlaying) ? 1 : 0;
        int verticalOverlay = (verticalOverlaying) ? 1 : 0;
        int verticalSpreadingFlag = (spreadingVertical) ? 1 : 0;

        int x = startX;
        int y = startY;

        for(int i = 0; i < hands.length; i++) {
            Hand hand = hands[i];
            if(hand != null) {
                int cardCount = hand.getCardCount();
                int overlayX = (int) Math.min(MAX_OVERLAY_X * (cardCount / MAX_OVERLAY_AT_COUNT), MAX_OVERLAY_X)
                        * horizontalSpreadingFlag * horizontalOverlay;
                int overlayY = (int) Math.min(MAX_OVERLAY_Y * (cardCount / MAX_OVERLAY_AT_COUNT), MAX_OVERLAY_Y)
                        * verticalSpreadingFlag * verticalOverlay;
                int spanHorizontal = CardSprite.WIDTH * horizontalSpreadingFlag;
                int spanVertical = CardSprite.HEIGHT * verticalSpreadingFlag;
                for(int j = 0; j < cardCount; j++) {
                    int nextX = x + (spanHorizontal - overlayX) * j;
                    int nextY = y + (spanVertical - overlayY) * j;
                    Card card = hand.getCard(j);
                    renderCard(g, card, nextX, nextY);
                    if(j == cardCount - 1) {
                        renderHandScore(g, hand.getScoreString(),
                                nextX + CardSprite.WIDTH, nextY + CardSprite.HEIGHT);
                    }
                }
                x += (spanHorizontal - overlayX) * (cardCount + 1) + 60;
            }
        }
    }

    private void renderCard(Graphics g, Card card, int x, int y) {
        if(card.isFlipped()) {
            g.setColor(Color.RED);
            g.fill3DRect(x, y, CardSprite.WIDTH, CardSprite.HEIGHT, true);
        } else {
            g.drawImage(CardSprite.getInstance().getCard(card), x, y,
                    CardSprite.WIDTH, CardSprite.HEIGHT, null);
        }
        g.draw3DRect(x, y, CardSprite.WIDTH, CardSprite.HEIGHT, true);
    }

    private void renderHandScore(Graphics g, String scoreString, int x, int y) {
        g.setColor(Color.YELLOW);
        g.fill3DRect(x + 5, y - 14, 20, 20, true);
        g.setColor(Color.BLACK);
        g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 12));
        g.drawString(scoreString, x + 9, y);
    }



}
