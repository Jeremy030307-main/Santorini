package View.Game.TimedGameView;

import View.Game.BasicGameView.ActivePlayerPanel;
import View.Game.GenericGameView.GenericGamePanel;
import View.Game.MapComponent.JPlayer;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class TimedGamePanel extends GenericGamePanel<TimedPlayerSidePanel> {
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
    public TimedGamePanel(List<JPlayer> players, List<String> playerNames, List<String> playerGodCardsImgPaths, boolean[][] cellLayout) {
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

        playersPanel = new TimedPlayerSidePanel(players, playerNames, playerGodCardsImgPaths, getMaxWidth());
        playersPanel.setBounds(0, 0, panelWidth, panelHeight);
        layeredPane.add(playersPanel, JLayeredPane.PALETTE_LAYER);
    }

}
