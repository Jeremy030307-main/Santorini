package Model.GodCard;

import Model.Action.Action;
import Model.Action.DoNothingAction;
import Model.Board.Cell;
import Model.Game.GameState;
import Model.Game.TurnPhase;
import Model.Player.Player;
import Model.Player.Worker;

import java.util.Iterator;
import java.util.List;

public class Artemis extends GodCard {

    private Cell originalPosition; // Store initial position before first move

    public Artemis() {
        super(GodCardFactory.ARTEMIS, "Your Worker may move one additional time, but not back to its initial space.", null);
    }

    @Override
    public List<Action> beforeMove(List<Action> moveActions) {

        for (Action action : moveActions) {
            action.setNextPhase(TurnPhase.OPTIONAL_ACTION);
            originalPosition = action.getTargetWorker().getLocatedCell();
        }

        return moveActions;
    }

    @Override
    public List<Action> getOptionalActions(GameState gameState, Worker currentWorker) {

        List<Action> actions = new java.util.ArrayList<>(gameState.getGameRule().moveActions(gameState.getBoard(), currentWorker)
                .stream().filter(action -> !action.getTargetCell().getPosition().equals(originalPosition.getPosition())).toList());

        DoNothingAction doNothingAction = new DoNothingAction(gameState.getTurnManager().getCurrentWorker(), TurnPhase.BUILD);

        actions.add(doNothingAction);

        return actions;
    }
}
