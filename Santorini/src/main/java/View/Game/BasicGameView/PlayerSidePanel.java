package View.Game.BasicGameView;

import View.Game.GenericGameView.GenericPlayerSidePanel;
import View.Game.MapComponent.JPlayer;

import java.util.ArrayList;
import java.util.List;

public class PlayerSidePanel extends GenericPlayerSidePanel<ActivePlayerPanel> {


    public PlayerSidePanel(List<JPlayer> players, List<String> playerNames, List<String> godNames, int width) {
        super(players, playerNames, godNames, width);
    }

    @Override
    public List<ActivePlayerPanel> setPlayerPanels(List<JPlayer> players, List<String> playerNames, List<String> godNames, int width) {
        List<ActivePlayerPanel> playerPanels = new ArrayList<>();
        for (int i = 0; i < players.size(); i++) {
            ActivePlayerPanel playerPanel = new ActivePlayerPanel(players.get(i), playerNames.get(i), godNames.get(i), width);
            playerPanels.add(playerPanel);
        };

        return playerPanels;
    }
}