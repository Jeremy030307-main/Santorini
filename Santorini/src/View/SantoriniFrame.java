package View;

import Controller.HomeController;

import javax.swing.*;
import java.awt.*;

public class SantoriniFrame extends JFrame {

    private static SantoriniFrame instance;
    private CardLayout cardLayout;
    private JPanel cardPanel;

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

    public static SantoriniFrame getInstance() {
        if (instance == null) {
            instance = new SantoriniFrame();
        }
        return instance;
    }

    // Method to switch views
    public void showView(String viewName) {
        cardLayout.show(cardPanel, viewName);
    }

    public void addView(JPanel view, String viewName) {
        cardPanel.add(view, viewName);
    }
}
