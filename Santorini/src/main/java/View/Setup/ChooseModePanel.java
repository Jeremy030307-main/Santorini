package View.Setup;

import View.ViewHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class ChooseModePanel extends SetupCard {

    private final List<String> optionsButtonImagePaths;
    private final List<ActionListener> actionListeners;
    private JPanel buttonPanel;

    public ChooseModePanel(List<String> optionsButtonImagePaths, List<ActionListener> actionListeners) {
        this.optionsButtonImagePaths = optionsButtonImagePaths;
        this.actionListeners = actionListeners;

        setLayout(new BorderLayout());
        setOpaque(false);

        // Title label
        JLabel titleLabel = new JLabel("Choose a Mode", SwingConstants.CENTER);
        titleLabel.setFont(ViewHelper.loadFont("Zeus-Land.ttf", 50, Font.BOLD));
        titleLabel.setForeground(new Color(0x715535));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(50, 0, 10, 0));

        add(titleLabel, BorderLayout.NORTH);

        // Center panel with GridBagLayout to center buttons vertically and horizontally
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false);

        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setOpaque(false);

        centerPanel.add(buttonPanel);
        add(centerPanel, BorderLayout.CENTER);
    }

    @Override
    void setup() {
        buttonPanel.removeAll();

        for (int i = 0; i < optionsButtonImagePaths.size(); i++) {
            String imagePath = optionsButtonImagePaths.get(i);
            ImageIcon icon = ViewHelper.loadImageIcon(imagePath, -1, -1);
            JButton imageButton = new JButton(icon);
            imageButton.setBorder(BorderFactory.createEmptyBorder());
            imageButton.setContentAreaFilled(false);
            imageButton.addActionListener(actionListeners.get(i));
            buttonPanel.add(imageButton);
        }

        revalidate();
        repaint();
    }
}
