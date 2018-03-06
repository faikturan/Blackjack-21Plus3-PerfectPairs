package de.rs.blackjack.view;

import de.rs.blackjack.model.cards.CardDeck;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Rene Sommerfeld on 06.03.2018.
 */
public class Test {

    public static void main(String[] args) {

        JFrame frame = new JFrame();
        Panel panel = new Panel();

        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setContentPane(panel);
        frame.setVisible(true);

    }

    public static class Panel extends JPanel {

        BufferedImage image;
        BufferedImage test;


        public Panel() {
            try {
                image = ImageIO.read(new File("playing-card-sprite.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            test = grabSubImageOnSprite(image, CardDeck.Value.SEVEN.ordinal(), CardDeck.Suit.CLUBS.ordinal(), 72, 96);
        }

        public static BufferedImage grabSubImageOnSprite(BufferedImage image, int column, int row, int width, int height) {
            return image.getSubimage(((column + 1) * width) - width, ((row + 1) * height) - height, width, height);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            g.drawImage(test, 0, 0, 72, 96, null);

        }
    }

}
