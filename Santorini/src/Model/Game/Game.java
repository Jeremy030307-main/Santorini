package Model.Game;

import Model.Board.Board;
import Model.GameRule.ClassicGameRule;
import Model.Player.Player;

public class Game {
    private final GameState gameState;

    public Game(Board board, Player[] players, ClassicGameRule rule) {
        this.gameState = new GameState(board, players, rule);
    }

    public void start() {
        gameState.getSetupManager().setup();
        while (!gameState.getTurnManager().isGameOver()) {
            gameState.getTurnManager().playTurn(gameState);
        }
        System.out.println("Winner: " + gameState.getTurnManager().getWinner().getName());
    }

    public GameState getGameState() {
        return gameState;
    }
}
