package Model.Game;

import Model.Board.Board;
import Model.GameRule.ClassicGameRule;
import Model.Player.Player;

public class Game {
    private final GameState gameState;

    public Game(Board board, Player[] players, ClassicGameRule rule, BlockPool blockPool, TurnManager turnManager, SetupManager setupManager) {
        this.gameState = new GameState(board, players, rule, blockPool, turnManager, setupManager);
    }

    public GameState getGameState() {
        return gameState;
    }
}
