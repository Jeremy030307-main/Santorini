package Model.Player;

import Model.Action.Action;
import Model.Action.BuildAction;
import Model.Action.MoveAction;
import Model.Board.Cell;
import Model.Board.Position;
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
