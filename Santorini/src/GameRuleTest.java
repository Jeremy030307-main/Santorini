import Model.Board.*;
import Model.Game.*;
import Model.GameRule.ClassicGameRule;
import Model.Player.*;

public class GameRuleTest {
    public static void main(String[] args) {
        // Set up board
        Board board = new Board();
        Game game = new Game(); // if needed to initialize GameState

        // Set up players
        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 2");

        // Add workers
        Worker w1 = new Worker("W1", player1);
        Worker w2 = new Worker("W2", player2);
        player1.setWorkers(w1);
        player2.setWorkers(w2);

        // Place workers on board
        Cell cellW1 = board.getCell(1, 1);
        cellW1.buildBlock(new Block(1));
        cellW1.buildBlock(new Block(2));
        cellW1.buildBlock(new Block(3)); // Level 3
        cellW1.setOccupant(w1);
        w1.setLocatedCell(cellW1);

        Cell cellW2 = board.getCell(2, 2);
        cellW2.setOccupant(w2);
        w2.setLocatedCell(cellW2);

        // Set up game state
        TurnManager turnManager = new TurnManager(player1, player2);
        GameState gameState = new GameState(board, turnManager);

        // Use your rule
        ClassicGameRule rule = new ClassicGameRule();

        System.out.println("isWin: " + rule.isWin(gameState));   // Expected: true (Player 1 stands on level 3)
        System.out.println("isLose: " + rule.isLose(gameState)); // Expected: false (Player 1 still has moves)
    }
}
