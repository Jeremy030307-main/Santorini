package View.Game;

import javax.swing.*;
import java.awt.*;

public class TopPanel extends JPanel {
    public TopPanel() {
        setOpaque(false);
        setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel titleLabel = new JLabel("Santorini - Player 1's Turn");
        add(titleLabel);
    }
}

