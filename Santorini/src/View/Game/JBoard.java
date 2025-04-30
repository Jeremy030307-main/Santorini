package View.Game;

import javax.swing.*;
import java.awt.*;

public class JBoard extends JPanel {
    private static final int SIZE = 5;

    public JBoard() {

        super(new GridBagLayout());

        setOpaque(false);
        setVisible(true);

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {

                GridBagConstraints c = new GridBagConstraints();
                c.gridx = row;
                c.gridy = SIZE - col - 1;
                c.fill = GridBagConstraints.BOTH;
                c.weighty = 1;
                c.weightx = 1;
                c.insets = new Insets(10, 10,10,10);

                add(new JCell(row, col), c);
            }
        }
    }

    // implement required function
}

