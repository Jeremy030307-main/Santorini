package View.Home;

import View.SantoriniPanel;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * The {@code HomePanel} class represents the home screen panel in the Santorini game.
 * It extends {@link SantoriniPanel} and contains the layout and functionality for the home screen,
 * including the "Play" and "Load" buttons.
 * <p>
 * This class handles the creation and positioning of the buttons, as well as the functionality
 * for user interactions such as starting a new game or loading a saved game.
 * </p>
 */
public class HomePanel extends SantoriniPanel {

    private static final String imgPath = "intro.png";
    private static final int BUTTON_SIZE = 100;
    private JButton playButton;
    private JButton loadButton;

    /**
     * Constructs a new {@code HomePanel} instance.
     * Initializes the home screen with a background image and sets the layout.
     * This constructor also creates the "Play" and "Load" buttons and places them on the panel.
     */
    public HomePanel() {
        super(imgPath);
        setLayout(new GridBagLayout());

        createPlayButton();
        createLoadButton();
    }

    /**
     * Creates and configures the "Play" button on the home screen.
     * The button is associated with an icon and positioned on the panel using {@link GridBagLayout}.
     */
    private void createPlayButton(){
        GridBagConstraints c = new GridBagConstraints();

        ImageIcon icon = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("../../Asset/Image/Button/play_button.png")));
        Image img = icon.getImage().getScaledInstance((int) (getMaxWidth()*0.2),(int) (getMaxWidth()*0.2) , Image.SCALE_SMOOTH);
        icon = new ImageIcon(img);

        c.gridy = 2;
        c.gridx = 0;
        c.anchor = GridBagConstraints.SOUTH;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 1;
        c.insets = new Insets(0, 0, (int) (getMaxHeight()*0.2), (int) (getMaxWidth() * 0.75));

        playButton = new JButton(icon);
        playButton.setOpaque(false);
        playButton.setContentAreaFilled(false);
        playButton.setBorderPainted(false);
        playButton.setFocusPainted(false);

        add(playButton, c);
    }

    /**
     * Creates and configures the "Load" button on the home screen.
     * The button is associated with an icon and positioned on the panel using {@link GridBagLayout}.
     */
    private void createLoadButton(){
        GridBagConstraints c = new GridBagConstraints();

        ImageIcon icon = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("../../Asset/Image/Button/load_button.png")));
        Image img = icon.getImage().getScaledInstance((int) (getMaxWidth()*0.2),(int) (getMaxWidth()*0.2) , Image.SCALE_SMOOTH);
        icon = new ImageIcon(img);

        c.gridy = 2;
        c.gridx = 0;
        c.anchor = GridBagConstraints.SOUTH;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 1;
        c.insets = new Insets(0, (int) (getMaxWidth() * 0.75),(int) (getMaxHeight()*0.2), 0 );

        loadButton = new JButton(icon);
        loadButton.setOpaque(false);
        loadButton.setContentAreaFilled(false);
        loadButton.setBorderPainted(false);
        loadButton.setFocusPainted(false);

        add(loadButton, c);
    }

    /**
     * Retrieves the "Play" button.
     *
     * @return The {@link JButton} representing the "Play" button
     */
    public JButton getPlayButton() {
        return playButton;
    }

    /**
     * Retrieves the "Load" button.
     *
     * @return The {@link JButton} representing the "Load" button
     */
    public JButton getLoadButton() {
        return loadButton;
    }
}
