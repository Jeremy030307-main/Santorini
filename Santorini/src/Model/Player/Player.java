package Model.Player;

import Model.Action.Action;
import Model.Board.Board;
import Model.Board.Cell;
import Model.GameRule.ClassicGameRule;
import Model.GodCard.GodCard;

import java.awt.*;
import java.util.List;

public class Player {

    private final int id;
    private final String name;
    private final Worker[] workers = new Worker[2];
    private GodCard godCard;
    private final WorkerColor workerColor;

    private boolean win;
    private boolean lose;


    public Player(int id, String name, GodCard godCard, WorkerColor workerColor) {
        this.id = id;
        this.name = name;
        this.godCard = godCard;
        this.workerColor = workerColor;
        this.workers[0] = new Worker(0, this, WorkerType.MALE, workerColor);
        this.workers[1] = new Worker(1, this, WorkerType.FEMALE, workerColor);
        this.win = false;
        this.lose = false;
    }

    public void placeWorker(Worker worker, Cell cell) {
        worker.setLocatedCell(cell);
        cell.setOccupant(worker);

        // Print a message that the worker has been placed on the cell
        System.out.println("Worker " + worker.getId() + " (" + name + ") has been placed on cell at position: (" + cell.getPosition() + ")");
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Worker[] getWorkers() {
        return workers;
    }

    public Worker getWorkerByID(int id) {
        for (Worker w : workers) {
            if (w.getId() == id) {
                return w;
            }
        }
        throw new IllegalArgumentException("No worker found with ID: " + id);
    }

    public GodCard getGodCard() {
        return godCard;
    }

    public WorkerColor getWorkerColor() {
        return workerColor;
    }

    public void setWin(boolean win) {
        this.win = win;
    }

    public void setGodCard(GodCard godCard) {
        this.godCard = godCard;
    }
}
