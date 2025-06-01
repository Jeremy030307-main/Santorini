package Model.GameRule;

import Model.Action.ActionList;
import Model.Action.BuildAction;
import Model.Action.MoveAction;
import Model.Board.Board;
import Model.Board.Cell;
import Model.Game.GameState;
import Model.Player.Player;
import Model.Player.Worker;

import java.util.List;

/**
 * The {@code ClassicGameRule} class defines the game rules for the classic version of Santorini.
 * It contains methods for determining possible actions for workers (move and build actions),
 * checking win and lose conditions, and ensuring that the game's rules are followed during play.
 * <p>
 * This class encapsulates the logic for the game, including determining if a player has won
 * or lost, as well as the available actions a player can take during their turn.
 * </p>
 */

public class ClassicGameRule {

    public ClassicGameRule() {
    }

    /**
     * Retrieves the possible move actions for a given worker on the board.
     *
     * @param board The game board to check for available move actions
     * @param worker The worker for whom the move actions are being evaluated
     * @return A list of move actions that the worker can perform
     */
    public ActionList moveActions(Board board, Worker worker){

        List<Cell> movableCells = board.getSurroundingCell(worker.getLocatedCell().getPosition()).stream()
                .filter(targetCell -> canMove(worker, targetCell))
                .toList();

        ActionList actions = new ActionList();

        for (Cell movableCell : movableCells){
            actions.add(new MoveAction(worker, movableCell));
        }

        return actions;
    }

    /**
     * Retrieves the possible build actions for a given worker on the board.
     *
     * @param board The game board to check for available build actions
     * @param worker The worker for whom the build actions are being evaluated
     * @return A list of build actions that the worker can perform
     */
    public ActionList buildActions(Board board, Worker worker){
        List<Cell> movableCells = board.getSurroundingCell(worker.getLocatedCell().getPosition()).stream()
                .filter(targetCell -> canBuild(worker, targetCell))
                .toList();

        ActionList actions = new ActionList();

        for (Cell movableCell : movableCells){
            actions.add(new BuildAction(worker, movableCell));
        }

        return actions;
    }

    public ActionList checkMoveList(ActionList actions){
        return actions;
    }

    public ActionList checkBuildList(ActionList actions){
        return actions;
    }
    /**
     * Checks if the current player has won the game.
     * A win occurs if the player has a worker on level 3 or if the opponent has no workers placed on the board.
     *
     * @param gameState The current game state to evaluate win conditions
     * @return {@code true} if the current player has won, {@code false} otherwise
     */
    public boolean checkWin(GameState gameState) {

        for (Player player : gameState.getPlayers()) {
            // 1. Check if any of current player's workers are standing on level 3
            for (Worker worker : player.getWorkers()) {
                if (worker.getLocatedCell() != null && worker.getLocatedCell().getPosition().z() == 3) {
                    player.setWin(true);
                    return true;  // classic win by reaching level 3
                }
            }
        }

        return false;
    }

    /**
     * Checks if the current player has lost the game.
     * A loss occurs if all the current player's workers are stuck and unable to move or build.
     *
     * @param gameState The current game state to evaluate lose conditions
     * @return {@code true} if the current player has lost, {@code false} otherwise
     */
    public boolean checkLose(GameState gameState) {

        boolean anyPlayerLost = false;

        Player[] players = gameState.getPlayers();

        for (Player player : players) {
            boolean allStuck = true;

            for (Worker worker : player.getWorkers()) {
                if (worker.getLocatedCell() != null) {
                    boolean canMove = !moveActions(gameState.getBoard(), worker).isEmpty();

                    if (canMove) {
                        allStuck = false; // This player still has options
                        break;
                    }
                }
            }

            if (allStuck) {
                player.setLose(true);
                anyPlayerLost = true;
            }
        }
        return anyPlayerLost;
    }

    /**
     * Checks if a worker can move to a target cell according to the game rules.
     * A move is valid if the target cell is adjacent to the current cell, is not occupied,
     * is not complete, and the worker can move to a higher or equal level.
     *
     * @param worker The worker attempting to move
     * @param targetCell The target cell to which the worker may move
     * @return {@code true} if the worker can move to the target cell, {@code false} otherwise
     */
    private boolean canMove(Worker worker, Cell targetCell) {

        Cell currentCell = worker.getLocatedCell();


        boolean test =  currentCell.isAdjacentTo(targetCell) &&
                !targetCell.isOccupied() &&
                !targetCell.isComplete() &&
                targetCell.getPosition().z() <= currentCell.getPosition().z() + 1  ;
        return test;
    }

    /**
     * Checks if a worker can build on a target cell according to the game rules.
     * A build is valid if the target cell is adjacent to the worker's current cell,
     * is not occupied, and is not complete.
     *
     * @param worker The worker attempting to build
     * @param targetCell The target cell where the worker may build
     * @return {@code true} if the worker can build on the target cell, {@code false} otherwise
     */
    private boolean canBuild(Worker worker, Cell targetCell) {

        return worker.getLocatedCell().isAdjacentTo(targetCell) &&
                !targetCell.isOccupied() &&
                !targetCell.isComplete();
    }

}