package Model.Action;

import Model.Board.Block;
import Model.Board.BlockType;
import Model.Board.Cell;
import Model.Game.BlockPool;
import Model.Game.TurnPhase;
import Model.Player.Worker;

public class BuildAction extends Action{

    private final Cell targetCell;
    private final Worker targetWorker;
    private final BlockPool blockPool;

    public BuildAction(Worker targetWorker, Cell targetCell, BlockPool blockPool) {
        super("build", TurnPhase.BUILD, TurnPhase.END_TURN);
        this.targetCell = targetCell;
        this.targetWorker = targetWorker;
        this.blockPool = blockPool;
    }

    @Override
    public void execute() {

        // First: check weather worker can build from targetWorker position to targetCell
        if (!targetWorker.getPosition().canMoveTo(targetCell)) {
            throw new IllegalStateException("Invalid move: cannot build from " +
                    targetWorker.getPosition() + " to " + targetCell.getPosition());
        }

        // Compute the block level needed for this build
        BlockType blockLevel = BlockType.fromLevel(targetCell.getPosition().z() + 1);

        // Take the required block from the pool
        Block block = blockPool.takeBlock(blockLevel);

        // build the required block on the target cell
        targetCell.buildBlock(block);
    }
}
