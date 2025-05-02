package View.Game.MapComponent;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * The {@code JCell} class represents a cell on the Santorini game board.
 * It is a custom {@link JButton} that is used to represent a square on the game board.
 * Each cell can contain one or more blocks (representing the level of the cell), a worker,
 * and an action icon that can be triggered by the player. The class handles rendering the cell,
 * displaying its contents (blocks, workers, actions), and managing interactions with the player.
 * </p>
 * This class provides methods for adding and removing blocks, setting workers, and handling actions.
 * </p>
 */
public class JCell extends JButton {

    private int row;
    private int col;

    private List<JBlock> blocks;
    private JWorker worker;
    private JCellAction action;

    private Image backgroundImage; // Background image
    private Color borderColor = null;


    /**
     * Constructs a new {@code JCell} with the specified row and column coordinates.
     * Initializes an empty list of blocks and sets up the necessary properties for rendering.
     *
     * @param row The row index of the cell
     * @param col The column index of the cell
     */
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

    /**
     * Paints the visual components of the cell, including blocks, workers, and action icons.
     * This method is responsible for rendering the content of the cell and drawing its border.
     *
     * @param g The graphics context used to paint the component
     */
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();

        // Draw blocks
        for (JBlock block : blocks) {
            Image img = new ImageIcon(Objects.requireNonNull(getClass().getResource(block.getPath()))).getImage();
            g2d.drawImage(img, 0, 0, getWidth(), getHeight(), this);
        }

        // Draw worker
        if (worker != null) {
            Image img1 = new ImageIcon(Objects.requireNonNull(getClass().getResource(worker.getPath()))).getImage();
            g2d.drawImage(img1, 0, 0, getWidth(), getHeight(), this);
        }

        // Draw action icon
        if (action != null) {
            Image img1 = new ImageIcon(Objects.requireNonNull(getClass().getResource(action.getPath()))).getImage();
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

    /**
     * Adds a block to the cell's list of blocks. This block represents a level on the cell.
     *
     * @param block The block to be added to the cell
     */
    public void addBlock(JBlock block) {
        blocks.add(block);
    }

    /**
     * Sets the list of blocks for the cell. This method replaces any existing blocks in the cell.
     *
     * @param blocks The new list of blocks to set for the cell
     */
    public void setBlocks(List<JBlock> blocks) {
        this.blocks = blocks;
    }

    /**
     * Removes and returns the top block from the cell. This operation simulates the removal of the topmost block
     * from a stack of blocks in the cell.
     *
     * @return The block that was removed from the top of the stack
     */
    public JBlock removeTopBlock(){
        return blocks.removeLast();
    }

    /**
     * Sets the worker to be placed on this cell.
     *
     * @param worker The worker to be placed on the cell
     */
    public void setWorker(JWorker worker) {
        this.worker = worker;
    }

    /**
     * Sets the action icon for this cell. The action represents an icon that is displayed on the cell for player interaction.
     *
     * @param action The action to set on the cell
     */
    public void setAction(JCellAction action) {
        this.action = action;
    }

    /**
     * Clears the action and removes the border color for this cell.
     * This is typically used when resetting the action after it has been completed.
     */
    public void clearAction(){
        action = null;
        borderColor = null;
    }

}

