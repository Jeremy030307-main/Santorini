package Model.GodCard;

import Model.Action.Action;
import java.util.List;

public abstract class GodCard {

    protected  String name;
    protected String description;

    /**
     * Constructs a new god card with the specified name and description.
     *
     * @param name The name of the god card
     * @param description A description of the god card's power or ability
     */
    public GodCard(String name, String description) {
        this.name = name;
    public GodCard(GodCardFactory godName, String description, Player player) {
        this.godName = godName;
        this.description = description;
        this.player = player;
    }
    /**
     * Retrieves the description of the god card.
     *
     * @return The description of the god card
     */
    public String getDescription() {
        return description;
    }

    public void onSetup(){
    };

    public List<Action> beforeMove(List<Action> moveActions){
        return moveActions;
    };

    /**
     * Hook method to execute after a move is made.
     * This method is empty by default and can be overridden by specific god card implementations.
     */
    public void afterMove(Action moveAction, GameState gameState){



    public List<Action> beforeBuild(List<Action> buildActions) {
        return buildActions;
    };

    /**
     * Hook method to execute after a build is made.
     * This method is empty by default and can be overridden by specific god card implementations.
     */
    public void afterBuild(Action buildAction, GameState gameState) {};

    public List<Action> getOptionalActions(GameState gameState, Worker currentWorker) {return null;};

    /**
     * Hook method to execute before an opponent's move.
     * This method is empty by default and can be overridden by specific god card implementations.
     */
    public void beforeOpponentMove(List<Action> moveActions){}
            }

    /**
     * Hook method to execute after an opponent's move.
     * This method is empty by default and can be overridden by specific god card implementations.
     */
    public void afterOpponentMove(Action moveAction, GameState gameState){

    /**
     * Hook method to execute before an opponent's build action.
     * This method is empty by default and can be overridden by specific god card implementations.
     */
    public void beforeOpponentBuild(List<Action> buildActions) {
    };

    /**
     * Hook method to execute after an opponent's build action.
     * This method is empty by default and can be overridden by specific god card implementations.
     */
    public void afterOpponentBuild(Action buildAction, GameState gameState) {}

    /**
     * Hook method to determine if the player has won the game.
     * This method is empty by default and can be overridden by specific god card implementations.
     */
    public void isWin(GameState gameState){}

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
    /**
     * Hook method to determine if the player has lost the game.
     * This method is empty by default and can be overridden by specific god card implementations.
     */
    public void isLose(GameState gameState){}

