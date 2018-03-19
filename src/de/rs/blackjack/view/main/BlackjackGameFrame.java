package de.rs.blackjack.view.main;

import de.rs.blackjack.model.BlackjackGameModel;

import javax.swing.*;

/**
 * Created by Rene Sommerfeld on 13.03.2018.
 */
public class BlackjackGameFrame extends JFrame {

    public static final int WINDOW_WIDTH = 1200;
    public static final int WINDOW_HEIGHT = 700;


    public BlackjackGameFrame(BlackjackGameView view) {
        setTitle("Blackjack Game Frame");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setContentPane(view);
        setVisible(true);
    }

    public static void main(String[] args) {
        BlackjackGameModel model = new BlackjackGameModel();
        BlackjackGameView view = new BlackjackGameView(model);
        model.notifyChanges();
        new BlackjackGameFrame(view);
    }

}


