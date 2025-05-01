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

public class Main {
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