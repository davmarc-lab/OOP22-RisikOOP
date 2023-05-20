package it.unibo.view.game_screen;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import it.unibo.model.territory.api.Territory;
import it.unibo.model.territory.api.TerritoryFactory;
import it.unibo.model.territory.impl.TerritoryFactoryImpl;

/**
 * This is where the player will be able to click on the territories.
 */
public class BoardPanel extends JPanel {

    private static final double SCREEN_SCALING_PERC = 10.0;
    private static final String PATH_SEPARATOR = System.getProperty("file.separator");
    private static final String MAP_PATH = "src" + PATH_SEPARATOR + "main" + PATH_SEPARATOR + "resources"
            + PATH_SEPARATOR
            + "images" + PATH_SEPARATOR + "RisikoMap.jpg";
    private static final String COORDINATES_PATH = "src" + PATH_SEPARATOR + "main" + PATH_SEPARATOR + "resources"
            + PATH_SEPARATOR
            + "config" + PATH_SEPARATOR + "territory" + PATH_SEPARATOR + "Coordinates.json";

    private final Map<JButton, Territory> territories = new HashMap<>();
    private final JLayeredPane pane = new JLayeredPane();

    private ImageIcon map;

    /**
     * Panel that contains the map and the buttons over the names of the
     * territories.
     */
    public BoardPanel() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        map = new ImageIcon(
                adjustImageSize(new ImageIcon(MAP_PATH), (int) screenSize.getWidth(), (int) screenSize.getHeight()));

        JLabel label = new JLabel(map);
        label.setBounds(0, 0, map.getIconWidth(), map.getIconHeight());

        try {
            loadButtons(map.getIconWidth(), map.getIconHeight());
            for (var jb : territories.keySet()) {
                pane.add(jb, Integer.valueOf(1)); // Puts all buttons on layer 1 (above the map)
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        pane.add(label, Integer.valueOf(0)); // Puts the map on the lowest layer (0)
        pane.setPreferredSize(new Dimension(map.getIconWidth(), map.getIconHeight()));

        this.add(pane);
    }

    /**
     * Adjusts the map.png size to the screen resolution (in %).
     * 
     * @param map    the ImageIcon of the map
     * @param width  the starting width (the one used to scale the new one)
     * @param height the starting width (the one used to scale the new one)
     * @return the adjusted image
     */
    private Image adjustImageSize(final ImageIcon map, final int width, final int height) {
        int newWidth = (int) (width * SCREEN_SCALING_PERC / 100);
        int newHeight = (int) (width * SCREEN_SCALING_PERC / 100);
        return map.getImage().getScaledInstance((int) width - newWidth, (int) height - newHeight,
                java.awt.Image.SCALE_SMOOTH);
    }

    private void loadButtons(final int width, final int height) throws FileNotFoundException {
        JSONParser parser = new JSONParser();
        JSONObject obj = new JSONObject();
        TerritoryFactory factory = new TerritoryFactoryImpl();
        try {
            final FileInputStream fileInputStream = new FileInputStream(COORDINATES_PATH);
            final InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
            final JSONArray array = (JSONArray) parser.parse(inputStreamReader);
            factory.createTerritories();
            for (final Object elem : array) {
                obj = (JSONObject) elem;
                String name = obj.get("name").toString();
                int x = (int) (width * Double.parseDouble(obj.get("x").toString()) / 100);
                int y = (int) (height * Double.parseDouble(obj.get("y").toString()) / 100);
                int w = (int) (width * Double.parseDouble(obj.get("width").toString()) / 100);
                int h = (int) (height * Double.parseDouble(obj.get("height").toString()) / 100);
                territories.put(createButton(x, y, w, h), factory.getTerritory(name));
            }
        } catch (IOException | ParseException e) {
            if (Files.notExists(Path.of(COORDINATES_PATH), LinkOption.NOFOLLOW_LINKS)) {
                throw new FileNotFoundException();
            }
        }
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
    private JButton createButton(final int x, final int y, final int width, final int height) {
        JButton jb = new JButton();
        jb.setBounds(x, y, width, height);
        jb.setOpaque(false);
        jb.setFocusable(false);
        jb.setBackground(new Color(0, 0, 0, 0));
        jb.setContentAreaFilled(false);
        jb.setBorderPainted(false);
        return jb;
    }

}
