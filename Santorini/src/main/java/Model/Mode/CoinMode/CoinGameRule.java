package Model.Mode.CoinMode;

import Model.Action.Action;
import Model.Action.ActionList;
import Model.Action.OptionalAction;
import Model.Action.SequenceAction;
import Model.Board.BlockType;
import Model.Board.Position;
import Model.Game.TurnPhase;
import Model.GameRule.ClassicGameRule;

import java.util.ArrayList;
import java.util.List;

public class CoinGameRule extends ClassicGameRule {

    private final CoinManager coinManager;

    public CoinGameRule(CoinManager coinManager) {
        this.coinManager = coinManager;
    }

    // add a collect coin action if the next target cell has a coin
    @Override
    public ActionList checkMoveList(ActionList actions) {

        List<Action> actionList = new ArrayList<>();

        for (Action action : actions) {
            Position targetPos = action.getTargetCell().getPosition();
            Action finalAction = action;
            if (coinManager.hasCoinAt(targetPos.x(), targetPos.y())) {
                SequenceAction sequenceAction = new SequenceAction(action.getCurrentPhase(), action.getNextPhase(), action.getTargetWorker(), action.getTargetCell());
                sequenceAction.addAction(action);
                sequenceAction.addAction(new CollectCoinAction(coinManager, 1, action.getCurrentPhase(), action.getNextPhase(), action.getTargetWorker(), action.getTargetCell()));
                finalAction = sequenceAction;
            }
            actionList.add(finalAction);
        }

        return new ActionList(actionList, actions.getOptionalAction());
    }

    // check is the player has enough coin to build the specific block
    @Override
    public ActionList checkBuildList(ActionList actions) {

        ActionList actionList = super.checkBuildList(actions);

        for (int i = 0; i < actionList.size(); i++) {
            Action action = actionList.get(i);
            BlockType requiredBlockType = BlockType.fromLevel(action.getTargetCell().getPosition().z() + 1);

            if (action.isActive()) {
                if (coinManager.canAffordBlock(action.getTargetWorker().getOwner(), requiredBlockType)) {

                    SequenceAction sequenceAction = new SequenceAction(action.getCurrentPhase(), action.getNextPhase(), action.getTargetWorker(), action.getTargetCell());
                    sequenceAction.addAction(action);
                    sequenceAction.addAction(new SpendCoinAction(coinManager, coinManager.getBlockPrice(requiredBlockType), action.getCurrentPhase(), action.getNextPhase(), action.getTargetWorker(), action.getTargetCell()));
                    actionList.set(i, sequenceAction);
                } else {
                    action.deactivate("Insufficient coin to build.");
                }
            }

        }

        if (actionList.getOptionalAction() == null) {
            OptionalAction optionalAction = new OptionalAction("Skip Build", "", actionList.getFirst().getTargetWorker(), TurnPhase.END_TURN);
            actionList.setOptionalAction(optionalAction);
        }

        return actionList;
    }
}
