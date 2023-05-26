package it.unibo.view.game_screen;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
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

import it.unibo.common.Constants;
import it.unibo.controller.AbstractFileReader;

public class MainPanel extends JPanel {

    private final Dimension dimension;
    private final JPanel centerPanel;
    private final JPanel southPanel;
    private final ImageIcon wallpaper;

    public MainPanel(final MainFrame frame) {
        final JButton jbPlay;
        final JButton jbRules;
        final JButton jbQuit;
        final BorderLayout layout = new BorderLayout();

        layout.setVgap(Constants.MAIN_PANEL_VGAP);
        this.setLayout(layout);

        this.dimension = Toolkit.getDefaultToolkit().getScreenSize();
        this.setPreferredSize(new Dimension((int) (this.dimension.getWidth() * Constants.MAIN_PANEL_WIDTH_PERC),
                (int) (this.dimension.getHeight() * Constants.MAIN_PANEL_HEIGHT_PERC)));

        this.wallpaper = new ImageIcon(adjustImageSize(new ImageIcon(Constants.WALLPAPER_PATH),
                (int) this.dimension.getWidth(), (int) this.dimension.getHeight()));
        JLayeredPane pane = new JLayeredPane();
        JLabel label = new JLabel(this.wallpaper);
        label.setBounds(0, 0, this.wallpaper.getIconWidth(), this.wallpaper.getIconHeight());

        jbPlay = new JButton(Constants.MAIN_PANEL_PLAY_LABEL);
        jbRules = new JButton(Constants.MAIN_PANEL_RULES_LABEL);
        jbQuit = new JButton(Constants.MAIN_PANEL_QUIT_LABEL);
        this.centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        this.centerPanel.setBounds(Constants.MAIN_PANEL_HGAP, Constants.MAIN_PANEL_VGAP, 670, 250);
        this.centerPanel.setOpaque(false);
        this.centerPanel.add(jbPlay);
        this.centerPanel.add(jbQuit);
        pane.add(this.centerPanel, BorderLayout.CENTER, Integer.valueOf(1));

        this.southPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        this.southPanel.setBounds(0, Constants.MAIN_PANEL_VGAP + this.centerPanel.getHeight() - 120,
                Double.valueOf(this.dimension.getWidth()).intValue(), 100);
        this.southPanel.setOpaque(false);
        this.southPanel.add(jbRules);
        pane.add(this.southPanel, Integer.valueOf(1));

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
}
