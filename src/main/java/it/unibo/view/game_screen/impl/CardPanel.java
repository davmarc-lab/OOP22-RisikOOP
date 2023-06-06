package it.unibo.view.game_screen.impl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import it.unibo.controller.gamecontroller.api.MainController;
import it.unibo.controller.playerhand.api.PlayerHandController;
import it.unibo.controller.playerhand.impl.PlayerHandControllerImpl;
import it.unibo.view.game_screen.api.CardZone;

/**
 * Implementation of {@link CardZone} interface.
 * It provides method to update the view and set the
 * {@link PlayerHandController}.
 */
public class CardPanel extends JPanel implements CardZone, Cloneable {

    private static final long serialVersionUID = 1L;

    private static final double HEIGHT_SCALING = 0.3;
    private static final String CARD_LABEL = "PLAYER HAND";
    private static final int BORDER_SIZE = 4;
    private static final String DEFAUL_STRING = "NaN";
    /**
     * Label that shows the number of the obtained cards of type {@code INFANTRY}.
     */
    private final JLabel firstText = new JLabel(DEFAUL_STRING);

    /**
     * Label that shows the number of the obtained cards of type {@code CAVALRY}.
     */
    private final JLabel secondText = new JLabel(DEFAUL_STRING);

    /**
     * Label that shows the number of the obtained cards of type {@code ARTILLERY}.
     */
    private final JLabel thirdText = new JLabel(DEFAUL_STRING);

    /**
     * Button of type {@code INFANTRY}.
     */
    private final JButton firstCard = new JButton("Infantry");

    /**
     * Button of type {@code CAVALRY}.
     */
    private final JButton secondCard = new JButton("Cavalry");

    /**
     * Button of type {@code ARTILLERY}.
     */
    private final JButton thirdCard = new JButton("Artillery");

    /**
     * Button to confirm the cards played.
     */
    private final JButton playCards = new JButton("Play");

    /**
     * Button to reset the cards played.
     */
    private final JButton resetCards = new JButton("Reset");

    /**
     * Label that shows the number of cards of type {@code INFANTRY} played.
     */
    private final JLabel firstPlayed = new JLabel(DEFAUL_STRING);

    /**
     * Label that shows the number of cards of type {@code CAVALRY} played.
     */
    private final JLabel secondPlayed = new JLabel(DEFAUL_STRING);

    /**
     * Label that shows the number of cards of type {@code ARTILLERY} played.
     */
    private final JLabel thirdPlayed = new JLabel(DEFAUL_STRING);

    private final transient MainController controller;
    private transient PlayerHandController phc;

