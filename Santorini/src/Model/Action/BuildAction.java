package Model.Action;

import Model.Board.Block;
import Model.Board.BlockType;
import Model.Board.Cell;
import Model.Game.BlockPool;
import Model.Game.GameState;
import Model.Game.TurnPhase;
import Model.Player.Worker;

public class BuildAction extends Action{

    public BuildAction(Worker targetWorker, Cell targetCell) {
        super("build", TurnPhase.BUILD, TurnPhase.END_TURN, targetWorker, targetCell);
    }

    @Override
    public void execute(GameState gameState) {

        // Compute the block level needed for this build
        BlockType blockLevel = BlockType.fromLevel(targetCell.getPosition().z() + 1);

        // Take the required block from the pool
        Block block = gameState.getBlockPool().takeBlock(blockLevel);

        // build the required block on the target cell
        targetCell.buildBlock(block);
    }

    @Override
    public String toString() {
        return "Worker " + targetWorker.getId() + " (" + targetWorker.getOwner().getName() + ") build on" + targetCell.getPosition();
    }
}
