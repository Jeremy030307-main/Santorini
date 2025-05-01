package View;

import Controller.HomeController;

import javax.swing.*;
import java.awt.*;

/**
 * The {@code SantoriniFrame} class represents the main window for the Santorini game.
 * This class extends {@link JFrame} and manages the display of different views using a {@link CardLayout}.
 * The frame includes methods to switch between different views and add new views dynamically.
 * <p>
 * It initializes the main game frame, sets up the layout, and provides methods to control the views
 * shown to the user during gameplay. The frame is responsible for managing the game's user interface.
 * </p>
 */
public class SantoriniFrame extends JFrame {

    private static SantoriniFrame instance;
    private CardLayout cardLayout;
    private JPanel cardPanel;

    /**
     * Constructs a new {@code SantoriniFrame} instance.
     * This constructor initializes the main game window, sets up a {@link CardLayout} to handle different views,
     * and displays the home view. It also sets the properties of the frame, such as the title, visibility, and size.
     */
    private SantoriniFrame() {
        setTitle("Santorini");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Setup CardLayout
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        HomeController homeController = new HomeController(this);
        showView(HomeController.HOME_VIEW);

        setContentPane(cardPanel);

        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

//        Insets insets = getInsets();
//
//        Dimension preferredSize = mainGamePanel.getPreferredSize(); // e.g., 1728 x 971
//        int frameWidth = preferredSize.width + insets.left + insets.right;
//        int frameHeight = preferredSize.height + insets.top + insets.bottom;
//
//        // Now explicitly set frame size to fit content
//        setSize(frameWidth, frameHeight);
    }

    /**
     * Retrieves the singleton instance of {@code SantoriniFrame}.
     * If the instance does not exist, it creates a new one.
     *
     * @return The singleton instance of {@code SantoriniFrame}
     */
    public static SantoriniFrame getInstance() {
        if (instance == null) {
            instance = new SantoriniFrame();
        }
        return instance;
    }

    /**
     * Switches the current view to the specified view name.
     * This method uses the {@link CardLayout} to display the desired view in the {@code cardPanel}.
     *
     * @param viewName The name of the view to display
     */
    public void showView(String viewName) {
        cardLayout.show(cardPanel, viewName);
    }

    /**
     * Adds a new view to the {@code cardPanel} with the specified view name.
     * This method allows new panels to be dynamically added to the {@link CardLayout}.
     *
     * @param view The panel to add to the frame
     * @param viewName The name associated with the view to be used for switching views
     */
    public void addView(JPanel view, String viewName) {
        cardPanel.add(view, viewName);
    }
}
