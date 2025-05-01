package View.Game.MapComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class JCell extends JButton {

    private int row;
    private int col;

    private List<JBlock> blocks;
    private JWorker worker;
    private JCellAction action;

    private Image backgroundImage; // Background image
    private Color borderColor = null;


    public JCell(int row, int col) {
        this.row = row;
        this.col = col;
        this.blocks = new ArrayList<>();

        setFocusPainted(false);
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setLayout(new GridBagLayout());
        setName("cell");

        addMouseListener(new java.awt.event.MouseAdapter() {

            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                if (action != null) {
                    borderColor = action.getBorderColor();
                    repaint();
                }
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                borderColor = null;
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();

        // Draw blocks
        for (JBlock block : blocks) {
            Image img = new ImageIcon(Objects.requireNonNull(getClass().getResource("../../../" + block.getPath()))).getImage();
            g2d.drawImage(img, 0, 0, getWidth(), getHeight(), this);
        }

        // Draw worker
        if (worker != null) {
            Image img1 = new ImageIcon(Objects.requireNonNull(getClass().getResource("../../../" + worker.getPath()))).getImage();
            g2d.drawImage(img1, 0, 0, getWidth(), getHeight(), this);
        }

        // Draw action icon
        if (action != null) {
            Image img1 = new ImageIcon(Objects.requireNonNull(getClass().getResource("../../../" + action.getPath()))).getImage();
            g2d.drawImage(img1, 0, 0, getWidth(), getHeight(), this);
        }

        // Draw border manually
        if (borderColor != null) {
            g2d.setColor(borderColor);
            g2d.setStroke(new BasicStroke(3));
            g2d.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
        }

        g2d.dispose();
        super.paintComponent(g);


    }

    public void addBlock(JBlock block) {
        blocks.add(block);
    }

    public void setBlocks(List<JBlock> blocks) {
        this.blocks = blocks;
    }

    public JBlock removeTopBlock(){
        return blocks.removeLast();
    }

    public void setWorker(JWorker worker) {
        this.worker = worker;
    }

    public void setAction(JCellAction action) {
        this.action = action;
    }

    public void clearAction(){
        action = null;
        borderColor = null;
    }

}

