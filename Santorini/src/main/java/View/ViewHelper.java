package View;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class ViewHelper {

    public static ImageIcon loadImageIcon(String path, int width, int height) {
        URL imgURL = ViewHelper.class.getResource("/Image/" + path);
        if (imgURL == null) {
            System.err.println("Image not found: " + path);
            return null;
        }

        ImageIcon icon = new ImageIcon(imgURL);
        int originalWidth = icon.getIconWidth();
        int originalHeight = icon.getIconHeight();

        // Compute new dimensions preserving aspect ratio
        if (width > 0 && height <= 0) {
            height = (int) ((double) originalHeight / originalWidth * width);
        } else if (height > 0 && width <= 0) {
            width = (int) ((double) originalWidth / originalHeight * height);
        } else if (width <= 0 && height <= 0) {
            // No scaling if both are <= 0
            return icon;
        }

        Image scaled = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaled);
    }

    public static ImageIcon makeRoundedImage(Image image, int arcWidth, int arcHeight) {
        int w = image.getWidth(null);
        int h = image.getHeight(null);
        BufferedImage output = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2 = output.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Shape clip = new RoundRectangle2D.Float(0, 0, w, h, arcWidth, arcHeight);
        g2.setClip(clip);
        g2.drawImage(image, 0, 0, null);
        g2.dispose();

        return new ImageIcon(output);
    }

    public static Font scaledFont(String name, int style, double heightRatio, int maxHeight) {
        int size = (int) (maxHeight * heightRatio);
        return new Font(name, style, size);
    }

    public static Font loadFont(String path, int fontSize, int fontStyle) {
        try {
            Font baseFont = Font.createFont(Font.TRUETYPE_FONT,
                    Objects.requireNonNull(ViewHelper.class.getResourceAsStream("/Font/" + path)));
            return baseFont.deriveFont(fontStyle, fontSize);
        } catch (FontFormatException | IOException e) {
            System.err.println("Failed to load font from: " + path);
            e.printStackTrace();
            return new Font("SansSerif", fontStyle, (int) fontSize); // fallback
        }
    }
}
