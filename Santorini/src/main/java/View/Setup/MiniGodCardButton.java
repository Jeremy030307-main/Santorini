package View.Setup;

import View.ViewHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class MiniGodCardButton extends JButton {

    public enum SelectionColor {
        NONE, RED, BLUE
    }

    private ImageIcon selectedIcon;
    private final ImageIcon defaultIcon;
    private final ImageIcon redIcon;
    private final ImageIcon blueIcon;

    public MiniGodCardButton(String godImagePath, int maxWidth) {

        String fgPath = "GodCard/mini_card.png";   // Mini god card overlay
        String fgHoverPath = "GodCard/mini_card_hover.png"; // <- your hover image
        String fgBluePath = "GodCard/mini_card_blue.png"; // <- your hover image
        String fgRedPath = "GodCard/mini_card_red.png"; // <- your hover image

        ImageIcon defaultFrame = ViewHelper.loadImageIcon(fgPath, (int) (maxWidth * 0.09), -1);
        ImageIcon hoverFrame = ViewHelper.loadImageIcon(fgHoverPath, (int)(maxWidth * 0.09), -1);
        ImageIcon redFrame = ViewHelper.loadImageIcon(fgRedPath, (int)(maxWidth * 0.09), -1);
        ImageIcon blueFrame = ViewHelper.loadImageIcon(fgBluePath, (int)(maxWidth * 0.09), -1);
        ImageIcon godIcon = ViewHelper.makeRoundedImage(
                Objects.requireNonNull(ViewHelper.loadImageIcon(godImagePath, (defaultFrame != null ? defaultFrame.getIconWidth() : 0) - 3, (defaultFrame != null ? defaultFrame.getIconHeight() : 0) - 3)).getImage(),
                50, 50);

        int width = godIcon.getIconWidth();
        int height = godIcon.getIconHeight();

        BufferedImage defaultImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics g = defaultImage.getGraphics();
        g.drawImage(godIcon.getImage(), 0, 0, null);
        g.drawImage(Objects.requireNonNull(defaultFrame).getImage(), (width - defaultFrame.getIconWidth()) / 2, (height - defaultFrame.getIconHeight()) / 2, null);
        g.dispose();
        this.defaultIcon = new ImageIcon(defaultImage);

        // Compose hover image
        BufferedImage hoverImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics g2 = hoverImage.getGraphics();
        g2.drawImage(godIcon.getImage(), 0, 0, null);
        g2.drawImage(Objects.requireNonNull(hoverFrame).getImage(), (width - hoverFrame.getIconWidth()) / 2, (height - hoverFrame.getIconHeight()) / 2, null);
        g2.dispose();
        ImageIcon hoverIcon = new ImageIcon(hoverImage);

        // Compose selected image (you can customize it more)
        BufferedImage redImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics g3 = redImage.getGraphics();
        g3.drawImage(godIcon.getImage(), 0, 0, null);
        g3.drawImage(Objects.requireNonNull(redFrame).getImage(), (width - redFrame.getIconWidth()) / 2, (height - redFrame.getIconHeight()) / 2, null);
        g3.dispose();
        this.redIcon = new ImageIcon(redImage);

        BufferedImage blueImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics g4 = blueImage.getGraphics();
        g4.drawImage(godIcon.getImage(), 0, 0, null);
        g4.drawImage(Objects.requireNonNull(blueFrame).getImage(), (width - blueFrame.getIconWidth()) / 2, (height - blueFrame.getIconHeight()) / 2, null);
        g4.dispose();
        this.blueIcon = new ImageIcon(blueImage);

        // Configure button visuals
        selectedIcon = defaultIcon;
        setIcon(selectedIcon);
        setRolloverIcon(hoverIcon);
        setDisabledIcon(selectedIcon);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        setOpaque(true);
        // === Set size to match image ===
        Dimension size = new Dimension(defaultIcon.getIconWidth(), defaultIcon.getIconHeight());
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
    }

    public void setSelected(SelectionColor selectionColor) {
        switch (selectionColor) {
            case NONE:
                selectedIcon = defaultIcon;
                setIcon(selectedIcon);
                setEnabled(true);
                break;
            case RED:
                selectedIcon = redIcon;
                setDisabledIcon(selectedIcon);
                setEnabled(false);
                break;
            case BLUE:
                selectedIcon = blueIcon;
                setDisabledIcon(selectedIcon);
                setEnabled(false);
                break;
            default:
                break;
        }

        revalidate();
        repaint();
    }
}
