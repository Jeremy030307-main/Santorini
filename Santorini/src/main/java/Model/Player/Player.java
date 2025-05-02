package Model.Player;

import Model.Board.Cell;
import Model.GodCard.GodCard;


/**
 * The {@code Player} class represents a player in the Santorini game.
 * Each player has a unique ID, name, god card, worker color, and two workers (male and female).
 * The class provides methods for managing workers, placing workers on the board, and retrieving player information.
 * <p>
 * The {@code Player} class also tracks the win and lose states of a player.
 * </p>
 */
public class Player {

    private int id;
    private final String name;
    private final Worker[] workers = new Worker[2];
    private GodCard godCard;
    private final WorkerColor workerColor;

    private boolean win;
    private boolean lose;


    /**
     * Constructs a new player with the specified ID, name, god card, and worker color.
     * The player will have two workers (male and female) associated with the specified worker color.
     *
     * @param id The unique ID of the player
     * @param name The name of the player
     * @param godCard The god card assigned to the player
     * @param workerColor The color of the player's workers
     */
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

    /**
     * Places a worker on the specified cell.
     * The worker's located cell is updated, and the cell's occupant is set to the worker.
     *
     * @param worker The worker to be placed
     * @param cell The cell on which the worker will be placed
     */
    public void placeWorker(Worker worker, Cell cell) {
        worker.setLocatedCell(cell);
        cell.setOccupant(worker);

    }

    /**
     * Retrieves the unique ID of the player.
     *
     * @return The ID of the player
     */
    public int getId() {
        return id;
    }

    /**
     * Retrieves the name of the player.
     *
     * @return The name of the player
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the array of workers associated with the player.
     *
     * @return An array containing the two workers of the player
     */
    public Worker[] getWorkers() {
        return workers;
    }

    /**
     * Retrieves a worker by their ID.
     * If no worker is found with the specified ID, an {@code IllegalArgumentException} is thrown.
     *
     * @param id The ID of the worker to retrieve
     * @return The worker associated with the specified ID
     * @throws IllegalArgumentException if no worker is found with the specified ID
     */
    public Worker getWorkerByID(int id) {
        for (Worker w : workers) {
            if (w.getId() == id) {
                return w;
            }
        }
        throw new IllegalArgumentException("No worker found with ID: " + id);
    }

    /**
     * Retrieves the god card assigned to the player.
     *
     * @return The god card assigned to the player
     */
    public GodCard getGodCard() {
        return godCard;
    }

    /**
     * Retrieves the color of the player's workers.
     *
     * @return The color of the player's workers
     */
    public WorkerColor getWorkerColor() {
        return workerColor;
    }

    public void setWin(boolean win) {
        this.win = win;
    }

    public void setGodCard(GodCard godCard) {
        this.godCard = godCard;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLose(boolean lose) {
        for (Worker w : workers) {
            w.getLocatedCell().clearOccupant();
            w.setLocatedCell(null);
        }
        this.lose = lose;
    }

    public boolean isLose() {
        return lose;
    }

    public boolean isWin() {
        return win;
    }
}
