package Controller.GameFlow;

import Controller.HomeController;
import Controller.SetupWorkerController;
import Model.Board.Block;
import Model.Board.BlockType;
import Model.Board.Cell;
import Model.Game.*;
import View.Game.BasicGameView.GamePanel;
import View.Game.GenericGameView.GenericGamePanel;
import View.Game.MapComponent.*;
import View.SantoriniFrame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GameController {

    public static final String GAME_VIEW = "gameView";

    private Game game;
    private final GenericGamePanel gamePanel;
    private final SantoriniFrame mainFrame;

    private SetupWorkerController setupController;
    private TurnController turnController;
    private GameOverController gameOverController;
    private Runnable optionalUpdatePanel;

    private int currentPlayerIndex;

    private GameController(SantoriniFrame santoriniFrame, GenericGamePanel gamePanel) {

        this.gamePanel = gamePanel;
        this.mainFrame = santoriniFrame;
    }

    public static class Builder {

        private final GameController gameController;

        public Builder(SantoriniFrame mainFrame, GenericGamePanel gamePanel) {
            gameController = new GameController(mainFrame, gamePanel);
        }

        public Builder setGame(Game game) {
            gameController.game = game;
            return this;
        }

        public Builder setSetupWorkerManager(SetupWorkerController setupWorkerController) {
            gameController.setupController = setupWorkerController;
            return this;
        };

        public Builder setTurnManager(TurnController turnController) {
            gameController.turnController = turnController;
            return this;
        }

        public Builder setGameOverController(GameOverController gameOverController) {
            gameController.gameOverController = gameOverController;
            return this;
        }

        public Builder setOptionalUpdatePanel(Runnable optionalUpdatePanel) {
            gameController.optionalUpdatePanel = optionalUpdatePanel;
            return this;
        }

        public GameController build() {
            gameController.initGame();
            return gameController;
        }

        public GameController getGameController() {
            return gameController;
        }
    }

    public void setupGame(){

        mainFrame.showView(GAME_VIEW);
        setupController.setup();
        return;
    }

    public void startGame(){
        game.getGameState().process();
        turnController.processTurn(game.getGameState());
        return;
    }

    public void gameOver(){
        gameOverController.showWinPanel(game.getGameState().getWinner());
    }

    public void updateGamePanel(GameState gameState, String actionLabelText) {

        Cell[][] cells = gameState.getBoard().getCells(); // assuming 'board' is a Board instance

        for (int row = 0; row < cells.length; row++) {
            for (int col = 0; col < cells[row].length; col++) {
                Cell cell = cells[row][col];
                JCell displayCell = gamePanel.getGameBoard().getCell(row, col);
                displayCell.activate();

                displayCell.setWorker(cell.isOccupied() ? JWorker.from(cell.getOccupant().getWorkerColor().toString(), cell.getOccupant().getGender().toString()): null );
                displayCell.setAction((JCellAction) null);

                List<JBlock> towerDisplay = new ArrayList<>();
                for (Block block: cell.getTower()){
                    towerDisplay.add(JBlock.from(block.getLevel()));
                };
                displayCell.setBlocks(towerDisplay);
            }
        }

        HashMap<JBlock, Integer> viewBlockPool = new HashMap<>();
        for (BlockType blockType : BlockType.getValuesInAscendingOrder()) {
            JBlock jblock = JBlock.from(blockType.getLevel());
            viewBlockPool.put(jblock, gameState.getBlockPool().getRemaining(blockType));
        }
        gamePanel.getBlockPoolSidePanel().updateBlockPool(viewBlockPool);

        gamePanel.setActivePlayerID(currentPlayerIndex, actionLabelText, gameState.getTurnManager().getPhase()==TurnPhase.OPTIONAL_ACTION);
        if (optionalUpdatePanel != null) {
            optionalUpdatePanel.run();
        }
        gamePanel.getGameBoard().update();
        gamePanel.validate();
        gamePanel.repaint();
    }

    private void initGame() {
        mainFrame.addView(gamePanel, GAME_VIEW);
        gamePanel.getQuitButton().addActionListener(e -> mainFrame.showView(HomeController.HOME_VIEW));
    }

    public Game getGame() {
        return game;
    }

    public void setCurrentPlayerIndex(int currentPlayerIndex) {
        this.currentPlayerIndex = currentPlayerIndex;
    }

}

