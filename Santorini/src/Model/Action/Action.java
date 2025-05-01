package Model.Action;

import Model.Board.Cell;
import Model.Game.GameState;
import Model.Game.TurnPhase;
import Model.Player.Worker;

public abstract class Action {

    private boolean active = false;
    private final TurnPhase currentPhase;
    private TurnPhase nextPhase;
    private final String description;
    protected final Worker targetWorker;
    protected final Cell targetCell;

    public Action(String description, TurnPhase currentPhase, TurnPhase nextPhase, Worker targetWorker, Cell targetCell) {
        this.description = description;
        this.currentPhase = currentPhase;
        this.nextPhase = nextPhase;
        this.targetCell = targetCell;
        this.targetWorker = targetWorker;
    }

    public abstract void execute(GameState gameState);

    // getter and setter
    public void activate() {
        active  = true;
    };

    public void deactivate() {
        active = false;
    }

    public TurnPhase getCurrentPhase() {
        return currentPhase;
    }

    public TurnPhase getNextPhase() {
        return nextPhase;
    }

    public void setNextPhase(TurnPhase nextPhase) {
        this.nextPhase = nextPhase;
    }

    public String getDescription() {
        return description;
    }

    public boolean isActive() {
        return active;
    }

    public Worker getTargetWorker() {
        return targetWorker;
    }

    public Cell getTargetCell() {
        return targetCell;
    }
}
