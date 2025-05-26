package View.Setup;

import View.ViewHelper;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ChooseGodPanel extends SetupCard {

    private final List<String> gods;
    private final List<MiniGodCardButton> godCardButtons;

    private List<PlayerPanelResult> playerPanelResults;
    private JButton confirmButton;
    private JButton cancelButton;

    public ChooseGodPanel(List<String> allGodsName){
        gods = allGodsName;
        godCardButtons = new ArrayList<>();
    }

    public void setup(){
        setLayout(new BorderLayout());

        // === Top Panel ===
        // Holds left, center, and right panels
        JPanel topPanel = new JPanel(new BorderLayout());

        // === Left Column (Player 1 God Info) ===
        PlayerPanelResult player1PanelResult = generatePlayerPanel(
                "PLAYER 1",
                "blue"
        );

        // === Right Column (Player 2 God Info) ===
        PlayerPanelResult player2PanelResult = generatePlayerPanel(
                "PLAYER 2",
                "red"
        );

        playerPanelResults = new ArrayList<>();
        playerPanelResults.add(player1PanelResult);
        playerPanelResults.add(player2PanelResult);

        // === Center Grid for God Selection ===

        ImageIcon bgIcon = ViewHelper.loadImageIcon("Label/lobby.png", maxWidth, maxHeight);
        Image bgImage = bgIcon != null ? bgIcon.getImage() : null;

        // God Selection Grid
        JPanel godSelectionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10)); // 3 columns, dynamic rows
        // God card buttons (replace with actual image or custom panels)
        for (String godName: gods) {
            // Create the button with the combined icon
            MiniGodCardButton godButton = new MiniGodCardButton("GodCard/" + godName + "/image.jpg", maxWidth);
            godSelectionPanel.add(godButton);
            godCardButtons.add(godButton);
        }

        JScrollPane scrollPane = new JScrollPane(godSelectionPanel);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(null); // Optional: remove border for cleaner look
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        JLabel chooseGodText = new JLabel("Choose a God Card", SwingConstants.CENTER);
        chooseGodText.setForeground(new Color(0x715535));
        chooseGodText.setFont(ViewHelper.loadFont("Zeus-Land.ttf", (int) (maxHeight*0.05), Font.BOLD));
        chooseGodText.setHorizontalAlignment(SwingConstants.CENTER);
        chooseGodText.setOpaque(false);
        JViewport header = new JViewport();
        header.setOpaque(false);
        header.setView(chooseGodText);
        scrollPane.setColumnHeader(header);

        // Wrap centerPanel in a padded container
        JPanel centerWrapper = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (bgImage != null) {
                    g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        centerWrapper.setBorder(BorderFactory.createEmptyBorder(50, 40, 50, 40)); // top, left, bottom, right
        centerWrapper.add(scrollPane, BorderLayout.CENTER);  // Fills the center space
        centerWrapper.setOpaque(false);

        topPanel.add(playerPanelResults.getFirst().panel, BorderLayout.WEST);
        topPanel.add(centerWrapper, BorderLayout.CENTER);
        topPanel.add(playerPanelResults.getLast().panel, BorderLayout.EAST);
        add(topPanel, BorderLayout.CENTER); // main content goes here

        // === Bottom Panel ===
        ImageIcon bottomIcon = ViewHelper.loadImageIcon("Label/bottom_message.png", maxWidth, maxHeight);
        Image bottomImage = bottomIcon != null ? bottomIcon.getImage() : null;

        // Bottom panel (e.g., buttons or status)
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (bottomImage != null) {
                    g.drawImage(bottomImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        bottomPanel.setPreferredSize(new Dimension((int) (maxWidth), (int) (maxHeight*0.1))); // Fixed width

        bottomPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));

        // confirm button
        confirmButton = new JButton("Start Game");
        ImageIcon greenButtonIcon = ViewHelper.loadImageIcon("Button/btn_green.png", (int) (maxWidth * 0.25), (int) (maxHeight * 0.08));
        ImageIcon greenButtonPressedIcon = ViewHelper.loadImageIcon("Button/btn_green_pressed.png",(int) (maxWidth * 0.25), (int) (maxHeight * 0.08) );

        if (greenButtonIcon != null) {
            confirmButton.setIcon(greenButtonIcon);
            confirmButton.setHorizontalTextPosition(SwingConstants.CENTER);
            confirmButton.setVerticalTextPosition(SwingConstants.CENTER);
            confirmButton.setForeground(Color.WHITE);
            confirmButton.setFont(ViewHelper.loadFont("Zeus-Land.ttf", (int)(maxHeight * 0.03), Font.BOLD));
            confirmButton.setContentAreaFilled(false);   // Don't fill background
            confirmButton.setBorderPainted(false);       // Remove border
            confirmButton.setFocusPainted(false);        // Remove focus outline
            confirmButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            confirmButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            confirmButton.setPressedIcon(greenButtonPressedIcon);
            bottomPanel.add(confirmButton);
        }

        add(bottomPanel, BorderLayout.SOUTH); // Add bottom panel

        confirmButton.setEnabled(false);
        for (PlayerPanelResult playerPanelResult : playerPanelResults){
            playerPanelResult.button.setVisible(false);playerPanelResult.button.setEnabled(false);
        }

        topPanel.setOpaque(false);
        for (PlayerPanelResult playerPanelResult : playerPanelResults){
            playerPanelResult.panel.setOpaque(false);
        }
        godSelectionPanel.setOpaque(false);
        bottomPanel.setOpaque(false);
        setOpaque(false);

    }

    private PlayerPanelResult generatePlayerPanel(String playerName, String playerLabelColor) {

        JPanel playerPanel = new JPanel();
        JButton imageButton = new JButton();
        JLabel godCardLabel = new JLabel();

        playerPanel.setPreferredSize(new Dimension((int) (maxWidth * 0.3), 0)); // Fixed width
        playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.Y_AXIS));

        playerPanel.add(Box.createVerticalStrut(40));

        // === Load and add player banner image ===
        ImageIcon playerBanner = ViewHelper.loadImageIcon("Label/title_"+ playerLabelColor + ".png", (int) (maxWidth * 0.25), -1);
        if (playerBanner != null) {
            // Create a layered pane with preferred size
            JLayeredPane layeredPane = new JLayeredPane();
            int iconWidth = playerBanner.getIconWidth();
            int iconHeight = playerBanner.getIconHeight();
            layeredPane.setPreferredSize(new Dimension(iconWidth, iconHeight));

            // Base image label
            JLabel topImageLabel = new JLabel(playerBanner);
            topImageLabel.setBounds(0, 0, iconWidth, iconHeight);

            // Overlay text label
            JLabel overlayText = new JLabel(playerName, SwingConstants.CENTER); // or "PLAYER 2"
            overlayText.setBounds(0, 10, iconWidth, iconHeight); // Same size as icon
            overlayText.setForeground(Color.WHITE);
            overlayText.setFont(ViewHelper.loadFont("Zeus-Land.ttf", (int) (maxHeight*0.04), Font.BOLD));
            overlayText.setHorizontalAlignment(SwingConstants.CENTER);
            overlayText.setVerticalAlignment(SwingConstants.CENTER);

            layeredPane.add(topImageLabel, JLayeredPane.DEFAULT_LAYER);
            layeredPane.add(overlayText, JLayeredPane.PALETTE_LAYER);

            // Wrap in a panel for alignment in BoxLayout
            JPanel wrapper = new JPanel();
            wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.X_AXIS));
            wrapper.setMaximumSize(new Dimension(iconWidth, iconHeight));
            wrapper.add(Box.createHorizontalGlue());
            wrapper.add(layeredPane);
            wrapper.add(Box.createHorizontalGlue());
            wrapper.setOpaque(false);

            playerPanel.add(wrapper);
        }

        playerPanel.add(Box.createVerticalStrut(20));

        ImageIcon icon1 = ViewHelper.loadImageIcon("GodCard/unknown.png", (int) (maxWidth * 0.15), -1);
        if (icon1 != null) {
            ImageIcon roundedIcon = ViewHelper.makeRoundedImage(icon1.getImage(), 30, 30);
            godCardLabel.setIcon(roundedIcon);
            godCardLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Horizontally center in BoxLayout
            playerPanel.add(Box.createVerticalStrut(10)); // Optional spacing
            playerPanel.add(godCardLabel);
        }

        playerPanel.add(Box.createVerticalStrut(20));

        // === Add description text box ===
        JTextArea description = new JTextArea("");
        description.setLineWrap(true);
        description.setWrapStyleWord(true);
        description.setEditable(false);
        description.setFocusable(false);
        description.setBackground(playerPanel.getBackground());
        description.setFont(ViewHelper.loadFont("Tyche.ttf", (int) (maxHeight*0.025), Font.PLAIN));
        description.setAlignmentX(Component.CENTER_ALIGNMENT);
        description.setMaximumSize(new Dimension((int) (maxWidth * 0.25), (int) (maxHeight * 0.4))); // restrict width
        description.setOpaque(false);

        playerPanel.add(Box.createVerticalStrut(10));
        playerPanel.add(description);

        // ==== Add button for each player panel
        ImageIcon buttonIcon = ViewHelper.loadImageIcon("Button/btn_"+ playerLabelColor + ".png", (int) (maxWidth * 0.25), (int) ((playerBanner != null ? playerBanner.getIconHeight() : 0) / 1.5));
        assert playerBanner != null;
        ImageIcon buttonPressedIcon = ViewHelper.loadImageIcon("Button/btn_"+ playerLabelColor + "_pressed.png", (int) (maxWidth * 0.25), (int) (playerBanner.getIconHeight() / 1.5));

        if (buttonIcon != null) {
            imageButton.setText("Confirm");
            imageButton.setIcon(buttonIcon);
            imageButton.setHorizontalTextPosition(SwingConstants.CENTER);
            imageButton.setVerticalTextPosition(SwingConstants.CENTER);
            imageButton.setForeground(Color.WHITE);
            imageButton.setFont(ViewHelper.loadFont("Zeus-Land.ttf", (int)(maxHeight * 0.03), Font.BOLD));
            imageButton.setContentAreaFilled(false);   // Don't fill background
            imageButton.setBorderPainted(false);       // Remove border
            imageButton.setFocusPainted(false);        // Remove focus outline
            imageButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            imageButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

            imageButton.setPressedIcon(buttonPressedIcon);

            playerPanel.add(Box.createVerticalStrut(20));
            playerPanel.add(imageButton);

        }

        return new PlayerPanelResult(playerPanel, imageButton, godCardLabel, description);
    }

    public void setPlayerPanel(int playerIndex, String godName, String godCardDescription){

        PlayerPanelResult playerPanel = playerPanelResults.get(playerIndex);

        ImageIcon icon = ViewHelper.loadImageIcon("GodCard/" + godName + "/card.png", (int) (maxWidth * 0.15), -1);
        ImageIcon roundedIcon = ViewHelper.makeRoundedImage(Objects.requireNonNull(icon).getImage(), 30, 30);
        playerPanel.godCardLabel.setIcon(roundedIcon);

        playerPanel.godCardDesc.setText(godCardDescription);
    }

    // ==== Getter ====
    public List<JButton> getPlayerButtons() {
        List<JButton> buttons = new ArrayList<>();
        for (PlayerPanelResult playerPanelResult : playerPanelResults) {
            buttons.add(playerPanelResult.button);
        }
        return buttons;
    }

    public List<MiniGodCardButton> getGodCardButtons() {
        return godCardButtons;
    }

    public JButton getConfirmButton() {
        return confirmButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    // ==== Setter ====

    private static class PlayerPanelResult {

        JPanel panel;
        JButton button;
        JLabel godCardLabel;
        JTextArea godCardDesc;

        PlayerPanelResult(JPanel panel, JButton button, JLabel godCardLabel, JTextArea godCardDesc) {

            this.panel = panel;
            this.button = button;
            this.godCardLabel = godCardLabel;
            this.godCardDesc = godCardDesc;
        }
    }
}

