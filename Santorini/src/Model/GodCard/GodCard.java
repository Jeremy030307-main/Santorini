package Model.GodCard;

import Model.Action.Action;
import java.util.List;

/**
 * The {@code GodCard} class serves as the base class for all god cards in the game.
 * Each god card has a name and description and provides hooks for the game flow,
 * such as the ability to perform actions before or after a player or opponent moves or builds.
 * <p>
 * This class is extended by specific god card implementations, which override the 
 * methods to define the unique behavior of each god card.
 * </p>
 */
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
        this.description = description;
    }

    /**
     * Retrieves the name of the god card.
     *
     * @return The name of the god card
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the description of the god card.
     *
     * @return The description of the god card
     */
    public String getDescription() {
        return description;
    }

    /**
     * Hook method for setting up the god card before the game starts.
     * This method is empty by default and can be overridden by specific god card implementations.
     */
    public void onSetup(){
    };

    /**
     * Hook method for modifying the list of possible move actions before a move is made.
     * This method is empty by default and can be overridden by specific god card implementations.
     *
     * @param moveActions The list of available move actions
     * @return A potentially modified list of move actions
     */
    public List<Action> beforeMove(List<Action> moveActions){
        return moveActions;
    };

    /**
     * Hook method to execute after a move is made.
     * This method is empty by default and can be overridden by specific god card implementations.
     */
    public void afterMove(){
    };

    /**
     * Hook method for modifying the list of possible build actions before a build is made.
     * This method is empty by default and can be overridden by specific god card implementations.
     *
     * @param buildActions The list of available build actions
     * @return A potentially modified list of build actions
     */
    public List<Action> beforeBuild(List<Action> buildActions) {
        return buildActions;
    };

    /**
     * Hook method to execute after a build is made.
     * This method is empty by default and can be overridden by specific god card implementations.
     */
    public void afterBuild() {};

    /**
     * Hook method to execute before an opponent's move.
     * This method is empty by default and can be overridden by specific god card implementations.
     */
    public void beforeOpponentMove(){
    };

    /**
     * Hook method to execute after an opponent's move.
     * This method is empty by default and can be overridden by specific god card implementations.
     */
    public void afterOpponentMove(){
    };

    /**
     * Hook method to execute before an opponent's build action.
     * This method is empty by default and can be overridden by specific god card implementations.
     */
    public void beforeOpponentBuild() {
    };

    /**
     * Hook method to execute after an opponent's build action.
     * This method is empty by default and can be overridden by specific god card implementations.
     */
    public void afterOpponentBuild() {}

    /**
     * Hook method to determine if the player has won the game.
     * This method is empty by default and can be overridden by specific god card implementations.
     */
    public void isWin(){}

    /**
     * Hook method to determine if the player has lost the game.
     * This method is empty by default and can be overridden by specific god card implementations.
     */
    public void isLose(){}
}
