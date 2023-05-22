package it.unibo.view.game_screen;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MainPanel extends JPanel {
    private static final double WIDTH_PERC = 0.4;
    private static final double HEIGHT_PERC = 0.4;
    private static final int VGAP = 100;
    private static final String PLAY = "Play";
    private static final String QUIT = "Quit";
    private static final String RULES = "Rules";
    private static final String TITLE_LABEL = "RISIKOOP";
    private static final String PATH_SEPARATOR = System.getProperty("file.separator");
    private static final String RULES_PATH = new String(new StringBuilder("src")
            .append(PATH_SEPARATOR).append("main")
            .append(PATH_SEPARATOR).append("resources")
            .append(PATH_SEPARATOR).append("instructions")
            .append(PATH_SEPARATOR).append("rules.txt"));

    private final Dimension dimension;
    private final JPanel centerPanel;
    private final JPanel southPanel;
    private final JLabel titleLabel;

    public MainPanel(final MainFrame frame) {
        final JButton jbPlay;
        final JButton jbRules;
        final JButton jbQuit;
        final BorderLayout layout = new BorderLayout();

        layout.setVgap(VGAP);
        this.setLayout(layout);

        this.dimension = Toolkit.getDefaultToolkit().getScreenSize();
        this.setPreferredSize(new Dimension((int) (this.dimension.getWidth() * WIDTH_PERC),
                (int) (this.dimension.getHeight() * HEIGHT_PERC)));

        jbPlay = new JButton(PLAY);
        jbRules = new JButton(RULES);
        jbQuit = new JButton(QUIT);
        this.centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        this.centerPanel.add(jbPlay);
        this.centerPanel.add(jbQuit);
        this.add(this.centerPanel, BorderLayout.CENTER);

        this.southPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        this.southPanel.add(jbRules);

        this.add(this.southPanel, BorderLayout.SOUTH);
        this.titleLabel = new JLabel(TITLE_LABEL, (int) JPanel.CENTER_ALIGNMENT);
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
            String line;
            final String message;
            StringBuilder sBuilder = new StringBuilder();
            final FileInputStream fileInputStream = new FileInputStream(RULES_PATH);
            final InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
            final BufferedReader reader = new BufferedReader(inputStreamReader);
            while ((line = reader.readLine()) != null) {
                sBuilder.append(line).append("\n");
            }
            message = sBuilder.toString();
            jbRules.addActionListener(e -> {
                JOptionPane.showMessageDialog(new MessageFrame(), message);
            });
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
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
