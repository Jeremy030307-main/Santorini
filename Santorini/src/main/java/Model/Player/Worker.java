package Model.Player;

import Model.Board.Cell;

/**
 * The {@code Worker} class represents a worker in the Santorini game.
 * Each worker is associated with a unique ID, an owner (player), a gender (male or female),
 * and a worker color. Workers are placed on cells on the game board and can be moved or built upon.
 * <p>
 * The {@code Worker} class provides methods for retrieving and modifying the worker's position on the board,
 * as well as accessing the worker's properties such as ID, owner, gender, and color.
 * </p>
 */
public class Worker {

    private final int id;
    private final Player owner;
    private final WorkerType gender;
    private final WorkerColor workerColor;

    private Cell cell;

    /**
     * Constructs a new worker with the specified ID, owner, gender, and worker color.
     *
     * @param id The unique ID of the worker
     * @param owner The player that owns the worker
     * @param gender The gender of the worker (male or female)
     * @param workerColor The color of the worker
     */
    public Worker(int id, Player owner , WorkerType gender, WorkerColor workerColor) {
        this.id = id;
        this.owner = owner;
        this.gender = gender;
        this.workerColor = workerColor;
    }

    /**
     * Retrieves the unique ID of the worker.
     *
     * @return The ID of the worker
     */
    public int getId() {
        return id;
    }

    /**
     * Retrieves the owner of the worker.
     *
     * @return The player that owns the worker
     */
    public Player getOwner() {
        return owner;
    }

    /**
     * Retrieves the cell where the worker is currently located.
     *
     * @return The cell where the worker is located
     */
    public Cell getLocatedCell() {
        return cell;
    }

    /**
     * Sets the location of the worker to the specified cell.
     *
     * @param position The cell where the worker will be placed
     */
    public void setLocatedCell(Cell position) {
        this.cell = position;
    }

    /**
     * Retrieves the gender of the worker.
     *
     * @return The gender of the worker
     */
    public WorkerType getGender() {
        return gender;
    }

    /**
     * Retrieves the color of the worker.
     *
     * @return The color of the worker
     */
    public WorkerColor getWorkerColor() {
        return workerColor;
    }
}
