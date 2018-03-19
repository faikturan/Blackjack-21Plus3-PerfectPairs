package de.rs.blackjack.view;

import de.rs.blackjack.model.cards.Card;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Rene Sommerfeld on 06.03.2018.
 */
public class CardSprite {

    public static final int WIDTH = 72;
    public static final int HEIGHT = 97;
    public static final int PADDING = 1;

    private BufferedImage sprite;
    private int width, height, hPadding, vPadding;

    private static CardSprite instance;


    private CardSprite() {
        try {
            this.sprite = ImageIO.read(new File("playing-card-sprite.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.width = WIDTH;
        this.height = HEIGHT;
        this.vPadding = PADDING;
        this.hPadding = PADDING;
    }

    public BufferedImage getCard(Card card) {
        if(card != null) {
            int row = card.getSuit().ordinal();
            int column = card.getValue().ordinal();
            return sprite.getSubimage((column * width) + hPadding * column, (row * height) + vPadding * row, width, height);
        }
        return null;
    }

    public static CardSprite getInstance() {
        if(instance == null) {
            instance = new CardSprite();
        }
        return instance;
    }

}
