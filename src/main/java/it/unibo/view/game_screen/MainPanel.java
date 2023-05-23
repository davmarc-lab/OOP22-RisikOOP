package it.unibo.view.game_screen;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import it.unibo.common.Constants;
import it.unibo.controller.AbstractFileReader;

public class MainPanel extends JPanel {

    private final Dimension dimension;
    private final JPanel centerPanel;
    private final JPanel southPanel;
    private final JLabel titleLabel;

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

        jbPlay = new JButton(Constants.MAIN_PANEL_PLAY_LABEL);
        jbRules = new JButton(Constants.MAIN_PANEL_RULES_LABEL);
        jbQuit = new JButton(Constants.MAIN_PANEL_QUIT_LABEL);
        this.centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        this.centerPanel.add(jbPlay);
        this.centerPanel.add(jbQuit);
        this.add(this.centerPanel, BorderLayout.CENTER);

        this.southPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        this.southPanel.add(jbRules);

        this.add(this.southPanel, BorderLayout.SOUTH);
        this.titleLabel = new JLabel(Constants.MAIN_PANEL_TITLE_LABEL, (int) JPanel.CENTER_ALIGNMENT);
        this.add(this.titleLabel, BorderLayout.NORTH);

        jbPlay.addActionListener(e -> {
            frame.getContentPane().removeAll();
            frame.getContentPane().revalidate();
            frame.getContentPane().repaint();
            frame.getContentPane().add(new BoardPanel(), BorderLayout.CENTER);
            frame.pack();
            frame.setLocationRelativeTo(null);
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
                final InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
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
        this.updateUI();
    }

    private class MessageFrame extends JFrame {

        MessageFrame() {
            this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            this.setLocationRelativeTo(null);
        }
    }
}
