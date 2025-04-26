package Model.Player;

import Model.Board.Cell;
import Model.GodCard.GodCard;

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

    // TODO: Add un allowable action for player after gamestate finalise
//    public List<Action> allowableAction(GameState gameState) {
//
//    }

    public int getId() {
        return id;
    }

    public void placeWorker(int workerID, Cell cell){
        if (workerID < 0 || workerID > 1) {
            throw new IllegalArgumentException("Invalid worker index");
        }
        workers[workerID].setLocatedCell(cell);
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
