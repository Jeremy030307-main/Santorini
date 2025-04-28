package Model.GameRule;

import Model.Action.Action;
import Model.Action.MoveAction;
import Model.Board.Board;
import Model.Board.Cell;
import Model.Game.Game;
import Model.Game.GameState;
import Model.Player.Player;
import Model.Player.Worker;

import java.util.ArrayList;
import java.util.List;

public class ClassicGameRule {

    public ClassicGameRule() {
    }

    public List<Action> moveActions(Board board, Worker worker){

        List<Cell> movableCells = board.getSurroundingCell(worker.getLocatedCell().getPosition()).stream()
                .filter(targetCell -> canMove(worker, targetCell))
                .toList();

        List<Action> actions = new ArrayList<>();

        for (Cell movableCell : movableCells){
            actions.add(new MoveAction(worker, movableCell));
        }

        return actions;
    }

    public List<Action> buildActions(Board board, Worker worker){
        List<Cell> movableCells = board.getSurroundingCell(worker.getLocatedCell().getPosition()).stream()
                .filter(targetCell -> canBuild(worker, targetCell))
                .toList();

        List<Action> actions = new ArrayList<>();

        for (Cell movableCell : movableCells){
            actions.add(new MoveAction(worker, movableCell));
        }

        return actions;
    }

    private boolean canMove(Worker worker, Cell targetCell) {

        Cell currentCell = worker.getLocatedCell();

        return currentCell.isAdjacentTo(targetCell) &&
                !targetCell.isOccupied() &&
                !targetCell.isComplete() &&
                currentCell.getPosition().z() - targetCell.getPosition().z() > -1;
    }

    private boolean canBuild(Worker worker, Cell targetCell) {

        return worker.getLocatedCell().isAdjacentTo(targetCell) &&
                !targetCell.isOccupied() &&
                !targetCell.isComplete();
    }

    public boolean isWin(GameState gameState){
        //TODO: Implement the win logic for classic game rule
        return false;
    }

    public boolean isLose(GameState gameState){
        // TODO: Implement the lose logic for the classic game rule
        return false;
    }
}
