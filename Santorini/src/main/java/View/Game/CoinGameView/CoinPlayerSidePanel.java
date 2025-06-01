package View.Game.CoinGameView;

import View.Game.GenericGameView.GenericPlayerSidePanel;
import View.Game.MapComponent.JPlayer;

import java.util.ArrayList;
import java.util.List;

public class CoinPlayerSidePanel extends GenericPlayerSidePanel<CoinPlayerPanel> {

    public CoinPlayerSidePanel(List<JPlayer> players, List<String> playerNames, List<String> godNames, int width) {
        super(players, playerNames, godNames, width);
    }

    @Override
    public List<CoinPlayerPanel> setPlayerPanels(List<JPlayer> players, List<String> playerNames, List<String> godNames, int width) {

        List<CoinPlayerPanel> playerPanels = new ArrayList<>();
        for (int i = 0; i < players.size(); i++) {
            CoinPlayerPanel playerPanel = new CoinPlayerPanel(players.get(i), playerNames.get(i), godNames.get(i), width);
            playerPanels.add(playerPanel);
        };
        return playerPanels;
    }
}
