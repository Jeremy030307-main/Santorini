package View.GameOver;

import View.Game.MapComponent.JPlayer;
import View.SantoriniPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class WinPanel extends SantoriniPanel {

    private static final String imgPath = "victory.png";

    private final JLabel baseLabel;
    private boolean buttonVisible = false;
    private JButton backButton;
    Font customFont = null;

    public WinPanel(JPlayer jPlayer, String playerName, String godCardImage) {
        super(imgPath);

        setLayout(null);
        setOpaque(false);

        ImageIcon middleIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("../../" + godCardImage)));
        Image originalImage = middleIcon.getImage();

        // Calculate dimensions preserving aspect ratio
        double maxWidth = getMaxWidth() * 0.3; // Adjusted for reasonable god card size
        double imgWidth = originalImage.getWidth(null);
        double imgHeight = originalImage.getHeight(null);
        double aspectRatio = imgWidth / imgHeight;

        int targetWidth = (int) maxWidth;
        int targetHeight = (int) (targetWidth / aspectRatio);

        // Scale the god card image
        Image scaledImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH);
        JLabel middleLabel = new JLabel(new ImageIcon(scaledImage));

        // Center middleLabel
        int middleX = (getMaxWidth() - targetWidth) / 2;
        int middleY = (int) (-getMaxHeight() * 0.12); // Adjust vertical positioning as needed
        middleLabel.setBounds(middleX, middleY, targetWidth, targetHeight);

        // Load and position baseLabel relative to middleLabel
        Image baseImage = loadImage("Asset/Image/Label/chosen_player.png", getMaxHeight() * 0.4, getMaxHeight() * 0.15);
        baseLabel = new JLabel(new ImageIcon(baseImage));
        baseLabel.setBounds(
                middleX + (targetWidth - baseImage.getWidth(null)) / 2,
                middleY + (int) (getMaxHeight() * 0.70), // below god card image
                baseImage.getWidth(null),
                baseImage.getHeight(null)
        );

        // Load and position overlay image (player tag/icon)
        Image overlayImg = loadImage(jPlayer.getTagPath(), baseLabel.getWidth()-20, baseLabel.getHeight()-20);
        JLabel overlayLabel = new JLabel(new ImageIcon(overlayImg));
        overlayLabel.setBounds(
                middleX + (targetWidth - baseImage.getWidth(null)) / 2,
                middleY + (int) (getMaxHeight() * 0.70), // below god card image
                baseImage.getWidth(null),
                baseImage.getHeight(null)
        );

        customFont = null;
        try {
            customFont = Font.createFont(Font.TRUETYPE_FONT,
                    getClass().getResourceAsStream("../../Asset/Font/DalekPinpointBold.ttf"));

        } catch (FontFormatException | IOException e) {

        }

        // Player name on top of overlay
        JLabel overlayTextLabel = new JLabel(playerName, SwingConstants.CENTER);
        if (customFont != null) {
            overlayTextLabel.setFont(customFont.deriveFont(Font.PLAIN, (int)(getMaxHeight() * 0.05)));
        }
        overlayTextLabel.setBounds(
                (int) (middleX + baseImage.getWidth(null) * 0.48),
                middleY + (int) (getMaxHeight() * 0.73), // below god card image
                (int) (baseImage.getWidth(null) * 0.6),
                (int) (baseImage.getHeight(null) * 0.5)
        );

        overlayTextLabel.setOpaque(false);

        // Load button background image
        Image buttonImage = loadImage("Asset/Image/Button/back_button.png", getMaxWidth() * 0.1, getMaxHeight() * 0.1);
        backButton = new JButton(new ImageIcon(buttonImage));

// Make button background transparent
        backButton.setBorderPainted(false);
        backButton.setFocusPainted(false);
        backButton.setContentAreaFilled(false);

// Set button position
        int buttonWidth = buttonImage.getWidth(null);
        int buttonHeight = buttonImage.getHeight(null);
        int buttonX = (getMaxWidth() - buttonWidth) / 2;
        int buttonY = middleY + targetHeight + baseImage.getHeight(null) + 100;

        backButton.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);

// Add button to panel
        add(backButton);

// Add components
        add(overlayTextLabel);
        add(middleLabel);
        add(baseLabel);
        add(overlayLabel);
    }

    private Image loadImage(String path, double w, double h) {
        java.net.URL imgURL = getClass().getResource("/" + path); // Use leading slash
        if (imgURL == null) {
            throw new IllegalArgumentException("Image not found: " + path);
        }
        ImageIcon icon = new ImageIcon(imgURL);
        return icon.getImage().getScaledInstance((int) w, (int) h, Image.SCALE_SMOOTH);
    }

    public JButton getBackButton() {
        return backButton;
    }
}
