package Model.Action;

import Model.Board.Cell;
import Model.Game.GameState;
import Model.Game.TurnPhase;
import Model.Player.Worker;

import java.util.ArrayList;
import java.util.List;

public class SequenceAction extends Action {

    List<Action> actions;

    /**
     * Constructs an action instance with all required parameters.
     *
     * @param currentPhase The phase in which this action is valid
     * @param nextPhase    The phase that follows after this action
     * @param targetWorker The worker that will perform the action
     * @param targetCell   The cell that is the target of the action
     */
    public SequenceAction( TurnPhase currentPhase, TurnPhase nextPhase, Worker targetWorker, Cell targetCell) {
        super("", currentPhase, nextPhase, targetWorker, targetCell);
        actions = new ArrayList<>();
    }

    public SequenceAction(List<Action> actions, String description, TurnPhase currentPhase, TurnPhase nextPhase, Worker targetWorker, Cell targetCell) {
        super(description, currentPhase, nextPhase, targetWorker, targetCell);
        this.actions = actions;
    }

    public void addAction(Action action) {
        actions.add(action);
    }

    @Override
    public void execute(GameState gameState) {
        for (Action action : actions) {
            action.execute(gameState);
        }
    }
}
