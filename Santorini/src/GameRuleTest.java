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
//    public static void main(String[] args) {
//        // Set up board and players with worker lists
//        Board board = new Board();
//        Player[] players = new Player[2];
//        players[0] = new Player(0, "Player 1", null);
//        players[1] = new Player(1, "Player 2", null);
//
//        // Create and assign workers
//        Worker w1 = players[0].getWorkers()[0]; // Player 1's worker
//        Worker w2 = players[0].getWorkers()[1]; // Player 1's second worker (will not be placed on the board)
//
//        Worker w3 = players[1].getWorkers()[0]; // Player 2's worker
//        Worker w4 = players[1].getWorkers()[1]; // Player 2's second worker
//
//        // Setup game
//        ClassicGameRule gameRule = new ClassicGameRule();
//        BlockPool blockPool = new BlockPool();
//        TurnManager turnManager = new TurnManager(players);
//        SetupManager setupManager = new SetupManager(players, board);
//        Game game = new Game(board, players, gameRule, blockPool, turnManager, setupManager);
//        GameState gameState = game.getGameState();
//
//        // === TEST: Player 1 has no workers on the board ===
//        // Set Player 1's workers to not be on the board
//        Cell cellForPlayer1Worker = board.getCell(new Position(3, 4));
//        w1.setLocatedCell(cellForPlayer1Worker);
//        //w1.setLocatedCell(null); // Player 1's first worker is removed
//        //w2.setLocatedCell(null); // Player 1's second worker is removed
//
//        // Set Player 2's workers on the board
//        Cell cellForPlayer2Worker = board.getCell(new Position(2, 2));
//        cellForPlayer2Worker.setOccupant(w3);
//        w3.setLocatedCell(cellForPlayer2Worker);
//
//        // Optionally, you can also set Player 2's second worker on the board.
//        Cell anotherCellForPlayer2Worker = board.getCell(new Position(3, 2));
//        anotherCellForPlayer2Worker.setOccupant(w4);
//        w4.setLocatedCell(anotherCellForPlayer2Worker);
//
//        // Set current player to Player 2
//        turnManager.setCurrentPlayer(players[1]);
//
//        // Check win and lose conditions
//        boolean win = gameRule.isWin(gameState);  // Player 1's workers are not on the board, Player 2 may win
//        boolean lose = gameRule.isLose(gameState); // Player 1 has no workers on the board, should be lose
//
//        System.out.println("=== TEST: No remaining workers for Player 1 ===");
//        System.out.println("Expected: WIN = true, LOSE = true (Player 1 has no workers on the board)");
//        System.out.println("Actual:   WIN = " + win + ", LOSE = " + lose);
//
//    }
}
