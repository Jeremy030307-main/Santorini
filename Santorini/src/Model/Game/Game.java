package Model.Game;

import Model.Board.Board;
import Model.GameRule.ClassicGameRule;
import Model.Player.Player;

public class Game {
    private final GameState gameState;

    public Game(Board board, Player[] players, ClassicGameRule rule, BlockPool blockPool, TurnManager turnManager, SetupManager setupManager) {
        this.gameState = new GameState(board, players, rule, blockPool, turnManager, setupManager);
    }

    public void start() {

        // 1. Set up the game ( Place all workers in unoccupied space)
        gameState.getSetupManager().setup();

        while (!gameState.isGameOver()) {
            gameState.getTurnManager().playTurn(gameState);
        }
//        System.out.println("Winner: " + gameState.getTurnManager().getWinner().getName());
    }

    public GameState getGameState() {
        return gameState;
    }
}
