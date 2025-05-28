package View.Game.BasicGameView;

import View.Game.GenericGameView.GenericGamePanel;
import View.Game.MapComponent.JPlayer;
import View.SantoriniPanel;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code GamePanel} class represents the main panel for rendering the game board and the player information in the Santorini game.
 * This class extends {@link SantoriniPanel} and handles the layout and initialization of the game board, player panels,
 * and interactions between the user interface components.
 * <p>
 * It contains methods for creating and managing the game board and the panels representing the players' information.
 * </p>
 */
public class GamePanel extends GenericGamePanel<PlayerSidePanel> {


    /**
     * Constructs a new {@code GamePanel} with the specified list of players.
     * This constructor initializes the game board, the left and right player panels, and the layered pane.
     * The game board and player images are loaded and positioned accordingly.
     *
     * @param players                The list of players in the game
     * @param playerNames
     * @param playerGodCardsImgPaths
     * @param cellLayout
     */
    public GamePanel(List<JPlayer> players, List<String> playerNames, List<String> playerGodCardsImgPaths, boolean[][] cellLayout) {
        super(players, playerNames, playerGodCardsImgPaths, cellLayout);
    }

    @Override
    public void createPlayerPanels(List<String> playerNames, List<String> playerGodCardsImgPaths) {
        int panelWidth = (int) (getMaxWidth() * 0.25);
        int panelHeight = layeredPane.getPreferredSize().height;

        List <ActivePlayerPanel> playerPanels = new ArrayList<>();

        for (int i = 0; i < players.size(); i++) {
            ActivePlayerPanel playerPanel = new ActivePlayerPanel(players.get(i), playerNames.get(i), playerGodCardsImgPaths.get(i), getMaxWidth());
            playerPanels.add(playerPanel);
        }

        playersPanel = new PlayerSidePanel(players, playerNames, playerGodCardsImgPaths, getMaxWidth());
        playersPanel.setBounds(0, 0, panelWidth, panelHeight);
        layeredPane.add(playersPanel, JLayeredPane.PALETTE_LAYER);
    }
}


