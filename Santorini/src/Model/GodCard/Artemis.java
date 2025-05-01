package Model.GodCard;

import Model.Action.Action;
import Model.Board.Cell;
import Model.Game.TurnPhase;
import java.util.Iterator;
import java.util.List;

public class Artemis extends GodCard {

    private Cell originalPosition; // Store initial position before first move
    private boolean extraMoveUsed;

    public Artemis() {
        super(GodCardFactory.ARTEMIS, "Your Worker may move one additional time, but not back to its initial space.");
        this.extraMoveUsed = false;
    }

    @Override
    public List<Action> beforeMove(List<Action> moveActions) {

        if (!extraMoveUsed) {
            for (Action action : moveActions) {
                action.setNextPhase(TurnPhase.MOVE_OR_BUILD);
                extraMoveUsed = true;
                originalPosition = action.getTargetWorker().getLocatedCell();
            }
        } else {
            // Second move: filter out move back to original cell
            Iterator<Action> iterator = moveActions.iterator();
            while (iterator.hasNext()) {
                Action action = iterator.next();
                Cell targetCell = action.getTargetCell();
                if (targetCell.equals(originalPosition)) {
                    iterator.remove(); // Disallow move back to where the worker came from
                }
            }
        }

        return moveActions;
    }
}
