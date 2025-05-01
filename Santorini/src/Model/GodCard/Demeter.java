package Model.GodCard;

import Model.Action.Action;
import Model.Board.Cell;
import Model.Game.TurnPhase;
import java.util.Iterator;
import java.util.List;

/**
 * The {@code Demeter} class represents the Demeter god card in the game.
 * This god card allows a player to build one additional time during their turn,
 * with the restriction that the worker cannot build on the same space as the first build.
 * <p>
 * The class extends {@link GodCard} and overrides the {@link GodCard#beforeBuild(List)} method
 * to implement the additional build feature and to filter out builds on the same cell as the first build.
 * </p>
 */
public class Demeter extends GodCard {

    private Cell firstBuildCell;
    private boolean extraBuildUsed;

    /**
     * Constructs a new Demeter god card.
     * The card grants the player the ability to build one additional time,
     * but prevents the worker from building on the same space as the first build.
     */
    public Demeter() {
        super("Demeter", "Your Worker may build one additional time, but not on the same space.");
        this.extraBuildUsed = false;
    }

    /**
     * Filters the list of possible build actions for a worker, granting an additional build
     * if it has not been used already, and preventing the worker from building on the same cell
     * as the first build after the first use.
     *
     * @param buildActions A list of build actions that the player may take
     * @return A filtered list of valid build actions, excluding builds on the same cell as the first build
     */
    @Override
    public List<Action> beforeBuild(List<Action> buildActions) {

        if (!extraBuildUsed){
            for (Action action: buildActions){
                action.setNextPhase(TurnPhase.BUILD);
                extraBuildUsed = true;
                firstBuildCell = action.getTargetCell();
            }
        } else {
            // Second build: disallow building on the same cell as first
            Iterator<Action> iterator = buildActions.iterator();
            while (iterator.hasNext()) {
                Action action = iterator.next();
                if (action.getTargetCell().equals(firstBuildCell)) {
                    iterator.remove();
                }
            }
        }
        return buildActions;
    }
}
