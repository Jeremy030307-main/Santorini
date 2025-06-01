package View.Game.GenericGameView;

import View.Game.BasicGameView.BlockPoolSidePanel;
import View.Game.MapComponent.JBlock;
import View.Game.MapComponent.JBoard;
import View.Game.MapComponent.JPlayer;
import View.SantoriniPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public abstract class GenericGamePanel<T extends GenericPlayerSidePanel> extends SantoriniPanel {

    protected static final String imgPath = "map.png";
    protected final JBoard gameBoard;
    protected JButton quitButton;

    protected T playersPanel;
    protected BlockPoolSidePanel blockPoolSidePanel;
    protected final JLayeredPane layeredPane;

    protected final List<JPlayer> players;

    /**
     * Constructs a new {@code GamePanel} with the specified list of players.
     * This constructor initializes the game board, the left and right player panels, and the layered pane.
     * The game board and player images are loaded and positioned accordingly.
     *
     * @param players The list of players in the game
     */
    public GenericGamePanel(List<JPlayer> players, List<String> playerNames, List<String> playerGodCardsImgPaths, boolean[][] cellLayout, HashMap<JBlock, Integer> blockPool) {
        super(imgPath);

        gameBoard = new JBoard(cellLayout);
        this.players = players;
        setLayout(new BorderLayout());

        // Main layered pane
        layeredPane = new JLayeredPane();
        add(layeredPane, BorderLayout.CENTER);

        int width = getMaxWidth();
        int height = getMaxHeight();
        layeredPane.setPreferredSize(new Dimension(width, height));

    }

    /**
     * Creates and configures the game board. The board is rendered within a specified width and height,
     * and it is added to the layered pane.
     *
     * @param width The width of the game panel
     * @param height The height of the game panel
     */
    protected void createGameBoard(int width, int height) {
        int boardSize = (int) (getMaxWidth() * 0.42);
        gameBoard.setBounds((int) ((width - boardSize) / 1.99), (int) ((height - boardSize) / 2.2), boardSize, boardSize);
        gameBoard.setOpaque(false);
        layeredPane.add(gameBoard, JLayeredPane.DEFAULT_LAYER);
    }

    protected abstract void createPlayerPanels(List<String> playerNames, List<String> playerGodCardsImgPaths);

    protected void createBlockPoolPanel(HashMap<JBlock, Integer> blockPool){

        int panelWidth = (int) (getMaxWidth() * 0.20);
        int panelHeight = layeredPane.getPreferredSize().height;

        this.blockPoolSidePanel = new BlockPoolSidePanel(blockPool, panelWidth, panelHeight);

        // Position it on the right side
        int x = layeredPane.getPreferredSize().width - panelWidth;
        blockPoolSidePanel.setBounds(x, 0, panelWidth, panelHeight);

        layeredPane.add(blockPoolSidePanel, JLayeredPane.PALETTE_LAYER);
    }

    protected void createQuitButton() {
        int width = (int)(getMaxWidth() * 0.05);
        int height = (int)(getMaxHeight() * 0.5);

        quitButton = createImageButton("/Image/Button/exit_icon.png", "/Image/Button/exit_icon_pressed.png");

        quitButton.setBounds(10, 10, width, width);
        quitButton.setContentAreaFilled(false);
        quitButton.setBorderPainted(false);
        quitButton.setFocusPainted(false);

        layeredPane.add(quitButton, JLayeredPane.POPUP_LAYER); // ensures it's on top
    }

    public void setActivePlayerID(int id, String label, boolean buttonVisible) {

        playersPanel.updateActivePlayer(id, label);
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

    public void setPlayerOptionalButton(int id, String buttonText, ActionListener actionListener) {
        playersPanel.setOptionalButton(id, buttonText, actionListener);
    }

    private JButton createImageButton(String normalImagePath, String clickedImagePath) {
        // Load normal state image
        ImageIcon normalIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource(normalImagePath)));
        Image normalImage = normalIcon.getImage();

        // Load clicked state image
        ImageIcon clickedIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource(clickedImagePath)));
        Image clickedImage = clickedIcon.getImage();

        // Scale images (optional - adjust as needed)
        int width = (int) (getMaxWidth() * 0.05); // Adjust to your preferred button width
        int normalHeight = (int) (normalImage.getHeight(null) * ((double) width / normalImage.getWidth(null)));
        int clickedHeight = (int) (clickedImage.getHeight(null) * ((double) width / clickedImage.getWidth(null)));

        Image scaledNormal = normalImage.getScaledInstance(width, normalHeight, Image.SCALE_SMOOTH);
        Image scaledClicked = clickedImage.getScaledInstance(width, clickedHeight, Image.SCALE_SMOOTH);

        // Create button with image states
        JButton button = new JButton(new ImageIcon(scaledNormal));

        button.setPressedIcon(new ImageIcon(scaledClicked));

        // Style adjustments
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setOpaque(false);
        button.setMargin(new Insets(0, 0, 0, 0));
        button.setPreferredSize(new Dimension(width, Math.max(normalHeight, clickedHeight)));

        return button;
    }

    public T getPlayersPanel() {
        return playersPanel;
    }

    public BlockPoolSidePanel getBlockPoolSidePanel() {
        return blockPoolSidePanel;
    }
}