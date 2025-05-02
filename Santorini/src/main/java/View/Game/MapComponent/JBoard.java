package View.Game.MapComponent;

import javax.swing.*;
import java.awt.*;

/**
 * The {@code JBoard} class represents the game board in the Santorini game.
 * It is a custom JPanel that uses a grid layout to display a 5x5 grid of cells (JCell).
 * Each cell represents a square on the board, and the board is rendered as a visual component.
 * <p>
 * The class provides methods for updating the board, accessing individual cells, and managing the layout.
 * </p>
 */
public class JBoard extends JPanel {

    private static final int SIZE = 5;

    private JCell[][] board;

    /**
     * Constructs a new {@code JBoard} instance, initializing a 5x5 grid of cells.
     * The board is rendered using a {@link GridBagLayout} layout, and each cell is added to the panel.
     */
    public JBoard() {

        super(new GridBagLayout());
        board = new JCell[SIZE][SIZE];

        setOpaque(false);
        setVisible(true);

        for (int col = 0; col < SIZE; col++) {
            for (int row = 0; row < SIZE; row++) {

                GridBagConstraints c = new GridBagConstraints();
                c.gridx = row;
                c.gridy = SIZE - col - 1;
                c.fill = GridBagConstraints.BOTH;
                c.weighty = 1;
                c.weightx = 1;
                c.insets = new Insets(10, 10,10,10);

                JCell cell = new JCell(row, col);
                board[row][col] = cell;
                add(cell, c);
            }
        }
    }

    /**
     * Updates the board by repainting and validating the panel.
     * This method is used to refresh the visual state of the board.
     */
    public void update(){
        repaint();
        validate();
    }

    /**
     * Retrieves the 2D array of cells representing the board.
     *
     * @return A 2D array of {@link JCell} objects representing the game board
     */
    public JCell[][] getBoard() {
        return board;
    }

    /**
     * Retrieves the cell at the specified row and column position.
     *
     * @param row The row index of the cell
     * @param col The column index of the cell
     * @return The {@link JCell} at the specified position
     */
    public JCell getCell(int row, int col) {
        return board[row][col];
    }
}
