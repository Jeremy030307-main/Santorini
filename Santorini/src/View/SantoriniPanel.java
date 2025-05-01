package View;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * The {@code SantoriniPanel} class is an abstract base class for panels that render backgrounds in the Santorini game.
 * This class extends {@link JPanel} and implements {@link BackgroundPanel}. It is responsible for rendering
 * the background image, scaling it to fit the panel, and providing utility methods for managing the panel's dimensions.
 * <p>
 * This class is intended to be extended by other panels in the game, such as the home panel or game panel, 
 * and provides the basic functionality for displaying a scaled background image.
 * </p>
 */
public abstract class SantoriniPanel extends JPanel implements BackgroundPanel{

    private final String imgPath;
    private final Image img;

    private int scaleWidth;
    private int scaleHeight;
    private int maxWidth;
    private int maxHeight;

    /**
     * Constructs a new {@code SantoriniPanel} with the specified image path.
     * Initializes the panel's background image, scales it, and sets the panel's layout.
     *
     * @param imgPath The path to the background image to be displayed
     */
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

    /**
     * Paints the component by scaling and rendering the background image.
     * The image is scaled to fit the panel while maintaining its aspect ratio.
     *
     * @param g The graphics context used to paint the component
     */
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

    /**
     * Retrieves the preferred size of the panel based on the maximum dimensions for the background image.
     *
     * @return A {@link Dimension} object representing the preferred size of the panel
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(maxWidth, maxHeight);
    }

    /**
     * Retrieves the scaled height of the background image.
     *
     * @return The scaled height of the background image
     */
    public int getScaleHeight() {
        return scaleHeight;
    }

    /**
     * Retrieves the scaled width of the background image.
     *
     * @return The scaled width of the background image
     */
    public int getScaleWidth() {
        return scaleWidth;
    }

    /**
     * Retrieves the maximum width of the background image, based on the screen size.
     *
     * @return The maximum width of the background image
     */
    public int getMaxWidth() {
        return maxWidth;
    }

    /**
     * Retrieves the maximum height of the background image, calculated based on the aspect ratio of the image.
     *
     * @return The maximum height of the background image
     */
    public int getMaxHeight() {
        return maxHeight;
    }
}
