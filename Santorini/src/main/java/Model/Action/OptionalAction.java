package Model.Action;

import Model.Game.GameState;
import Model.Game.TurnPhase;
import Model.Player.Worker;

public class OptionalAction extends Action{

    private final String actionName;

    public OptionalAction(String actionName, String description,Worker targetWorker, TurnPhase nextPhase) {
        super("build", TurnPhase.OPTIONAL_ACTION, nextPhase, targetWorker, null);
        this.actionName = actionName;
    }


    @Override
    public void execute(GameState gameState) {
        return;
    }

    public String getActionName() {
        return actionName;
    }
}
