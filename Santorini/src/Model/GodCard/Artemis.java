package Model.GodCard;

import Model.Action.Action;
import Model.Board.Cell;
import Model.Game.TurnPhase;
import java.util.Iterator;
import java.util.List;

/**
 * The {@code Artemis} class represents the Artemis god card in the game.
 * This god card allows a player to move a worker one additional time during their turn,
 * with the restriction that the worker cannot move back to its initial position.
 * <p>
 * The class extends {@link GodCard} and overrides the {@link GodCard#beforeMove(List)} method
 * to implement the additional move feature and to filter out moves that would return the worker to
 * its original position.
 * </p>
 */
public class Artemis extends GodCard {

    private Cell originalPosition; // Store initial position before first move
    private boolean extraMoveUsed;

    /**
     * Constructs a new Artemis god card.
     * The card grants the player the ability to move a worker an additional time,
     * but prevents the worker from moving back to its original position.
     */
    public Artemis() {
        super("Artemis", "Your Worker may move one additional time, but not back to its initial space.");
        this.extraMoveUsed = false;
    }

    /**
     * Filters the list of possible move actions for a worker, granting an additional move
     * if it has not been used already, and preventing the worker from moving back to its original position
     * after the first move.
     *
     * @param moveActions A list of move actions that the player may take
     * @return A filtered list of valid move actions, excluding moves that would return the worker to its original position
     */
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
