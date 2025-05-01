package View;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public abstract class SantoriniPanel extends JPanel implements BackgroundPanel{

    private final String imgPath;
    private final Image img;

    private int scaleWidth;
    private int scaleHeight;
    private int maxWidth;
    private int maxHeight;

    public SantoriniPanel(String imgPath) {
        this.imgPath = imgPath;

        this.scaleWidth = BackgroundPanel.WIDTH;
        this.scaleHeight = BackgroundPanel.HEIGHT;
        Dimension firstSize = new Dimension(scaleWidth, scaleHeight);
        maxWidth = (int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.85);
        maxHeight = maxWidth * firstSize.height / firstSize.width;

        img = BackgroundPanel.getScaledImage(new ImageIcon(Objects.requireNonNull(this.getClass().getResource(BackgroundPanel.BACKGROUND + this.imgPath))),
                maxWidth, maxHeight).getImage();

        setLayout(new GridBagLayout());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        double scaleFactor = Math.min(1d, BackgroundPanel.getScaleFactorToFit(
                new Dimension(img.getWidth(null), img.getHeight(null)), getSize()));

        scaleWidth = (int) Math.round(img.getWidth(null) * scaleFactor);
        scaleHeight = (int) Math.round(img.getHeight(null) * scaleFactor);

        Image scaled = img.getScaledInstance(scaleWidth, scaleHeight, Image.SCALE_SMOOTH);

        int width = getWidth() - 1;
        int height = getHeight() - 1;

        int x = (width - scaled.getWidth(null)) / 2;
        int y = (height - scaled.getHeight(null)) / 2;

        g.drawImage(scaled, x, y, this);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(maxWidth, maxHeight);
    }

    public int getScaleHeight() {
        return scaleHeight;
    }

    public int getScaleWidth() {
        return scaleWidth;
    }

    public int getMaxWidth() {
        return maxWidth;
    }

    public int getMaxHeight() {
        return maxHeight;
    }
}
