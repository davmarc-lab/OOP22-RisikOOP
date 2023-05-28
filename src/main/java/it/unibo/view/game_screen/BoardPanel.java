package it.unibo.view.game_screen;

import java.awt.Color;
import java.awt.Dimension;
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
import it.unibo.controller.impl.MainControllerImpl;
import it.unibo.controller.reader.impl.JsonReaderCoordinates;
import it.unibo.model.territory.api.Territory;

/**
 * This is where the player will be able to click on the territories.
 */
public class BoardPanel extends JPanel {

    private static final double WIDTH_SCALING = 0.9;
    private static final double HEIGHT_SCALING = 0.8;
    private static final int BUTTON_BORDER_SIZE = 2;

    private final Map<CustomButton, String> territories = new HashMap<>();
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
        for (var jb : this.territories.keySet()) {
            this.pane.add(((JButton) jb), Integer.valueOf(1)); // Puts all buttons on layer 1 (above the map)
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

    private void loadButtons(final int width, final int height) {
        new JsonReaderCoordinates().readFromFile().forEach(p -> {
            Iterator<Double> it = p.getY().iterator();
            int x = (int) (it.next() * width / 100);
            int y = (int) (it.next() * height / 100);
            int w = (int) (it.next() * width / 100);
            int h = (int) (it.next() * height / 100);
            territories.put(createButton(x, y, w, h), p.getX());
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

    /**
     * Disables the buttons placed on the list of territories provided.
     * 
     * @param t list of territories to disable
     */
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

    /**
     * Enables all buttons.
     */
    public void enableAll() {
        this.territories.entrySet().forEach(e -> {
            ((JButton) e.getKey()).setEnabled(true);
            ((JButton) e.getKey()).setBorder(new LineBorder(Color.WHITE, BUTTON_BORDER_SIZE));
            ((JButton) e.getKey()).setBorderPainted(true);
        });
    }

    /**
     * Disables all buttons.
     */
    public void disableAll() {
        this.territories.entrySet().forEach(e -> {
            ((JButton) e.getKey()).setEnabled(false);
            ((JButton) e.getKey()).setBorderPainted(false);
        });
    }

    /**
     * @return the controller
     */
    public MainController getController() {
        return this.controller;
    }

    /**
     * Creates a controller.
     */
    public void createController() {
        this.controller = new MainControllerImpl(this);
    }
}
