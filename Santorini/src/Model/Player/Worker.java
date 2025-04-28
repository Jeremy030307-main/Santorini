package Model.Player;

import Model.Action.Action;
import Model.Action.BuildAction;
import Model.Action.MoveAction;
import Model.Board.Cell;
import Model.Game.GameState;
import java.util.ArrayList;
import java.util.List;

public class Worker {

    private final int id;
    private final Player owner;
    private Cell cell;

    public Worker(int id, Player owner){
        this.id = id;
        this.owner = owner;
    }

    // TODO: Add in the allowable action for worker after finalise the gamestate
//    public List<Action> allowableAction(GameState gameState) {
//
//    }

    private List<Action> generateMoveAction(GameState gameState) {

         List<Cell> movableCells = gameState.getBoard().getSurroundingCell(cell.getPosition()).stream()
                                    .filter(targetCell -> gameState.getGameRule().canMove(this, targetCell))
                                    .toList();

         List<Action> actions = new ArrayList<>();

         for (Cell movableCell : movableCells){
             actions.add(new MoveAction(this, movableCell));
         }

         return actions;
    }

    private List<Action> generateBuildAction(GameState gameState) {

        List<Cell> buildableCells = gameState.getBoard().getSurroundingCell(cell.getPosition()).stream()
                .filter(targetCell -> gameState.getGameRule().canBuild(this, targetCell))
                .toList();

        List<Action> actions = new ArrayList<>();

        for (Cell buildableCell : buildableCells){
            actions.add(new BuildAction(this, buildableCell));
        }

        return actions;
    }

    public int getId() {
        return id;
    }

    public Player getOwner() {
        return owner;
    }

    public Cell getLocatedCell() {
        return cell;
    }

    public void setLocatedCell(Cell position) {
        this.cell = position;
    }
}
