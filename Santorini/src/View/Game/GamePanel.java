package View.Game;

import View.Game.MapComponent.JBoard;
import View.Game.MapComponent.JPlayer;
import View.SantoriniPanel;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;
import java.util.List;

public class GamePanel extends SantoriniPanel {

    private static final String imgPath = "map.png";
    private final JBoard gameBoard;
    private JPanel right;
    private JPanel left;
    private JLayeredPane layeredPane;

    private final List<JPlayer> players;

    public GamePanel(List<JPlayer> players) {
        super(imgPath);

        gameBoard = new JBoard();
        this.players = players;
        setLayout(new BorderLayout());

        // Main layered pane
        layeredPane = new JLayeredPane();
        add(layeredPane, BorderLayout.CENTER);

        int width = getMaxWidth();
        int height = getMaxHeight();
        layeredPane.setPreferredSize(new Dimension(width, height));

        createGameBoard(width, height);
        createLeftPanel();
        createRightPanel();
    }

    private void createGameBoard(int width, int height) {
        int boardSize = (int) (getMaxWidth() * 0.42);
        gameBoard.setBounds((int) ((width - boardSize) / 1.99), (int) ((height - boardSize) / 2.2), boardSize, boardSize);
        gameBoard.setOpaque(false);
        gameBoard.setBorder(BorderFactory.createLineBorder(Color.RED)); // debug border

        layeredPane.add(gameBoard, JLayeredPane.DEFAULT_LAYER);
    }

    private void createLeftPanel() {
        int panelWidth = (int) (getMaxWidth() * 0.25);
        int panelHeight = layeredPane.getPreferredSize().height;

        left = new JPanel(new BorderLayout());
        left.setOpaque(false);
        left.setBounds(0, 0, panelWidth, panelHeight);

        // Load and scale the image
        ImageIcon originalIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("../../" + players.getFirst().getPath())));
        Image scaledImage = originalIcon.getImage().getScaledInstance((int) (getMaxWidth() * 0.2), (int)(getMaxHeight() * 0.1), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel imageLabel = new JLabel(scaledIcon);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Wrap in a panel to add padding at the bottom
        JPanel paddedPanel = new JPanel(new BorderLayout());
        paddedPanel.setOpaque(false);
        paddedPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0)); // 20px bottom padding
        paddedPanel.add(imageLabel, BorderLayout.CENTER);

        left.add(paddedPanel, BorderLayout.SOUTH);
        layeredPane.add(left, JLayeredPane.PALETTE_LAYER);
    }

    private void createRightPanel() {
        int panelWidth = (int) (getMaxWidth() * 0.25);
        int panelHeight = layeredPane.getPreferredSize().height;

        right = new JPanel(new BorderLayout());
        right.setOpaque(false);
        right.setBounds(layeredPane.getPreferredSize().width - panelWidth, 0, panelWidth, panelHeight);

        // Load and scale image
        ImageIcon originalIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("../../" + players.getLast().getPath())));
        Image scaledImage = originalIcon.getImage().getScaledInstance((int) (getMaxWidth() * 0.2), (int)(getMaxHeight() * 0.1), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel imageLabel = new JLabel(scaledIcon);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Padding panel to offset from bottom
        JPanel paddedPanel = new JPanel(new BorderLayout());
        paddedPanel.setOpaque(false);
        paddedPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0)); // 20px bottom padding
        paddedPanel.add(imageLabel, BorderLayout.CENTER);

        right.add(paddedPanel, BorderLayout.SOUTH);
        layeredPane.add(right, JLayeredPane.PALETTE_LAYER);
    }

    public JBoard getGameBoard() {
        return gameBoard;
    }

    public JPanel getRight() {
        return right;
    }

    public JPanel getLeft() {
        return left;
    }

    public JLayeredPane getLayeredPane() {
        return layeredPane;
    }
}


