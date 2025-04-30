package View.Game;

import javax.swing.*;

public class SidePanel extends JPanel {
    public SidePanel() {
        setOpaque(false);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Example player info
        add(new JLabel("Player 1: Apollo"));
//        add(new GodCardPanel("Apollo", "Move into opponent's space"));

        add(Box.createVerticalStrut(20)); // Space between players

        add(new JLabel("Player 2: Artemis"));
//        add(new GodCardPanel("Artemis", "Move twice but not back"));
    }
}
