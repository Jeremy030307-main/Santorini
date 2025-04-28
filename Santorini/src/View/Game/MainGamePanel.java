package View.Game;

import View.SantoriniFrame;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class MainGamePanel extends JPanel {

    private Image backgroundImage;

    public MainGamePanel(SantoriniFrame frame) {
        setLayout(new BorderLayout());

        backgroundImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("../../Asset/sea_background.jpg"))).getImage();

        // Top: Turn Info
        add(new TopPanel(), BorderLayout.NORTH);

        // Center: Board (centered with GridBagLayout)
        JPanel boardPanelWrapper = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Set the constraints to center the BoardPanel
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER; // Centers the component in both directions
        boardPanelWrapper.add(new BoardPanel(), gbc);

        // Make sure the wrapper is transparent
        boardPanelWrapper.setOpaque(false);

        // Add to center of BorderLayout
        add(boardPanelWrapper, BorderLayout.CENTER);

        // East: Player info and God Cards
        add(new SidePanel(), BorderLayout.WEST);

        // South: Bottom Buttons
        add(new BottomPanel(), BorderLayout.SOUTH);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}

