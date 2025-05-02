package Controller.GameFlow;

import Controller.HomeController;
import Controller.SetupController;
import Model.Board.Block;
import Model.Board.Cell;
import Model.Game.*;
import Model.GodCard.GodCardFactory;
import Model.Player.Player;
import View.Game.GamePanel;
import View.Game.MapComponent.*;
import View.SantoriniFrame;

import java.util.ArrayList;
import java.util.List;

public class GameController {

    public static final String GAME_VIEW = "gameView";

    private final Game game;
    private final GamePanel gamePanel;
    private final SantoriniFrame mainFrame;

    private final SetupController setupController;
    private final TurnController turnController;
    private final GameOverController gameOverController;

    private int currentPlayerIndex;

    public GameController(SantoriniFrame santoriniFrame, Game game) {

        this.game = game;
        List<JPlayer> displayPlayers = new ArrayList<>();
        List<String> displayPlayerNames = new ArrayList<>();
        List<String> displayGodCardsPath = new ArrayList<>();
        for (Player player : game.getGameState().getPlayers()) {
            displayPlayers.add(JPlayer.from(player.getWorkerColor().toString()));
            displayPlayerNames.add(player.getName());
            displayGodCardsPath.add(matchCardImage(player.getGodCard().getName()));
        }

        this.gamePanel = new GamePanel(displayPlayers, displayPlayerNames, displayGodCardsPath);
        this.mainFrame = santoriniFrame;

        this.setupController = new SetupController(this.game.getGameState().getSetupManager(), this.gamePanel, this);
        this.turnController = new TurnController(this.game.getGameState().getTurnManager(), this.gamePanel, this);
        this.gameOverController = new GameOverController(this.mainFrame);
        initGame();
    }

    public void setupGame(){

        mainFrame.showView(GAME_VIEW);
        setupController.setup();
        return;
    }

    public void startGame(){
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

                displayCell.setWorker(cell.isOccupied() ? JWorker.from(cell.getOccupant().getWorkerColor().toString(), cell.getOccupant().getGender().toString()): null );
                displayCell.setAction((JCellAction) null);

                List<JBlock> towerDisplay = new ArrayList<>();
                for (Block block: cell.getTower()){
                    towerDisplay.add(JBlock.from(block.getLevel()));
                }
                displayCell.setBlocks(towerDisplay);
            }
        }

        gamePanel.setActivePlayerID(currentPlayerIndex, actionLabelText, gameState.getTurnManager().getPhase()==TurnPhase.OPTIONAL_ACTION);
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

    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

    public void setCurrentPlayerIndex(int currentPlayerIndex) {
        this.currentPlayerIndex = currentPlayerIndex;
    }

    private String matchCardImage(GodCardFactory god){

        switch (god){
            case ARTEMIS -> {
                return "/Image/GodCard/Artemis/podium.png";
            }
            case DEMETER -> {
                return "/Image/GodCard/Demeter/podium.png";
            }
            default -> throw new IllegalStateException("Unexpected value: " + god);
        }
    }
}

