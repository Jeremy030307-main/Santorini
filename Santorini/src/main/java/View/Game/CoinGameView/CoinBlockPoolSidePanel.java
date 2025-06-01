package View.Game.CoinGameView;

import View.Game.BasicGameView.BlockPoolSidePanel;
import View.Game.MapComponent.JBlock;
import View.ViewHelper;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class CoinBlockPoolSidePanel extends BlockPoolSidePanel {

    public CoinBlockPoolSidePanel(HashMap<JBlock, Integer> blockPool,
                                  HashMap<JBlock, Integer> blockPrice,
                                  int width, int height) {
        super(blockPool, width, height);
        replaceBlockRowsWithOverlay(blockPool, blockPrice, width);
    }

    private void replaceBlockRowsWithOverlay(HashMap<JBlock, Integer> blockPool,HashMap<JBlock, Integer> blockPrice, int width) {
        this.removeAll(); // Remove original BlockRowPanels
        for (JBlock block : blockPool.keySet()) {
            BlockRowPanel blockRowPanel = new CoinBlockRowPanel(block, blockPrice.get(block), blockPool.get(block), (int) (width * 0.3));
            this.blockPool.put(block,blockRowPanel);
            add(blockRowPanel);
        }
        revalidate();
        repaint();
    }

    private class CoinBlockRowPanel extends BlockRowPanel {

        public static final String COIN_ICON_PATH = "Cell/coin.png";

        public CoinBlockRowPanel(JBlock block, int amount, int count, int width) {
            super(block, count, width);

            // Remove all from BlockRowPanel to customize layout
            this.removeAll();
            this.setLayout(new FlowLayout(FlowLayout.CENTER));
            this.setOpaque(false);

            // Create layered pane to hold image and overlay
            JLayeredPane layeredPane = new JLayeredPane();
            layeredPane.setPreferredSize(new Dimension(width, width));
            layeredPane.setOpaque(false);

            // --- Block Image ---
            ImageIcon icon = ViewHelper.loadImageIcon(block.getPath(), width, -1);
            JLabel iconLabel = new JLabel(icon);
            iconLabel.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());

            ImageIcon coinIcon = ViewHelper.loadImageIcon(COIN_ICON_PATH, (int) (icon.getIconWidth() * 0.4), (int) (icon.getIconWidth() * 0.4));

            // --- Coin Overlay Panel ---
            JPanel overlayPanel = new JPanel();
            overlayPanel.setOpaque(false);
            overlayPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 2, 2));
            overlayPanel.setBounds(
                    0,
                    icon.getIconHeight()/2 - coinIcon.getIconHeight() / 2,
                    icon.getIconWidth(),
                    icon.getIconHeight()); // center-ish

            if (coinIcon != null) {
                overlayPanel.add(new JLabel(coinIcon));
            }

            JLabel priceLabel = new JLabel(String.valueOf(amount));
            priceLabel.setFont(new Font("Arial", Font.BOLD, 15));
            priceLabel.setForeground(Color.YELLOW);
            overlayPanel.add(priceLabel);

            // Add to layeredPane
            layeredPane.add(iconLabel, JLayeredPane.DEFAULT_LAYER);
            layeredPane.add(overlayPanel, JLayeredPane.PALETTE_LAYER);

            this.add(layeredPane);

            // --- Count label beside image ---
            countLabel = new JLabel("x" + count);
            countLabel.setFont(new Font("Arial", Font.BOLD, 16));
            countLabel.setForeground(Color.WHITE);
            this.add(countLabel);
        }
    }
}



