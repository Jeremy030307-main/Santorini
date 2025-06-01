package Controller.GameFlow;

import Controller.SetupWorkerController;
import Model.Board.Block;
import Model.Board.BlockType;
import Model.Board.Board;
import Model.Board.Cell;
import Model.Game.BlockPool;
import Model.Game.Game;
import Model.Game.GameState;
import Model.Game.SetupManager;
import Model.Game.TurnManager;
import Model.GameRule.ClassicGameRule;
import Model.Mode.CoinMode.CoinGameRule;
import Model.Mode.CoinMode.CoinManager;
import Model.Mode.GameMode;
import Model.Mode.TimedMode.TimedGameRule;
import Model.Mode.TimedMode.TimerManager;
import Model.Player.Player;
import View.Game.BasicGameView.GamePanel;
import View.Game.CoinGameView.CoinGamePanel;
import View.Game.CoinGameView.CoinPlayerSidePanel;
import View.Game.GenericGameView.GenericGamePanel;
import View.Game.MapComponent.*;
import View.Game.TimedGameView.TimedGamePanel;
import View.SantoriniFrame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GameDirector {

    public static GameController constructGame(GameMode gameMode, SantoriniFrame mainFrame, Player[] players, boolean[][] layout, BlockPool blockPool) {
        switch (gameMode) {
            case TIMER -> {
                return constructTimedGame(mainFrame, players, layout, blockPool);
            }
            case WEALTH -> {
                return constructCoinGame(mainFrame, players, layout, blockPool);
            }
            case CLASSIC -> {
                return constructClassicGame(mainFrame, players, layout, blockPool);
            }
        }
        return null;
    }

    private static GameController constructClassicGame(SantoriniFrame mainFrame, Player[] players, boolean[][] layout, BlockPool blockPool) {

        // create a game panel for this mode
        List<JPlayer> displayPlayers = new ArrayList<>();
        List<String> displayPlayerNames = new ArrayList<>();
        List<String> playerGodNames = new ArrayList<>();
        for (Player player : players) {
            displayPlayers.add(JPlayer.from(player.getWorkerColor().toString()));
            displayPlayerNames.add(player.getName());
            playerGodNames.add(player.getGodCard().getName().getName());
        }

        HashMap<JBlock, Integer> viewBlockPool = new HashMap<>();
        for (BlockType blockType : BlockType.getValuesInAscendingOrder()) {
            JBlock jblock = JBlock.from(blockType.getLevel());
            viewBlockPool.put(jblock, blockPool.getRemaining(blockType));
        }

        GamePanel gamePanel = new GamePanel(displayPlayers, displayPlayerNames, playerGodNames, layout, viewBlockPool);

        GameController.Builder gameControllerBuilder = new GameController.Builder(mainFrame, gamePanel);
        GameController gameController = gameControllerBuilder.getGameController();

        Board board= new Board(layout);
        TurnManager turnManager = new TurnManager(players);
        SetupManager setupManager = new SetupManager(players, board);
        ClassicGameRule classicGameRule = new ClassicGameRule();
        Game newGame = new Game(board, players, classicGameRule, blockPool, turnManager, setupManager);

        TurnController turnController  = new TurnController(turnManager, gamePanel, gameController);
        SetupWorkerController setupWorkerController = new SetupWorkerController(setupManager, gamePanel, gameController);
        GameOverController gameOverController = new GameOverController(mainFrame);

        return gameControllerBuilder
                .setTurnManager(turnController)
                .setSetupWorkerManager(setupWorkerController)
                .setGameOverController(gameOverController)
                .setGame(newGame)
                .build();
    };

    private static GameController constructTimedGame(SantoriniFrame mainFrame, Player[] players, boolean[][] layout, BlockPool blockPool) {

        // create a game panel for this mode
        List<JPlayer> displayPlayers = new ArrayList<>();
        List<String> displayPlayerNames = new ArrayList<>();
        List<String> playerGodNames = new ArrayList<>();
        for (Player player : players) {
            displayPlayers.add(JPlayer.from(player.getWorkerColor().toString()));
            displayPlayerNames.add(player.getName());
            playerGodNames.add(player.getGodCard().getName().getName());
        }

        HashMap<JBlock, Integer> viewBlockPool = new HashMap<>();
        for (BlockType blockType : BlockType.values()) {
            JBlock jblock = JBlock.from(blockType.getLevel());
            viewBlockPool.put(jblock, blockPool.getRemaining(blockType));
        }

        TimedGamePanel timedGamePanel = new TimedGamePanel(displayPlayers, displayPlayerNames, playerGodNames, layout, viewBlockPool);

        GameController.Builder gameControllerBuilder = new GameController.Builder(mainFrame, timedGamePanel);
        GameController gameController = gameControllerBuilder.getGameController();

        Board board= new Board(layout);
        TimerManager timerManager = new TimerManager(players, gameController::startGame);
        TurnManager turnManager = new TurnManager(players){
            @Override
            public void onStartTurn() {
                timerManager.startTimer(this.getCurrentPlayer());
                super.onStartTurn();
            }

            @Override
            public void onEndTurn() {
                timerManager.stopTimer(this.getCurrentPlayer());
                super.onEndTurn();
            }
        };
        SetupManager setupManager = new SetupManager(players, board);
        TimedGameRule timedGameRule = new TimedGameRule(timerManager);
        Game newGame = new Game(board, players, timedGameRule, blockPool, turnManager, setupManager);

        TurnController turnController  = new TurnController(turnManager, timedGamePanel, gameController){
            @Override
            protected void startTurn(GameState gameState) {
                super.startTurn(gameState);
                timedGamePanel.getPlayersPanel().getPlayerPanels()
                        .get(turnManager.getCurrentPlayer().getId())
                        .startTimer((int) timerManager.getRemainingSeconds(turnManager.getCurrentPlayer()));

            }
        };
        SetupWorkerController setupWorkerController = new SetupWorkerController(setupManager, timedGamePanel, gameController);
        GameOverController gameOverController = new GameOverController(mainFrame);

        return gameControllerBuilder
                .setTurnManager(turnController)
                .setSetupWorkerManager(setupWorkerController)
                .setGameOverController(gameOverController)
                .setGame(newGame)
                .build();
    };

    private static GameController constructCoinGame(SantoriniFrame mainFrame, Player[] players, boolean[][] layout, BlockPool blockPool) {

        // create a game panel for this mode
        List<JPlayer> displayPlayers = new ArrayList<>();
        List<String> displayPlayerNames = new ArrayList<>();
        List<String> playerGodNames = new ArrayList<>();
        for (Player player : players) {
            displayPlayers.add(JPlayer.from(player.getWorkerColor().toString()));
            displayPlayerNames.add(player.getName());
            playerGodNames.add(player.getGodCard().getName().getName());
        }
        Board board= new Board(layout);
        CoinManager coinManager = new CoinManager(board, players);

        HashMap<JBlock, Integer> viewBlockPool = new HashMap<>();
        HashMap<JBlock, Integer> blockPrice = new HashMap<>();
        for (BlockType blockType : BlockType.values()) {
            JBlock jblock = JBlock.from(blockType.getLevel());
            viewBlockPool.put(jblock, blockPool.getRemaining(blockType));
            blockPrice.put(jblock, coinManager.getBlockPrice(blockType));
        }

        GenericGamePanel<CoinPlayerSidePanel> gamePanel = new CoinGamePanel(blockPrice,displayPlayers, displayPlayerNames, playerGodNames, layout, viewBlockPool);

        GameController.Builder gameControllerBuilder = new GameController.Builder(mainFrame, gamePanel);
        GameController gameController = gameControllerBuilder.getGameController();

        TurnManager turnManager = new TurnManager(players){

            @Override
            public void onStartTurn() {
                coinManager.trySpawnCoinWithCap();
                super.onStartTurn();
            }
        };

        SetupManager setupManager = new SetupManager(players, board);
        ClassicGameRule classicGameRule = new CoinGameRule(coinManager);
        Game newGame = new Game(board, players, classicGameRule, blockPool, turnManager, setupManager);

        TurnController turnController  = new TurnController(turnManager, gamePanel, gameController);
        SetupWorkerController setupWorkerController = new SetupWorkerController(setupManager, gamePanel, gameController);
        GameOverController gameOverController = new GameOverController(mainFrame);

        return gameControllerBuilder
                .setTurnManager(turnController)
                .setSetupWorkerManager(setupWorkerController)
                .setGameOverController(gameOverController)
                .setGame(newGame)
                .setOptionalUpdatePanel(()->{
                    Cell[][] cells = newGame.getGameState().getBoard().getCells(); // assuming 'board' is a Board instance

                    for (int row = 0; row < cells.length; row++) {
                        for (int col = 0; col < cells[row].length; col++) {
                            Cell cell = cells[row][col];
                            JCell displayCell = gamePanel.getGameBoard().getCell(row, col);

                            displayCell.setWorker(cell.isOccupied() ? JWorker.from(cell.getOccupant().getWorkerColor().toString(), cell.getOccupant().getGender().toString()): null );
                            displayCell.setAction((JCellAction) null);

                            List<JBlock> towerDisplay = new ArrayList<>();
                            for (Block block: cell.getTower()){
                                towerDisplay.add(JBlock.from(block.getLevel()));
                            };
                            if (coinManager.hasCoinAt(row, col)) {
                                towerDisplay.add(JBlock.COIN);
                            }

                            displayCell.setBlocks(towerDisplay);
                        }
                    }

                    for (Player player : players) {
                        gamePanel.getPlayersPanel().getPlayerPanels().get(player.getId()).setCoinCount(coinManager.getCoinCount(player));
                    }
                })
                .build();
    };

}
