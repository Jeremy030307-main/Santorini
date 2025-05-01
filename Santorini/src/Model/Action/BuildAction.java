package Model.Action;

import Model.Board.Block;
import Model.Board.BlockType;
import Model.Board.Cell;
import Model.Game.GameState;
import Model.Game.TurnPhase;
import Model.Player.Worker;

/**
 * The {@code BuildAction} class represents a construction action in the game.
 * When executed, it builds the next appropriate block (Level 1–3 or Dome)
 * on the target cell by the specified worker.
 * <p>
 * The build action retrieves the appropriate block from the game's block pool
 * and places it on the board, modifying the state of the target cell.
 * </p>
 */
public class BuildAction extends Action {

    /**
     * Constructs a {@code BuildAction} instance for a specific worker and target cell.
     *
     * @param targetWorker The worker performing the build
     * @param targetCell The cell where the block is to be built
     */
    public BuildAction(Worker targetWorker, Cell targetCell) {
        super("build", TurnPhase.BUILD, TurnPhase.END_TURN, targetWorker, targetCell);
    }

    /**
     * Executes the build action by:
     * <ol>
     *     <li>Determining the next block type required on the target cell</li>
     *     <li>Retrieving the appropriate block from the block pool</li>
     *     <li>Placing the block on the target cell</li>
     * </ol>
     *
     * @param gameState The current game state, including block pool and board
     */
    @Override
    public void execute(GameState gameState) {
        // Compute the block level needed for this build
        BlockType blockLevel = BlockType.fromLevel(targetCell.getPosition().z() + 1);

        // Take the required block from the pool
        Block block = gameState.getBlockPool().takeBlock(blockLevel);

        // Build the required block on the target cell
        targetCell.buildBlock(block);
    }

    /**
     * Returns a string representation of the action, indicating which worker built and where.
     *
     * @return A user-friendly string describing the build action
     */
    @Override
    public String toString() {
        return "Worker " + targetWorker.getId() + " (" + targetWorker.getOwner().getName() + ") built on " + targetCell.getPosition();
    }
}
