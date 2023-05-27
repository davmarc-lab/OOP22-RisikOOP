package it.unibo.view.game_screen;

import java.awt.BorderLayout;
import java.awt.Color;
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
import it.unibo.controller.AbstractFileReader;

public class MainPanel extends JPanel {

    private static final double BUTTON_WIDTH_PERC = Constants.MAIN_PANEL_WIDTH_PERC * 0.2;
    private static final double BUTTON_HEIGHT_PERC = Constants.MAIN_PANEL_HEIGHT_PERC * 0.1;
    private static final double INNER_PANEL_HEIGHT_PERC = Constants.MAIN_PANEL_HEIGHT_PERC * 0.3;
    private static final int FONT_SIZE = 35;
    private static final int BUTTON_BORDER_SIZE = 3;

    private final Dimension dimension;
    private final ImageIcon wallpaper;
    private final JPanel panel;
    private final JLayeredPane pane;

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
                new Dimension(Double.valueOf(this.dimension.getWidth() * Constants.MAIN_PANEL_WIDTH_PERC).intValue(),
                        Double.valueOf(this.dimension.getHeight() * Constants.MAIN_PANEL_HEIGHT_PERC).intValue()));

        this.wallpaper = new ImageIcon(adjustImageSize(new ImageIcon(Constants.WALLPAPER_PATH),
                (int) this.dimension.getWidth(), (int) this.dimension.getHeight()));

        label = new JLabel(this.wallpaper);
        label.setBounds(0, 0, this.wallpaper.getIconWidth(), this.wallpaper.getIconHeight());

        jbPlay = this.createButton(Constants.MAIN_PANEL_PLAY_LABEL, this.getButtonDimension());
        jbRules = this.createButton(Constants.MAIN_PANEL_RULES_LABEL, this.getButtonDimension());
        jbQuit = this.createButton(Constants.MAIN_PANEL_QUIT_LABEL, this.getButtonDimension());

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
            bp.createController();
            sb.getButtonPanel().setController(bp.getController());
        });

        jbQuit.addActionListener(e -> {
            final int n = JOptionPane.showConfirmDialog(this,
                    "Do you really want to quit?",
                    "Quitting", JOptionPane.YES_NO_OPTION);
            if (n == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        try {
            final String message;
            message = new AbstractFileReader<String>(Constants.RULES_PATH) {
                String line;
                StringBuilder sBuilder = new StringBuilder();
                final FileInputStream fileInputStream = new FileInputStream(this.getFilePath());
                final InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream,
                        StandardCharsets.UTF_8);
                final BufferedReader reader = new BufferedReader(inputStreamReader);

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
                Double.valueOf(this.dimension.getWidth() * Constants.MAIN_PANEL_WIDTH_PERC).intValue(),
                Double.valueOf(this.dimension.getHeight() * Constants.MAIN_PANEL_HEIGHT_PERC).intValue());
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

    private Image adjustImageSize(final ImageIcon map, final int width, final int height) {
        return map.getImage().getScaledInstance((int) (width * Constants.MAIN_PANEL_WIDTH_PERC),
                (int) (height * Constants.MAIN_PANEL_HEIGHT_PERC),
                java.awt.Image.SCALE_SMOOTH);
    }

    private JButton createButton(final String name, final Dimension dim) {
        JButton jb = new JButton(name);
        jb.setPreferredSize(dim);
        jb.setFocusPainted(false);
        jb.setContentAreaFilled(false);
        jb.setFont(new Font("Arial", Font.BOLD, FONT_SIZE));
        jb.setForeground(Color.BLACK);
        jb.setBorder(new LineBorder(Color.ORANGE, BUTTON_BORDER_SIZE));

        return jb;
    }

    private Dimension getButtonDimension() {
        return new Dimension(
                Double.valueOf(this.dimension.getWidth() * BUTTON_WIDTH_PERC).intValue(),
                Double.valueOf(this.dimension.getHeight() * BUTTON_HEIGHT_PERC).intValue());
    }

    private Dimension getInnerPanelDimension() {
        return new Dimension(Double.valueOf(this.dimension.getWidth() * Constants.MAIN_PANEL_WIDTH_PERC).intValue(),
                Double.valueOf(this.dimension.getHeight() * INNER_PANEL_HEIGHT_PERC).intValue());
    }
}
