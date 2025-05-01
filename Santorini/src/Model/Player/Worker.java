package Model.Player;

import Model.Action.Action;
import Model.Action.BuildAction;
import Model.Action.MoveAction;
import Model.Board.Cell;
import Model.Game.GameState;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Worker {

    private final int id;
    private final Player owner;
    private final WorkerType gender;
    private final WorkerColor workerColor;

    private Cell cell;

    public Worker(int id, Player owner , WorkerType gender, WorkerColor workerColor) {
        this.id = id;
        this.owner = owner;
        this.gender = gender;
        this.workerColor = workerColor;
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

    public WorkerType getGender() {
        return gender;
    }

    public WorkerColor getWorkerColor() {
        return workerColor;
    }
}
