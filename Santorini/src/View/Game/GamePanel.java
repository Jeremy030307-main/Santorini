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
    private JButton quitButton;
    private PlayerPanel leftPanel;
    private PlayerPanel rightPanel;
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
        createPlayerPanels();
        createQuitButton();
    }

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

    public void setActivePlayerID(int id, String label) {

        leftPanel.update(label, id == 0);
        rightPanel.update(label, id == 1);

        layeredPane.revalidate();
        layeredPane.repaint();
    }

    public JBoard getGameBoard() {
        return gameBoard;
    }

    public JButton getQuitButton() {
        return quitButton;
    }
}


