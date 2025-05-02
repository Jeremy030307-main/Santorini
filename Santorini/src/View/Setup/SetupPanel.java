package View.Setup;

import View.SantoriniPanel;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public abstract class SetupPanel extends SantoriniPanel {

    private static final String imgPath = "lobby.png";

    protected JButton leftButton;
    protected JButton rightButton;

    public SetupPanel(String logoImgPath, String leftButtonImgPath, String rightButtonImgPath, String leftButtonClickedImgPath, String rightButtonClickedImgPath) {
        super(imgPath);
        setLayout(new GridBagLayout());
        add_active_player_label();
        add_choose_gods_text(logoImgPath);
        createButton(leftButtonImgPath, rightButtonImgPath, leftButtonClickedImgPath, rightButtonClickedImgPath);
    }

    // Add a new method to be called after child class is initialized
    public void initializeContent() {
        add_content();
    }

    private void add_active_player_label(){
        ImageIcon logoIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("../../Asset/Image/Label/message.png")));
        Image originalImage = logoIcon.getImage();

        int originalWidth = originalImage.getWidth(null);
        int originalHeight = originalImage.getHeight(null);

        // Set your desired width
        int targetWidth = (int) (getMaxWidth() * 0.3);

        // Maintain aspect ratio: newHeight = (originalHeight / originalWidth) * targetWidth
        int targetHeight = (int) (((double) originalHeight / originalWidth) * targetWidth);

        Image scaledImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        JLabel logoLabel = new JLabel(scaledIcon);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Span across both columns
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets((int) (getMaxHeight()*0.1), 0, 0, 0);
        gbc.weightx = 1.0;
        gbc.weighty = 0.0;

        add(logoLabel, gbc);
    }

    private void add_choose_gods_text(String imgPath) {
        ImageIcon logoIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("../../Asset/Image/Text/" + imgPath)));
        Image originalImage = logoIcon.getImage();

        int originalWidth = originalImage.getWidth(null);
        int originalHeight = originalImage.getHeight(null);

        // Set your desired width
        int targetHeight = (int) (getMaxHeight() * 0.1);

        // Maintain aspect ratio: newHeight = (originalHeight / originalWidth) * targetWidth
        int targetWidth = (int) (((double) originalWidth / originalHeight) * targetHeight);

        Image scaledImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        JLabel logoLabel = new JLabel(scaledIcon);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2; // Span across both columns
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.weightx = 1.0;
        gbc.weighty = 0.0;

        add(logoLabel, gbc);

    }

    private void add_content() {

        GridBagConstraints gbcCards = new GridBagConstraints();
        gbcCards.gridx = 0;
        gbcCards.gridy = 2;
        gbcCards.weightx = 1.0;
        gbcCards.weighty = 1.0;
        gbcCards.anchor = GridBagConstraints.CENTER;
        gbcCards.insets = new Insets(0, 0, 0, 0);
        add(getContentPanel(), gbcCards);
    }

    public abstract JPanel getContentPanel();

    private void createButton(String leftButtonImgPath, String rightButtonImgPath, String leftButtonClickedImgPath, String rightButtonClickedImgPath) {
        // Add buttons panel at the bottom
        JPanel buttonsPanel = new JPanel(new GridBagLayout());
        buttonsPanel.setOpaque(false);

        // Create buttons with image states
        leftButton = createImageButton(
                "../../Asset/Image/Button/" + leftButtonImgPath,
                "../../Asset/Image/Button/" + leftButtonClickedImgPath);

        rightButton = createImageButton(
                "../../Asset/Image/Button/" + rightButtonImgPath,
                "../../Asset/Image/Button/" + rightButtonClickedImgPath);

        rightButton.setEnabled(false);

        /* Add exit button to left */
        GridBagConstraints gbcExit = new GridBagConstraints();
        gbcExit.gridx = 0;
        gbcExit.gridy = 0;
        gbcExit.anchor = GridBagConstraints.WEST;
        gbcExit.insets = new Insets(0, (int) (getMaxWidth() * 0.135), 0, 0);
        buttonsPanel.add(leftButton, gbcExit);

        GridBagConstraints gbcLabel = new GridBagConstraints();
        gbcLabel.gridx = 1;
        gbcLabel.gridy = 0;
        gbcLabel.weightx = 1.0; // Take all available horizontal space
        gbcLabel.anchor = GridBagConstraints.CENTER;
        buttonsPanel.add(new JLabel(), gbcLabel);

        /* Add next button to right */
        GridBagConstraints gbcNext = new GridBagConstraints();
        gbcNext.gridx = 2;
        gbcNext.gridy = 0;
        gbcNext.anchor = GridBagConstraints.EAST;
        gbcNext.insets = new Insets(0, 0, 0, (int) (getMaxWidth() * 0.13));
        buttonsPanel.add(rightButton, gbcNext);

        GridBagConstraints mainGbc = new GridBagConstraints();
        mainGbc.gridx = 0;
        mainGbc.gridy = 3;
        mainGbc.fill = GridBagConstraints.HORIZONTAL;
        mainGbc.weightx = 1.0;
        mainGbc.insets = new Insets(0, 0, (int) (getMaxHeight()*0.08), 0);
        add(buttonsPanel, mainGbc);
    }

    private JButton createImageButton(String normalImagePath, String clickedImagePath) {
        // Load normal state image
        ImageIcon normalIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource(normalImagePath)));
        Image normalImage = normalIcon.getImage();

        // Load clicked state image
        ImageIcon clickedIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource(clickedImagePath)));
        Image clickedImage = clickedIcon.getImage();

        // Scale images (optional - adjust as needed)
        int width = (int) (getMaxWidth() * 0.1); // Adjust to your preferred button width
        int normalHeight = (int) (normalImage.getHeight(null) * ((double) width / normalImage.getWidth(null)));
        int clickedHeight = (int) (clickedImage.getHeight(null) * ((double) width / clickedImage.getWidth(null)));

        Image scaledNormal = normalImage.getScaledInstance(width, normalHeight, Image.SCALE_SMOOTH);
        Image scaledClicked = clickedImage.getScaledInstance(width, clickedHeight, Image.SCALE_SMOOTH);

        // Create button with image states
        JButton button = new JButton(new ImageIcon(scaledNormal));

        button.setPressedIcon(new ImageIcon(scaledClicked));

        // Style adjustments
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setOpaque(false);
        button.setMargin(new Insets(0, 0, 0, 0));
        button.setPreferredSize(new Dimension(width, Math.max(normalHeight, clickedHeight)));

        return button;
    }
}
