package Model.GodCard;

import Model.Action.Action;
import Model.Action.ActionList;
import Model.Action.OptionalAction;
import Model.Board.Cell;
import Model.Board.Position;
import Model.Game.GameState;
import Model.Game.TurnPhase;
import Model.Player.Worker;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code Artemis} class represents the Artemis god card in the game.
 * This god card allows a player to move a worker one additional time during their turn,
 * with the restriction that the worker cannot move back to its initial position.
 * <p>
 * The class extends {@link GodCard} and overrides the {@link GodCard#beforeMove(ActionList)} method
 * to implement the additional move feature and to filter out moves that would return the worker to
 * its original position.
 * </p>
 */
public class Artemis extends GodCard {

    private Cell originalPosition; // Store initial position before first move
    private List<Action> actions;

    /**
     * Constructs a new Artemis god card.
     * The card grants the player the ability to move a worker an additional time,
     * but prevents the worker from moving back to its original position.
     */
    public Artemis() {
        super(GodCardFactory.ARTEMIS, "Your Move", "Your Worker may move one additional time, but not back to its initial space.", null);
        originalPosition = null;
        actions = new ArrayList<>();
    }

    /**
     * Filters the list of possible move actions for a worker, granting an additional move
     * if it has not been used already, and preventing the worker from moving back to its original position
     * after the first move.
     *
     * @param moveActions A list of move actions that the player may take
     * @return A filtered list of valid move actions, excluding moves that would return the worker to its original position
     */
    @Override
    public ActionList beforeMove(ActionList moveActions) {

        if (originalPosition == null) {
            originalPosition = moveActions.getFirst().getTargetWorker().getLocatedCell();
        } else {
            // cannot move back to original position
            for (Action action : moveActions) {
                if (action.getTargetCell().getPosition().equals(originalPosition.getPosition())) {
                    action.deactivate("Artemis Power: Worker extra move cannot back to its initial space.");
                }
            }
            moveActions.setOptionalAction(new OptionalAction("Skip Move", "", moveActions.getFirst().getTargetWorker(), TurnPhase.BUILD));
        }

        return moveActions;
    }

    @Override
    public void afterMove(Action moveAction, GameState gameState) {
        actions.add(moveAction);
    }

    @Override
    public ActionList beforeBuild(ActionList buildActions) {

        if (actions.size() == 1) {
            // means it only  move one time
            buildActions.setOptionalAction(new OptionalAction("Extra Move", "", buildActions.getFirst().getTargetWorker(), TurnPhase.MOVE));
        }
        return buildActions;
    }

    @Override
    public void afterBuild(Action buildAction, GameState gameState) {
        actions.clear();
        originalPosition = null;
    }

    @Override
    public void beforeOpponentMove(ActionList moveActions) {
        actions.clear();
        originalPosition = null;
        super.beforeOpponentMove(moveActions);
    }
}
