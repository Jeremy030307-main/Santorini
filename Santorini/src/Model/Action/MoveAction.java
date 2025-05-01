package Model.Action;

import Model.Board.Cell;
import Model.Game.GameState;
import Model.Game.TurnPhase;
import Model.Player.Worker;

/**
 * The {@code MoveAction} class represents a move operation in the game,
 * where a worker moves from its current cell to a specified adjacent target cell.
 * <p>
 * Executing this action updates the game board to reflect the worker's new position,
 * clears the previous cell, and transitions the game to the BUILD phase.
 * </p>
 */
public class MoveAction extends Action {

    /** The cell from which the worker is moving. */
    private final Cell sourceCell;

    /**
     * Constructs a {@code MoveAction} for a given worker and target cell.
     * Initializes the source cell based on the worker's current location.
     *
     * @param targetWorker The worker that is moving
     * @param targetCell The cell the worker is moving to
     */
    public MoveAction(Worker targetWorker, Cell targetCell) {
        super("move", TurnPhase.MOVE, TurnPhase.BUILD, targetWorker, targetCell);
        this.sourceCell = targetWorker.getLocatedCell();
    }

    /**
     * Executes the move action by:
     * <ol>
     *     <li>Clearing the worker from its current cell</li>
     *     <li>Setting the target cell as the worker's new location</li>
     *     <li>Updating the worker's internal state</li>
     * </ol>
     *
     * @param gameState The current game state, used to update the board
     */
    @Override
    public void execute(GameState gameState) {
        // Remove worker from the original cell
        sourceCell.clearOccupant();

        // Set worker as occupant of the new cell
        targetCell.setOccupant(targetWorker);

        // Update worker's internal location
        targetWorker.setLocatedCell(targetCell);
    }

    /**
     * Returns a string representation of the move action,
     * indicating which worker moved and to which cell.
     *
     * @return A descriptive string of the move
     */
    @Override
    public String toString() {
        return "Worker " + targetWorker.getId() + " (" + targetWorker.getOwner().getName() + ") moved to " + targetCell.getPosition();
    }
}
