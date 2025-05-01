package Model.Game;

import Model.Action.Action;
import Model.Player.Player;
import java.util.ArrayList;
import java.util.List;

public class TurnManager {

    private final Player[] players;
    private int currentPlayerIndex;
    private Integer playerSelectedWorkerID;
    private TurnPhase phase;

    public TurnManager(Player[] players) {
        this.players = players;
        this.currentPlayerIndex = 0;
        this.phase = TurnPhase.START_TURN;
        this.playerSelectedWorkerID = null;
    }

    // === TESTING CONTROL METHODS ===

    public void onStartTurn(){

        this.phase = TurnPhase.SELECT_WORKER; // no additional proces, direct pass the turn to select worker phase
        return;
    }

    public void handleWorkerSelection(int workerID){

        playerSelectedWorkerID = workerID;
        phase = TurnPhase.MOVE;
        return;
    }

    public List<Action> getMoveActions(GameState gameState){
        List<Action> moveActions = gameState.getMovesAction(getCurrentPlayer().getWorkerByID(playerSelectedWorkerID));

        moveActions = getCurrentPlayer().getGodCard().beforeMove(moveActions);
;        
        return moveActions;
    };

    public List<Action> getBuildActions(GameState gameState){
        List<Action> buildActions = gameState.getBuildAction(getCurrentPlayer().getWorkerByID(playerSelectedWorkerID));

        buildActions = getCurrentPlayer().getGodCard().beforeBuild(buildActions);
        return buildActions;
    };

    public List<Action> getMoveBuildActions(GameState gameState){
        List<Action> moveActions = gameState.getMovesAction(getCurrentPlayer().getWorkerByID(playerSelectedWorkerID));
        List<Action> buildActions = gameState.getBuildAction(getCurrentPlayer().getWorkerByID(playerSelectedWorkerID));
        List<Action> moveBuildActions = new ArrayList<>(moveActions);
        moveBuildActions.addAll(buildActions);
        return moveBuildActions;
    }

    public void getOptionalActions(GameState gameState) {

    }

    public void handleAction(Action action, GameState gameState){
        action.execute(gameState);
        phase = action.getNextPhase();
        gameState.process();
    }

    public void onEndTurn(){
        currentPlayerIndex = (currentPlayerIndex + 1) % players.length;
        playerSelectedWorkerID = null;
        phase = TurnPhase.START_TURN;
    }

    public TurnPhase getPhase() {
        return phase;
    }

    public Player getCurrentPlayer() {
        return players[currentPlayerIndex];
    }

    public Player getOpponent(Player player) {
        for (Player p : players) {
            if (!p.equals(player)) {
                return p;
            }
        }
        return null;
    }
}
