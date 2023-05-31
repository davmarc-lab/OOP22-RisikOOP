package it.unibo.view.game_screen.impl;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import it.unibo.common.Constants;
import it.unibo.controller.api.MainController;
import it.unibo.controller.reader.impl.JsonReaderCoordinates;
import it.unibo.controller.reader.impl.JsonReaderSquareCoordinates;
import it.unibo.model.territory.api.Territory;
import it.unibo.view.game_screen.api.BoardZone;
import it.unibo.view.game_screen.api.CustomButton;

/**
 * This is where the player will be able to click on the territories.
 */
public final class BoardPanel extends JPanel implements BoardZone {

    private static final double WIDTH_SCALING = 0.9;
    private static final double HEIGHT_SCALING = 0.8;
    private static final int BUTTON_BORDER_SIZE = 2;
    private static final int LABEL_SIZE = 20;

    private final Map<CustomButton, String> territories = new HashMap<>();
    private final Map<JLabel, String> squares = new HashMap<>();
    private final JLayeredPane pane = new JLayeredPane();
    private MainController controller;

    private ImageIcon map;

    /**
     * Panel that contains the map and the buttons over the names of the
     * territories.
     */
    public BoardPanel() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        map = new ImageIcon(adjustImageSize(new ImageIcon(Constants.MAP_PATH), (int) screenSize.getWidth(),
                (int) screenSize.getHeight()));

        JLabel label = new JLabel(map);
        label.setBounds(0, 0, map.getIconWidth(), map.getIconHeight());

        loadButtons(map.getIconWidth(), map.getIconHeight());
        loadLabels(map.getIconWidth(), map.getIconHeight());
        // Puts all buttons and labels on layer 1 (above the map)
        for (var jb : this.territories.keySet()) {
            this.pane.add(((JButton) jb), Integer.valueOf(1));
        }
        for (var lb : this.squares.keySet()) {
            this.pane.add(lb, Integer.valueOf(1));
        }

        this.pane.add(label, Integer.valueOf(0)); // Puts the map on the lowest layer (0)
        this.pane.setPreferredSize(new Dimension(map.getIconWidth(), map.getIconHeight()));

        this.add(this.pane);
    }

    /**
     * Adjusts the map.png size.
     * 
     * @param map    the ImageIcon of the map
     * @param width  the starting width (the one used to scale the new one)
     * @param height the starting width (the one used to scale the new one)
     * @return the adjusted image
     */
    private Image adjustImageSize(final ImageIcon map, final int width, final int height) {
        return map.getImage().getScaledInstance((int) (width * WIDTH_SCALING), (int) (height * HEIGHT_SCALING),
                java.awt.Image.SCALE_SMOOTH);
    }

    /**
     * Creates the JButtons that will contain the number of troops on a territory.
     */
    private void loadButtons(final int width, final int height) {
        new JsonReaderCoordinates().readFromFile().forEach(p -> {
            final Iterator<Double> it = p.getY().iterator();
            final int x = Double.valueOf(it.next() * width / 100).intValue();
            final int y = Double.valueOf(it.next() * height / 100).intValue();
            final int w = Double.valueOf(it.next() * width / 100).intValue();
            final int h = Double.valueOf(it.next() * height / 100).intValue();
            this.territories.put(createButton(x, y, w, h), p.getX());
        });
    }

    /**
     * Creates the JLabels that will contain the number of troops on a territory.
     */
    private void loadLabels(final int width, final int height) {
        new JsonReaderSquareCoordinates().readFromFile().forEach(pair -> {
            final JLabel lab = new JLabel();
            lab.setFont(new Font("Arial", Font.PLAIN, 14));
            lab.setText("10");
            lab.setForeground(Color.WHITE);
            lab.setBackground(Color.BLACK);
            lab.setOpaque(true);
            final int x = Double.valueOf(pair.getY().getX() * width / 100).intValue();
            final int y = Double.valueOf(pair.getY().getY() * height / 100).intValue();
            lab.setBounds(x, y, LABEL_SIZE, LABEL_SIZE);
            this.squares.put(lab, pair.getX());
        });
    }

    /**
     * Creates and invisible button.
     * 
     * @param x      horizontal position
     * @param y      vertical position
     * @param width  button width
     * @param height button height
     * @return invisible JButton with specified values
     */
    private CustomButton createButton(final int x, final int y, final int width, final int height) {
        CustomButton b = new CustomButtonImpl(x, y, width, height);
        ((JButton) b).addActionListener(e -> {
            this.controller.sendInput(this.territories.get(b));
        });
        return b;
    }

    @Override
    public void disableButtons(final Set<Territory> territorySet) {
        Set<String> territoryNames = new HashSet<>();
        territorySet.forEach(t -> territoryNames.add(t.getName()));
        this.territories.entrySet().forEach(e -> {
            if (territoryNames.contains(e.getValue())) {
                ((JButton) e.getKey()).setEnabled(false);
                ((JButton) e.getKey()).setBorderPainted(false);
            }
        });
    }

    @Override
    public void enableAll() {
        this.territories.entrySet().forEach(e -> {
            ((JButton) e.getKey()).setEnabled(true);
            ((JButton) e.getKey()).setBorder(new LineBorder(Color.WHITE, BUTTON_BORDER_SIZE));
            ((JButton) e.getKey()).setBorderPainted(true);
        });
    }

    @Override
    public void disableAll() {
        this.territories.entrySet().forEach(e -> {
            ((JButton) e.getKey()).setEnabled(false);
            ((JButton) e.getKey()).setBorderPainted(false);
        });
    }

    @Override
    public MainController getController() {
        return this.controller;
    }

    @Override
    public void setController(final MainController controller) {
        this.controller = controller;
    }

}
