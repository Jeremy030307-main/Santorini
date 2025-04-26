package Model.Action;

import Model.Board.Cell;
import Model.Game.GameState;
import Model.Game.TurnPhase;
import Model.Player.Worker;

public class MoveAction extends Action {

    private final Worker targetWorker;
    private final Cell targetCell;
    private final Cell sourceCell;

    public MoveAction(Worker targetWorker, Cell targetCell) {
        super("move", TurnPhase.MOVE, TurnPhase.BUILD);
        this.targetWorker = targetWorker;
        this.sourceCell = targetWorker.getLocatedCell();
        this.targetCell = targetCell;
    }

    @Override
    public void execute(GameState gameState) {

        // First: check weather worker can move from currCell to targetCell
        if (!gameState.getGameRule().canMove(targetWorker, targetCell)) {
            throw new IllegalStateException("Invalid move: cannot move from " +
                    sourceCell.getPosition() + " to " + targetCell.getPosition());
        }

        sourceCell.clearOccupant();
        targetCell.setOccupant(targetWorker);
        targetWorker.setLocatedCell(targetCell);
    }
}
