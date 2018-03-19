package de.rs.blackjack.view;

import de.rs.blackjack.model.BlackjackGameModel;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.Observable;

/**
 * Created by Rene Sommerfeld on 17.03.2018.
 */
public class PlayerInfoPanel extends ObserverPanel {

    private static final Color FONT_COLOR = Color.WHITE;
    private static final Font TEXT_LABEL_FONT = new Font(Font.MONOSPACED, Font.BOLD, 14);
    private static final Font VALUE_LABEL_FONT = new Font(Font.MONOSPACED, Font.BOLD, 20);

    private JPanel currentPlayerPanel;
    private JLabel currentPlayerNameTextLabel, currentPlayerNameValueLabel;
    private JPanel currentBalancePanel;
    private JLabel currentBalanceTextLabel, currentBalanceValueLabel;
    private JPanel currentBetPanel;
    private JLabel currentBetTextLabel, currentBetValueLabel;

    public PlayerInfoPanel(BlackjackGameModel model) {
        super(model);

        setLayout(new GridLayout(1, 2, 10, 10));

        GridLayout panelLayout = new GridLayout(2, 1, 5,5);

        currentPlayerPanel = new JPanel();
        currentPlayerPanel.setBackground(Color.DARK_GRAY);
        currentPlayerPanel.setLayout(panelLayout);

        currentPlayerNameTextLabel = new JLabel("Player :");
        currentPlayerNameTextLabel.setFont(TEXT_LABEL_FONT);
        currentPlayerNameTextLabel.setForeground(FONT_COLOR);
        currentPlayerNameTextLabel.setHorizontalAlignment(JLabel.CENTER);
        currentPlayerPanel.add(currentPlayerNameTextLabel);

        currentPlayerNameValueLabel = new JLabel();
        currentPlayerNameValueLabel.setFont(VALUE_LABEL_FONT);
        currentPlayerNameValueLabel.setForeground(FONT_COLOR);
        currentPlayerNameValueLabel.setHorizontalAlignment(JLabel.CENTER);
        currentPlayerPanel.add(currentPlayerNameValueLabel);

        currentBalancePanel = new JPanel();
        currentBalancePanel.setBackground(Color.DARK_GRAY);
        currentBalancePanel.setLayout(panelLayout);

        currentBalanceTextLabel = new JLabel("Balance :");
        currentBalanceTextLabel.setFont(TEXT_LABEL_FONT);
        currentBalanceTextLabel.setForeground(FONT_COLOR);
        currentBalanceTextLabel.setHorizontalAlignment(JLabel.CENTER);
        currentBalancePanel.add(currentBalanceTextLabel);

        currentBalanceValueLabel = new JLabel();
        currentBalanceValueLabel.setFont(VALUE_LABEL_FONT);
        currentBalanceValueLabel.setForeground(FONT_COLOR);
        currentBalanceValueLabel.setHorizontalAlignment(JLabel.CENTER);
        currentBalancePanel.add(currentBalanceValueLabel);

        currentBetPanel = new JPanel();
        currentBetPanel.setBackground(Color.DARK_GRAY);
        currentBetPanel.setLayout(panelLayout);

        currentBetTextLabel = new JLabel("Bet :");
        currentBetTextLabel.setFont(TEXT_LABEL_FONT);
        currentBetTextLabel.setForeground(FONT_COLOR);
        currentBetTextLabel.setHorizontalAlignment(JLabel.CENTER);
        currentBetPanel.add(currentBetTextLabel);

        currentBetValueLabel = new JLabel();
        currentBetValueLabel.setFont(VALUE_LABEL_FONT);
        currentBetValueLabel.setForeground(FONT_COLOR);
        currentBetValueLabel.setHorizontalAlignment(JLabel.CENTER);
        currentBetPanel.add(currentBetValueLabel);


        setLabelValue(currentBetValueLabel, 9686864448686.65);

        add(currentPlayerPanel);
        add(currentBalancePanel);
        add(currentBetPanel);

    }

    private void setLabelValue(JLabel label, double balance) {
        String balanceString = DecimalFormat.getCurrencyInstance().format(balance);
        float fontSize = label.getFont().getSize();
        fontSize = (balanceString.length() > 14) ? fontSize - (balanceString.length() - 14) * 0.85f : fontSize;
        label.setFont(label.getFont().deriveFont(fontSize));
        label.setText(balanceString);
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
