package Model.Game.TurnManager;

import Model.Player.Player;

public abstract class EventBasedTurnManager extends TurnManager {

    protected Runnable onEvent;

    public EventBasedTurnManager(Player[] players) {
        super(players);
    }

    public void setOnEvent(Runnable onEvent) {
        this.onEvent = onEvent;
    }
}
