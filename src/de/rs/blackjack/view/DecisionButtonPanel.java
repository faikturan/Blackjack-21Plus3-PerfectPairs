package de.rs.blackjack.view;

import de.rs.blackjack.model.BlackjackGameModel;
import de.rs.blackjack.model.table.Box;

import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * Created by Rene Sommerfeld on 17.03.2018.
 */
public class DecisionButtonPanel extends ObserverPanel {

    private JButton doubleDownButton, hitButton, standButton, splitButton;

    public DecisionButtonPanel(BlackjackGameModel model) {
        super(model);


        setLayout(new GridLayout(1, 4));

        Image scaledImage;

        doubleDownButton = new JButton("Double Down");
        scaledImage = new ImageIcon("assets/buttons/double.png").getImage()
                .getScaledInstance(32, 32, Image.SCALE_SMOOTH);
        doubleDownButton.setIcon(new ImageIcon(scaledImage));
        add(doubleDownButton);

        hitButton = new JButton("Hit");
        scaledImage = new ImageIcon("assets/buttons/hit.png").getImage()
                .getScaledInstance(32, 32, Image.SCALE_SMOOTH);
        hitButton.setIcon(new ImageIcon(scaledImage));
        add(hitButton);

        standButton = new JButton("Stand");
        scaledImage = new ImageIcon("assets/buttons/stand.png").getImage()
                .getScaledInstance(32, 32, Image.SCALE_SMOOTH);
        standButton.setIcon(new ImageIcon(scaledImage));
        add(standButton);

        splitButton = new JButton("Split");
        scaledImage = new ImageIcon("assets/buttons/split.png").getImage()
                .getScaledInstance(32, 32, Image.SCALE_SMOOTH);
        splitButton.setIcon(new ImageIcon(scaledImage));
        add(splitButton);

    }

    @Override
    public void update(Observable o, Object arg) {

    }

    private void applyAllAvailableDecisions(Set<Box.Decision> decisions) {
        boolean onlyInsurance = decisions.size() == 2 &&
                decisions.contains(Box.Decision.TAKE_INSURANCE) &&
                decisions.contains(Box.Decision.DENY_INSURANCE);

        if(onlyInsurance) {
            hitButton.setEnabled(true);
            hitButton.setText("Take Ins.");
            standButton.setEnabled(true);
            standButton.setText("Deny Ins.");

            doubleDownButton.setEnabled(false);
            doubleDownButton.setVisible(false);
            splitButton.setEnabled(false);
            splitButton.setVisible(false);
        } else {
            boolean doubleDownAvailable = decisions.contains(Box.Decision.DOUBLE_DOWN);
            doubleDownButton.setEnabled(doubleDownAvailable);
            doubleDownButton.setVisible(doubleDownAvailable);
            boolean hitAvailable = decisions.contains(Box.Decision.HIT);
            hitButton.setEnabled(hitAvailable);
            hitButton.setVisible(hitAvailable);
            boolean standAvailable = decisions.contains(Box.Decision.STAND);
            standButton.setEnabled(standAvailable);
            standButton.setVisible(standAvailable);
            boolean splitAvailable = decisions.contains(Box.Decision.SPLIT);
            splitButton.setEnabled(splitAvailable);
            splitButton.setVisible(splitAvailable);
        }
    }
}
