package Model.Action;

import Model.Game.GameState;
import Model.Game.TurnPhase;
import Model.Player.Worker;

public class DoNothingAction extends Action{

    public DoNothingAction(Worker targetWorker, TurnPhase nextPhase) {
        super("build", TurnPhase.OPTIONAL_ACTION, nextPhase, targetWorker, null);
    }

    @Override
    public void execute(GameState gameState) {
        return;
    }
}
