package Model.Mode.TimedMode;

import Model.Game.TurnManager.EventBasedTurnManager;
import Model.Player.Player;

import java.util.HashMap;
import java.util.Map;

public class TimedTurnManager extends EventBasedTurnManager {

    private static final long TOTAL_TIME_MS = 15 * 60 * 1000; // 15 minutes
    private final Map<Player, PlayerClock> clocks = new HashMap<>();

    public TimedTurnManager(Player[] players) {
        super(players);

        for (Player p : players) {
            clocks.put(p, new PlayerClock(TOTAL_TIME_MS, () -> onEvent.run()));
        }
    }

    @Override
    public void onStartTurn() {
        super.onStartTurn();
        clocks.get(getCurrentPlayer()).start();
    }

    @Override
    public void onEndTurn() {
        clocks.get(getCurrentPlayer()).stop();
        super.onEndTurn();
    }

    public boolean stillAlive(Player player) {
        return clocks.get(player).getRemainingTimeMs() > 0;
    }

    public long getRemainingSeconds(Player player){
        return clocks.get(player).getRemainingTimeMs() / 1000;
    }
}

