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

    public boolean isWin(GameState gameState) {
        for (Player player : gameState.getPlayers()) {
            for (Worker worker : player.getWorkers()) {
                if (worker.getLocatedCell().getPosition().z() == 3) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isLose(GameState gameState) {
        Player currentPlayer = gameState.getTurnManager().getCurrentPlayer();
        return getLegalActions(currentPlayer, gameState).isEmpty();
    }
}
