package de.rs.blackjack.view.box;

import de.rs.blackjack.model.BlackjackGameModel;
import de.rs.blackjack.view.renderer.HandsRenderer;

import java.awt.*;
import java.util.Observable;

/**
 * Created by Rene Sommerfeld on 17.03.2018.
 */
public class DealerBoxPanel extends BoxPanel {

    public DealerBoxPanel(BlackjackGameModel model, int identificationIndex) {
        super(model, identificationIndex);
        handsRenderer = new HandsRenderer(250, 0,
                model.getBox(identificationIndex).getHands());
        handsRenderer.setSpreadingHorizontal(true);
        handsRenderer.setOverlaying(false);
    }

    @Override
    public void update(Observable o, Object arg) {

    }



}
