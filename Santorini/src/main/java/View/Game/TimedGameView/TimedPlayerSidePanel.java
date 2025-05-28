package View.Game.TimedGameView;

import View.Game.GenericGameView.GenericPlayerSidePanel;
import View.Game.MapComponent.JPlayer;

import java.util.ArrayList;
import java.util.List;

public class TimedPlayerSidePanel extends GenericPlayerSidePanel<TimedPlayerPanel> {

    public TimedPlayerSidePanel(List<JPlayer> players, List<String> playerNames, List<String> godNames, int width) {
        super(players, playerNames, godNames, width);
    }

    @Override
    public List<TimedPlayerPanel> setPlayerPanels(List<JPlayer> players, List<String> playerNames, List<String> godNames, int width) {

        List<TimedPlayerPanel> playerPanels = new ArrayList<>();
        for (int i = 0; i < players.size(); i++) {
            TimedPlayerPanel playerPanel = new TimedPlayerPanel(players.get(i), playerNames.get(i), godNames.get(i), width);
            playerPanels.add(playerPanel);
        };
        return playerPanels;
    }

    @Override
    public List<TimedPlayerPanel> getPlayerPanels() {
        return super.getPlayerPanels();
    }
}
