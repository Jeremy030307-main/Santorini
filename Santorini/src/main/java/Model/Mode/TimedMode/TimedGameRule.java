package Model.Mode.TimedMode;

import Model.Game.GameState;
import Model.GameRule.ClassicGameRule;
import Model.Player.Player;

public class TimedGameRule extends ClassicGameRule {

    TimerManager timerManager;

    public TimedGameRule(TimerManager timerManager) {
        this.timerManager = timerManager;
    }

    @Override
    public boolean checkLose(GameState gameState) {
        boolean oriCheckLose = super.checkLose(gameState);

        for (Player player : gameState.getPlayers()) {
            if (!timerManager.stillAlive(player)) {
                player.setLose(true);
            }
        }

        return oriCheckLose;
    }
}
