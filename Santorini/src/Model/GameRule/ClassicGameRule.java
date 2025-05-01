package Model.GameRule;

import Model.Action.Action;
import Model.Action.BuildAction;
import Model.Action.MoveAction;
import Model.Board.Board;
import Model.Board.Cell;
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
            actions.add(new BuildAction(worker, movableCell));
        }

        return actions;
    }

    public boolean checkWin(GameState gameState) {
        Player currentPlayer = gameState.getTurnManager().getCurrentPlayer();
        Player opponent = gameState.getTurnManager().getOpponent(currentPlayer);
    
        // 1. Check if any of current player's workers are standing on level 3
        for (Worker worker : currentPlayer.getWorkers()) {
            if (worker.getLocatedCell() != null && worker.getLocatedCell().getPosition().z() == 3) {
                return true;  // classic win by reaching level 3
            }
        }
    
        // 2. Check if opponent has any workers placed on the board
        boolean opponentHasWorkers = false;
        for (Worker w : opponent.getWorkers()) {
            if (w.getLocatedCell() != null) {
                opponentHasWorkers = true;
                break; // The opponent has at least one worker placed on the board
            }
        }
        if (!opponentHasWorkers) {
            return true;  // Win condition met: the opponent has no workers placed on the board
        }
    
        return false; // No win condition met
    }

    public boolean checkLose(GameState gameState) {
        Player currentPlayer = gameState.getTurnManager().getCurrentPlayer();
    
        for (Worker worker : currentPlayer.getWorkers()) {
            if (worker.getLocatedCell() != null) {
                // If the worker can move OR build, player is not stuck
                boolean canMove = !moveActions(gameState.getBoard(), worker).isEmpty();
                boolean canBuild = !buildActions(gameState.getBoard(), worker).isEmpty();
                if (canMove || canBuild) {
                    return false; // Player still has options
                }
            }
        }
    
        return true; // All workers are stuck, player loses
    }

    // Private method
    private boolean canMove(Worker worker, Cell targetCell) {

        Cell currentCell = worker.getLocatedCell();


        boolean test =  currentCell.isAdjacentTo(targetCell) &&
                !targetCell.isOccupied() &&
                !targetCell.isComplete() &&
                targetCell.getPosition().z() <= currentCell.getPosition().z() + 1  ;
        return test;
    }

    private boolean canBuild(Worker worker, Cell targetCell) {

        return worker.getLocatedCell().isAdjacentTo(targetCell) &&
                !targetCell.isOccupied() &&
                !targetCell.isComplete();
    }

}