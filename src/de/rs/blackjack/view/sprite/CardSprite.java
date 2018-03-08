package de.rs.blackjack.view.sprite;

import de.rs.blackjack.model.cards.Card;

import java.awt.image.BufferedImage;

/**
 * Created by Rene Sommerfeld on 06.03.2018.
 */
public class CardSprite {

    private BufferedImage sprite;
    private int width, height, hPadding, vPadding;

    public CardSprite(BufferedImage sprite, int width, int height, int hPadding, int vPadding) {
        this.sprite = sprite;
        this.width = width;
        this.height = height;
        this.vPadding = vPadding;
        this.hPadding = hPadding;
    }

    public BufferedImage getCard(Card card) {
        if(card != null) {
            int row = card.getSuit().ordinal();
            int column = card.getValue().ordinal();
            return sprite.getSubimage((column * width) + hPadding * column, (row * height) + vPadding * row, width, height);
        }
        return null;
    }

}
