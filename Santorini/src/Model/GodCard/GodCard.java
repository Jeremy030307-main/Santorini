package Model.GodCard;

import Model.Action.Action;
import java.util.List;

public abstract class GodCard {

    protected GodCardFactory godName;
    protected String description;

    public GodCard(GodCardFactory godName, String description) {
        this.godName = godName;
        this.description = description;
    }

    public GodCardFactory getName() {
        return godName;
    }

    public String getDescription() {
        return description;
    }

    public void onSetup(){
    };

    public List<Action> beforeMove(List<Action> moveActions){
        return moveActions;
    };

    public void afterMove(){
    };

    public List<Action> beforeBuild(List<Action> buildActions) {
        return buildActions;
    };

    public void afterBuild() {};

    public void beforeOpponentMove(){
    };

    public void afterOpponentMove(){
    };

    public void beforeOpponentBuild() {
    };

    public void afterOpponentBuild() {}

    public void isWin(){}

    public void isLose(){}
}
