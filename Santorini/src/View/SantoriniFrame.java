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
        setLocationRelativeTo(null); // Center on screen

        // Setup CardLayout
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Add the different views (screens)
        cardPanel.add(new MainGamePanel(this), GAME_VIEW);
//        cardPanel.add(new SettingsPanel(this), SETTINGS_VIEW);

        // Show Home first
        cardLayout.show(cardPanel, GAME_VIEW);

        setContentPane(cardPanel);
        setVisible(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

    }

    // Method to switch views
    public void showView(String viewName) {
        cardLayout.show(cardPanel, viewName);
    }
}
