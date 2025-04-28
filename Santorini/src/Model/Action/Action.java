package Model.Action;

import Model.Game.GameState;
import Model.Game.TurnPhase;

public abstract class Action {

    private boolean active = false;
    private final TurnPhase currentPhase;
    private TurnPhase nextPhase;
    private final String description;

    public Action(String description, TurnPhase currentPhase, TurnPhase nextPhase) {
        this.description = description;
        this.currentPhase = currentPhase;
        this.nextPhase = nextPhase;
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
}
