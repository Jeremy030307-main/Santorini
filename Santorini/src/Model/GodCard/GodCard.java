package Model.GodCard;

import Model.Action.Action;
import Model.Game.Game;
import Model.Game.GameState;
import Model.Player.Player;
import Model.Player.Worker;

import java.util.List;

public abstract class GodCard {

    protected final GodCardFactory godName;
    protected final String description;
    protected Player player;

    public GodCard(GodCardFactory godName, String description, Player player) {
        this.godName = godName;
        this.description = description;
        this.player = player;
    }

    public void onSetup(){
    };

    public List<Action> beforeMove(List<Action> moveActions){
        return moveActions;
    };

    public void afterMove(Action moveAction, GameState gameState){
    };

    public List<Action> beforeBuild(List<Action> buildActions) {
        return buildActions;
    };

    public void afterBuild(Action buildAction, GameState gameState) {};

    public List<Action> getOptionalActions(GameState gameState, Worker currentWorker) {return null;};

    public void beforeOpponentMove(List<Action> moveActions){
    };

    public void afterOpponentMove(Action moveAction, GameState gameState){
    };

    public void beforeOpponentBuild(List<Action> buildActions) {
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
