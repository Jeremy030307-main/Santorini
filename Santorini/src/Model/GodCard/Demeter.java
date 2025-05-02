package Model.GodCard;

import Model.Action.Action;
import Model.Action.DoNothingAction;
import Model.Board.Cell;
import Model.Game.GameState;
import Model.Game.TurnPhase;
import Model.Player.Worker;

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

    /**
     * Constructs a new Demeter god card.
     * The card grants the player the ability to build one additional time,
     * but prevents the worker from building on the same space as the first build.
     */
    public Demeter() {
        super(GodCardFactory.DEMETER, "Your Worker may build one additional time, but not on the same space.", null);
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
        for (Action action: buildActions){
            action.setNextPhase(TurnPhase.OPTIONAL_ACTION);
        }

        return buildActions;
    }

    @Override
    public void afterBuild(Action buildAction, GameState gameState) {
        firstBuildCell = buildAction.getTargetCell();
    }

    @Override
    public List<Action> getOptionalActions(GameState gameState, Worker currentWorker) {
        List<Action> actions = new java.util.ArrayList<>(gameState.getGameRule().buildActions(gameState.getBoard(), currentWorker)
                .stream().filter(action -> !action.getTargetCell().getPosition().equals(firstBuildCell.getPosition())).toList());

        DoNothingAction doNothingAction = new DoNothingAction(currentWorker, TurnPhase.END_TURN);

        actions.add(doNothingAction);

        return actions;
    }
}
