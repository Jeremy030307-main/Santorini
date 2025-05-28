package Model.Mode.TimedMode;

import Model.Game.GameState;
import Model.GameRule.ClassicGameRule;
import Model.Player.Player;

public class TimedGameRule extends ClassicGameRule {

    TimedTurnManager timedTurnManager;

    public TimedGameRule(TimedTurnManager timedTurnManager) {
        this.timedTurnManager = timedTurnManager;
    }

    @Override
    public boolean checkLose(GameState gameState) {
        boolean oriCheckLose = super.checkLose(gameState);

        for (Player player : gameState.getPlayers()) {
            if (!timedTurnManager.stillAlive(player)) {
                player.setLose(true);
            }
        }

        return oriCheckLose;
    }
}
