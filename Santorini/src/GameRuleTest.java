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

/**
 * The {@code GameRuleTest} class is responsible for testing the game rules and ensuring that the game mechanics
 * function as expected. It includes tests for checking win and lose conditions based on the game state and player actions.
 * <p>
 * This class simulates a game scenario, sets up players and their workers on the board, and verifies the outcomes
 * of different game situations using the game rule logic. It can be used to validate if the game behaves correctly
 * in various scenarios.
 * </p>
 */
public class GameRuleTest {
//    /**
//     * The main method sets up the game, simulates a scenario, and tests the win and lose conditions.
//     * It creates the board, players, and workers, assigns them to cells, and uses the game rule to check if
//     * the correct win/lose conditions are met based on the game state.
//     */
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
//        /**
//         * Test scenario where Player 1 has no workers on the board. 
//         * This test checks if the game correctly identifies that Player 1 loses when they have no workers remaining.
//         * Player 2, on the other hand, is set up with workers on the board to simulate a valid state where they may win.
//         */
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
//        /**
//         * Checks the win and lose conditions using the game rule logic.
//         * The win condition checks if Player 2 has won (Player 1 has no workers on the board),
//         * while the lose condition checks if Player 1 has no remaining workers.
//         */
//        boolean win = gameRule.isWin(gameState);  // Player 1's workers are not on the board, Player 2 may win
//        boolean lose = gameRule.isLose(gameState); // Player 1 has no workers on the board, should be lose
//
//        System.out.println("=== TEST: No remaining workers for Player 1 ===");
//        System.out.println("Expected: WIN = true, LOSE = true (Player 1 has no workers on the board)");
//        System.out.println("Actual:   WIN = " + win + ", LOSE = " + lose);
//
//    }
}
