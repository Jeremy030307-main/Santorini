package Model.Game;

import Model.Action.Action;
import Model.Player.Player;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code TurnManager} class is responsible for managing the turn sequence in the game.
 * It controls the flow of the game by determining which phase the game is in, managing player actions,
 * and determining the current player and their opponent.
 * <p>
 * The TurnManager class handles the progression through different phases such as selecting workers, moving,
 * building, and handling actions, as well as moving between turns.
 * </p>
 */
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

    /**
     * Starts the turn by changing the phase to worker selection.
     * No additional processing is required for this action, it simply passes the turn
     * to the next phase: selecting a worker.
     */
    public void onStartTurn(){

        this.phase = TurnPhase.SELECT_WORKER; // no additional proces, direct pass the turn to select worker phase
        return;
    }

    /**
     * Handles the worker selection by the current player.
     * Changes the phase to MOVE after a worker is selected.
     *
     * @param workerID The ID of the selected worker
     */
    public void handleWorkerSelection(int workerID){

        playerSelectedWorkerID = workerID;
        phase = TurnPhase.MOVE;
        return;
    }

    /**
     * Retrieves the possible move actions for the selected worker of the current player.
     *
     * @param gameState The current game state to evaluate the actions
     * @return A list of possible move actions for the selected worker
     */
    public List<Action> getMoveActions(GameState gameState){
        List<Action> moveActions = gameState.getMovesAction(getCurrentPlayer().getWorkerByID(playerSelectedWorkerID));

        moveActions = getCurrentPlayer().getGodCard().beforeMove(moveActions);
;        
        return moveActions;
    };

    /**
     * Retrieves the possible build actions for the selected worker of the current player.
     *
     * @param gameState The current game state to evaluate the actions
     * @return A list of possible build actions for the selected worker
     */
    public List<Action> getBuildActions(GameState gameState){
        List<Action> buildActions = gameState.getBuildAction(getCurrentPlayer().getWorkerByID(playerSelectedWorkerID));

        buildActions = getCurrentPlayer().getGodCard().beforeBuild(buildActions);
        return buildActions;
    };

    /**
     * Retrieves both move and build actions for the selected worker of the current player.
     * Combines move actions and build actions into a single list.
     *
     * @param gameState The current game state to evaluate the actions
     * @return A combined list of move and build actions for the selected worker
     */
    public List<Action> getMoveBuildActions(GameState gameState){
        List<Action> moveActions = gameState.getMovesAction(getCurrentPlayer().getWorkerByID(playerSelectedWorkerID));
        List<Action> buildActions = gameState.getBuildAction(getCurrentPlayer().getWorkerByID(playerSelectedWorkerID));
        List<Action> moveBuildActions = new ArrayList<>(moveActions);
        moveBuildActions.addAll(buildActions);
        return moveBuildActions;
    }

    /**
     * Retrieves any optional actions that can be performed by the current player.
     * This method is currently not implemented.
     *
     * @param gameState The current game state to evaluate optional actions
     */
    public void getOptionalActions(GameState gameState) {

    }

    /**
     * Executes the specified action and applies its effects to the game state.
     * The turn phase is updated after the action is executed, and the game state is processed.
     *
     * @param action The action to execute
     * @param gameState The current game state to apply the action to
     */
    public void handleAction(Action action, GameState gameState){
        action.execute(gameState);
        phase = action.getNextPhase();
        gameState.process();
    }

    /**
     * Ends the current player's turn, moves to the next player, and resets relevant state
     * (e.g., worker selection) for the next turn.
     */
    public void onEndTurn(){
        currentPlayerIndex = (currentPlayerIndex + 1) % players.length;
        playerSelectedWorkerID = null;
        phase = TurnPhase.START_TURN;
    }

    /**
     * Retrieves the current phase of the turn.
     *
     * @return The current phase of the turn
     */
    public TurnPhase getPhase() {
        return phase;
    }

    /**
     * Retrieves the current player whose turn it is.
     *
     * @return The current player
     */
    public Player getCurrentPlayer() {
        return players[currentPlayerIndex];
    }

    /**
     * Retrieves the opponent of the specified player.
     *
     * @param player The player whose opponent is to be retrieved
     * @return The opponent of the specified player
     */
    public Player getOpponent(Player player) {
        for (Player p : players) {
            if (!p.equals(player)) {
                return p;
            }
        }
        return null;
    }
}
