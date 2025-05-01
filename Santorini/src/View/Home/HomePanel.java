package View.Home;

import View.SantoriniPanel;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class HomePanel extends SantoriniPanel {

    private static final String imgPath = "intro.png";
    private static final int BUTTON_SIZE = 100;
    private JButton playButton;
    private JButton loadButton;

    public HomePanel() {
        super(imgPath);
        setLayout(new GridBagLayout());

        createPlayButton();
        createLoadButton();
    }

    private void createPlayButton(){
        GridBagConstraints c = new GridBagConstraints();

        ImageIcon icon = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("../../Asset/Image/Button/play_button.png")));
        Image img = icon.getImage().getScaledInstance((int) (getMaxWidth()*0.2),(int) (getMaxWidth()*0.2) , Image.SCALE_SMOOTH);
        icon = new ImageIcon(img);

        c.gridy = 2;
        c.gridx = 0;
        c.anchor = GridBagConstraints.SOUTH;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 1;
        c.insets = new Insets(0, 0, (int) (getMaxHeight()*0.2), (int) (getMaxWidth() * 0.75));

        playButton = new JButton(icon);
        playButton.setOpaque(false);
        playButton.setContentAreaFilled(false);
        playButton.setBorderPainted(false);
        playButton.setFocusPainted(false);

        add(playButton, c);
    }

    private void createLoadButton(){
        GridBagConstraints c = new GridBagConstraints();

        ImageIcon icon = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("../../Asset/Image/Button/load_button.png")));
        Image img = icon.getImage().getScaledInstance((int) (getMaxWidth()*0.2),(int) (getMaxWidth()*0.2) , Image.SCALE_SMOOTH);
        icon = new ImageIcon(img);

        c.gridy = 2;
        c.gridx = 0;
        c.anchor = GridBagConstraints.SOUTH;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 1;
        c.insets = new Insets(0, (int) (getMaxWidth() * 0.75),(int) (getMaxHeight()*0.2), 0 );

        loadButton = new JButton(icon);
        loadButton.setOpaque(false);
        loadButton.setContentAreaFilled(false);
        loadButton.setBorderPainted(false);
        loadButton.setFocusPainted(false);

        add(loadButton, c);
    }

    public JButton getPlayButton() {
        return playButton;
    }

    public JButton getLoadButton() {
        return loadButton;
    }
}
