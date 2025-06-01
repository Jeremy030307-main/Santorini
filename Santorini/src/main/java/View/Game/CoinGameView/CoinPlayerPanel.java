package View.Game.CoinGameView;

import View.Game.BasicGameView.ActivePlayerPanel;
import View.Game.MapComponent.JPlayer;
import View.ViewHelper;

import javax.swing.*;
import java.awt.*;

public class CoinPlayerPanel extends ActivePlayerPanel {
    public static final String COIN_ICON_PATH = "Cell/coin.png";

    private JPanel combineLabel;
    private JLabel coinLabel;
    private int coinCount = 0;
    private int width;

    public CoinPlayerPanel(JPlayer player, String playerName, String godName, int width) {
        super(player, playerName, godName, width);
        this.width = width;
        SwingUtilities.invokeLater(() -> setupCoinLabel());
    }

    private void setupCoinLabel() {

        ImageIcon coinIcon = ViewHelper.loadImageIcon(COIN_ICON_PATH, 40, -1);

        // --- Coin Overlay Panel ---
        combineLabel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                // Enable anti-aliasing for smoother corners
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Semi-transparent black background
                g2.setColor(new Color(0, 0, 0, 150));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20); // 20px rounded corners

                g2.dispose();
                super.paintComponent(g); // optional; you can omit this if not painting children yourself
            }

            @Override
            public boolean isOpaque() {
                return false; // Important for transparency
            }
        };
        combineLabel.setOpaque(false);
        combineLabel.setLayout(new FlowLayout(FlowLayout.CENTER, 2, 2));

        if (coinIcon != null) {
            combineLabel.add(new JLabel(coinIcon));
        }

        coinLabel = new JLabel("" + coinCount);
        coinLabel.setFont(new Font("SansSerif", Font.BOLD, 17));
        coinLabel.setForeground(Color.YELLOW);
        coinLabel.setOpaque(false);
        combineLabel.add(coinLabel);
        combineLabel.setOpaque(true);
        combineLabel.setBackground(new Color(0, 0, 0, 150)); // Black with ~60% opacity


        add(combineLabel);
        positionCoinLabel();
    }

    private void positionCoinLabel() {
        int x = getWidth() - 100; // Adjust position
        int y = 10;
        combineLabel.setBounds(x, y, 70, 45);
    }

    public void setCoinCount(int coins) {
        this.coinCount = coins;
        if (coinLabel != null) {
            coinLabel.setText("" + coinCount);
            positionCoinLabel();
            repaint();
        }
    }
}
