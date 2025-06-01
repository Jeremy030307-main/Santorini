package Model.Action;

import Model.Board.Cell;
import Model.Game.GameState;
import Model.Game.TurnPhase;
import Model.Player.Worker;

/**
 * The {@code Action} abstract class represents a game action in Santorini 
 * such as a move or build. Each action is associated with a specific worker,
 * a target cell on the board, and the turn phase it belongs to.
 * <p>
 * Subclasses must implement the {@link #execute(GameState)} method 
 * to define the specific behavior of the action.
 * </p>
 */
public abstract class Action {

    /** Indicates whether the action is currently active and should be available to the player. */
    private boolean active = true;
    private String diabledText = "";

    /** The current turn phase when this action is valid (e.g., MOVE, BUILD). */
    private final TurnPhase currentPhase;

    /** The turn phase that follows after this action is executed. */
    private TurnPhase nextPhase;

    /** Human-readable description of this action (used for UI or debugging). */
    private final String description;

    /** The worker performing this action. */
    protected final Worker targetWorker;

    /** The cell on the board affected by this action. */
    protected final Cell targetCell;

    /**
     * Constructs an action instance with all required parameters.
     *
     * @param description A brief description of the action
     * @param currentPhase The phase in which this action is valid
     * @param nextPhase The phase that follows after this action
     * @param targetWorker The worker that will perform the action
     * @param targetCell The cell that is the target of the action
     */
    public Action(String description, TurnPhase currentPhase, TurnPhase nextPhase, Worker targetWorker, Cell targetCell) {
        this.description = description;
        this.currentPhase = currentPhase;
        this.nextPhase = nextPhase;
        this.targetCell = targetCell;
        this.targetWorker = targetWorker;
    }

    /**
     * Executes the action and applies its effects to the game state.
     * This method must be implemented by concrete subclasses.
     *
     * @param gameState The current game state to apply this action to
     */
    public abstract void execute(GameState gameState);

    /**
     * Activates this action, making it available for execution.
     */
    public void activate() {
        active = true;
        diabledText = "";
    }

    /**
     * Deactivates this action, making it unavailable for execution.
     */
    public void deactivate(String reason) {
        active = false;
        diabledText = reason;
    }

    public String getDiabledText() {
        return diabledText;
    }

    /**
     * Returns the turn phase in which this action is valid.
     *
     * @return The current phase of this action
     */
    public TurnPhase getCurrentPhase() {
        return currentPhase;
    }

    /**
     * Returns the phase that follows after this action is executed.
     *
     * @return The next phase after executing this action
     */
    public TurnPhase getNextPhase() {
        return nextPhase;
    }

    /**
     * Sets the next turn phase to follow this action.
     *
     * @param nextPhase The new next phase
     */
    public void setNextPhase(TurnPhase nextPhase) {
        this.nextPhase = nextPhase;
    }

    /**
     * Returns a human-readable description of the action.
     *
     * @return Description of this action
     */
    public String getDescription() {
        return description;
    }

    /**
     * Checks whether this action is currently active.
     *
     * @return {@code true} if the action is active, {@code false} otherwise
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Returns the worker performing this action.
     *
     * @return The worker associated with this action
     */
    public Worker getTargetWorker() {
        return targetWorker;
    }

    /**
     * Returns the cell targeted by this action.
     *
     * @return The board cell affected by this action
     */
    public Cell getTargetCell() {
        return targetCell;
    }
}
