package View;

import View.Game.MainGamePanel;

import javax.swing.*;
import java.awt.*;

public class SantoriniFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel cardPanel;

    public static final String GAME_VIEW = "GameView";
    public static final String SETTINGS_VIEW = "SettingsView";

    public SantoriniFrame() {
        setTitle("Santorini");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Setup CardLayout
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Add the different views (screens)
        MainGamePanel mainGamePanel = new MainGamePanel();
        cardPanel.add(mainGamePanel, GAME_VIEW);

        // Show Home first
        cardLayout.show(cardPanel, GAME_VIEW);

        setContentPane(cardPanel);

        // Now make the frame's content pane exactly fit the panel's preferred size
        pack(); // important: makes insets available
        Insets insets = getInsets();

        Dimension preferredSize = mainGamePanel.getPreferredSize(); // e.g., 1728 x 971
        int frameWidth = preferredSize.width + insets.left + insets.right;
        int frameHeight = preferredSize.height + insets.top + insets.bottom;

        // Now explicitly set frame size to fit content
        setSize(frameWidth, frameHeight);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    // Method to switch views
    public void showView(String viewName) {
        cardLayout.show(cardPanel, viewName);
    }
}
