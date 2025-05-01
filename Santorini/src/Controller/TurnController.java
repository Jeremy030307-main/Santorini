package Controller;

import Model.Action.Action;
import Model.Board.Cell;
import Model.Board.Position;
import Model.Game.GameState;
import Model.Game.TurnManager;
import Model.Player.Worker;
import View.Game.GamePanel;
import View.Game.MapComponent.JCell;
import View.Game.MapComponent.JCellAction;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TurnController {

    private final TurnManager turnManager;
    private final GamePanel gamePanel;
    private final GameController gameController;
    private final Map<JCell, MouseListener> attachedListeners = new HashMap<>();

    public TurnController(TurnManager turnManager, GamePanel gamePanel, GameController gameController) {
        this.turnManager = turnManager;
        this.gamePanel = gamePanel;
        this.gameController = gameController;
    }

    public void processTurn(GameState gameState) {

        if (gameState.isGameOver()) {
            gameController.gameOver();  // handle over for game controller to control the game over
            return;
        }

        updateUIForCurrentPhase(gameState);
    }

    private void updateUIForCurrentPhase(GameState gameState) {
        switch (turnManager.getPhase()) {
            case START_TURN:
                System.out.println("Starting turn");
                turnManager.onStartTurn();
                processTurn(gameState);
                break;
            case SELECT_WORKER:
                System.out.println("Selecting worker");
                showWorkerSelection(turnManager.getCurrentPlayer().getWorkers(), gameState);
                break;
            case MOVE:
                System.out.println("Moving");
                showWorkerAction(turnManager.getMoveActions(gameState), gameState, JCellAction.MOVE);
                break;
            case BUILD:
                System.out.println("Building");
                showWorkerAction(turnManager.getBuildActions(gameState), gameState, JCellAction.BUILD);
                break;
            case MOVE_OR_BUILD:
                System.out.println("Moving or Build");
                break;
            case END_TURN:
                System.out.println("Ending turn");
                turnManager.onEndTurn();
                processTurn(gameState);
                break;
        }
    }

    private void showWorkerSelection(Worker[] workers, GameState gameState) {
        for (Worker worker : workers) {

            Position currPos = worker.getLocatedCell().getPosition();
            JCell displayCell = gamePanel.getGameBoard().getCell(currPos.x(), currPos.y());

            displayCell.setAction(JCellAction.CHOOSE_WORKER);
            addListener(displayCell, new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    turnManager.handleWorkerSelection(worker.getId());
                    clearListeners();
                    gameController.updateGamePanel(gameState);
                    processTurn(gameState);
                }
            });
        }
    }

    private void showWorkerAction(List<Action> actions, GameState gameState, JCellAction cellAction) {
        for (Action action : actions) {

            Position currPos = action.getTargetCell().getPosition();
            JCell displayCell = gamePanel.getGameBoard().getCell(currPos.x(), currPos.y());

            displayCell.setAction(cellAction);
            addListener(displayCell, new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    turnManager.handleAction(action, gameState);
                    clearListeners();
                    gameController.updateGamePanel(gameState);
                    processTurn(gameState);
                }
            });
        }
    }

    private void addListener(JCell cellDisplay,  MouseListener listener){
        cellDisplay.addMouseListener(listener);
        attachedListeners.put(cellDisplay, listener);
    }

    private void removeListener(JCell cellDisplay){
        MouseListener listener = attachedListeners.remove(cellDisplay);
        cellDisplay.removeMouseListener(listener);
    }

    private void clearListeners(){
        for (Map.Entry<JCell, MouseListener> entry : attachedListeners.entrySet()) {
            JCell cell = entry.getKey();
            MouseListener listener = entry.getValue();

            cell.removeMouseListener(listener);
            cell.clearAction();
        }
        attachedListeners.clear();  // Important to clear the map after removal

        // update the board view
        gamePanel.getGameBoard().update();
    }

}
