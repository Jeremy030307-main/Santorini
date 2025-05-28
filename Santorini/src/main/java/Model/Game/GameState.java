package Model.Game;

import Model.Action.ActionList;
import Model.Board.Board;
import Model.Game.TurnManager.TurnManager;
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
    private Player[] players;
     /** The pool of available blocks for the game. */
    private final BlockPool blockPool;
     /** The turn manager that controls the turn order and player actions. */
    private final TurnManager turnManager;
     /** The setup manager responsible for the initial setup of the game. */
    private final SetupManager setupManager;
    /** Flag indicating whether the game is over or not. */
    private boolean gameOver;

    private List<Player> losePlayers;


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

    public ActionList getMovesAction(Worker worker) {
        return gameRule.moveActions(board, worker);
    }

    public ActionList getBuildAction(Worker worker) {
        return gameRule.buildActions(board, worker);
    }

    public void process() {
        gameRule.checkLose(this);  // Mark players as 'lose' if stuck
        gameRule.checkWin(this);   // Mark players as 'win' if on level 3 or opponents all lost

        // Check how many players have NOT lost
        int activePlayers = 0;
        Player lastStanding = null;

        for (Player player : players) {
            if (!player.isLose()) {
                activePlayers++;
                lastStanding = player;
            }
        }

        // If only one player is left and no one is marked as win yet
        if (activePlayers == 1 && !lastStanding.isWin()) {
            lastStanding.setWin(true);  // Last player standing wins
            gameOver = true;
        }

        // If any player has won, the game is over
        for (Player player : players) {
            if (player.isWin()) {
                gameOver = true;
                break;
            }
        }
    }

    public boolean isGameOver() {
        return gameOver;
    }

    // Getter and Setter
    public Board getBoard() {
        return board;
    }

    public Player[] getPlayers() {
        return players;
    }

    public Player getPlayerById(int id) {
        for (Player player : players) {
            if (player.getId() == id) {
                return player;
            }
        }
        return null;
    }

    public ClassicGameRule getGameRule() {
        return gameRule;
    }

    public Player getWinner(){
        for (Player player : players) {
            if (player.isWin()){
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

    public BlockPool getBlockPool() {
        return blockPool;
    }

    public TurnManager getTurnManager() {
        return turnManager;
    }
}
