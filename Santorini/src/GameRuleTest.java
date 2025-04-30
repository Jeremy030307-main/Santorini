import Model.Board.Board;
import Model.Board.Cell;
import Model.Board.Position;
import Model.Board.BlockType;
import Model.Game.BlockPool;
import Model.Game.Game;
import Model.Game.GameState;
import Model.Game.SetupManager;
import Model.Game.TurnManager;
import Model.GameRule.ClassicGameRule;
import Model.Player.Player;
import Model.Player.Worker;

import java.util.ArrayList;

public class GameRuleTest {
    public static void main(String[] args) {
        // Set up board and players with worker lists
        Board board = new Board();
        Player[] players = new Player[2];
        players[0] = new Player(0, "Player 1", null);
        players[1] = new Player(1, "Player 2", null);

        // Create and assign workers
        Worker w1 = players[0].getWorkers()[0];
        Worker w2 = players[0].getWorkers()[1];
        Worker w3 = players[1].getWorkers()[0];
        Worker w4 = players[1].getWorkers()[1];

        // Setup game
        ClassicGameRule gameRule = new ClassicGameRule();
        BlockPool blockPool = new BlockPool();
        TurnManager turnManager = new TurnManager(players);
        SetupManager setupManager = new SetupManager(players, board);
        Game game = new Game(board, players, gameRule, blockPool, turnManager, setupManager);
        GameState gameState = game.getGameState();

        // === TEST: Win by reaching Level 3 ===
        Cell level3Cell = board.getCell(new Position(2, 2));

        // Build level 1 → 2 → 3 using correct block drawing method
        level3Cell.buildBlock(blockPool.takeBlock(BlockType.LEVEL1));
        level3Cell.buildBlock(blockPool.takeBlock(BlockType.LEVEL2));
        level3Cell.buildBlock(blockPool.takeBlock(BlockType.LEVEL3));

        // Set worker on the level 3 cell
        level3Cell.setOccupant(w1);
        w1.setLocatedCell(level3Cell);

        // Set current player to Player 1
        turnManager.setCurrentPlayer(players[0]);

        // Check win and lose conditions
        boolean win = gameRule.isWin(gameState);
        boolean lose = gameRule.isLose(gameState);

        System.out.println("=== TEST: Worker on Level 3 ===");
        System.out.println("Expected: WIN = true, LOSE = false");
        System.out.println("Actual:   WIN = " + win + ", LOSE = " + lose);
    }
}
