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
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import it.unibo.controller.api.MainController;
import it.unibo.controller.impl.MainControllerImpl;
import it.unibo.model.territory.api.Territory;

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

    private final Map<JButton, String> territories = new HashMap<>();
    private final JLayeredPane pane = new JLayeredPane();
    private final MainController controller;

    private ImageIcon map;

    /**
     * Panel that contains the map and the buttons over the names of the
     * territories.
     */
    public BoardPanel() {
        this.controller = new MainControllerImpl(this);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        map = new ImageIcon(
                adjustImageSize(new ImageIcon(MAP_PATH), (int) screenSize.getWidth(), (int) screenSize.getHeight()));

        JLabel label = new JLabel(map);
        label.setBounds(0, 0, map.getIconWidth(), map.getIconHeight());

        try {
            loadButtons(map.getIconWidth(), map.getIconHeight());
            for (var jb : this.territories.keySet()) {
                this.pane.add(jb, Integer.valueOf(1)); // Puts all buttons on layer 1 (above the map)
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        this.pane.add(label, Integer.valueOf(0)); // Puts the map on the lowest layer (0)
        this.pane.setPreferredSize(new Dimension(map.getIconWidth(), map.getIconHeight()));

        this.add(this.pane);
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
        try {
            final FileInputStream fileInputStream = new FileInputStream(COORDINATES_PATH);
            final InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
            final JSONArray array = (JSONArray) parser.parse(inputStreamReader);
            for (final Object elem : array) {
                obj = (JSONObject) elem;
                String name = obj.get("name").toString();
                int x = (int) (width * Double.parseDouble(obj.get("x").toString()) / 100);
                int y = (int) (height * Double.parseDouble(obj.get("y").toString()) / 100);
                int w = (int) (width * Double.parseDouble(obj.get("width").toString()) / 100);
                int h = (int) (height * Double.parseDouble(obj.get("height").toString()) / 100);
                this.territories.put(createButton(x, y, w, h), name);
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
        jb.setBorderPainted(false);
        jb.setBackground(new Color(0, 0, 0, 0));
        jb.setContentAreaFilled(false);
        jb.addActionListener(e -> {
            this.controller.sendInput(this.territories.get(jb));
        });
        return jb;
    }

    /**
     * Disables the buttons placed on the list of territories provided.
     * @param t list of territories to disable
     */
    public void disableButtons(final Set<Territory> territorySet) {
        Set<String> territoryNames = new HashSet<>();
        territorySet.forEach(t -> territoryNames.add(t.getName()));
        this.territories.entrySet().forEach(e -> {
            if (territoryNames.contains(e.getValue())) {
                e.getKey().setEnabled(false);
            }
        });
    }

    /**
     * Enables all buttons.
     */
    public void enableAll() {
        this.territories.entrySet().forEach(e -> e.getKey().setEnabled(true));
    }

    /**
     * Disables all buttons.
     */
    public void disableAll() {
        this.territories.entrySet().forEach(e -> e.getKey().setEnabled(false));
    }
}
