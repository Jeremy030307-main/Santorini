package View.Game.BasicGameView;

import View.Game.MapComponent.JBlock;
import View.ViewHelper;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class BlockPoolSidePanel extends JPanel {

    protected HashMap<JBlock, BlockRowPanel> blockPool;
    private ImageIcon background;

    public BlockPoolSidePanel(HashMap<JBlock, Integer> blockPool, int width, int height) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(
                (int) (height * 0.1),
                (int) (width * 0.2),
                (int) (height * 0.3),
                (int) (width * 0.35)));

        this.blockPool = new HashMap<>();
        for (JBlock block : blockPool.keySet()) {
            BlockRowPanel blockRowPanel = new BlockRowPanel(block, blockPool.get(block), (int) (width * 0.3));
            this.blockPool.put(block,blockRowPanel);
            add(blockRowPanel);
        }
        this.background = ViewHelper.loadImageIcon("Label/block_pool.png", (int) (width * 0.8), (int) (height * 0.8));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw background
        if (background != null) {
            g.drawImage(background.getImage(), 0, 0, background.getIconWidth(), background.getIconHeight(), this);
        }
    }

    public void updateBlockPool(HashMap<JBlock, Integer> blockPool) {
        for (JBlock block : blockPool.keySet()) {
            this.blockPool.get(block).updateCount(blockPool.get(block));
        }
        revalidate();
        repaint();
    }

    /**
     * Inner panel class to show a block image and its count.
     */
    protected class BlockRowPanel extends JPanel {

        private int count;
        protected JLabel countLabel; // <--- Store the label so you can update it

        public BlockRowPanel(JBlock block, int count, int width) {
            setLayout(new FlowLayout(FlowLayout.CENTER));
            setOpaque(false);

            ImageIcon icon = ViewHelper.loadImageIcon(block.getPath(), width, -1);
            if (icon != null) {
                Image img = icon.getImage();
                JLabel iconLabel = new JLabel(new ImageIcon(img));
                add(iconLabel);
            }

            this.count = count;
            countLabel = new JLabel("x" + this.count); // Store it
            countLabel.setFont(new Font("Arial", Font.BOLD, 16));
            countLabel.setForeground(Color.BLACK);
            add(countLabel);
        }

        public void updateCount(int count) {
            this.count = count;
            countLabel.setText("x" + this.count);
            revalidate();
            repaint();
        }
    }

}
