package Model.Player;

import Model.Action.Action;
import Model.Board.Cell;
import Model.Game.Game;
import Model.Game.GameState;
import Model.GodCard.GodCard;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private final int id;
    private final String name;
    private final Worker[] workers = new Worker[2];
    private final GodCard godCard;

    public Player(int id, String name, GodCard godCard) {
        this.id = id;
        this.name = name;
        this.godCard = godCard;
        this.workers[0] = new Worker(1, this);
        this.workers[1] = new Worker(2, this);
    }

//    public List<Action> allowableAction(GameState gameState) {
//
//    }

    public void placeWorker(int workerID, Cell cell){
        if (workerID < 0 || workerID > 1) {
            throw new IllegalArgumentException("Invalid worker index");
        }
        workers[workerID].setPosition(cell);
        cell.setOccupant(workers[workerID]);
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
}
