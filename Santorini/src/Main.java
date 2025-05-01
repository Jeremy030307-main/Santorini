import Model.Board.Board;
import Model.Board.Cell;
import Model.Board.Position;
import Model.Game.BlockPool;
import Model.Game.Game;
import Model.Game.SetupManager;
import Model.Game.TurnManager;
import Model.GameRule.ClassicGameRule;
import Model.Player.Player;
import Model.Player.Worker;
import View.SantoriniFrame;

import javax.swing.*;
import java.util.List;

/**
 * The {@code Main} class serves as the entry point for the Santorini game.
 * It is responsible for starting the application and initializing the main game frame.
 * <p>
 * The {@code main} method sets up the user interface by invoking {@link SantoriniFrame}
 * and making it visible to the user. This class is the starting point for the game when the program is run.
 * </p>
 */
public class Main {
    /**
     * The {@code main} method initializes the user interface by launching the {@code SantoriniFrame}.
     * This method is executed when the program is run and makes the main game window visible to the user.
     */
    public static void main(String[] args) {

//        Board board = new Board();
//        Player[] players = new Player[2];
//        players[0] = new Player(0, "Player 1", null);
//        players[1] = new Player(1, "Player 2", null);
//        ClassicGameRule gameRule = new ClassicGameRule();
//        BlockPool blockPool = new BlockPool();
//        TurnManager turnManager = new TurnManager(players);
//        SetupManager setupManager = new SetupManager(players, board);
//
//        Game game = new Game(board, players, gameRule, blockPool, turnManager, setupManager);
//
//        game.start();

        SwingUtilities.invokeLater(() -> {
            SantoriniFrame.getInstance().setVisible(true);
        });
    }
}