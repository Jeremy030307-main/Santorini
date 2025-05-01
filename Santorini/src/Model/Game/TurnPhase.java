package Model.Game;

/**
 * The {@code TurnPhase} enum defines the different phases of a turn in the game.
 * Each phase represents a specific step in the turn process, such as selecting workers,
 * moving or building, checking win conditions, and ending the turn.
 */
public enum TurnPhase {
    /**
     * The phase where the turn starts and initial preparations are made.
     */
    START_TURN,
    /**
     * The phase where the player selects a worker to perform actions.
     */
    SELECT_WORKER,
    /**
     * The phase where the player chooses between moving or building.
     */
    MOVE_OR_BUILD,
    /**
     * The phase where the player performs the movement action with a selected worker.
     */
    MOVE,
    /**
     * The phase where the player performs the building action with a selected worker.
     */
    BUILD,
    /**
     * The phase where the game checks if any player has won the game.
     */
    CHECK_WIN,
    /**
     * The phase where optional actions (if any) can be performed.
     */
    OPTIONAL_ACTION,
    /**
     * The phase where the turn ends and the game prepares for the next turn.
     */
    END_TURN
}
