package Controller.GameFlow;

import Controller.SetupWorkerController;
import Model.Board.Board;
import Model.Game.BlockPool;
import Model.Game.Game;
import Model.Game.SetupManager;
import Model.Game.TurnManager.TurnManager;
import Model.GameRule.ClassicGameRule;
import Model.Mode.TimedMode.TimedGameRule;
import Model.Mode.TimedMode.TimedTurnManager;
import Model.Player.Player;
import View.Game.BasicGameView.ActivePlayerPanel;
import View.Game.BasicGameView.GamePanel;
import View.Game.BasicGameView.PlayerSidePanel;
import View.Game.MapComponent.JPlayer;
import View.Game.TimedGameView.TimedGamePanel;
import View.Game.TimedGameView.TimedPlayerPanel;
import View.SantoriniFrame;

import java.util.ArrayList;
import java.util.List;

public class GameDirector {

    public static GameController constructClassicGame(SantoriniFrame mainFrame, Player[] players, boolean[][] layout, BlockPool blockPool) {

        // create a game panel for this mode
        List<JPlayer> displayPlayers = new ArrayList<>();
        List<String> displayPlayerNames = new ArrayList<>();
        List<String> playerGodNames = new ArrayList<>();
        for (Player player : players) {
            displayPlayers.add(JPlayer.from(player.getWorkerColor().toString()));
            displayPlayerNames.add(player.getName());
            playerGodNames.add(player.getGodCard().getName().getName());
        }

        GamePanel gamePanel = new GamePanel(displayPlayers, displayPlayerNames, playerGodNames, layout);

        GameController.Builder gameControllerBuilder = new GameController.Builder(mainFrame, gamePanel);
        GameController gameController = gameControllerBuilder.getGameController();

        Board board= new Board(layout);
        TurnManager turnManager = new TurnManager(players);
        SetupManager setupManager = new SetupManager(players, board);
        ClassicGameRule classicGameRule = new ClassicGameRule();
        Game newGame = new Game(board, players, classicGameRule, blockPool, turnManager, setupManager);

        TurnController<TurnManager> turnController  = new TurnController<>(turnManager, gamePanel, gameController);
        SetupWorkerController setupWorkerController = new SetupWorkerController(setupManager, gamePanel, gameController);
        GameOverController gameOverController = new GameOverController(mainFrame);

        return gameControllerBuilder
                .setTurnManager(turnController)
                .setSetupWorkerManager(setupWorkerController)
                .setGameOverController(gameOverController)
                .setGame(newGame)
                .build();
    };

    public static GameController constructTimedGame(SantoriniFrame mainFrame, Player[] players, boolean[][] layout, BlockPool blockPool) {

        // create a game panel for this mode
        List<JPlayer> displayPlayers = new ArrayList<>();
        List<String> displayPlayerNames = new ArrayList<>();
        List<String> playerGodNames = new ArrayList<>();
        for (Player player : players) {
            displayPlayers.add(JPlayer.from(player.getWorkerColor().toString()));
            displayPlayerNames.add(player.getName());
            playerGodNames.add(player.getGodCard().getName().getName());
        }

        TimedGamePanel gamePanel = new TimedGamePanel(displayPlayers, displayPlayerNames, playerGodNames, layout);

        GameController.Builder gameControllerBuilder = new GameController.Builder(mainFrame, gamePanel);
        GameController gameController = gameControllerBuilder.getGameController();

        Board board= new Board(layout);
        TimedTurnManager timedTurnManager = new TimedTurnManager(players);
        SetupManager setupManager = new SetupManager(players, board);
        TimedGameRule timedGameRule = new TimedGameRule(timedTurnManager);
        Game newGame = new Game(board, players, timedGameRule, blockPool, timedTurnManager, setupManager);

        TurnController<TimedTurnManager> turnController  = new TimedTurnController(timedTurnManager, gamePanel, gameController);
        SetupWorkerController setupWorkerController = new SetupWorkerController(setupManager, gamePanel, gameController);
        GameOverController gameOverController = new GameOverController(mainFrame);

        timedTurnManager.setOnEvent(gameController::startGame);

        return gameControllerBuilder
                .setTurnManager(turnController)
                .setSetupWorkerManager(setupWorkerController)
                .setGameOverController(gameOverController)
                .setGame(newGame)
                .build();
    };

}
