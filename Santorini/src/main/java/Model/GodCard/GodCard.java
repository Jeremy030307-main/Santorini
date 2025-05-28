package Model.GodCard;

import Model.Action.Action;
import Model.Action.ActionList;
import Model.Game.GameState;
import Model.Player.Player;
import Model.Player.Worker;

import java.util.List;

public abstract class GodCard {

    protected final GodCardFactory godName;
    protected final String description;
    protected final String timing;
    protected Player player;

    public GodCard(GodCardFactory godName, String timing, String description, Player player) {
        this.godName = godName;
        this.timing = timing;
        this.description = description;
        this.player = player;
    }

    public void onSetup(){
    };

    public ActionList beforeMove(ActionList moveActions){
        return moveActions;
    };

    public void afterMove(Action moveAction, GameState gameState){
    };

    public ActionList beforeBuild(ActionList buildActions) {
        return buildActions;
    };

    public void afterBuild(Action buildAction, GameState gameState) {};

    public ActionList getOptionalActions(GameState gameState, Worker currentWorker) {return null;};

    public void beforeOpponentMove(ActionList moveActions){
    };

    public void afterOpponentMove(Action moveAction, GameState gameState){
    };

    public void beforeOpponentBuild(ActionList buildActions) {
    };

    public void afterOpponentBuild(Action buildAction, GameState gameState) {}

    public void isWin(GameState gameState){}

    public void isLose(GameState gameState){}

    public String getDescription() {
        return description;
    }

    public GodCardFactory getName() {
        return godName;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
