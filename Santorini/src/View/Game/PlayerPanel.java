package View.Game;

import View.Game.MapComponent.JPlayer;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class PlayerPanel extends JPanel {

    private final JLabel baseLabel;
    private final JLabel textLabel;
    private final JLabel cloudLabel;
    private final JButton topButton;
    private boolean buttonVisible = false;

    public PlayerPanel(JPlayer player, int width, int height) {
        setLayout(null);
        setOpaque(false);

        // Base image
        Image baseImage = loadImage("Asset/Image/Label/chosen_player.png", width * 0.21, height * 0.12);
        baseLabel = new JLabel(new ImageIcon(baseImage));
        baseLabel.setBounds(19, (int)(height * 0.80), baseImage.getWidth(null), baseImage.getHeight(null));

        // Overlay (player icon)
        Image overlayImg = loadImage(player.getPath(), width * 0.2, height * 0.1);
        JLabel overlayLabel = new JLabel(new ImageIcon(overlayImg));
        overlayLabel.setBounds(20, (int)(height * 0.80), baseImage.getWidth(null), baseImage.getHeight(null));

        // Cloud
        Image cloudImage = loadImage("Asset/Image/Label/clouds.png", width * 0.2, height * 0.06);
        cloudLabel = new JLabel(new ImageIcon(cloudImage));
        cloudLabel.setBounds(20, (int)(height * 0.873), baseImage.getWidth(null), baseImage.getHeight(null));

        // Text
        textLabel = new JLabel("", SwingConstants.CENTER);
        textLabel.setFont(new Font("Comic Sans MS", Font.BOLD, (int)(cloudLabel.getHeight() * 0.25)));
        textLabel.setForeground(Color.BLACK);
        textLabel.setBounds(20, (int)(height * 0.87), baseImage.getWidth(null), baseImage.getHeight(null));

        Image buttonImage = loadImage("Asset/Image/Button/end_turn.png", width * 0.15, height * 0.07);
        topButton = new JButton(new ImageIcon(buttonImage));
        topButton.setBounds(
                (int)(baseLabel.getX() + baseLabel.getWidth()/2 - buttonImage.getWidth(null)/2), // Center horizontally
                (int)(baseLabel.getY() - buttonImage.getHeight(null) - 5), // Position above base image
                buttonImage.getWidth(null),
                buttonImage.getHeight(null)
        );

        topButton.setContentAreaFilled(false);
        topButton.setBorderPainted(false);
        topButton.setFocusPainted(false);
        topButton.setOpaque(false);
        topButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        topButton.setVisible(buttonVisible);

        add(textLabel);
        add(baseLabel);
        add(overlayLabel);
        add(cloudLabel);
        add(topButton); // Added last so it appears on top

    }

    public void update(String text, boolean visible, boolean buttonVisible) {
        System.out.println(text);
        textLabel.setText(text);
        baseLabel.setVisible(visible);
        textLabel.setVisible(visible);
        cloudLabel.setVisible(visible);
        topButton.setVisible(buttonVisible);
        repaint();
        revalidate();
    }

    private Image loadImage(String path, double w, double h) {
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("../../" + path)));
        return icon.getImage().getScaledInstance((int)w, (int)h, Image.SCALE_SMOOTH);
    }

    public JButton getTopButton() {
        return topButton;
    }
}
