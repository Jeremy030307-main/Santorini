package Model.Game;

public enum TurnPhase {
    START_TURN(""),
    SELECT_WORKER("Select A Worker"),
    MOVE_OR_BUILD("Move or Build"),
    MOVE("Move"),
    BUILD("Build"),
    CHECK_WIN(""),
    OPTIONAL_ACTION("Optional Action"),
    END_TURN("");

    private final String phaseText;

    TurnPhase(String phaseText) {
        this.phaseText = phaseText;
    }

    public String getPhaseText() {
        return phaseText;
    }
}
