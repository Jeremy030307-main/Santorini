package View.Game.MapComponent;

import javax.swing.*;
import java.awt.*;

public class JBoard extends JPanel {

    private static final int SIZE = 5;

    private JCell[][] board;

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

    public void update(){
        repaint();
        validate();
    }

    public JCell[][] getBoard() {
        return board;
    }

    public JCell getCell(int row, int col) {
        return board[row][col];
    }
}

