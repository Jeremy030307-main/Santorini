package View.Game.CoinGameView;

import View.Game.BasicGameView.ActivePlayerPanel;
import View.Game.BasicGameView.BlockPoolSidePanel;
import View.Game.GenericGameView.GenericGamePanel;
import View.Game.MapComponent.JBlock;
import View.Game.MapComponent.JPlayer;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CoinGamePanel extends GenericGamePanel<CoinPlayerSidePanel> {

    private final HashMap<JBlock, Integer> blockPrice;

    public CoinGamePanel( HashMap<JBlock, Integer> blockPrice,List<JPlayer> players, List<String> playerNames, List<String> playerGodCardsImgPaths, boolean[][] cellLayout, HashMap<JBlock, Integer> blockPool) {
        super(players, playerNames, playerGodCardsImgPaths, cellLayout, blockPool);
        this.blockPrice = blockPrice;
        createGameBoard(getMaxWidth(), getMaxHeight());
        createBlockPoolPanel(blockPool);
        createPlayerPanels(playerNames, playerGodCardsImgPaths);
        createQuitButton();
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

        playersPanel = new CoinPlayerSidePanel(players, playerNames, playerGodCardsImgPaths, getMaxWidth());
        playersPanel.setBounds(0, 0, panelWidth, panelHeight);
        layeredPane.add(playersPanel, JLayeredPane.PALETTE_LAYER);
    }

    @Override
    public void createBlockPoolPanel(HashMap<JBlock, Integer> blockPool) {
//        super.createBlockPoolPanel(blockPool);
        int panelWidth = (int) (getMaxWidth() * 0.20);
        int panelHeight = layeredPane.getPreferredSize().height;

        this.blockPoolSidePanel = new CoinBlockPoolSidePanel(blockPool,blockPrice, panelWidth, panelHeight);

        // Position it on the right side
        int x = layeredPane.getPreferredSize().width - panelWidth;
        blockPoolSidePanel.setBounds(x, 0, panelWidth, panelHeight);

        layeredPane.add(blockPoolSidePanel, JLayeredPane.PALETTE_LAYER);
    }
}
