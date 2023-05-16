package it.unibo.view.game_screen;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

/**
 * This is where the player will be able to click on the territories.
 */
public class BoardPanel extends JPanel {

    private static final double SCREEN_SCALING_PERC = 10.0;
    private static final String PATH_SEPARATOR = System.getProperty("file.separator");
    private static final String MAP_PATH = "src" + PATH_SEPARATOR + "main" + PATH_SEPARATOR + "resources" + PATH_SEPARATOR
            + "images" + PATH_SEPARATOR + "RisikoMap.jpg";

    private final JLayeredPane pane = new JLayeredPane();

    private ImageIcon map;

    /**
     * Panel that contains the map and the buttons over the names of the territories.
     * @param mapPath relative path to map.png
     */
    public BoardPanel() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        map = new ImageIcon(adjustImageSize(new ImageIcon(MAP_PATH), (int) screenSize.getWidth(), (int) screenSize.getHeight()));

        JLabel label = new JLabel(map);
        label.setBounds(0, 0, map.getIconWidth(), map.getIconHeight());
        pane.add(label, Integer.valueOf(0));    // Puts the map on the lowest layer (0)
        pane.setPreferredSize(new Dimension(map.getIconWidth(), map.getIconHeight()));

        this.add(pane);
    }

    /**
     * Adjusts the map.png size to the screen resolution (in %).
     * @param map the ImageIcon of the map
     * @param width the starting width (the one used to scale the new one)
     * @param height the starting width (the one used to scale the new one)
     * @return the adjusted image
     */
    private Image adjustImageSize(final ImageIcon map, final int width, final int height) {
        int newWidth = (int) (width * SCREEN_SCALING_PERC / 100);
        int newHeight = (int) (width * SCREEN_SCALING_PERC / 100);
        return map.getImage().getScaledInstance((int) width - newWidth, (int) height - newHeight, java.awt.Image.SCALE_SMOOTH);
    }

}
