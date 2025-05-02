package Model.Game;

import Model.Action.Action;
import Model.Action.BuildAction;
import Model.Action.MoveAction;
import Model.Player.Player;
import Model.Player.Worker;

import javax.swing.text.html.HTMLDocument;
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

    public List<Action> getOptionalActions(GameState gameState) {
        return getCurrentPlayer().getGodCard().getOptionalActions(gameState, getCurrentWorker());  // last one is a do nothing action
    }

    public void handleAction(Action action, GameState gameState){
        action.execute(gameState);
        System.out.println(action.getNextPhase());
        phase = action.getNextPhase();

        if (action instanceof MoveAction moveAction) {
            getCurrentPlayer().getGodCard().afterMove(action, gameState);

            for (Player player : getOpponents(getCurrentPlayer())) {
                player.getGodCard().afterOpponentMove(action, gameState);
            }
        } else if (action instanceof BuildAction buildAction) {
            getCurrentPlayer().getGodCard().afterBuild(action, gameState);

            for (Player player : getOpponents(getCurrentPlayer())) {
                player.getGodCard().afterOpponentBuild(action, gameState);
            }
        }

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

    public Worker getCurrentWorker() {
        return getCurrentPlayer().getWorkers()[playerSelectedWorkerID];
    }

    public Player[] getOpponents(Player player) {
        List<Player> opponents = new ArrayList<>();
        for (Player p : players) {
            if (!p.equals(player)) {
                opponents.add(p);
            }
        }
        return opponents.toArray(new Player[0]);
    }
}
