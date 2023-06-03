package it.unibo.view.game_screen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import it.unibo.controller.reader.impl.AbstractFileReader;
import it.unibo.view.game_screen.impl.MainFrame;
import it.unibo.view.viewconstants.ViewConstants;

/**
 * The extension of JPanel which defines the main panel of the game with the
 * start menu.
 */
public class MainPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private static final String RULES_PATH = new StringBuilder(ViewConstants.RESOURCES_PATH).append("instructions")
            .append(ViewConstants.PATH_SEPARATOR).append("rules.txt").toString();
    private static final String WALLPAPER_PATH = new StringBuilder(ViewConstants.RESOURCES_PATH).append("images")
            .append(ViewConstants.PATH_SEPARATOR).append("MenuWallpaper.jpg").toString();

    private static final String PLAY_LABEL = "Play";
    private static final String QUIT_LABEL = "Quit";
    private static final String RULES_LABEL = "Rules";
    private static final double WIDTH_PERC = 0.4;
    private static final double HEIGHT_PERC = 0.4;
    private static final double BUTTON_WIDTH_PERC = WIDTH_PERC * 0.2;
    private static final double BUTTON_HEIGHT_PERC = HEIGHT_PERC * 0.1;
    private static final double INNER_PANEL_HEIGHT_PERC = HEIGHT_PERC * 0.3;
    private static final int FONT_SIZE = 35;
    private static final int BUTTON_BORDER_SIZE = 3;

    private final Dimension dimension;

    /**
     * Creates the main panel with the start menu.
     * 
     * @param frame
     *              the main frame.
     */
    public MainPanel(final MainFrame frame) {
        final JPanel panel;
        final JLayeredPane pane;
        final JButton jbPlay;
        final JButton jbRules;
        final JButton jbQuit;
        final JLabel label;
        final JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        final JPanel northPanel = new JPanel(new FlowLayout());
        final ImageIcon wallpaper;

        pane = new JLayeredPane();

        panel = new JPanel();
        panel.setLayout(new BorderLayout());

        this.dimension = Toolkit.getDefaultToolkit().getScreenSize();
        this.setPreferredSize(
                new Dimension(Double.valueOf(this.dimension.getWidth() * WIDTH_PERC).intValue(),
                        Double.valueOf(this.dimension.getHeight() * HEIGHT_PERC).intValue()));

        wallpaper = new ImageIcon(adjustImageSize(new ImageIcon(WALLPAPER_PATH),
                dimension.getWidth(), this.dimension.getHeight()));

        label = new JLabel(wallpaper);
        label.setBounds(0, 0, wallpaper.getIconWidth(), wallpaper.getIconHeight());

        jbPlay = this.createButton(PLAY_LABEL, this.getButtonDimension());
        jbRules = this.createButton(RULES_LABEL, this.getButtonDimension());
        jbQuit = this.createButton(QUIT_LABEL, this.getButtonDimension());

        jbPlay.addActionListener(e -> {
            frame.startGame();
        });

        jbQuit.addActionListener(e -> {
            final String[] options = { "Yes", "No" };
            final int result = JOptionPane.showOptionDialog(this,
                    "Do you really want to quit?",
                    "Quitting",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[1]);
            if (result == 0) {
                frame.dispose();
            }
        });

        try {
            final String message;
            message = new AbstractFileReader<String>(RULES_PATH) {
                private InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(RULES_PATH),
                        StandardCharsets.UTF_8);
                private BufferedReader reader = new BufferedReader(inputStreamReader);

                @Override
                public String readFromFile() {
                    String line;
                    final StringBuilder sBuilder = new StringBuilder();
                    try {
                        line = reader.readLine();
                        while (line != null) {
                            sBuilder.append(line).append('\n');
                            line = reader.readLine();
                        }
                        reader.close();
                    } catch (IOException e) {
                        this.getLogger().log(Level.SEVERE, "Reading from file error", e);
                    }
                    return sBuilder.toString();
                }

            }.readFromFile();
            jbRules.addActionListener(e -> {
                JOptionPane.showMessageDialog(this, message);
            });
        } catch (FileNotFoundException e) {
            Logger.getLogger(MainPanel.class.getName()).log(Level.SEVERE, "File not found in the path given", e);
        }

        pane.add(label, Integer.valueOf(0));
        pane.setPreferredSize(new Dimension(wallpaper.getIconWidth(), wallpaper.getIconHeight()));

        northPanel.add(jbPlay);
        northPanel.add(jbQuit);
        northPanel.setOpaque(false);
        northPanel.setBounds(0, 0, Double.valueOf(this.getInnerPanelDimension().getWidth()).intValue(),
                Double.valueOf(this.getInnerPanelDimension().getHeight()).intValue());

        southPanel.add(jbRules);
        southPanel.setOpaque(false);
        southPanel.setBounds(0, 0, Double.valueOf(this.getInnerPanelDimension().getWidth()).intValue(),
                Double.valueOf(this.getInnerPanelDimension().getHeight()).intValue());

        panel.setBounds(0, 0,
                Double.valueOf(this.dimension.getWidth() * WIDTH_PERC).intValue(),
                Double.valueOf(this.dimension.getHeight() * HEIGHT_PERC).intValue());
        panel.add(northPanel, BorderLayout.NORTH);
        panel.add(southPanel, BorderLayout.SOUTH);
        panel.setOpaque(false);

        pane.add(panel, Integer.valueOf(1));
        this.add(pane);
    }

    private Image adjustImageSize(final ImageIcon map, final double width, final double height) {
        return map.getImage().getScaledInstance(Double.valueOf(width * WIDTH_PERC).intValue(),
                Double.valueOf(height * HEIGHT_PERC).intValue(),
                Image.SCALE_SMOOTH);
    }

    private JButton createButton(final String name, final Dimension dim) {
        final JButton jb = new JButton(name);
        jb.setPreferredSize(dim);
        jb.setFocusPainted(false);
        jb.setContentAreaFilled(false);
        jb.setFont(new Font("Arial", Font.BOLD, FONT_SIZE));
        jb.setForeground(Color.BLACK);
        jb.setBorder(new LineBorder(Color.ORANGE, BUTTON_BORDER_SIZE));
        jb.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return jb;
    }

    private Dimension getButtonDimension() {
        return new Dimension(
                Double.valueOf(this.dimension.getWidth() * BUTTON_WIDTH_PERC).intValue(),
                Double.valueOf(this.dimension.getHeight() * BUTTON_HEIGHT_PERC).intValue());
    }

    private Dimension getInnerPanelDimension() {
        return new Dimension(Double.valueOf(this.dimension.getWidth() * WIDTH_PERC).intValue(),
                Double.valueOf(this.dimension.getHeight() * INNER_PANEL_HEIGHT_PERC).intValue());
    }
}
