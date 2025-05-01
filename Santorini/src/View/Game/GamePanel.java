package View.Game;

import View.Game.MapComponent.JBoard;
import View.Game.MapComponent.JPlayer;
import View.SantoriniPanel;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;
import java.util.List;


/**
 * The {@code GamePanel} class represents the main panel for rendering the game board and the player information in the Santorini game.
 * This class extends {@link SantoriniPanel} and handles the layout and initialization of the game board, player panels,
 * and interactions between the user interface components.
 * <p>
 * It contains methods for creating and managing the game board and the panels representing the players' information.
 * </p>
 */
public class GamePanel extends SantoriniPanel {

    private static final String imgPath = "map.png";
    private final JBoard gameBoard;
    private JPanel right;
    private JPanel left;
    private JLayeredPane layeredPane;

    private final List<JPlayer> players;

    /**
     * Constructs a new {@code GamePanel} with the specified list of players.
     * This constructor initializes the game board, the left and right player panels, and the layered pane.
     * The game board and player images are loaded and positioned accordingly.
     *
     * @param players The list of players in the game
     */
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

    /**
     * Creates and configures the game board. The board is rendered within a specified width and height,
     * and it is added to the layered pane.
     *
     * @param width The width of the game panel
     * @param height The height of the game panel
     */
    private void createGameBoard(int width, int height) {
        int boardSize = (int) (getMaxWidth() * 0.42);
        gameBoard.setBounds((int) ((width - boardSize) / 1.99), (int) ((height - boardSize) / 2.2), boardSize, boardSize);
        gameBoard.setOpaque(false);
        gameBoard.setBorder(BorderFactory.createLineBorder(Color.RED)); // debug border

        layeredPane.add(gameBoard, JLayeredPane.DEFAULT_LAYER);
    }

    /**
     * Creates the left panel for displaying the first player's information.
     * The panel includes an image for the player and is positioned on the left side of the game panel.
     * The panel is added to the layered pane.
     */
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

    /**
     * Creates the right panel for displaying the second player's information.
     * The panel includes an image for the player and is positioned on the right side of the game panel.
     * The panel is added to the layered pane.
     */
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

    /**
     * Retrieves the game board component.
     *
     * @return The {@link JBoard} object representing the game board
     */
    public JBoard getGameBoard() {
        return gameBoard;
    }

    /**
     * Retrieves the right player panel.
     *
     * @return The {@link JPanel} object representing the right player panel
     */
    public JPanel getRight() {
        return right;
    }

    /**
     * Retrieves the left player panel.
     *
     * @return The {@link JPanel} object representing the left player panel
     */
    public JPanel getLeft() {
        return left;
    }

    /**
     * Retrieves the layered pane that contains the game board and player panels.
     *
     * @return The {@link JLayeredPane} object used for rendering the game components
     */
    public JLayeredPane getLayeredPane() {
        return layeredPane;
    }
}


