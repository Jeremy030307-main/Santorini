package View.Game;

import javax.swing.*;
import java.awt.*;

public class BoardPanel extends JPanel {
    private static final int SIZE = 5;

    public BoardPanel() {
        setOpaque(false);

        setLayout(new GridLayout(SIZE, SIZE));
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                add(new CellPanel(row, col));
            }
        }
    }
}

