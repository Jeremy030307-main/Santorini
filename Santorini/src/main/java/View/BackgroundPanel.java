package View;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * The {@code BackgroundPanel} interface defines constants and static utility methods used for handling
 * background images and scaling operations in the Santorini game.
 * <p>
 * The interface provides functionality for scaling images to fit specific dimensions, 
 * as well as calculating scale factors to maintain aspect ratios when resizing components.
 * </p>
 */
public interface BackgroundPanel {
    String BACKGROUND = "/Image/Background/";
    int WIDTH = 1067;
    int HEIGHT = 600;

    /**
     * Scales the provided image to the specified width and height.
     * This method resizes the image while maintaining its visual quality using bilinear interpolation.
     *
     * @param srcImg The source image to be resized
     * @param w The target width for the resized image
     * @param h The target height for the resized image
     * @return A new {@link ImageIcon} containing the resized image
     */
    static ImageIcon getScaledImage(ImageIcon srcImg, int w, int h) {
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg.getImage(), 0, 0, w, h, null);
        g2.dispose();

        return new ImageIcon(resizedImg);
    }

    /**
     * Calculates the scale factor required to resize an object from the master size to the target size.
     *
     * @param iMasterSize The original size of the object
     * @param iTargetSize The target size for the object
     * @return The scale factor for resizing the object
     */
    static double getScaleFactor(int iMasterSize, int iTargetSize) {
        double dScale = 1;

        if (iMasterSize > iTargetSize)
            dScale = (double) iTargetSize / (double) iMasterSize;
        else
            dScale = (double) iTargetSize / (double) iMasterSize;

        return dScale;
    }

    /**
     * Calculates the scale factor needed to fit an original dimension into a target dimension,
     * maintaining the aspect ratio of the original.
     *
     * @param original The original dimension of the object
     * @param toFit The target dimension to fit the object into
     * @return The scale factor for resizing the object to fit the target dimension
     */
    static double getScaleFactorToFit(Dimension original, Dimension toFit) {
        double dScale = 1d;

        if (original != null && toFit != null) {
            double dScaleWidth = getScaleFactor(original.width, toFit.width);
            double dScaleHeight = getScaleFactor(original.height, toFit.height);

            dScale = Math.min(dScaleHeight, dScaleWidth);
        }

        return dScale;
    }
}