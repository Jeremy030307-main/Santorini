package Model.GodCard;

import Model.Action.Action;
import Model.Board.Cell;
import Model.Game.TurnPhase;
import java.util.Iterator;
import java.util.List;

public class Demeter extends GodCard {

    private Cell firstBuildCell;
    private boolean extraBuildUsed;

    public Demeter() {
        super("Demeter", "Your Worker may build one additional time, but not on the same space.");
        this.extraBuildUsed = false;
    }

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
