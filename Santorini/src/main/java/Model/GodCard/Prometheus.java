package Model.GodCard;

import Model.Action.Action;
import Model.Action.ActionList;
import Model.Action.OptionalAction;
import Model.Game.GameState;
import Model.Game.TurnPhase;
import Model.Player.Worker;

import java.util.ArrayList;
import java.util.List;

public class Prometheus extends GodCard{

    private List<Action> actionSequence;

    public Prometheus() {
        super(GodCardFactory.PROMETHEUS,
                "Your Move and your Build",
                "If your Worker does not move up, it may build both before and after moving.",
                null);

        this.actionSequence = new ArrayList<>();
    }

    @Override
    public ActionList beforeMove(ActionList moveActions) {

        if (actionSequence.isEmpty()) {
            // this mean no before build action being taken
            moveActions.setOptionalAction(
                    new OptionalAction("Before Build", "Prometheus can build before move", moveActions.getFirst().getTargetWorker(), TurnPhase.OPTIONAL_ACTION));
            return moveActions;
        } else {
            ActionList noMoveUp = moveActions.filter(action -> action.getTargetCell().getPosition().z() <= action.getTargetWorker().getLocatedCell().getPosition().z());

            return noMoveUp;
        }
    };

    @Override
    public void afterMove(Action moveAction, GameState gameState) {
        actionSequence.add(moveAction);
    }

    @Override
    public ActionList getOptionalActions(GameState gameState, Worker currentWorker) {

        ActionList beforeBuilds = gameState.getGameRule().buildActions(gameState.getBoard(), currentWorker);

        for (Action action : beforeBuilds) {
            action.setNextPhase(TurnPhase.MOVE);
        }
        return beforeBuilds;
    }

    @Override
    public void afterBuild(Action buildAction, GameState gameState) {

        // suppose it should only have a move action taken, so if the list size is more than 1, then a befreo build is taken
        if (actionSequence.isEmpty()) {
            actionSequence.add(buildAction);
        } else {
            actionSequence.clear();
        }
    }
}
