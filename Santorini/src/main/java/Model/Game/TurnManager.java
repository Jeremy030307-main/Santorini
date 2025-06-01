package Model.Game;

import Model.Action.Action;
import Model.Action.ActionList;
import Model.Player.Player;
import Model.Player.Worker;

import java.util.ArrayList;
import java.util.List;

public class TurnManager {

    protected final Player[] players;
    protected int currentPlayerIndex;
    protected Integer playerSelectedWorkerID;
    protected TurnPhase phase;

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

    public ActionList getMoveActions(GameState gameState){
        ActionList moveActions = gameState.getMovesAction(getCurrentPlayer().getWorkerByID(playerSelectedWorkerID));

        moveActions = getCurrentPlayer().getGodCard().beforeMove(moveActions);

        for (Player player : getOpponents(getCurrentPlayer())) {
            player.getGodCard().beforeOpponentMove(moveActions);
        }
;
        return gameState.getGameRule().checkMoveList(moveActions);
    };

    public ActionList getBuildActions(GameState gameState){
        ActionList buildActions = gameState.getBuildAction(getCurrentPlayer().getWorkerByID(playerSelectedWorkerID));

        buildActions = getCurrentPlayer().getGodCard().beforeBuild(buildActions);
        return gameState.getGameRule().checkBuildList(buildActions);
    };

    /**
     * Retrieves any optional actions that can be performed by the current player.
     * This method is currently not implemented.
     *
     * @param gameState The current game state to evaluate optional actions
     */
    public ActionList getOptionalActions(GameState gameState) {

        ActionList optionalActions = getCurrentPlayer().getGodCard().getOptionalActions(gameState, getCurrentWorker());

        if (optionalActions.getFirst().getCurrentPhase() == TurnPhase.MOVE){
            return gameState.getGameRule().checkMoveList(optionalActions);
        } else if (optionalActions.getFirst().getCurrentPhase() == TurnPhase.BUILD) {
            return gameState.getGameRule().checkBuildList(optionalActions);
        } else {
            return optionalActions;
        }
    }

    public void handleAction(Action action, GameState gameState){
        action.execute(gameState);
        phase = action.getNextPhase();

        if (action.getCurrentPhase() == TurnPhase.MOVE) {
            getCurrentPlayer().getGodCard().afterMove(action, gameState);

            for (Player player : getOpponents(getCurrentPlayer())) {
                player.getGodCard().afterOpponentMove(action, gameState);
            }
        } else if (action.getCurrentPhase() == TurnPhase.BUILD) {
            getCurrentPlayer().getGodCard().afterBuild(action, gameState);

            for (Player player : getOpponents(getCurrentPlayer())) {
                player.getGodCard().afterOpponentBuild(action, gameState);
            }
        }

        gameState.process();
    }

    public void onEndTurn(){

        System.out.println(currentPlayerIndex);
        currentPlayerIndex ++;

        if (currentPlayerIndex >= players.length) {
            currentPlayerIndex = 0;
        }
        playerSelectedWorkerID = null;
        phase = TurnPhase.START_TURN;
        System.out.println(currentPlayerIndex);
    }

    // ==== Getter ====

    public TurnPhase getPhase() {
        return phase;
    }

    public Player getCurrentPlayer() {
        return players[currentPlayerIndex];
    }

    public Worker getCurrentWorker() {
        return getCurrentPlayer().getWorkers()[playerSelectedWorkerID];
    }

    public Worker[] getUnselectedWorker() {
        List<Worker> unselectedWorker = new ArrayList<>();

        for (Worker worker: getCurrentPlayer().getWorkers()) {
            if (worker != getCurrentWorker()) {
                unselectedWorker.add(worker);
            }
        }
        return unselectedWorker.toArray(new Worker[0]);
    }

    /**
     * Retrieves the opponent of the specified player.
     *
     * @param player The player whose opponent is to be retrieved
     * @return The opponent of the specified player
     */
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
