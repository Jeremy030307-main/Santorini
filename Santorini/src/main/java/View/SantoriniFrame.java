package View;

import Controller.HomeController;
import View.Game.MapComponent.JPlayer;
import View.GameOver.WinPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

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
        SwingUtilities.invokeLater(() -> showView(HomeController.HOME_VIEW));

        setContentPane(cardPanel);

        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

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
     * @param nextViewName The name of the view to display
     */
    public void showView(String nextViewName) {
        // Prepare both current and next components
        Component currentComponent = null;
        for (Component comp : cardPanel.getComponents()) {
            if (comp.isVisible()) {
                currentComponent = comp;
                break;
            }
        }

        if (currentComponent == null) {
            // Just show the view if nothing visible
            showView(nextViewName);
            return;
        }

        // Make sure the next view is added and laid out
        cardLayout.show(cardPanel, nextViewName);
        Component nextComponent = null;
        for (Component comp : cardPanel.getComponents()) {
            if (comp.isVisible()) {
                nextComponent = comp;
                break;
            }
        }

        // Render both components to images
        int w = cardPanel.getWidth();
        int h = cardPanel.getHeight();
        BufferedImage currentImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        BufferedImage nextImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

        currentComponent.paint(currentImage.getGraphics());
        nextComponent.paint(nextImage.getGraphics());

        // Hide next view during transition
        nextComponent.setVisible(false);

        // Create overlay label for animation
        JLabel animationLabel = new JLabel() {
            float alpha = 0f;
            Timer timer = new Timer(20, null);

            {
                setOpaque(false);
                timer.addActionListener(e -> {
                    alpha += 0.05f;
                    if (alpha >= 1f) {
                        alpha = 1f;
                        timer.stop();
                        cardLayout.show(cardPanel, nextViewName);
                        cardPanel.remove(this);
                        cardPanel.repaint();
                    }
                    repaint();
                });
                timer.start();
            }

            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f - alpha));
                g2.drawImage(currentImage, 0, 0, null);
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
                g2.drawImage(nextImage, 0, 0, null);
            }
        };

        animationLabel.setSize(w, h);
        cardPanel.add(animationLabel);
        cardPanel.setComponentZOrder(animationLabel, 0);
        animationLabel.repaint();
    }


    public void addView(JPanel view, String viewName) {
        cardPanel.add(view, viewName);
    }
}
