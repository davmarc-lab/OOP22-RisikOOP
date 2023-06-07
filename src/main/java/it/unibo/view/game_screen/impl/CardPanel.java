package it.unibo.view.game_screen.impl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import it.unibo.common.Pair;
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
     * Button to confirm the cards played.
     */
    private final JButton playCards = new JButton("Play");

    /**
     * Button to reset the cards played.
     */
    private final JButton resetCards = new JButton("Reset");

    /**
     * Map containing for each key-value the row buttons and labels.
     */
    private final Map<Pair<JLabel, JButton>, JLabel> map = new HashMap<>();

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
        cnst.insets = new Insets(2, 2, 2, 2);
        IntStream.range(0, 3).boxed().forEach(i -> {
            map.put(new Pair<>(new JLabel(DEFAUL_STRING), new JButton(DEFAUL_STRING)), new JLabel(DEFAUL_STRING));
        });
        map.keySet().forEach(t -> t.getY().setMargin(buttonInsets));
        map.forEach((k, v) -> {
            cardsPanel.add(k.getX(), cnst);
            cardsPanel.add(k.getY(), cnst);
            cardsPanel.add(v, cnst);
            cnst.gridy++;
        });
        playCards.setEnabled(false);
        resetCards.setEnabled(false);

        map.forEach((k, v) -> {
            k.getY().setEnabled(false);
            k.getY().addActionListener(e -> {
                if (this.phc.isAddPossible(Integer.parseInt(k.getX().getText()), Integer.parseInt(v.getText()))) {
                    this.phc.addInputCard(k.getY().getText());
                    v.setText(String.valueOf(this.phc.getNumberOfCards(k.getY().getText())));
                }
                this.updateInputCards();
                this.updateButtons();
            });
        });

        playCards.addActionListener(e -> {
            this.controller.addBonustTroops(this.phc.attemptPlayCards());
            this.updateInputCards();
            this.controller.sendMessage(this.phc.getMessage());
            this.resetUserInput();
        });

        resetCards.addActionListener(e -> {
            this.resetUserInput();
        });

        final JPanel playPanel = new JPanel(new BorderLayout());
        playPanel.add(playCards, BorderLayout.WEST);
        playPanel.add(resetCards, BorderLayout.EAST);

        cardsPanel.setBackground(Color.WHITE);
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
        IntStream.range(0, 3).boxed().forEach(i -> {
            final JButton b = this.map.entrySet().stream().collect(Collectors.toList()).get(i).getKey().getY();
            if (DEFAUL_STRING.equals(b.getText())) {
                b.setText(this.phc.getCardName(i));
            }
        });
        this.updateInputCards();
        this.updateButtons();
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
     * Updates the input number of cards.
     */
    private void updateInputCards() {
        this.map.forEach((k, v) -> {
            k.getX().setText(String.valueOf(this.phc.getNumberOfCards(k.getY().getText())));
            k.getY().setEnabled(!this.phc.isInputFull());
            v.setText(String.valueOf(this.phc.getInputCards(k.getY().getText())));
        });
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
        this.map.forEach((k, v) -> {
            k.getX().setText(String.valueOf(this.phc.getNumberOfCards(k.getY().getText())));
            k.getY().setEnabled(true);
            v.setText("0");
        });
        this.phc.clearInputCards();
    }
}
