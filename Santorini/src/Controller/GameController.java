package Controller;

import Model.Board.Block;
import Model.Board.Board;
import Model.Board.Cell;
import Model.Game.*;
import Model.GameRule.ClassicGameRule;
import Model.Player.Player;
import Model.Player.Worker;
import Model.Player.WorkerColor;
import View.Game.GamePanel;
import View.Game.MapComponent.*;
import View.SantoriniFrame;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameController {

    public static final String GAME_VIEW = "gameView";

    private final Game game;
    private final GamePanel gamePanel;
    private final SantoriniFrame mainFrame;

    private final SetupController setupController;
    private final TurnController turnController;

    public GameController(SantoriniFrame santoriniFrame) {

        Board board = new Board();
        Player[] players = new Player[2];
        players[0] = new Player(0, "Player 1", null, WorkerColor.ORANGE);
        players[1] = new Player(1, "Player 2", null, WorkerColor.RED);
        ClassicGameRule gameRule = new ClassicGameRule();
        BlockPool blockPool = new BlockPool();
        TurnManager turnManager = new TurnManager(players);
        SetupManager setupManager = new SetupManager(players, board);

        this.game = new Game(board, players, gameRule, blockPool, turnManager, setupManager);

        List<JPlayer> displayPlayers = new ArrayList<>();
        for (Player player : players) {
            displayPlayers.add(JPlayer.from(player.getWorkerColor().toString()));
        }

        this.gamePanel = new GamePanel(displayPlayers);
        this.mainFrame = santoriniFrame;

        this.setupController = new SetupController(this.game.getGameState().getSetupManager(), this.gamePanel, this);
        this.turnController = new TurnController(this.game.getGameState().getTurnManager(), this.gamePanel, this);
        initGame();
    }

    public void setupGame(){

        System.out.println("Run Setup");
        setupController.setup();
        return;
    }

    public void startGame(){
        System.out.println("Run turn");
        turnController.processTurn(game.getGameState());
        return;
    }

    public void gameOver(){

    }

    public void updateGamePanel(GameState gameState) {

        Cell[][] cells = gameState.getBoard().getCells(); // assuming 'board' is a Board instance

        for (int row = 0; row < cells.length; row++) {
            for (int col = 0; col < cells[row].length; col++) {
                Cell cell = cells[row][col];
                JCell displayCell = gamePanel.getGameBoard().getCell(row, col);

                displayCell.setWorker(cell.isOccupied() ? JWorker.from(cell.getOccupant().getWorkerColor().toString(), cell.getOccupant().getGender().toString()): null );
                displayCell.setAction((JCellAction) null);

                List<JBlock> towerDisplay = new ArrayList<>();
                for (Block block: cell.getTower()){
                    towerDisplay.add(JBlock.from(block.getLevel()));
                }
                displayCell.setBlocks(towerDisplay);
            }
        }

        gamePanel.getGameBoard().update();
    }

    private void initGame() {
        mainFrame.addView(gamePanel, GAME_VIEW);
    }

    public Game getGame() {
        return game;
    }
}

