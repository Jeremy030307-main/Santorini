package View.Game;

import View.Game.MapComponent.JPlayer;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class PlayerPanel extends JPanel {

    private final JLabel baseLabel;
    private final JLabel textLabel;
    private final JLabel cloudLabel;

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
        textLabel.setBounds(20, (int)(height * 0.91), baseImage.getWidth(null), 20);

        add(textLabel);
        add(baseLabel);
        add(overlayLabel);
        add(cloudLabel);
    }

    public void update(String text, boolean visible) {
        System.out.println(text);
        textLabel.setText(text);
        baseLabel.setVisible(visible);
        textLabel.setVisible(visible);
        cloudLabel.setVisible(visible);
        repaint();
        revalidate();
    }

    private Image loadImage(String path, double w, double h) {
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("../../" + path)));
        return icon.getImage().getScaledInstance((int)w, (int)h, Image.SCALE_SMOOTH);
    }
}
