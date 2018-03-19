package de.rs.blackjack.view.box;

import de.rs.blackjack.model.BlackjackGameModel;
import de.rs.blackjack.view.main.BlackjackGameView;
import de.rs.blackjack.view.renderer.HandsRenderer;

import javax.swing.*;
import java.awt.*;
import java.util.Observer;

/**
 * Created by Rene Sommerfeld on 17.03.2018.
 */
public abstract class BoxPanel extends JPanel implements Observer {

    protected BlackjackGameModel model;
    protected HandsRenderer handsRenderer;
    protected int identificationIndex;

    public BoxPanel(BlackjackGameModel model, int identificationIndex) {
        this.model = model;
        this.identificationIndex = identificationIndex;
        setBackground(new Color(6, 120, 0));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(handsRenderer != null) {
            handsRenderer.render(g);
        }
    }
}
