package View.Game.BasicGameView;

import View.Game.MapComponent.JPlayer;
import View.ViewHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ActivePlayerPanel extends JPanel {

    private final static String CLOUD_IMAGE = "Label/clouds.png";
    private final static String ACTION_WINDOW = "Label/action_message.png";

    private final int width;
    private final JPlayer player;
    private final String playerName;
    private final String godName;

    private ImageIcon godIcon;
    private ImageIcon cloudIcon;
    private ImageIcon playerTagIcon;
    private ImageIcon actionWindowIcon;
    private ImageIcon buttonIcon;
    private ImageIcon pressedButtonIcon;

    private boolean playerActive;
    private boolean godActive;

    private JTextArea actionTextArea;
    private JButton actionButton;

    public ActivePlayerPanel(JPlayer player, String playerName, String godName, int width) {

        setOpaque(false);
        setLayout(null); // Enabling absolute positioning

        this.width = width;
        this.player = player;
        this.playerName = playerName;
        this.godName = godName;

        this.playerActive = false;

        loadScaleIcon(width);
        actionWindowIcon = ViewHelper.loadImageIcon(ACTION_WINDOW, playerTagIcon.getIconWidth(), (int) (playerTagIcon.getIconHeight() * 2.5));
        buttonIcon = ViewHelper.loadImageIcon("Worker/"+ player.getColor() + "/btn.png", (int) (playerTagIcon.getIconWidth()*0.6), (int) (playerTagIcon.getIconHeight() * 0.7));
        pressedButtonIcon = ViewHelper.loadImageIcon("Worker/"+ player.getColor() + "/btn_pressed.png", (int) (playerTagIcon.getIconWidth()*0.6), (int) (playerTagIcon.getIconHeight() * 0.7));

        // Revalidate and repaint will now use correct dimensions
        revalidate();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        int centerX = getWidth() / 2;

        // Draw action window
        if (actionWindowIcon != null && playerActive) {
            int x = centerX - actionWindowIcon.getIconWidth() / 2;
            int y = getHeight() - actionWindowIcon.getIconHeight() - playerTagIcon.getIconHeight() / 3;
            g2d.drawImage(actionWindowIcon.getImage(), x, y, this);
        }

        // Draw cloud first
        if (cloudIcon != null) {
            int x = centerX - cloudIcon.getIconWidth() / 2;
            int y = getHeight() - cloudIcon.getIconHeight() - playerTagIcon.getIconHeight();
            if (playerActive) {
                y -= actionWindowIcon.getIconHeight();
            }
            g2d.drawImage(cloudIcon.getImage(), x, y, this);
        }

        // Draw rectangle
        if (playerTagIcon != null && playerActive) {
            int x = centerX - playerTagIcon.getIconWidth() / 2;
            int y = getHeight() - playerTagIcon.getIconHeight() - actionWindowIcon.getIconHeight()-10;
            g2d.drawImage(playerTagIcon.getImage(), x, y, this);
        }

        // Draw character on top
        if (godIcon != null) {
            int x = centerX - godIcon.getIconWidth() / 2;
            int y = getHeight() - godIcon.getIconHeight() -  playerTagIcon.getIconHeight();
            if (playerActive) {
                y -= actionWindowIcon.getIconHeight();
            }
            g2d.drawImage(godIcon.getImage(), x, y, this);
        }

        g2d.dispose();
    }

    public void setPlayerActive(boolean updatedPlayerActive, String actionMessage) {

        if (actionTextArea == null && actionButton == null) {
            if (updatedPlayerActive) {
                loadScaleIcon(width);
                addActionMessage(actionMessage);
                addOptionalButton("");
            } else {
                loadScaleIcon(width / 2);
            }
        }

        if (playerActive != updatedPlayerActive) {
            actionTextArea.setVisible(false);
            actionButton.setVisible(false);
//            removeAll();
            playerActive = updatedPlayerActive;

            if (playerActive) {
                loadScaleIcon(width);
            } else {
                loadScaleIcon(width / 2);
            }
        }

        if (playerActive) {
            actionTextArea.setVisible(false);
            actionButton.setVisible(false);
//            removeAll();
            setActionMessage(actionMessage);
        }

        // Force layout updates after loading icons
        revalidate();
        repaint();
    }

    public void setActionMessage(String actionMessage){
        actionTextArea.setText(actionMessage);
//        add(actionTextArea);
        actionTextArea.setVisible(true);
    }


    public void setOptionalButton(String buttonText, ActionListener actionListener){
        actionButton.setText(buttonText);
        for (ActionListener oldActionListener: actionButton.getActionListeners()){
            actionButton.removeActionListener(oldActionListener);
        }
        actionButton.addActionListener(actionListener);
        actionButton.setVisible(true);
        revalidate();
        repaint();
    }

    public void setViewCardDetail(boolean viewCard){

    }

    public void setGodActive(boolean godActive){

    }

    // ==== getter ====

    public JButton getActionButton() {
        return actionButton;
    }

    // ==== private method to set up the view class

    private void addActionMessage(String actionMessage){

        actionTextArea = new JTextArea(actionMessage);
        actionTextArea.setLineWrap(true);
        actionTextArea.setWrapStyleWord(true);
        actionTextArea.setEditable(false);
        actionTextArea.setOpaque(false); // Optional: transparent background
        actionTextArea.setFont(ViewHelper.loadFont("Tyche.ttf", (int) (buttonIcon.getIconHeight() * 0.5), Font.BOLD));
        actionTextArea.setFocusable(false);
        actionTextArea.setBorder(null);

        add(actionTextArea);
        // Defer bounds setting using invokeLater

        SwingUtilities.invokeLater(() -> {
            int maxWidth = buttonIcon.getIconWidth(); // Leave some padding
            int centerX = getWidth() / 2;
            int x = centerX - maxWidth / 2;

            // Set preferred width temporarily for wrapping
            actionTextArea.setSize(new Dimension(maxWidth, Short.MAX_VALUE));
            int preferredHeight = buttonIcon.getIconHeight();

            // Now place it on the action window
            int y = getHeight() - actionWindowIcon.getIconHeight();

            actionTextArea.setBounds(x, y, maxWidth, preferredHeight);
            actionTextArea.revalidate();
            actionTextArea.repaint();
        });
    }

    private void addOptionalButton(String buttonText){

        this.actionButton = new JButton(buttonIcon);

        actionButton.setHorizontalTextPosition(SwingConstants.CENTER);
        actionButton.setVerticalTextPosition(SwingConstants.CENTER);
        actionButton.setForeground(Color.WHITE);
        actionButton.setFont(ViewHelper.loadFont("Zeus-Land.ttf", (int) (buttonIcon.getIconHeight() * 0.4), Font.BOLD));
        actionButton.setContentAreaFilled(false);   // Don't fill background
        actionButton.setBorderPainted(false);       // Remove border
        actionButton.setFocusPainted(false);        // Remove focus outline
        actionButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        actionButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        actionButton.setPressedIcon(pressedButtonIcon);
        actionButton.setText(buttonText);

        add(actionButton);
        SwingUtilities.invokeLater(() -> {
            int centerX = getWidth() / 2;
            int x = centerX - buttonIcon.getIconWidth() / 2;
            int y = getHeight() - buttonIcon.getIconHeight() * 2;

            actionButton.setBounds(x, y, buttonIcon.getIconWidth(), buttonIcon.getIconHeight());
            actionButton.revalidate();
            actionButton.repaint();
        });

        revalidate();
        repaint();
    }

    private void loadScaleIcon(int width){
        playerTagIcon = ViewHelper.loadImageIcon("Worker/"+ player.getColor() + "/tag.png", (int) (width * 0.2), -1);
        cloudIcon = ViewHelper.loadImageIcon(CLOUD_IMAGE, (int) (width * 0.19), -1);
        godIcon = ViewHelper.loadImageIcon("GodCard/" + godName + "/podium.png", playerTagIcon.getIconWidth(), -1 );

    }

    // === Background method to set the size of panel to fit image ====
    @Override
    public Dimension getPreferredSize() {
        int totalHeight = 0;
        int maxWidth = 0;

        if (godIcon != null) {
            totalHeight += playerActive ? godIcon.getIconHeight() / 1.8 : godIcon.getIconHeight() ;
            maxWidth = Math.max(maxWidth, godIcon.getIconWidth());
        }

        if (playerTagIcon != null && playerActive) {
            totalHeight += playerTagIcon.getIconHeight();
            maxWidth = Math.max(maxWidth, playerTagIcon.getIconWidth());
        }

        if (actionWindowIcon != null && playerActive) {
            totalHeight += actionWindowIcon.getIconHeight();
            maxWidth = Math.max(maxWidth, actionWindowIcon.getIconWidth());
        }

        return new Dimension(maxWidth, totalHeight + 10); // extra margin for spacing
    }

    @Override
    public Dimension getMaximumSize() {
        return getPreferredSize(); // important for BoxLayout
    }

    @Override
    public Dimension getMinimumSize() {
        return getPreferredSize(); // just in case
    }
}

