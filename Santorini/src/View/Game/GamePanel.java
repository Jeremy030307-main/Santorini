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
    private JButton quitButton;
    private PlayerPanel leftPanel;
    private PlayerPanel rightPanel;
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
        createPlayerPanels();
        createQuitButton();
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
        layeredPane.add(gameBoard, JLayeredPane.DEFAULT_LAYER);
    }


    private void createPlayerPanels() {
        int panelWidth = (int) (getMaxWidth() * 0.25);
        int panelHeight = layeredPane.getPreferredSize().height;

        leftPanel = new PlayerPanel(players.getFirst(), getMaxWidth(), getMaxHeight());
        leftPanel.setBounds(0, 0, panelWidth, panelHeight);
        layeredPane.add(leftPanel, JLayeredPane.PALETTE_LAYER);

        rightPanel = new PlayerPanel(players.getLast(), getMaxWidth(), getMaxHeight());
        rightPanel.setBounds(getMaxWidth() - panelWidth, 0, panelWidth, panelHeight);
        layeredPane.add(rightPanel, JLayeredPane.PALETTE_LAYER);
    }

    private void createQuitButton() {
        int width = getMaxWidth();
        int height = getMaxHeight();

        ImageIcon quitIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("../../Asset/Image/Button/quit_button.png")));
        Image quickImage = quitIcon.getImage().getScaledInstance((int)(width * 0.1), (int)(height * 0.15), Image.SCALE_SMOOTH);

        quitButton = new JButton(new ImageIcon(quickImage));
        quitButton.setBounds(10, 10, quickImage.getWidth(null), quickImage.getHeight(null));
        quitButton.setContentAreaFilled(false);
        quitButton.setBorderPainted(false);
        quitButton.setFocusPainted(false);

        layeredPane.add(quitButton, JLayeredPane.POPUP_LAYER); // ensures it's on top
    }

    public void setActivePlayerID(int id, String label, boolean buttonVisible) {

        leftPanel.update(label, id == 0, id == 0 && buttonVisible);
        rightPanel.update(label, id == 1, id == 1 && buttonVisible);

        layeredPane.revalidate();
        layeredPane.repaint();
    }

    /**
     * Retrieves the game board component.
     *
     * @return The {@link JBoard} object representing the game board
     */
    public JBoard getGameBoard() {
        return gameBoard;
    }

    public JButton getQuitButton() {
        return quitButton;
    }


    public JButton getEndTurnButon(int id) {
        if (id == 0) {
            return leftPanel.getTopButton();
        } else {
            return rightPanel.getTopButton();
        }
    }

}


