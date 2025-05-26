package View.Setup;

import View.SantoriniPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class SetupLobby extends SantoriniPanel {

    private static final String imgPath = "lobby.png";

    private CardLayout cardLayout;
    private JPanel cardPanel;

    /**
     * Constructs a new {@code SantoriniPanel} with the specified image path.
     * Initializes the panel's background image, scales it, and sets the panel's layout.
     *
     */
    public SetupLobby() {
        super(imgPath);

        setLayout(null); // Allow manual positioning

        // Create transparent CardLayout panel
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        cardPanel.setOpaque(false);

        // Position and size this to match the black content area
        cardPanel.setBounds(
                getMaxWidth()/8,
                (int) (getMaxHeight()/15.5),
                getMaxWidth() - (2*getMaxWidth()/8),
                (int) (getMaxHeight() - (2*getMaxHeight()/15.5)));

        add(cardPanel);
    }

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
            cardLayout.show(cardPanel, nextViewName);
            return;
        }

        // Show the next view to render it
        cardLayout.show(cardPanel, nextViewName);
        Component nextComponent = null;
        for (Component comp : cardPanel.getComponents()) {
            if (comp.isVisible()) {
                nextComponent = comp;
                break;
            }
        }

        // Render both components into images
        int w = cardPanel.getWidth();
        int h = cardPanel.getHeight();
        BufferedImage currentImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        BufferedImage nextImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        currentComponent.paint(currentImage.getGraphics());
        nextComponent.paint(nextImage.getGraphics());

        // Hide actual components during animation
        currentComponent.setVisible(false);
        nextComponent.setVisible(false);

        // Animation label overlay
        JLabel animationLabel = new JLabel() {
            int offset = 0;
            Timer timer = new Timer(20, null);

            {
                setOpaque(false);
                timer.addActionListener(e -> {
                    offset += w / 20; // Adjust speed here (e.g., 20 frames total)
                    if (offset >= w) {
                        offset = w;
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
                g2.drawImage(currentImage, -offset, 0, null);      // Slide out to left
                g2.drawImage(nextImage, w - offset, 0, null);      // Slide in from right
            }
        };

        animationLabel.setSize(w, h);
        cardPanel.add(animationLabel);
        cardPanel.setComponentZOrder(animationLabel, 0);
        animationLabel.repaint();
    }

    public void addCard(SetupCard newCard, String name) {
        newCard.setMaxWidth(cardPanel.getWidth());
        newCard.setMaxHeight(cardPanel.getHeight());
        newCard.setup();
        cardPanel.add(newCard, name);
    }
}
