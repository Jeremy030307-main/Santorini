package Model.Game;

public enum TurnPhase {

    /**
     * The phase where the turn starts and initial preparations are made.
     */
    START_TURN("Start Turn"),

    /**
     * The phase where the player selects a worker to perform actions.
     */
    SELECT_WORKER("Select A Worker"),

    /**
     * The phase where the player chooses between moving or building.
     */
    MOVE_OR_BUILD("Move or Build"),

    /**
     * The phase where the player performs the movement action with a selected worker.
     */
    MOVE("Move"),

    /**
     * The phase where the player performs the building action with a selected worker.
     */
    BUILD("Build"),

    /**
     * The phase where the game checks if any player has won the game.
     */
    CHECK_WIN(""),

    /**
     * The phase where optional actions (if any) can be performed.
     */
    OPTIONAL_ACTION("Optional_Action"),

    /**
     * The phase where the turn ends and the game prepares for the next turn.
     */
    END_TURN("END TURN");

    private final String phaseText;

    TurnPhase(String phaseText) {
        this.phaseText = phaseText;
    }

    public String getPhaseText() {
        return phaseText;
    }
}
