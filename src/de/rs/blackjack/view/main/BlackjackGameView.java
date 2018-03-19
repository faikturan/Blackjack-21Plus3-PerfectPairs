package de.rs.blackjack.view.main;

import de.rs.blackjack.model.BlackjackGameModel;
import de.rs.blackjack.view.DecisionButtonPanel;
import de.rs.blackjack.view.PlayerInfoPanel;
import de.rs.blackjack.view.box.DealerBoxPanel;
import de.rs.blackjack.view.box.PlayerBoxPanel;

import javax.swing.JPanel;
import java.awt.*;

/**
 * Created by Rene Sommerfeld on 13.03.2018.
 */
public class BlackjackGameView extends JPanel {

    private static final Color TABLE_COLOR = new Color(6, 120, 0);

    private BlackjackGameModel model;

    private JPanel hudPanel, playingAreaPanel, footerPanel;
    private JPanel playerSectionPanel, dealerSectionPanel;

    private DecisionButtonPanel decisionButtonPanel;
    private PlayerInfoPanel currentPlayerInfoPanel;

    public BlackjackGameView(BlackjackGameModel model) {
        this.model = model;

        setLayout(new BorderLayout());

        //Hud Panel
        hudPanel = new JPanel();
        hudPanel.setBackground(Color.BLACK);

        //Playing Area Panel
        playingAreaPanel = new JPanel();
        playingAreaPanel.setLayout(new GridBagLayout());

        //Footer Panel
        footerPanel = new JPanel();
        footerPanel.setLayout(new GridLayout(1, 2, 5,5));

        //Decision Button Panel
        decisionButtonPanel = new DecisionButtonPanel(model);

        //Current Player Label
        currentPlayerInfoPanel = new PlayerInfoPanel(model);

        footerPanel.add(currentPlayerInfoPanel);
        footerPanel.add(decisionButtonPanel);


        GridBagConstraints constraints = new GridBagConstraints();

        //Dealer Section
        dealerSectionPanel = new JPanel();
        dealerSectionPanel.setLayout(new GridLayout(1,1));
        dealerSectionPanel.setBackground(TABLE_COLOR);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.ipady = BlackjackGameFrame.WINDOW_HEIGHT / 4;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        constraints.anchor = GridBagConstraints.PAGE_START;

        playingAreaPanel.add(dealerSectionPanel, constraints);

        //Player Section
        playerSectionPanel = new JPanel();
        playerSectionPanel.setBackground(TABLE_COLOR);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.ipady = BlackjackGameFrame.WINDOW_HEIGHT / 2;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        constraints.anchor = GridBagConstraints.PAGE_END;

        playingAreaPanel.add(playerSectionPanel, constraints);

        add(hudPanel, BorderLayout.NORTH);
        add(playingAreaPanel, BorderLayout.CENTER);
        add(footerPanel, BorderLayout.SOUTH);

        setupPlayerSection();
        setupDealerSection();
    }

    private void setupPlayerSection() {

        int playerBoxCount = model.getBoxCount() - 1;
        playerSectionPanel.setLayout(new GridLayout(1, playerBoxCount));

        for(int i = playerBoxCount - 1; i >= 0; i--) {
            PlayerBoxPanel playerBoxPanel = new PlayerBoxPanel(model, i);
            playerSectionPanel.add(playerBoxPanel);
        }
    }

    private void setupDealerSection() {
        int boxCount = model.getBoxCount();
        dealerSectionPanel.add(new DealerBoxPanel(model, boxCount - 1));
    }

}
