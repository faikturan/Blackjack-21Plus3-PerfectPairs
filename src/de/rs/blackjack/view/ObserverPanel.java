package de.rs.blackjack.view;

import de.rs.blackjack.model.BlackjackGameModel;

import javax.swing.*;
import java.util.Observer;

/**
 * Created by Rene Sommerfeld on 17.03.2018.
 */
public abstract class ObserverPanel extends JPanel implements Observer {

    protected BlackjackGameModel model;

    public ObserverPanel(BlackjackGameModel model) {
        this.model = model;
        model.addObserver(this);
    }

}
