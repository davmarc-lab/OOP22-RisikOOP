package it.unibo.view.game_screen.impl;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import it.unibo.controller.gamecontroller.api.MainController;
import it.unibo.controller.reader.impl.JsonReaderCoordinates;
import it.unibo.controller.reader.impl.JsonReaderSquareCoordinates;
import it.unibo.view.game_screen.api.BoardZone;
import it.unibo.view.game_screen.api.CustomButton;
import it.unibo.view.viewconstants.ViewConstants;

/**
 * Implementation of {@link BoardPanel} interface, where the player will be able
 * to click on the territories.
 */
public class BoardPanel extends JPanel implements BoardZone, Cloneable {

    private static final long serialVersionUID = 1L;
    private static final String MAP_PATH = new StringBuilder(ViewConstants.RESOURCES_PATH).append("images")
            .append(ViewConstants.PATH_SEPARATOR).append("RisikoMap.jpg").toString();
    private static final double WIDTH_SCALING = 0.9;
    private static final double HEIGHT_SCALING = 0.8;
    private static final int BUTTON_BORDER_SIZE = 2;
    private static final double LABEL_SCALING = 1.04;
    private static final int FONT_SIZE = 14;

    private final Map<CustomButton, String> territories = new HashMap<>();
    private final Map<String, JLabel> squares = new HashMap<>();
    private final JLayeredPane pane = new JLayeredPane();
    private final transient MainController controller;

    /**
     * Constructs a {@code BoardPanel} containing the map and the buttons over the
     * names of the territories.
     * 
     * @param controller the main controller
     */
    public BoardPanel(final MainController controller) {
        this.controller = controller;
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final ImageIcon map = new ImageIcon(adjustImageSize(new ImageIcon(MAP_PATH), (int) screenSize.getWidth(),
                (int) screenSize.getHeight()));

        final JLabel label = new JLabel(map);
        label.setBounds(0, 0, map.getIconWidth(), map.getIconHeight());

        loadButtons(map.getIconWidth(), map.getIconHeight());
        loadLabels(map.getIconWidth(), map.getIconHeight());
        // Puts all buttons and labels on layer 1 (above the map)
        this.territories.keySet().forEach(b -> this.pane.add((JButton) b, Integer.valueOf(1)));
        this.squares.values().forEach(t -> this.pane.add(t, Integer.valueOf(1)));

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
                Image.SCALE_SMOOTH);
    }

    /**
     * Creates the JButtons that will contain the number of troops on a territory.
     * 
     * @param width  map's width
     * @param height map's height
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
     * 
     * @param width  map's width
     * @param height map's height
     */
    private void loadLabels(final int width, final int height) {
        new JsonReaderSquareCoordinates().readFromFile().forEach(pair -> {
            final JLabel lab = new JLabel("1", SwingConstants.CENTER);
            lab.setFont(new Font("Arial", Font.PLAIN, FONT_SIZE));
            lab.setForeground(Color.WHITE);
            lab.setBackground(Color.BLACK);
            lab.setOpaque(true);
            final int x = Double.valueOf(pair.getY().getX() * width / 100).intValue();
            final int y = Double.valueOf(pair.getY().getY() * height / 100).intValue();
            lab.setBounds(x, y, Double.valueOf(width * LABEL_SCALING / 100).intValue(),
                    Double.valueOf(width * LABEL_SCALING / 100).intValue());
            this.squares.put(pair.getX(), lab);
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
        final CustomButton b = new CustomButtonImpl(x, y, width, height);
        ((JButton) b).addActionListener(e -> {
            this.controller.sendInput(this.territories.get(b));
        });
        return b;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void disableButtons(final Set<String> territorySet) {
        this.territories.entrySet().forEach(e -> {
            if (territorySet.contains(e.getValue())) {
                ((JButton) e.getKey()).setEnabled(false);
                ((JButton) e.getKey()).setBorderPainted(false);
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void enableAll() {
        this.territories.entrySet().forEach(e -> {
            ((JButton) e.getKey()).setEnabled(true);
            ((JButton) e.getKey()).setBorder(new LineBorder(Color.WHITE, BUTTON_BORDER_SIZE));
            ((JButton) e.getKey()).setBorderPainted(true);
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void disableAll() {
        this.territories.entrySet().forEach(e -> {
            ((JButton) e.getKey()).setEnabled(false);
            ((JButton) e.getKey()).setBorderPainted(false);
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MainController getController() {
        return this.controller;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setTroopsView() {
        this.controller.getGameLoop().getBoard().getAllPlayers().forEach(
                p -> p.getTerritories().forEach(
                        t -> this.getLabel(t.getName()).setForeground(new Color(p.getColorPlayer().getRedValue(),
                                p.getColorPlayer().getGreenValue(), p.getColorPlayer().getBlueValue()))));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateTroopsView(final String territory) {
        final int troops = this.controller.getGameLoop().getBoard().getGameTerritories().getTerritories().stream()
                .filter(t -> t.getName().equals(territory)).findAny().get().getTroops();
        this.getLabel(territory).setText(String.valueOf(troops));
        this.getLabel(territory).setForeground(this.getPlayerColor(territory));
    }

    /**
     * Retrieves the label relative to a certain territory
     * 
     * @param t the name of the territory
     * @return the label
     */
    private JLabel getLabel(final String t) {
        return this.squares.get(t);
    }

    /**
     * Retrievs the color of the player that possesses a certain territory.
     * 
     * @param territory the name of the territory
     * @return the color of the player
     */
    private Color getPlayerColor(final String territory) {
        return new Color(this.controller.getPlayerFromTerritory(territory).getColorPlayer().getRedValue(),
                this.controller.getPlayerFromTerritory(territory).getColorPlayer().getGreenValue(),
                this.controller.getPlayerFromTerritory(territory).getColorPlayer().getBlueValue());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Dimension getDimension() {
        return this.getPreferredSize();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BoardPanel clone() throws CloneNotSupportedException {
        return (BoardPanel) super.clone();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BoardZone getCopy() {
        try {
            return (BoardZone) this.clone();
        } catch (CloneNotSupportedException e) {
            Logger.getLogger(SideBar.class.getName()).log(Level.SEVERE, "Cannot create a copy of the object");
        }
        throw new IllegalCallerException("Cannot create a copy");
    }

}
