package Model.GodCard;

import Model.Action.Action;
import Model.Action.DoNothingAction;
import Model.Board.Cell;
import Model.Game.GameState;
import Model.Game.TurnPhase;
import Model.Player.Worker;

import java.util.List;

public class Demeter extends GodCard {

    private Cell firstBuildCell;

    public Demeter() {
        super(GodCardFactory.DEMETER, "Your Worker may build one additional time, but not on the same space.", null);
    }

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
