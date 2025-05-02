package View.Game;

import View.Game.MapComponent.JPlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class PlayerPanel extends JPanel {

    private final JLabel baseLabel;
    private final JLabel textLabel;
    private final JLabel cloudLabel;
    private final JButton topButton;
    private boolean buttonVisible = false;
    Font customFont = null;

    public PlayerPanel(JPlayer player, String playerName, String godCardImgPath, int width, int height) {
        setLayout(null);
        setOpaque(false);
        setPreferredSize(new Dimension((int)(width * 0.25), (int)(height * 0.4)));
        setBounds(0, 0, (int)(width * 0.25), (int)(height * 0.4));

        // Load font from resource
        customFont = null;
        try {
            customFont = Font.createFont(Font.TRUETYPE_FONT,
                    getClass().getResourceAsStream("/Font/DalekPinpointBold.ttf"));

        } catch (FontFormatException | IOException e) {

        }

        // Base image
        Image baseImage = loadImage("/Image/Label/chosen_player.png", width * 0.21, height * 0.12);
        baseLabel = new JLabel(new ImageIcon(baseImage));
        baseLabel.setBounds(19, (int)(height * 0.80), baseImage.getWidth(null), baseImage.getHeight(null));

        // Overlay (player icon)
        Image overlayImg = loadImage(player.getTagPath(), width * 0.2, height * 0.1);
        JLabel overlayLabel = new JLabel(new ImageIcon(overlayImg));
        overlayLabel.setBounds(20, (int)(height * 0.80), baseImage.getWidth(null), baseImage.getHeight(null));

        // Text on top of overlay (e.g., player name or ID)
        JLabel overlayTextLabel = new JLabel(playerName, SwingConstants.CENTER);

        overlayTextLabel.setFont(customFont.deriveFont(Font.PLAIN,  (int)(height * 0.03)));
        overlayTextLabel.setForeground(Color.BLACK);
        overlayTextLabel.setBounds(
                (int) (overlayLabel.getX() + width*0.025),
                (int) (overlayLabel.getY() - height*0.005), // Move slightly down if needed
                overlayLabel.getWidth(),
                overlayLabel.getHeight()
        );
        overlayTextLabel.setOpaque(false);

        // Cloud
        Image cloudImage = loadImage("/Image/Label/clouds.png", width * 0.2, height * 0.06);
        cloudLabel = new JLabel(new ImageIcon(cloudImage));
        cloudLabel.setBounds(20, (int)(height * 0.873), baseImage.getWidth(null), baseImage.getHeight(null));

        // Text
        textLabel = new JLabel("", SwingConstants.CENTER);
        textLabel.setFont(customFont.deriveFont(Font.PLAIN, (int)(cloudLabel.getHeight() * 0.25)));
        textLabel.setForeground(Color.BLACK);
        textLabel.setBounds(20, (int)(height * 0.87), baseImage.getWidth(null), baseImage.getHeight(null));

        // Load original image
        ImageIcon middleIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource(godCardImgPath)));
        Image originalImage = middleIcon.getImage();

        // Calculate dimensions preserving aspect ratio
        double maxWidth = baseImage.getWidth(null);
        double imgWidth = originalImage.getWidth(null);
        double imgHeight = originalImage.getHeight(null);
        double aspectRatio = imgWidth / imgHeight;

        int targetWidth, targetHeight;

        targetWidth = (int) maxWidth;
        targetHeight = (int) (targetWidth / aspectRatio);

        // Scale the image
        Image scaledImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH);
        JLabel middleLabel = new JLabel(new ImageIcon(scaledImage));

        // Position it on top of the base label (centered)
        middleLabel.setBounds(
                baseLabel.getX() + baseLabel.getWidth() / 2 - targetWidth / 2,
                baseLabel.getY() - targetHeight,
                targetWidth,
                targetHeight
        );

        Image flippedCloudImage = flipImageVertically(cloudImage);
        JLabel flippedCloudLabel = new JLabel(new ImageIcon(flippedCloudImage));

// Position it above the baseLabel (mirrored placement)
        flippedCloudLabel.setBounds(
                (int) (baseLabel.getX() + baseLabel.getWidth()*0.03),
                (int) (baseLabel.getY() - flippedCloudImage.getHeight(null) + (baseLabel.getHeight()*0.2)),
                flippedCloudImage.getWidth(null),
                flippedCloudImage.getHeight(null)
        );

        Image buttonImage = loadImage("/Image/Button/button_endTurn.png", height * 0.08, height * 0.08);
        topButton = new JButton(new ImageIcon(buttonImage));
        topButton.setBounds(
                (int)(baseLabel.getX() + baseLabel.getWidth()/15), // Center horizontally
                (int)(baseLabel.getY() + baseLabel.getHeight()/5), // Position above base image
                buttonImage.getWidth(null),
                buttonImage.getHeight(null)
        );

        topButton.setContentAreaFilled(false);
        topButton.setBorderPainted(false);
        topButton.setFocusPainted(false);
        topButton.setOpaque(false);
        topButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        topButton.setVisible(buttonVisible);

        add(topButton);
        add(overlayTextLabel);
        add(middleLabel); // Inserted here
        add(textLabel);
        add(baseLabel);
        add(overlayLabel);
        add(cloudLabel);
        add(flippedCloudLabel);

    }

    public void update(String text, boolean visible, boolean buttonVisible) {
        textLabel.setText(text);
        baseLabel.setVisible(visible);
        textLabel.setVisible(visible);
        cloudLabel.setVisible(visible);
        topButton.setVisible(buttonVisible);
        repaint();
        revalidate();
    }

    private Image loadImage(String path, double w, double h) {
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource(path)));
        return icon.getImage().getScaledInstance((int)w, (int)h, Image.SCALE_SMOOTH);
    }

    public JButton getTopButton() {
        return topButton;
    }

    private Image flipImageVertically(Image image) {
        int width = image.getWidth(null);
        int height = image.getHeight(null);
        BufferedImage flipped = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = flipped.createGraphics();
        g2d.drawImage(image, 0, 0, width, height, 0, height, width, 0, null); // Flip vertically
        g2d.dispose();
        return flipped;
    }
}
