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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import it.unibo.common.Constants;
import it.unibo.controller.impl.MainControllerImpl;
import it.unibo.controller.reader.impl.AbstractFileReader;
import it.unibo.view.game_screen.impl.BoardPanel;
import it.unibo.view.game_screen.impl.SideBar;

/**
 * The extension of JPanel which defines the main panel of the game with the start menu.
 */
public class MainPanel extends JPanel {

    private static final String RULES_PATH = new StringBuilder(Constants.RESOURCES_PATH).append("instructions")
            .append(Constants.PATH_SEPARATOR).append("rules.txt").toString();
    private static final String WALLPAPER_PATH = new StringBuilder(Constants.RESOURCES_PATH).append("images")
            .append(Constants.PATH_SEPARATOR).append("MenuWallpaper.jpg").toString();

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
    private final ImageIcon wallpaper;
    private final JPanel panel;
    private final JLayeredPane pane;

    /**
     * Creates the main panel with the start menu.
     * 
     * @param frame
     *              the main frame.
     */
    public MainPanel(final MainFrame frame) {
        final JButton jbPlay;
        final JButton jbRules;
        final JButton jbQuit;
        final JLabel label;
        final JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        final JPanel northPanel = new JPanel(new FlowLayout());

        this.pane = new JLayeredPane();

        this.panel = new JPanel();
        this.panel.setLayout(new BorderLayout());

        this.dimension = Toolkit.getDefaultToolkit().getScreenSize();
        this.setPreferredSize(
                new Dimension(Double.valueOf(this.dimension.getWidth() * WIDTH_PERC).intValue(),
                        Double.valueOf(this.dimension.getHeight() * HEIGHT_PERC).intValue()));

        this.wallpaper = new ImageIcon(adjustImageSize(new ImageIcon(WALLPAPER_PATH),
                this.dimension.getWidth(), this.dimension.getHeight()));

        label = new JLabel(this.wallpaper);
        label.setBounds(0, 0, this.wallpaper.getIconWidth(), this.wallpaper.getIconHeight());

        jbPlay = this.createButton(PLAY_LABEL, this.getButtonDimension());
        jbRules = this.createButton(RULES_LABEL, this.getButtonDimension());
        jbQuit = this.createButton(QUIT_LABEL, this.getButtonDimension());

        jbPlay.addActionListener(e -> {
            frame.getContentPane().removeAll();
            frame.getContentPane().revalidate();
            frame.getContentPane().repaint();
            BoardPanel bp = new BoardPanel();
            SideBar sb = new SideBar(bp.getPreferredSize());
            frame.getContentPane().add(bp, BorderLayout.CENTER);
            frame.getContentPane().add(sb, BorderLayout.EAST);
            frame.pack();
            frame.setLocationRelativeTo(null);
            bp.setController(new MainControllerImpl(bp));
            sb.getButtonPanel().setController(bp.getController());
        });

        jbQuit.addActionListener(e -> {
            final int n = JOptionPane.showConfirmDialog(this,
                    "Do you really want to quit?",
                    "Quitting", JOptionPane.YES_NO_OPTION);
            if (n == JOptionPane.YES_OPTION) {
                frame.dispose();
            }
        });

        try {
            final String message;
            message = new AbstractFileReader<String>(RULES_PATH) {
                private String line;
                private StringBuilder sBuilder = new StringBuilder();
                private FileInputStream fileInputStream = new FileInputStream(this.getFilePath());
                private InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream,
                        StandardCharsets.UTF_8);
                private BufferedReader reader = new BufferedReader(inputStreamReader);

                @Override
                public String readFromFile() {
                    try {
                        while ((line = reader.readLine()) != null) {
                            sBuilder.append(line).append("\n");
                        }
                        reader.close();
                    } catch (IOException e) {
                        this.getLogger().log(Level.SEVERE, "Reading from file error", e);
                    }
                    return sBuilder.toString();
                }

            }.readFromFile();
            jbRules.addActionListener(e -> {
                JOptionPane.showMessageDialog(new MessageFrame(), message);
            });
        } catch (FileNotFoundException e) {
            Logger logger = Logger.getLogger(MainPanel.class.getName());
            logger.log(Level.SEVERE, "File not found in the path given", e);
        }

        pane.add(label, Integer.valueOf(0));
        pane.setPreferredSize(new Dimension(this.wallpaper.getIconWidth(), this.wallpaper.getIconHeight()));

        northPanel.add(jbPlay);
        northPanel.add(jbQuit);
        northPanel.setOpaque(false);
        northPanel.setBounds(0, 0, Double.valueOf(this.getInnerPanelDimension().getWidth()).intValue(),
                Double.valueOf(this.getInnerPanelDimension().getHeight()).intValue());

        southPanel.add(jbRules);
        southPanel.setOpaque(false);
        southPanel.setBounds(0, 0, Double.valueOf(this.getInnerPanelDimension().getWidth()).intValue(),
                Double.valueOf(this.getInnerPanelDimension().getHeight()).intValue());

        this.panel.setBounds(0, 0,
                Double.valueOf(this.dimension.getWidth() * WIDTH_PERC).intValue(),
                Double.valueOf(this.dimension.getHeight() * HEIGHT_PERC).intValue());
        this.panel.add(northPanel, BorderLayout.NORTH);
        this.panel.add(southPanel, BorderLayout.SOUTH);
        this.panel.setOpaque(false);

        pane.add(this.panel, Integer.valueOf(1));
        this.add(pane);
        this.updateUI();
    }

    private class MessageFrame extends JFrame {

        MessageFrame() {
            this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            this.setLocationRelativeTo(null);
        }
    }

    private Image adjustImageSize(final ImageIcon map, final double width, final double height) {
        return map.getImage().getScaledInstance(Double.valueOf(width * WIDTH_PERC).intValue(),
                Double.valueOf(height * HEIGHT_PERC).intValue(),
                Image.SCALE_SMOOTH);
    }

    private JButton createButton(final String name, final Dimension dim) {
        JButton jb = new JButton(name);
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
