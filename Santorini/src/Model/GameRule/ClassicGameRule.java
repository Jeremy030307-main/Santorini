package Model.GameRule;

import Model.Action.Action;
import Model.Board.Board;
import Model.Board.Cell;
import Model.Game.Game;
import Model.Game.GameState;
import Model.Player.Player;
import Model.Player.Worker;

import java.util.List;

public class ClassicGameRule {

    public ClassicGameRule() {
    }

    public Game newGame(Player[] players){

        return new Game(new Board(), players, this);
    }

    public boolean canMove(Worker worker, Cell targetCell) {

        Cell currentCell = worker.getLocatedCell();

        return currentCell.isAdjacentTo(targetCell) &&
                !targetCell.isOccupied() &&
                !targetCell.isComplete() &&
                currentCell.getPosition().z() - targetCell.getPosition().z() > -1;
    }

    public boolean canBuild(Worker worker, Cell targetCell) {

        return worker.getLocatedCell().isAdjacentTo(targetCell) &&
                !targetCell.isOccupied() &&
                !targetCell.isComplete();
    }

    public List<Action> getLegalActions(Player player, GameState gameState){
        // TODO: implement generateAction, get raw possible action form player's workers, and apply god power modification
        return null;
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
