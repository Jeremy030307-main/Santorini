package Model.Game;

import Model.Board.Board;
import Model.GameRule.ClassicGameRule;
import Model.Player.Player;

/**
 * Represents a game of Santorini, encompassing game state, players, rules, and game management.
 * <p>
 * The {@link Game} class initializes the state of the game, sets up players, applies the game rules,
 * manages turns, and handles game setup using a combination of associated managers and components.
 * </p>
 */
public class Game {

    private final GameState gameState;

    /**
     * Constructs a new instance of a Santorini game with the specified components.
     * <p>
     * This constructor initializes the game state with a board, players, rules, block pool,
     * turn manager, and setup manager. It prepares the game for play.
     * </p>
     *
     * @param board the game board, which holds the cells and their state
     * @param players the array of players participating in the game
     * @param rule the set of game rules (e.g., {@link ClassicGameRule})
     * @param blockPool the pool from which blocks will be drawn for building
     * @param turnManager manages the sequence and phases of turns
     * @param setupManager handles game setup tasks like initializing the board and players
     */
    public Game(Board board, Player[] players, ClassicGameRule rule, BlockPool blockPool, TurnManager turnManager, SetupManager setupManager) {
        this.gameState = new GameState(board, players, rule, blockPool, turnManager, setupManager);
    }

    /**
     * Returns the current state of the game.
     *
     * @return the current {@link GameState} object, which tracks all game data
     */
    public GameState getGameState() {
        return gameState;
    }
}
