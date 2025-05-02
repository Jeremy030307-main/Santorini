package View.Setup;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ChallengerChooseGodsPanel extends SetupPanel {

    private static final String textPath = "choose_the_gods.png";

    private final List<String> godCardPaths;
    private final List<JButton> godCardButtons = new ArrayList<>(); // Store god card buttons

    public ChallengerChooseGodsPanel(List<String> godCardPaths) {
        super(textPath, "home_exit_btn.png", "home_next_btn.png", "home_exit_btn_pressed.png", "home_next_btn_pressed.png");
        this.godCardPaths = godCardPaths;
        initializeContent();
    }

    @Override
    public JPanel getContentPanel() {
        JPanel cardsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        cardsPanel.setOpaque(false);

        for (String path : godCardPaths) {
            // Create button instead of label
            JButton godCardButton = createGodCardButton("../../" + path);
            godCardButtons.add(godCardButton); // Add to our list

            godCardButton.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
            cardsPanel.add(godCardButton);
        }

        return cardsPanel;
    }

    public JButton getExitButton() {
        return leftButton;
    }

    public JButton getNextButton() {
        return rightButton;
    }

    public List<JButton> getGodCardButtons() {
        return godCardButtons;
    }

    private JButton createGodCardButton(String imagePath) {
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource(imagePath)));
        Image originalImage = icon.getImage();

        int targetWidth = 120;
        int targetHeight = (int) (originalImage.getHeight(null) *
                ((double) targetWidth / originalImage.getWidth(null)));

        Image scaledImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        JButton button = new JButton(scaledIcon);

        // Style the button to look like the original card
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setOpaque(false);
        button.setPreferredSize(new Dimension(targetWidth, targetHeight));

        return button;
    }
}
