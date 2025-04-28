package View.Game;

import javax.swing.*;
import java.awt.*;

public class BottomPanel extends JPanel {
    public BottomPanel() {
        setOpaque(false);
        setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton endTurnButton = new JButton("End Turn");
        JButton undoButton = new JButton("Undo");

        add(endTurnButton);
        add(undoButton);

        // You can add ActionListeners to these buttons later
    }
}

