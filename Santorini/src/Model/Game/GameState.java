package Model.Game;

import Model.Action.Action;
import Model.Board.Board;
import Model.GameRule.ClassicGameRule;
import Model.Player.Player;
import Model.Player.Worker;
import java.util.List;

/**
 * The {@code GameState} class represents the current state of the game.
 * It holds information about the board, players, game rules, block pool, 
 * and turn management. It is responsible for checking the win and lose conditions.
 * <p>
 * This class allows for retrieving possible actions, such as move and build actions, 
 * for a worker and managing the game's progression through phases.
 * </p>
 */

public class GameState {
     /** The board of the game. */
    private final Board board;
     /** The game rules being followed. */
    private final ClassicGameRule gameRule;
    /** The players in the game. */
    private final Player[] players;
     /** The pool of available blocks for the game. */
    private final BlockPool blockPool;
     /** The turn manager that controls the turn order and player actions. */
    private final TurnManager turnManager;
     /** The setup manager responsible for the initial setup of the game. */
    private final SetupManager setupManager;
    /** Flag indicating whether the game is over or not. */
    private boolean gameOver;


    /**
     * Constructs a new {@code GameState} with the given parameters.
     *
     * @param board The board for the game
     * @param players The players participating in the game
     * @param rule The game rule being followed
     * @param blockPool The block pool for the game
     * @param turnManager The turn manager for managing turns
     * @param setupManager The setup manager for the game setup
     */
    public GameState(Board board, Player[] players, ClassicGameRule rule, BlockPool blockPool, TurnManager turnManager, SetupManager setupManager) {
        this.board = board;
        this.gameRule = rule;
        this.players = players;
        this.blockPool = blockPool;
        this.turnManager = turnManager;
        this.setupManager = setupManager;
        this.gameOver = false;
    }

    /**
     * Retrieves the possible move actions for a given worker.
     *
     * @param worker The worker for whom to retrieve possible move actions
     * @return A list of possible move actions for the worker
     */
    public List<Action> getMovesAction(Worker worker) {
        return gameRule.moveActions(board, worker);
    }
    
     /**
     * Retrieves the possible build actions for a given worker.
     *
     * @param worker The worker for whom to retrieve possible build actions
     * @return A list of possible build actions for the worker
     */
    public List<Action> getBuildAction(Worker worker) {
        return gameRule.buildActions(board, worker);
    }
    
    /**
     * Processes the current game state by checking for win and lose conditions.
     * If the game is won, {@code gameOver} is set to {@code true}.
     * It also checks if the game is lost according to the game rule.
     */
    public void process(){
        if (gameRule.checkWin(this)){
            gameOver = true;
        }

        gameRule.checkLose(this);
    }

    /**
     * Returns whether the game is over.
     *
     * @return {@code true} if the game is over, {@code false} otherwise
     */
    public boolean isGameOver() {
        return gameOver;
    }

     // Getter and Setter methods

    /**
     * Returns the board of the game.
     *
     * @return The board of the game
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Returns the players in the game.
     *
     * @return The players in the game
     */
    public Player[] getPlayers() {
        return players;
    }

     /**
     * Returns a player by their ID.
     *
     * @param id The ID of the player to retrieve
     * @return The player with the specified ID, or {@code null} if no player is found
     */
    public Player getPlayerById(int id) {
        for (Player player : players) {
            if (player.getId() == id) {
                return player;
            }
        }
        return null;
    }

     /**
     * Returns the setup manager for the game.
     *
     * @return The setup manager
     */
    public SetupManager getSetupManager() {
        return setupManager;
    }

     /**
     * Returns the block pool for the game.
     *
     * @return The block pool
     */
    public BlockPool getBlockPool() {
        return blockPool;
    }

    /**
     * Returns the turn manager for the game.
     *
     * @return The turn manager
     */
    public TurnManager getTurnManager() {
        return turnManager;
    }
}