    /**
     * Constructs a {@code CardPanel} object providing the panel size and the main
     * controller.
     * 
     * @param size       panel size
     * @param controller main controller
     */
    public CardPanel(final Dimension size, final MainController controller) {
        super();
        this.controller = controller.getCopy();
        this.setLayout(new BorderLayout());
        final Dimension dim = new Dimension(Double.valueOf(size.getWidth()).intValue(),
                Double.valueOf(size.getHeight() * HEIGHT_SCALING).intValue());
        this.setPreferredSize(dim);
        this.add(new JLabel(CARD_LABEL, SwingConstants.CENTER), BorderLayout.NORTH);
        this.setBorder(new LineBorder(Color.BLACK, BORDER_SIZE));

        final JPanel cardsPanel = new JPanel(new GridBagLayout());
        final GridBagConstraints cnst = new GridBagConstraints();
        cnst.gridy = 0;
        final int n = (int) (dim.getHeight() * 0.05);
        final Insets buttonInsets = new Insets(n, n, n, n);
        firstCard.setMargin(buttonInsets);
        secondCard.setMargin(buttonInsets);
        thirdCard.setMargin(buttonInsets);
        cnst.insets = new Insets(2, 2, 2, 2);

        cardsPanel.add(this.firstText, cnst);
        cardsPanel.add(firstCard, cnst);
        cardsPanel.add(this.firstPlayed, cnst);
        cnst.gridy++;

        cardsPanel.add(this.secondText, cnst);
        cardsPanel.add(secondCard, cnst);
        cardsPanel.add(this.secondPlayed, cnst);
        cnst.gridy++;
        cardsPanel.add(this.thirdText, cnst);
        cardsPanel.add(thirdCard, cnst);
        cardsPanel.add(this.thirdPlayed, cnst);
        cnst.gridy++;

        firstCard.setEnabled(false);
        secondCard.setEnabled(false);
        thirdCard.setEnabled(false);
        playCards.setEnabled(false);
        resetCards.setEnabled(false);

        firstCard.addActionListener(e -> {
            if (this.phc.isAddPossible(this.phc.getPlayerFirstCards(), Integer.parseInt(this.firstPlayed.getText()))) {
                this.phc.addInputCard(firstCard.getText());
                this.firstPlayed.setText(String.valueOf(this.phc.getNumberOfCards(firstCard.getText())));
            }
            this.updateButtons();
        });

        secondCard.addActionListener(e -> {
            if (this.phc.isAddPossible(Integer.parseInt(secondText.getText()),
                    Integer.parseInt(this.secondPlayed.getText()))) {
                this.phc.addInputCard(secondCard.getText());
                this.secondPlayed.setText(String.valueOf(this.phc.getNumberOfCards(secondCard.getText())));
            }
            this.updateButtons();
        });

        thirdCard.addActionListener(e -> {
            if (this.phc.isAddPossible(Integer.parseInt(thirdText.getText()),
                    Integer.parseInt(this.thirdPlayed.getText()))) {
                this.phc.addInputCard(thirdCard.getText());
                this.thirdPlayed.setText(String.valueOf(this.phc.getNumberOfCards(thirdCard.getText())));
            }
            this.updateButtons();
        });

        playCards.addActionListener(e -> {
            this.phc.attemptPlayCards();
            this.controller.sendMessage(this.phc.getMessage());
            this.resetUserInput();
            this.updateButtons();
        });

        resetCards.addActionListener(e -> {
            this.resetUserInput();
            this.updateButtons();
        });

        final JPanel playPanel = new JPanel(new BorderLayout());
        playPanel.add(playCards, BorderLayout.WEST);
        playPanel.add(resetCards, BorderLayout.EAST);

        this.add(cardsPanel, BorderLayout.CENTER);
        this.add(playPanel, BorderLayout.SOUTH);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setController() {
        this.phc = new PlayerHandControllerImpl(this.controller.getCurrentPlayer(), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateView() {
        this.firstCard.setEnabled(!this.phc.isInputFull());
        this.secondCard.setEnabled(!this.phc.isInputFull());
        this.thirdCard.setEnabled(!this.phc.isInputFull());
        this.firstPlayed.setText(String.valueOf(this.phc.getNumberOfCards(firstCard.getText())));
        this.secondPlayed.setText(String.valueOf(this.phc.getNumberOfCards(secondCard.getText())));
        this.thirdPlayed.setText(String.valueOf(this.phc.getNumberOfCards(thirdCard.getText())));
        this.firstText.setText(String.valueOf(this.getFirstCardCount()));
        this.secondText.setText(String.valueOf(this.getSecondCardCount()));
        this.thirdText.setText(String.valueOf(this.getThirdCardCount()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CardPanel clone() throws CloneNotSupportedException {
        return (CardPanel) super.clone();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CardZone getCopy() {
        try {
            return (CardZone) this.clone();
        } catch (CloneNotSupportedException e) {
            Logger.getLogger(CardPanel.class.getName()).log(Level.SEVERE, "Cannot create a copy of the object");
        }
        throw new IllegalCallerException("Cannot create a copy");
    }

    /**
     * Updates the confirm and reset buttons.
     */
    private void updateButtons() {
        this.resetCards.setEnabled(true);
        this.playCards.setEnabled(this.phc.isInputFull());
    }

    /**
     * Resets the played cards.
     */
    private void resetUserInput() {
        this.firstPlayed.setText("0");
        this.secondPlayed.setText("0");
        this.thirdPlayed.setText("0");
        this.phc.clearInputCards();
    }

    /**
     * Counts how many cards of type {@code INFANTRY} are in the player hand.
     * 
     * @return the number of cards of the specified type
     */
    private int getFirstCardCount() {
        return this.phc.getPlayerFirstCards();
    }

    /**
     * Counts how many cards of type {@code CAVALRY} are in the player hand.
     * 
     * @return the number of cards of the specified type
     */
    private int getSecondCardCount() {
        return this.phc.getPlayerSecondCards();
    }

    /**
     * Counts how many cards of type {@code ARTILLERY} are in the player hand.
     * 
     * @return the number of cards of the specified type
     */
    private int getThirdCardCount() {
        return this.phc.getPlayerThirdCards();
    }

}
